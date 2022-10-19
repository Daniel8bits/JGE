package game.engine.ui.dom.nodes;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.dom.Sapphire;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

public abstract class DOMCustomElement extends DOMElement {

    @Override
    public void init(IProps props, String hierarchyName) {
        super.init(props, hierarchyName);
        getShadowDom().add(new Sapphire().createElement(render()));
    }

    @Override
    public void reconciliate() {
        val template = render();
        val element = getShadowDom().get(0);
        /**
         * Caso tipos sejam diferentes
         * a arvore deve ser reconstruida
         */
        if(template.getType() != element.getClass()) {
            element.callWhenUnmounted();
            // recria arvore
            return;
        }
        /**
         * Caso tipos sejam iguais
         * mandar atualizar elemento
         */
        element.callWhenUpdated(template);
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
