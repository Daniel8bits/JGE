package game.engine.ui.dom.nodes;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.dom.Sapphire;
import game.engine.ui.framework.StateManager;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public abstract class DOMItem extends DOMNode<DOMItem> {

    @Getter
    private String hierarchyName;
    private StateManager stateManager;
    @Setter
    private Consumer<IStates> defaultStates;

    private final int MAX_UPDATE_CYCLES = 100;
    private int updateCycles;
    private boolean shouldUpdate, alreadyUpdating;
    private Queue<String> stateChangingQueue;

    public void init(IProps props, String hierarchyName) {
        this.hierarchyName = hierarchyName;
        stateChangingQueue = new LinkedList<>();
        this.stateManager = new StateManager(this, props, defaultStates);
        this.updateCycles = 0;
    }

    protected abstract void whenMounted();
    protected abstract void whenUpdated(IProps previousProps, IStates previousStates);
    protected abstract void whenUnmounted();
    public IProps props() {
        return stateManager.getProps();
    }

    protected IStates get() {
        return stateManager.getState();
    }

    protected void set(IStates states) {
        if(!stateManager.equalsStates(states)) {
            stateManager.setStates(states);
            doUpdates();
        }
    }

    protected void set(Consumer<IStates> fn) {
        IStates states = get();
        fn.accept(states);
        set(states);
    }
/*
    private void doUpdates() {
        if(alreadyUpdating) return;
        alreadyUpdating = true;
        if(updateCycles == MAX_UPDATE_CYCLES) {
            throw new RuntimeException("Max update cycles was reached!");
        }
        updateCycles++;
        whenUpdated(stateManager.getPreviousProps(), stateManager.getPreviousState());
        updateCycles--;
        if(updateCycles == 0) {
            //setup();
        }
    }
*/
    private void doUpdates() {
        shouldUpdate = Boolean.TRUE;
        if(alreadyUpdating) return;
        alreadyUpdating = Boolean.TRUE;

        while(shouldUpdate) {
            shouldUpdate = Boolean.FALSE;
            reconciliate();
            whenUpdated(stateManager.getPreviousProps(), stateManager.getPreviousState());
        }
        alreadyUpdating = Boolean.FALSE;
    }

    public abstract void callWhenMounted();
    public void callWhenUpdated(DOMTemplate template) {
        stateManager.setProps(new Sapphire().newProps(template));
        doUpdates();
    }
    public abstract void callWhenUnmounted();
    public abstract void reconciliate();

    protected StateManager getStateManager() {
        return stateManager;
    }


    /**
     * TODO: Suporte para comparação de arrays
     * @see <a href="https://stackoverflow.com/questions/23500237/how-to-get-set-array-element-with-reflection">referencia</a>
     */
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

        // TODO HERE

        return !a.equals(b);
    }


    protected DOMTemplate $(Class<? extends DOMItem> type) {
        return $(type, (Consumer<IProps>) null, (DOMTemplate) null);
    }

    protected DOMTemplate $(Class<? extends DOMItem> type, DOMTemplate ...children) {
        return $(type, null, children);
    }

    protected DOMTemplate $(Class<? extends DOMItem> type, Consumer<IProps> props) {
        return $(type, props, (DOMTemplate) null);
    }

    protected DOMTemplate $(Class<? extends DOMItem> type, Consumer<IProps> props, DOMTemplate ...children) {
        return new DOMTemplate(type, props, children);
    }

}
