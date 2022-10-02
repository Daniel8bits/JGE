package game.engine.ui.dom.nodes;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.dom.Sapphire;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;

import java.util.ArrayList;
import java.util.List;

public abstract class DOMCustomElement extends DOMElement {

    @Override
    public void init(IProps props, String hierarchyName) {
        super.init(props, hierarchyName);
        getShadowDom().add(new Sapphire().createElement(render()));
    }

    public List<DOMAtomicElement<?>> getAtomicElements() {
        ArrayList<DOMAtomicElement<?>> elements = new ArrayList<>();
        getShadowDom().forEach(e -> {
            elements.addAll(e.getAtomicElements());
        });
        return elements;
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
