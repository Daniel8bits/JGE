package game.engine.ui.dom.elements;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import org.w3c.dom.Node;

public abstract class DOMCustomElement extends DOMItem {

    public DOMCustomElement(IProps props, Node node, String hierarchyName) {
        super(props, node, hierarchyName);
    }

    @Override
    public void pack() {

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
    protected void setup() {

    }

    public abstract DOMTemplate render();

}
