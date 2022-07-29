package game.engine.ui.components;

import game.engine.ui.events.management.EventManager;
import io.qt.widgets.QHBoxLayout;
import io.qt.widgets.QVBoxLayout;
import io.qt.widgets.QWidget;
import lombok.Getter;

public abstract class Component<T extends QWidget> {

    public enum Layout {
        HORIZONTAL("horizontal"),
        VERTICAL("vertical");

        private String layout;
        Layout(String layout) {
            this.layout = layout;
        }
        public boolean equals(String layout) {
            return this.layout.equals(layout);
        }
    }

    @Getter
    private EventManager eventManager;
    @Getter
    protected T qWidget;

    public Component() {
        this.eventManager = new EventManager();
        initComponent();
    }

    protected abstract void initComponent();

    public void setLayout(String layout) {
        if(Layout.HORIZONTAL.equals(layout)) {
            qWidget.setLayout(new QHBoxLayout());
        } else if (Layout.VERTICAL.equals(layout)) {
            qWidget.setLayout(new QVBoxLayout());
        }
    }

    public void add(Component<?> component) {
        qWidget.layout().addWidget(component.qWidget);
    }


}
