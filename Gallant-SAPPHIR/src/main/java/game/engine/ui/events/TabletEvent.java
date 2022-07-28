package game.engine.ui.events;

import io.qt.gui.QTabletEvent;

public class TabletEvent extends Event<QTabletEvent> {
    public TabletEvent(QTabletEvent qEvent) {
        super(qEvent);
    }
}