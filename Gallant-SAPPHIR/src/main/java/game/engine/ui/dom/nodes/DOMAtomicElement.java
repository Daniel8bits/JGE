package game.engine.ui.dom.nodes;

import game.engine.ui.dom.IComponent;
import game.engine.ui.dom.ShadowDOM;
import game.engine.ui.framework.annotations.Childrenless;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.annotations.States;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import lombok.Getter;
import org.w3c.dom.Node;

import java.util.Arrays;

public abstract class DOMAtomicElement<T extends IComponent> extends DOMItem {

    @Getter
    private T component;

    /*
        TODO:
            - implementar o distribuidor de estilos CSS
    */

    public DOMAtomicElement(IProps props, String hierarchyName) {
        super(props, hierarchyName);
        if(Arrays.stream(this.getClass().getAnnotations()).noneMatch(annotation -> annotation.annotationType() == Childrenless.class)) {
            props.getChildren().forEach(child -> {
                this.getChildren().add(ShadowDOM.createItem(child));
            });
        }
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
