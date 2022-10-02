package game.engine.ui.qt.components.layouts;

import game.engine.ui.events.management.EventManager;
import game.engine.ui.qt.components.ILayoutComponent;
import io.qt.widgets.QGridLayout;

public class GridLayout extends QGridLayout implements ILayoutComponent {
    @Override
    public EventManager getEventManager() {
        return null;
    }
}
