package game.engine.ui.qt.layouts;

import game.engine.ui.qt.DOMQtLayout;
import game.engine.ui.qt.DOMQtSpacer;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.components.layouts.GridLayout;
import io.qt.widgets.QLayout;
import io.qt.widgets.QSpacerItem;
import io.qt.widgets.QWidget;

public class DOMGridLayout extends DOMQtLayout<GridLayout> {
    public DOMGridLayout() {
        setComponent(new GridLayout());
    }

    @Override
    public void addChild(DOMQtWidget<?> domQtWidget, int[] cell) {
        if(cell != null) {
            getComponent().addWidget((QWidget) domQtWidget.getComponent(), cell[0], cell[1], cell[2], cell[3]);
        }
    }

    @Override
    public void addChild(DOMQtLayout<?> domQtLayout, int[] cell) {
        if(cell != null) {
            getComponent().addLayout((QLayout) domQtLayout.getComponent(), cell[0], cell[1], cell[2], cell[3]);
        }
    }

    @Override
    public void addChild(DOMQtSpacer<?> domQtSpacer, int[] cell) {
        if(cell != null) {
            getComponent().addItem((QSpacerItem) domQtSpacer.getComponent(), cell[0], cell[1], cell[2], cell[3]);
        }
    }
}
