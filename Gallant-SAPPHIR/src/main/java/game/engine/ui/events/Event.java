package game.engine.ui.events;

import io.qt.core.QEvent;
import lombok.Getter;

public class Event<T extends QEvent> {

    @Getter
    private T qEvent;

    public Event(T qEvent) {
        this.qEvent = qEvent;
    }


}
