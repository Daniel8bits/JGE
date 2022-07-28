package game.engine.ui.events;

import io.qt.gui.QMouseEvent;

public class MouseEvent extends Event<QMouseEvent> {
    public MouseEvent(QMouseEvent qEvent) {
        super(qEvent);
    }
}