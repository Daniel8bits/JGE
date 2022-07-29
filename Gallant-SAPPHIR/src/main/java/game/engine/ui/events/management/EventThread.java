package game.engine.ui.events.management;

import game.engine.ui.events.Event;
import game.engine.ui.events.callbacks.IEventCallback;

public class EventThread<T extends Event> implements Runnable {

    private IEventCallback<T> callback;
    private T event;

    public EventThread(IEventCallback<T> callback, T event) {
        this.callback = callback;
        this.event = event;
        new Thread(this).start();
    }

    @Override
    public void run() {
        callback.fn(event);
    }
}
