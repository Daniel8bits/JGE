package game.engine.ui.qt.layouts;

import io.qt.widgets.QGridLayout;
import io.qt.widgets.QHBoxLayout;
import io.qt.widgets.QLayout;
import io.qt.widgets.QVBoxLayout;

public enum ELayoutType {
    NONE,
    GRID_LAYOUT,
    HORIZONTAL_LAYOUT,
    VERTICAL_LAYOUT;

    public static QLayout reduce(ELayoutType type) {
        return switch (type) {
            case GRID_LAYOUT -> new QGridLayout();
            case VERTICAL_LAYOUT -> new QVBoxLayout();
            case HORIZONTAL_LAYOUT -> new QHBoxLayout();
            case NONE -> null;
        };
    }
}
