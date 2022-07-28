package game.engine.ui.events.callbacks;

import game.engine.ui.events.Event;

public interface IEventCallback<T extends Event> {
    void fn(T e);
}
