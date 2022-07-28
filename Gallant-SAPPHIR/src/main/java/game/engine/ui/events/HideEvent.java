package game.engine.ui.events;

import io.qt.gui.QHideEvent;

public class HideEvent extends Event<QHideEvent> {
    public HideEvent(QHideEvent qEvent) {
        super(qEvent);
    }
}