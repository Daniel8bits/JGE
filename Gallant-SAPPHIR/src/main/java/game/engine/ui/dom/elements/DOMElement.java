package game.engine.ui.dom.elements;

import game.engine.ui.components.IComponent;
import game.engine.ui.dom.VirtualDOM;
import game.engine.ui.dom.layouts.DOMLayout;
import game.engine.ui.dom.nodes.DOMElementFacade;
import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.dom.spacers.DOMSpacer;
import io.qt.widgets.QGridLayout;
import io.qt.widgets.QLayout;
import io.qt.widgets.QWidget;
import lombok.Getter;
import org.w3c.dom.Node;

public class DOMElement<T extends IComponent> extends DOMItem {
    @Getter
    private T component;

    public DOMElement(T component, Node node, String hierarchyName) {
        super(node, hierarchyName);
        this.component = component;
    }

    public DOMElement(T component, Node node, String hierarchyName, QLayout layout) {
        super(node, hierarchyName);
        this.component = component;
        component.setLayout(layout);
    }

    @Override
    public void update() {
        this.getChildren().forEach(DOMItem::update);
    }

    @Override
    public void pack() {
        System.out.println(getHierarchyName());
        if(component.layout() == null) {
            this.getChildren().forEach(domItem -> {
                if(!(domItem instanceof DOMElement<?>)) return;
                DOMElement<?> domElement = (DOMElement<?>) domItem;
                domElement.pack();
                domElement.component.setParent((QWidget) component);
            });
            return;
        }
        // if this has layout
        this.getChildren().forEach(domItem -> {
            domItem.pack();
            if(domItem instanceof DOMElement<?>) {
                DOMElement<?> domElement = (DOMElement<?>) domItem;
                if(component.layout() instanceof QGridLayout) {
                    QGridLayout gridLayout = (QGridLayout) component.layout();
                    int[] cell = new DOMElementFacade().getCell(domElement);
                    if(cell != null) {
                        gridLayout.addWidget((QWidget) domElement.component, cell[0], cell[1], cell[2], cell[3]);
                    }
                    return;
                }
                component.layout().addWidget((QWidget) domElement.component);
            } else if (domItem instanceof DOMLayout<?>) {
                DOMLayout<?> domLayout = (DOMLayout<?>) domItem;
                if(component.layout() instanceof QGridLayout) {
                    QGridLayout gridLayout = (QGridLayout) component.layout();
                    int[] cell = new DOMElementFacade().getCell(domLayout);
                    if(cell != null) {
                        gridLayout.addLayout(domLayout.getLayout(), cell[0], cell[1], cell[2], cell[3]);
                    }
                    return;
                }
                component.layout().addItem(domLayout.getLayout());
            } else if (domItem instanceof DOMSpacer) {
                DOMSpacer domSpacer = (DOMSpacer) domItem;
                if(component.layout() instanceof QGridLayout) {
                    QGridLayout gridLayout = (QGridLayout) component.layout();
                    int[] cell = new DOMElementFacade().getCell(domSpacer);
                    if(cell != null) {
                        gridLayout.addItem(domSpacer.getSpacer(), cell[0], cell[1], cell[2], cell[3]);
                    }
                    return;
                }
                component.layout().addItem(domSpacer.getSpacer());
            }
        });
    }

    @Override
    protected void initializeComponent() {
        calculateBounds();
        new DOMElementFacade().initializeAttributes(this);
        super.initializeComponent();
    }

    protected void calculateBounds() {
        this.component.bounds(0, 0, VirtualDOM.getWindowWidth(), VirtualDOM.getWindowHeight());
    }

    @Override
    protected void recalculateBounds() {
        calculateBounds();
        super.recalculateBounds();
    }

    public DOMElement<?> getParentAsElement() {
        return (DOMElement<?>) this.getParent();
    }

}
