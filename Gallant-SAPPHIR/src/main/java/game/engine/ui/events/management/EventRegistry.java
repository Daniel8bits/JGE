package game.engine.ui.events.management;

import game.engine.ui.events.Event;
import game.engine.ui.events.callbacks.IEventCallback;
import lombok.Getter;
import lombok.Synchronized;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Getter
public class EventRegistry<T extends Event<?>> {
    private ArrayList<IEventCallback<T>> callbacks;
    private Queue<T> eventQueue;

    private boolean dispatchLocker;

    public EventRegistry() {
        callbacks = new ArrayList<>();
        eventQueue = new LinkedList<>();
        dispatchLocker = false;
    }

    private Runnable dispatcher = () -> {
        T event;
        while((event = pop()) != null) {
            T finalEvent = event;
            callbacks.forEach(iEventCallback -> iEventCallback.fn(finalEvent));
        }
        unlock();
    };

    public void dispatch(T event) {
        push(event);
        if(!isLocked()) {
            lock();
            new Thread(dispatcher).start();
        }
    }

    public void registerCallback(IEventCallback<T> iEventCallback) {
        add(iEventCallback);
    }

    @Synchronized
    public void lock() {
        dispatchLocker = true;
    }

    @Synchronized
    public void unlock() {
        dispatchLocker = false;
    }

    @Synchronized
    public boolean isLocked() {
        return dispatchLocker;
    }

    @Synchronized
    private void push(T event) {
        eventQueue.add(event);
    }

    @Synchronized
    private T pop() {
        return eventQueue.poll();
    }

    @Synchronized
    private void add(IEventCallback<T> iEventCallback) {
        callbacks.add(iEventCallback);
    }

}
