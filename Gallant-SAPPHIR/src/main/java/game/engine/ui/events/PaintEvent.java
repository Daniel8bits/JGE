package game.engine.ui.events;

import io.qt.gui.QPaintEvent;

public class PaintEvent extends Event<QPaintEvent> {
    public PaintEvent(QPaintEvent qEvent) {
        super(qEvent);
    }
}