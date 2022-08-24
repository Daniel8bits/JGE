package game.engine.ui.dom.nodes;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.dom.elements.DOMCustomElement;
import game.engine.ui.framework.StateManager;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class DOMItem extends DOMNode<DOMItem> {

    @Props
    public static class DOMItemProps implements IProps {
        public int[] cell;

        @Setter
        public List<DOMTemplate> children;
    }

    @Getter
    private String hierarchyName;
    private StateManager stateManager;

    private final int MAX_UPDATE_CYCLES = 100;
    private int updateCycles;

    public DOMItem(IProps props, Node node, String hierarchyName) {
        super(node);
        this.hierarchyName = hierarchyName;
        this.stateManager = new StateManager(this, getNode(), props);
        this.updateCycles = 0;
    }

    public abstract void pack();
    protected void initializeComponent() {
        this.getChildren().forEach(DOMItem::initializeComponent);
    }
    protected void recalculateBounds() {
        this.getChildren().forEach(DOMItem::recalculateBounds);
    }

    protected abstract void whenMounted();
    protected abstract void whenUpdated(IProps previousProps, IStates previousStates);
    protected abstract void whenUnmounted();

    protected abstract void setup();

    protected IProps props() {
        return stateManager.getProps();
    }

    protected IStates get() {
        return stateManager.getState();
    }

    protected void set(IStates states) {
        if(!stateManager.equalsStates(states)) {
            stateManager.setStates(states);
            whenUpdated(stateManager.getPreviousProps(), stateManager.getPreviousState());
            doUpdates();
        }
    }

    protected void set(Consumer<IStates> fn) {
        IStates states = get();
        fn.accept(states);
        set(states);
    }

    private void doUpdates() {
        if(updateCycles == MAX_UPDATE_CYCLES) {
            throw new RuntimeException("Max update cycles was reached!");
        }
        updateCycles++;
        whenUpdated(stateManager.getPreviousProps(), stateManager.getPreviousState());
        updateCycles--;
        if(updateCycles == 0) {
            setup();
        }
    }

    protected StateManager getStateManager() {
        return stateManager;
    }


    protected boolean notEquals(Object a, Object b) {
        if(a == null && b == null) {
            return false;
        }
        if(a != null && b == null) {
            return true;
        }
        if(a == null && b != null) {
            return true;
        }
        return !a.equals(b);
    }


    protected DOMTemplate $(Class<? extends DOMItem> type) {
        return $(type, null, null);
    }

    protected DOMTemplate $(Class<? extends DOMItem> type, DOMTemplate[] children) {
        return $(type, null, children);
    }

    protected DOMTemplate $(Class<? extends DOMItem> type, Consumer<IProps> props) {
        return $(type, props, null);
    }

    protected DOMTemplate $(Class<? extends DOMItem> type, Consumer<IProps> props, DOMTemplate[] children) {
        return null;
    }
}
