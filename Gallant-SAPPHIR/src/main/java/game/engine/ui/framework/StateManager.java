package game.engine.ui.framework;

import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.annotations.States;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import org.w3c.dom.Node;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.Consumer;

public class StateManager {

    private IProps props, previousProps;
    private IStates states, previousStates;
    private DOMItem domItem;

    public StateManager(DOMItem domItem, IProps props, Consumer<IStates> defaultStates) {
        this.props = props;
        this.domItem = domItem;
        initialize(defaultStates);
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

    private void initialize(Consumer<IStates> defaultStates) {
        Class<?> domItemClass = domItem.getClass();
        previousProps = cloneProps(props);

        Arrays.stream(domItemClass.getClasses())
                .filter(clazz -> Arrays.stream(clazz.getAnnotations()).anyMatch(annotation -> annotation instanceof States))
                .findFirst()
                .ifPresent(statesClass -> {
                    try {
                        states = (IStates) statesClass.getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                        previousStates = cloneStates(states);
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

    public boolean equalsProps(IProps a) {
        return equalsProps(props, a);
    }

    public static boolean equalsProps(IProps a, IProps b) {
        if(!a.getClass().isInstance(b)) {
            throw new RuntimeException("Props are not of the same type!");
        }
        Class<?> classType = a.getClass();
        return Arrays.stream(classType.getFields())
                .allMatch(field -> {
                    try {
                        return field.get(a).equals(field.get(b));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public boolean equalsStates(IStates a) {
        return equalsStates(states, a);
    }

    public static boolean equalsStates(IStates a, IStates b) {
        if(!a.getClass().isInstance(b)) {
            throw new RuntimeException("Props are not of the same type!");
        }
        Class<?> classType = a.getClass();
        return Arrays.stream(classType.getFields())
                .allMatch(field -> {
                    try {
                        Object aValue = field.get(a);
                        Object bValue = field.get(b);
                        if(aValue == null || bValue == null) {
                            return aValue == null && bValue == null;
                        }
                        return aValue.equals(bValue);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}
