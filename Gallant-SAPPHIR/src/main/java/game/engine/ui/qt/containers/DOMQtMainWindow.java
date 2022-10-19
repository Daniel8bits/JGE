package game.engine.ui.qt.containers;

import game.engine.ui.dom.nodes.DOMContainer;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import game.engine.ui.qt.*;
import game.engine.ui.qt.components.containers.MainWindow;
import game.engine.ui.qt.components.widgets.Div;
import game.engine.ui.qt.layouts.ELayoutType;
import io.qt.widgets.QGridLayout;
import io.qt.widgets.QLayout;
import io.qt.widgets.QSpacerItem;
import io.qt.widgets.QWidget;
import lombok.val;

import java.util.HashMap;

public abstract class DOMQtMainWindow extends DOMContainer<MainWindow>
    implements IDOMQtElementHandleLayout {

    @Props
    public static class DOMQtMainWindowProps extends DOMQtElement.DOMQtElementProps {
        public ELayoutType layout;
        public int windowWidth, windowHeight;
    }

    private Div centralWidget;

    private final HashMap<DOMQtElement<?>, int[]> childrenCells;

    public DOMQtMainWindow() {
        childrenCells = new HashMap<>();
        setContainer(new MainWindow());
        centralWidget = new Div();
    }

    private void configureLayout(DOMQtElement<?> domQtElement) {
        if (domQtElement instanceof DOMQtWidget<?>) {
            addChild((DOMQtWidget<?>) domQtElement, childrenCells.get(domQtElement));
        } else if (domQtElement instanceof DOMQtLayout<?>) {
            addChild((DOMQtLayout<?>) domQtElement, childrenCells.get(domQtElement));
        } else if (domQtElement instanceof DOMQtSpacer<?>) {
            addChild((DOMQtSpacer<?>) domQtElement, childrenCells.get(domQtElement));
        }
    }

    @Override
    protected void whenMounted() {
        val props = (DOMQtMainWindowProps) props();
        if(props.layout != null && props.layout != ELayoutType.NONE) {
            getChildren().forEach(child -> ((DOMQtElement<?>) child).removeFromParentComponent());
            centralWidget.setLayout(ELayoutType.reduce(props.layout));
            getChildren().forEach(child -> configureLayout((DOMQtElement<?>) child));
        }
        getContainer().setCentralWidget(centralWidget);
        getContainer().resize(props.windowWidth, props.windowHeight);
        getContainer().show();
    }

    @Override
    protected void whenUpdated(IProps previousProps, IStates previousStates) {
        val props = (DOMQtMainWindowProps) props();
        val pProps = (DOMQtMainWindowProps) previousProps;
        if(notEquals(pProps.layout, props.layout)) {
            getChildren().forEach(child -> configureLayout((DOMQtElement<?>) child));
        }
    }

    @Override
    public void addChild(DOMQtWidget<?> domQtWidget, int[] cell) {
        childrenCells.put(domQtWidget, cell);
        if(centralWidget.layout() == null) {
            domQtWidget.getComponent().setParent(centralWidget);
            return;
        }

        if(centralWidget.layout() instanceof QGridLayout) {
            QGridLayout gridLayout = (QGridLayout) centralWidget.layout();
            if(cell != null) {
                domQtWidget.removeFromParentComponent();
                if(cell.length > 2) {
                    gridLayout.addWidget((QWidget) domQtWidget.getComponent(), cell[0], cell[1], cell[2], cell[3]);
                } else {
                    gridLayout.addWidget((QWidget) domQtWidget.getComponent(), cell[0], cell[1]);
                }
            }
            return;
        }
        centralWidget.layout().addWidget((QWidget) domQtWidget.getComponent());
    }

    @Override
    public void addChild(DOMQtLayout<?> domQtLayout, int[] cell) {
        childrenCells.put(domQtLayout, cell);
        if(centralWidget.layout() == null) return;
        if(centralWidget.layout() instanceof QGridLayout) {
            QGridLayout gridLayout = (QGridLayout) getContainer().layout();
            if(cell != null) {
                domQtLayout.removeFromParentComponent();
                if(cell.length > 2) {
                    gridLayout.addLayout((QLayout) domQtLayout.getComponent(), cell[0], cell[1], cell[2], cell[3]);
                } else {
                    gridLayout.addLayout((QLayout) domQtLayout.getComponent(), cell[0], cell[1]);
                }
            }
            return;
        }
        centralWidget.layout().addItem((QLayout) domQtLayout.getComponent());
    }

    @Override
    public void addChild(DOMQtSpacer<?> domQtSpacer, int[] cell) {
        childrenCells.put(domQtSpacer, cell);
        if(centralWidget.layout() == null) return;
        if(centralWidget.layout() instanceof QGridLayout) {
            QGridLayout gridLayout = (QGridLayout) centralWidget.layout();
            if(cell != null) {
                domQtSpacer.removeFromParentComponent();
                if(cell.length > 2) {
                    gridLayout.addItem((QSpacerItem) domQtSpacer.getComponent(), cell[0], cell[1], cell[2], cell[3]);
                } else {
                    gridLayout.addItem((QSpacerItem) domQtSpacer.getComponent(), cell[0], cell[1]);
                }
            }
            return;
        }
        centralWidget.layout().addItem((QSpacerItem) domQtSpacer.getComponent());
    }
}
