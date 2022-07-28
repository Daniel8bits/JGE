package game.engine.ui.events;

import io.qt.gui.QInputMethodEvent;

public class InputMethodEvent extends Event<QInputMethodEvent> {
    public InputMethodEvent(QInputMethodEvent qEvent) {
        super(qEvent);
    }
}