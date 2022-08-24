package game.engine.ui.dom.elements;

import game.engine.ui.components.IComponent;
import game.engine.ui.dom.VirtualDOM;
import game.engine.ui.dom.layouts.DOMLayout;
import game.engine.ui.dom.nodes.DOMElementFacade;
import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.dom.spacers.DOMSpacer;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.annotations.States;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import io.qt.widgets.QGridLayout;
import io.qt.widgets.QLayout;
import io.qt.widgets.QWidget;
import lombok.Getter;
import org.w3c.dom.Node;

import java.util.Optional;

public abstract class DOMElement<T extends IComponent> extends DOMItem {

    @Props
    public static class DOMElementProps extends DOMItem.DOMItemProps {
        public String layout;
    }

    @States
    public static class DOMElementStates implements IStates {
        public String test;
    }

    @Getter
    private T component;

    /*
        TODO:
            - implementar o distribuidor de estilos CSS
    */

    public DOMElement(T component, IProps props, Node node, String hierarchyName) {
        super(props, node, hierarchyName);
        this.component = component;

        set(states -> ((DOMElementStates) states).test = "teste");

    }

    @Override
    public void pack() {
        System.out.println(getHierarchyName());
        this.getChildren().forEach(domItem -> {
            domItem.pack();
            configureLayout(domItem);
        });
    }

    private void configureLayout(DOMItem domItem) {

        if(component.layout() == null) {
            if(!(domItem instanceof DOMElement<?>)) {
                return;
            }
            DOMElement<?> domElement = (DOMElement<?>) domItem;
            domElement.component.setParent((QWidget) component);
        } else {
            if(domItem instanceof DOMElement<?>) {
                DOMElement<?> domElement = (DOMElement<?>) domItem;
                if(component.layout() instanceof QGridLayout) {
                    QGridLayout gridLayout = (QGridLayout) component.layout();
                    int[] cell = new DOMElementFacade().getCell(domElement);
                    if(cell != null) {
                        domElement.removeFromParentComponent();
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
                        domLayout.removeFromParentComponent();
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
                        domSpacer.removeFromParentComponent();
                        gridLayout.addItem(domSpacer.getSpacer(), cell[0], cell[1], cell[2], cell[3]);
                    }
                    return;
                }
                component.layout().addItem(domSpacer.getSpacer());
            }
        }

    }

    public void removeFromParentComponent() {
        DOMItem parent = (DOMItem) getParent();
        if(parent == null) {
            return;
        }
        if(parent instanceof DOMElement<?>) {
            DOMElement<?> pDomElement = (DOMElement<?>) parent;
            Optional.ofNullable(pDomElement.component.layout())
                    .ifPresentOrElse(
                            qLayout -> qLayout.removeWidget((QWidget) component),
                            () -> component.setParent(null)
                    );
        } else if (parent instanceof DOMLayout<?>) {
            DOMLayout<?> pDomLayout = (DOMLayout<?>) parent;
            pDomLayout.getLayout().removeWidget((QWidget) component);
        }
    }

    @Override
    public void removeFromParent() {
        removeFromParentComponent();
        super.removeFromParent();
    }

    /*
    *
    *   TODO:
    *       - o metodo initializeComponent pode ser entendido como o componentDidMount
    *         do ReactJS
    *       - é necessário implementar os equivalentes de componentDidUpdate e
    *         componentWillUnmount
    *       - encontrar uma forma de fazer atualização do componente por estados e atributos
    *       - implementar um sistema de update por estado global
    *       - implementar sistema de referenciamento de componentes (refs)
    *       - implementar sistema parecido com useEffect
    *
    * */
    @Override
    protected void initializeComponent() {
        calculateBounds();
        new DOMElementFacade().initializeAttributes(this); // talvez n seja o melhor lugar
        whenMounted();
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



    /*==================================
                LIFECYCLE
    ==================================*/

    @Override
    protected void whenMounted() {
        System.out.println(((DOMElementProps) props()).layout);
    }

    @Override
    protected void whenUpdated(IProps previousProps, IStates previousStates) {
        DOMElementProps props = (DOMElementProps) props();
        DOMElementProps pProps = (DOMElementProps) previousProps;
        if(notEquals(pProps.layout, props.layout)) {
            getChildren().forEach(this::configureLayout);
        }
    }

    @Override
    protected void whenUnmounted() {

    }

    @Override
    protected void setup() {

    }
}
