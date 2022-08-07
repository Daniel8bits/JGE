package game.engine.ui.dom.layouts;

import game.engine.ui.dom.elements.DOMElement;
import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.dom.spacers.DOMSpacer;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import io.qt.widgets.QLayout;
import io.qt.widgets.QWidget;
import lombok.Getter;
import org.w3c.dom.Node;

public abstract class DOMLayout<T extends QLayout> extends DOMItem {

    public final static String PREFIX = "layout-";

    @Getter
    private T layout;

    public DOMLayout(T layout, Node node, String hierarchyName) {
        super(node, hierarchyName);
        this.layout = layout;
    }

    @Override
    public void pack() {
        getChildren().forEach(domItem -> {
            domItem.pack();
            if(domItem instanceof DOMElement<?>) {
                DOMElement<?> domElement = (DOMElement<?>) domItem;
                domElement.removeFromParentComponent();
                getLayout().addWidget((QWidget) domElement.getComponent());
            } else if (domItem instanceof DOMLayout<?>) {
                DOMLayout<?> domLayout = (DOMLayout<?>) domItem;
                domLayout.removeFromParentComponent();
                getLayout().addItem(domLayout.getLayout());
            } else if (domItem instanceof DOMSpacer) {
                DOMSpacer domSpacer = (DOMSpacer) domItem;
                domSpacer.removeFromParentComponent();
                getLayout().addItem(domSpacer.getSpacer());
            }
        });
    }

    public void removeFromParentComponent() {
        DOMItem parent = (DOMItem) getParent();
        if(parent == null) {
            return;
        }
        if(parent instanceof DOMElement<?>) {
            DOMElement<?> pDomElement = (DOMElement<?>) parent;
            pDomElement.getComponent().layout().removeItem(layout);
        } else if (parent instanceof DOMLayout<?>) {
            DOMLayout<?> pDomLayout = (DOMLayout<?>) parent;
            pDomLayout.getLayout().removeItem(layout);
        }
    }

    @Override
    public void removeFromParent() {
        removeFromParentComponent();
        super.removeFromParent();
    }

    @Override
    protected void whenMounted() {

    }

    @Override
    protected void whenUpdated(IProps previousProps, IStates previousStates) {

    }

    @Override
    protected void whenUnmounted() {

    }

    @Override
    protected void render() {

    }

}
