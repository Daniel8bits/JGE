package game.engine.ui.events;

import io.qt.gui.QActionEvent;

public class ActionEvent extends Event<QActionEvent> {
    public ActionEvent(QActionEvent qEvent) {
        super(qEvent);
    }
}
