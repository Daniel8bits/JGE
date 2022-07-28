package game.engine.ui.events;

import io.qt.gui.QDragEnterEvent;

public class DragEnterEvent extends Event<QDragEnterEvent> {
    public DragEnterEvent(QDragEnterEvent qEvent) {
        super(qEvent);
    }
}