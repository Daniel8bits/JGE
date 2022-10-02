package game.engine.ui.dom.nodes;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.dom.IContainer;
import game.engine.ui.dom.Sapphire;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class DOMContainer<T extends IContainer> extends DOMElement {

    @Getter
    private T container;

    private DOMItem diffingTree;

    @Override
    public void init(IProps props, String hierarchyName) {
        super.init(props, hierarchyName);
        getShadowDom().add(new Sapphire().createElement(body()));
        getChildren().addAll(getAtomicElements());
        callWhenMounted();
    }

    protected abstract DOMTemplate body();

    @Override
    public List<DOMAtomicElement<?>> getAtomicElements() {
        List<DOMAtomicElement<?>> elements = new ArrayList<>();
        getShadowDom().forEach(e -> {
            List<DOMAtomicElement<?>> children = e.getAtomicElements();
            children.forEach(child -> child.setParent(this));
            elements.addAll(children);
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

    protected void setContainer(T container) {
        if(this.container != null) {
            throw new RuntimeException("Error: Component already initialized!");
        }
        this.container = container;
    }
}
