package game.engine.ui.qt.components.layouts;

import game.engine.ui.events.management.EventManager;
import game.engine.ui.qt.components.ILayoutComponent;
import io.qt.widgets.QHBoxLayout;

public class HorizontalLayout extends QHBoxLayout implements ILayoutComponent {
    @Override
    public EventManager getEventManager() {
        return null;
    }
}
