package game.engine.ui.events.management;

import game.engine.ui.events.Event;
import game.engine.ui.events.EventType;
import game.engine.ui.events.callbacks.IEventCallback;
import io.qt.core.QEvent;

import java.util.HashMap;

public class EventManager {

    private HashMap<EventType, EventRegistry<Event<? extends QEvent>>> eventRegistries;

    public EventManager() {
        this.eventRegistries = new HashMap<>();
    }

    public void fire(EventType eventType, Event<? extends QEvent> event) {
        if(eventRegistries.containsKey(eventType)) {
            eventRegistries.get(eventType).dispatch(event);
        }
    }

    @SuppressWarnings("unchecked")
    public void register(EventType eventType, IEventCallback iEventCallback) {
        eventRegistries.get(eventType).registerCallback(iEventCallback);
    }

    @SuppressWarnings("unchecked")
    public void register(EventType eventType, IEventCallback iEventCallback, EventRegistry eventRegistry) {
        eventRegistries.put(eventType, eventRegistry);
        eventRegistries.get(eventType).registerCallback(iEventCallback);
    }

    public boolean isRegistered(EventType eventType) {
        return eventRegistries.containsKey(eventType);
    }

}
