package game.engine.ui.events;

import io.qt.gui.QFocusEvent;

public class FocusEvent extends Event<QFocusEvent> {
    public FocusEvent(QFocusEvent qEvent) {
        super(qEvent);
    }
}