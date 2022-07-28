package game.engine.ui.events;

import io.qt.gui.QCloseEvent;

public class CloseEvent extends Event<QCloseEvent> {
    public CloseEvent(QCloseEvent qEvent) {
        super(qEvent);
    }
}