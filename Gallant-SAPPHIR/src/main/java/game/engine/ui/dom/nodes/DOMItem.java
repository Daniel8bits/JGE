package game.engine.ui.dom.nodes;

import game.engine.ui.framework.StateManager;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import lombok.Getter;
import org.w3c.dom.Node;

import java.util.List;
import java.util.function.Consumer;

public abstract class DOMItem extends DOMNode<DOMItem> {

    public static class DOMItemProps implements IProps {
        //public List<Integer> cell;
        //public List<DOMItem> children;
    }

    @Getter
    private String hierarchyName;
    private StateManager stateManager;

    private final int MAX_UPDATE_CYCLES = 100;
    private int updateCycles;

    public DOMItem(Node node, String hierarchyName) {
        super(node);
        this.hierarchyName = hierarchyName;
        this.stateManager = new StateManager(this);
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

    protected abstract void render();

    protected IProps props() {
        return stateManager.getProps();
    }

    protected IStates get() {
        return stateManager.getState();
    }

    protected void set(IStates states) {
        stateManager.setStates(states);
        whenUpdated(stateManager.getPreviousProps(), stateManager.getPreviousState());
        doUpdates();
    }

    protected void set(Consumer<IStates> fn) {
        IStates states = get();
        fn.accept(states);
        set(states);
        doUpdates();
    }

    private void doUpdates() {
        if(updateCycles == MAX_UPDATE_CYCLES) {
            throw new RuntimeException("Max update cycles was reached!");
        }
        updateCycles++;
        whenUpdated(stateManager.getPreviousProps(), stateManager.getPreviousState());
        updateCycles--;
        if(updateCycles == 0) {
            render();
        }
    }

    protected StateManager getStateManager() {
        return stateManager;
    }
}
