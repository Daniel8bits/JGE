package game.engine.ui.events;

import io.qt.core.QTimerEvent;

public class TimerEvent extends Event<QTimerEvent> {
    public TimerEvent(QTimerEvent qEvent) {
        super(qEvent);
    }
}