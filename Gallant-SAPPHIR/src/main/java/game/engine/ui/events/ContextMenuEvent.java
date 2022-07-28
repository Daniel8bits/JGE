package game.engine.ui.events;

import io.qt.gui.QContextMenuEvent;

public class ContextMenuEvent extends Event<QContextMenuEvent> {
    public ContextMenuEvent(QContextMenuEvent qEvent) {
        super(qEvent);
    }
}