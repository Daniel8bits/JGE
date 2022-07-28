package game.engine.ui.events;

import io.qt.gui.QWheelEvent;

public class WheelEvent extends Event<QWheelEvent> {
    public WheelEvent(QWheelEvent qEvent) {
        super(qEvent);
    }
}