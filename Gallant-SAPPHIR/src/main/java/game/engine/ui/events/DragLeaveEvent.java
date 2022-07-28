package game.engine.ui.events;

import io.qt.gui.QDragLeaveEvent;

public class DragLeaveEvent extends Event<QDragLeaveEvent> {
    public DragLeaveEvent(QDragLeaveEvent qEvent) {
        super(qEvent);
    }
}