package game.engine.ui.qt.components.layouts;

import game.engine.ui.events.management.EventManager;
import game.engine.ui.qt.components.ILayoutComponent;
import io.qt.widgets.QVBoxLayout;

public class VerticalLayout extends QVBoxLayout implements ILayoutComponent {
    @Override
    public EventManager getEventManager() {
        return null;
    }
}
