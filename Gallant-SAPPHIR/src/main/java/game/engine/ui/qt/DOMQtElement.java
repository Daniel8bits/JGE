package game.engine.ui.qt;

import game.engine.ui.dom.IComponent;
import game.engine.ui.dom.nodes.DOMAtomicElement;
import game.engine.ui.dom.nodes.DOMElement;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.annotations.States;
import game.engine.ui.framework.interfaces.IStates;

public abstract class DOMQtElement<T extends IComponent> extends DOMAtomicElement<T> {

    @Props
    public static class DOMQtElementProps extends DOMElement.DOMElementProps {
        public int[] cell;
    }

    @States
    public static class DOMQtElementStates implements IStates {
        public String test;
    }

    public abstract void removeFromParentComponent();

}
