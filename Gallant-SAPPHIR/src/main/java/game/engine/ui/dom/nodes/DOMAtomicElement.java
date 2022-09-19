package game.engine.ui.dom.nodes;

import game.engine.ui.dom.IComponent;
import game.engine.ui.dom.Sapphire;
import game.engine.ui.framework.annotations.Childrenless;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DOMAtomicElement<T extends IComponent> extends DOMElement {

    @Getter
    private T component;

    /*
        TODO:
            - implementar o distribuidor de estilos CSS
    */

    public DOMAtomicElement(IProps props, String hierarchyName) {
        super(props, hierarchyName);
        if(!CHILDRENLESS) {
            setShadowDom(Sapphire.createElements(props.getChildren()));
        }
    }

    @Override
    public List<DOMAtomicElement<?>> getAtomicElements() {
        List<DOMAtomicElement<?>> elements = new ArrayList<>();
        elements.add(this);
        if(CHILDRENLESS) {
            getShadowDom().forEach(e -> {
                List<DOMAtomicElement<?>> children = e.getAtomicElements();
                children.forEach(child -> child.setParent(this));
                getChildren().addAll(children);
            });
        }
        return elements;
    }

    public abstract void pack();

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


    public DOMAtomicElement<?> getParentAsElement() {
        return (DOMAtomicElement<?>) this.getParent();
    }



    /*==================================
                LIFECYCLE
    ==================================*/

    @Override
    protected void whenMounted() {

    }

    @Override
    protected void whenUpdated(IProps previousProps, IStates previousStates) {

    }

    @Override
    protected void whenUnmounted() {

    }

    protected void setComponent(T component) {
        if(this.component != null) {
            throw new RuntimeException("Error: Component already initialized!");
        }
        this.component = component;
    }
}
