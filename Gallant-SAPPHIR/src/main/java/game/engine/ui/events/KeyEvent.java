package game.engine.ui.events;

import io.qt.gui.QKeyEvent;

public class KeyEvent extends Event<QKeyEvent> {
    public KeyEvent(QKeyEvent qEvent) {
        super(qEvent);
    }
}