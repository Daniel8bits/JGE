package game.engine.ui.dom.nodes;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.dom.ShadowDOM;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;

public abstract class DOMCustomElement extends DOMElement {
    public DOMCustomElement(IProps props, String hierarchyName) {
        super(props, hierarchyName);
        this.getChildren().add(ShadowDOM.createItem(render()));
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


    public abstract DOMTemplate render();

}
