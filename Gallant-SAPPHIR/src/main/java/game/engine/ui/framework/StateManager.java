package game.engine.ui.framework;

import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class StateManager {

    private IProps props, previousProps;
    private IStates states, previousStates;

    public StateManager(DOMItem domItem) {
        initialize(domItem);
    }

    public void setProps(IProps props) {
        previousProps = cloneProps(this.props);
        this.props = cloneProps(props);
    }

    public IProps getProps() {
        return cloneProps(props);
    }

    public void setStates(IStates states) {
        previousStates = cloneStates(this.states);
        this.states = cloneStates(states);
    }

    public IStates getState() {
        return cloneStates(states);
    }

    public IProps getPreviousProps() {
        return cloneProps(previousProps);
    }

    public IStates getPreviousState() {
        return cloneStates(previousStates);
    }

    @SuppressWarnings("unchecked")
    private void initialize(DOMItem domItem) {
        Class<?> domItemClass = domItem.getClass();

        Arrays.stream(domItemClass.getClasses())
                .filter(clazz -> Arrays.stream(clazz.getInterfaces()).anyMatch(interface_ -> interface_.getName().equals(IProps.class.getName())))
                .forEach(propsClass -> {
                    try {
                        props = (IProps) propsClass.getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                });

        Arrays.stream(domItemClass.getClasses())
                .filter(clazz -> Arrays.stream(clazz.getInterfaces()).anyMatch(interface_ -> interface_.getName().equals(IStates.class.getName())))
                .forEach(statesClass -> {
                    try {
                        states = (IStates) statesClass.getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                });

    }

    @SuppressWarnings("unchecked")
    private IProps cloneProps(IProps original) {
        if(original == null){
            return null;
        }
        Class<IProps> classType = (Class<IProps>) original.getClass();
        IProps copy;
        try {
            copy = classType.getConstructor().newInstance();

            Arrays.stream(classType.getFields()).forEach(f -> {
                try {
                    f.set(copy, f.get(original));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return copy;
    }

    @SuppressWarnings("unchecked")
    private IStates cloneStates(IStates original) {
        if(original == null){
            return null;
        }
        Class<IStates> classType = (Class<IStates>) original.getClass();
        IStates copy;
        try {
            copy = classType.getConstructor().newInstance();

            Arrays.stream(classType.getFields()).forEach(f -> {
                try {
                    f.set(copy, f.get(original));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return copy;
    }

}
