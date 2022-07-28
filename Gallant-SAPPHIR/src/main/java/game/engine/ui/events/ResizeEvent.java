package game.engine.ui.events;

import io.qt.gui.QResizeEvent;

public class ResizeEvent extends Event<QResizeEvent> {
    public ResizeEvent(QResizeEvent qEvent) {
        super(qEvent);
    }
}