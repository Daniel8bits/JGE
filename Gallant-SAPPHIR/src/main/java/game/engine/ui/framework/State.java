package game.engine.ui.framework;

import game.engine.ui.framework.annotations.StateValue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class State<T> {

    private T state;
    private T previousState;

    private T cache;

    public State(T state) {
        this.state = state;
    }

    public void set(T state) {
        if(state == null) {
            if(this.state != null && !isLiteral(this.state)) {
                previousState = clone(this.state);
            } else {
                previousState = this.state;
            }
            this.state = state;
            return;
        }

        if(!isLiteral(state)) {
            previousState = clone(this.state);
            this.state = clone(state);
            return;
        }

        previousState = this.state;
        this.state = state;
    }

    public T get() {
        if(state != null && (cache == null || !cache.equals(state))) {
            cache = isLiteral(state) ? state : clone(state);
        }
        return cache;
    }

    private T clone(T original) {
        if(original == null) {
            return null;
        }
        Class<T> classType = (Class<T>) original.getClass();
        T copy;
        try {
            copy = classType.getConstructor().newInstance();

            Field[] fields = classType.getFields();

            for(int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if(!isStateValue(field)) continue;
                String methodName = "set" + String.valueOf(field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
                classType.getMethod(methodName, field.getType()).invoke(copy, field.get(original));
            }

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return copy;
    }

    private boolean isLiteral(T value) {
        return (
            value instanceof Integer ||
            value instanceof Float ||
            value instanceof Long ||
            value instanceof Byte ||
            value instanceof Short ||
            value instanceof String ||
            value instanceof Character ||
            value instanceof Boolean
        );
    }

    private boolean isStateValue(Field field) {
        return Arrays.stream(field.getAnnotations()).anyMatch(annotation -> annotation instanceof StateValue);
    }

}
