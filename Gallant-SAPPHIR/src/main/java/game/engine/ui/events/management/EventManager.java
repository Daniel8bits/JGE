package game.engine.ui.events.management;

import game.engine.ui.events.Event;
import game.engine.ui.events.EventType;
import game.engine.ui.events.callbacks.IEventCallback;

import java.util.HashMap;

public class EventManager {

    private HashMap<EventType, EventRegistry<Event<?>>> eventRegistries;

    public EventManager() {
        this.eventRegistries = new HashMap<>();
    }

    public void fire(EventType eventType, Event<?> event) {
        if(eventRegistries.containsKey(eventType)) {
            eventRegistries.get(eventType).dispatch(event);
        }
    }

    public void register(EventType eventType, IEventCallback<Event<?>> iEventCallback) {
        eventRegistries.get(eventType).registerCallback(iEventCallback);
    }

    public void register(EventType eventType, IEventCallback<Event<?>> iEventCallback, EventRegistry<Event<?>> eventRegistry) {
        eventRegistries.put(eventType, eventRegistry);
        eventRegistries.get(eventType).registerCallback(iEventCallback);
    }

    public boolean isRegistered(EventType eventType) {
        return eventRegistries.containsKey(eventType);
    }

}
