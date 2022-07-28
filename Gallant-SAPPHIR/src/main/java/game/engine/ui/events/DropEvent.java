package game.engine.ui.events;

import io.qt.gui.QDropEvent;

public class DropEvent extends Event<QDropEvent> {
    public DropEvent(QDropEvent qEvent) {
        super(qEvent);
    }
}