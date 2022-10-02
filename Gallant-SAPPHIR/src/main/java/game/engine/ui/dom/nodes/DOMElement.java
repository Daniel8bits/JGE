package game.engine.ui.dom.nodes;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.framework.annotations.Childrenless;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.interfaces.IProps;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DOMElement extends DOMItem {

    @Props
    public static class DOMElementProps implements IProps {
        @Setter
        @Getter
        public List<DOMTemplate> children;
    }

    @Setter
    @Getter
    private List<DOMElement> shadowDom;

    final public boolean CHILDRENLESS;

    public DOMElement() {
        shadowDom = new ArrayList<>();
        CHILDRENLESS = Arrays.stream(this.getClass().getAnnotations()).anyMatch(annotation -> annotation.annotationType() == Childrenless.class);
    }

    public abstract List<DOMAtomicElement<?>> getAtomicElements();

    public void callWhenMounted() {
        shadowDom.forEach(DOMElement::callWhenMounted);
        whenMounted();
    }

}
