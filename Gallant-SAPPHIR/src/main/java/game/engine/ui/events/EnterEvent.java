package game.engine.ui.events;

import io.qt.gui.QEnterEvent;

public class EnterEvent extends Event<QEnterEvent> {
    public EnterEvent(QEnterEvent qEvent) {
        super(qEvent);
    }
}