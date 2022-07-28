package game.engine.ui.events;

import io.qt.gui.QDragMoveEvent;

public class DragMoveEvent extends Event<QDragMoveEvent> {
    public DragMoveEvent(QDragMoveEvent qEvent) {
        super(qEvent);
    }
}