package game.engine.ui.events;

import io.qt.gui.QShowEvent;

public class ShowEvent extends Event<QShowEvent> {
    public ShowEvent(QShowEvent qEvent) {
        super(qEvent);
    }
}