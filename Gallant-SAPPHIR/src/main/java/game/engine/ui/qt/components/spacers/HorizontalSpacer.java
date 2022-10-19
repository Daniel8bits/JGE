package game.engine.ui.qt.components.spacers;

import game.engine.ui.events.management.EventManager;
import game.engine.ui.qt.components.ISpacerComponent;
import io.qt.core.QObject;
import io.qt.widgets.QSizePolicy;
import io.qt.widgets.QSpacerItem;

public class HorizontalSpacer extends QSpacerItem implements ISpacerComponent {
    public HorizontalSpacer(int w, int h) {
        super(w, h, QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Fixed);
    }

    @Override
    public EventManager getEventManager() {
        return null;
    }

    @Override
    public QObject parent() {
        return super.layout();
    }
}
