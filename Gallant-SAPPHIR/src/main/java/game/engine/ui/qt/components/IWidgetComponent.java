package game.engine.ui.qt.components;

import game.engine.ui.dom.IComponent;
import game.engine.ui.events.management.EventManager;
import io.qt.core.QObject;
import io.qt.widgets.QHBoxLayout;
import io.qt.widgets.QLayout;
import io.qt.widgets.QVBoxLayout;
import io.qt.widgets.QWidget;

public interface IWidgetComponent extends IComponent {
    enum LayoutEnum {
        NONE("none"),
        HORIZONTAL("horizontal"),
        VERTICAL("vertical"),
        GRID("grid");

        private final String LAYOUT;
        LayoutEnum(String layout) {
            this.LAYOUT = layout;
        }
        public boolean equals(String layout) {
            return this.LAYOUT.equals(layout);
        }

        public static LayoutEnum reduce(String layout) {
            if(layout.equals(NONE.LAYOUT)) {
                return NONE;
            } else if(layout.equals(HORIZONTAL.LAYOUT)) {
                return HORIZONTAL;
            } else if(layout.equals(VERTICAL.LAYOUT)) {
                return VERTICAL;
            } else if(layout.equals(GRID.LAYOUT)) {
                return GRID;
            }
            return NONE;
        }
    }
    default void add(IWidgetComponent component) {
        if(layout() != null) {
            layout().addWidget((QWidget) component);
        } else {
            ((QWidget) component).setParent((QWidget) this);
        }
    }
    default void bounds(int x, int y, int width, int height) {
        move(x, y);
        resize(width, height);
    }
    default void setLayout(String layout) {
        if(LayoutEnum.HORIZONTAL.equals(layout)) {
            setLayout(new QHBoxLayout());
        } else if (LayoutEnum.VERTICAL.equals(layout)) {
            setLayout(new QVBoxLayout());
        }
    }
    QLayout layout();
    void resize(int width, int height);
    void move(int x, int y);
    void setLayout(QLayout layout);
    int width();
    int height();
    QObject parent();
    void setParent(QWidget qWidget);
}
