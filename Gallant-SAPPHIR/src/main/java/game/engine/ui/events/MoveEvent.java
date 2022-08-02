package game.engine.ui.events;

import io.qt.gui.QMoveEvent;

public class MoveEvent extends Event<QMoveEvent> {
    public MoveEvent(QMoveEvent qEvent) {
        super(qEvent);
    }
}
