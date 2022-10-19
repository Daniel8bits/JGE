package game.engine.ui.qt;

import game.engine.ui.dom.nodes.DOMAtomicElement;
import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import game.engine.ui.qt.components.IWidgetComponent;
import game.engine.ui.qt.layouts.ELayoutType;
import io.qt.widgets.QGridLayout;
import io.qt.widgets.QLayout;
import io.qt.widgets.QSpacerItem;
import io.qt.widgets.QWidget;
import lombok.val;

import java.util.HashMap;
import java.util.Optional;

public abstract class DOMQtWidget<T extends IWidgetComponent> extends DOMQtElement<T>
    implements IDOMQtElementHandleLayout {

    @Props
    public static class DOMQtWidgetProps extends DOMQtElement.DOMQtElementProps {
        public ELayoutType layout;
    }

    private final HashMap<DOMQtElement<?>, int[]> childrenCells;

    public DOMQtWidget() {
        childrenCells = new HashMap<>();
    }


    @Override
    public void pack() {
        System.out.println(getHierarchyName());
        this.getChildren().forEach(domItem -> {
            ((DOMAtomicElement<?>) domItem).pack();
            //configureLayout(domItem);
        });
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
    public void removeFromParentComponent() {
        DOMItem parent = (DOMItem) getParent();
        if(parent == null || getComponent().parent() == null) {
            return;
        }
        if(parent instanceof DOMQtWidget<?>) {
            DOMQtWidget<?> pDomWidget = (DOMQtWidget<?>) parent;
            Optional.ofNullable(pDomWidget.getComponent().layout())
                    .ifPresentOrElse(
                            qLayout -> qLayout.removeWidget((QWidget) getComponent()),
                            () -> getComponent().setParent(null)
                    );
        } else if (parent instanceof DOMQtLayout<?>) {
            DOMQtLayout<?> pDomLayout = (DOMQtLayout<?>) parent;
            ((QLayout) pDomLayout.getComponent()).removeWidget((QWidget) getComponent());
        }
    }

    @Override
    public void removeFromParent() {
        removeFromParentComponent();
        super.removeFromParent();
    }

    @Override
    protected void whenMounted() {
        val props = (DOMQtWidgetProps) props();
        if(props.layout != null && props.layout != ELayoutType.NONE) {
            getChildren().forEach(child -> ((DOMQtElement<?>) child).removeFromParentComponent());
            getComponent().setLayout(ELayoutType.reduce(props.layout));
            getChildren().forEach(child -> configureLayout((DOMQtElement<?>) child));
        }
        if(getParent() instanceof IDOMQtElementHandleLayout) {
            val parent = (IDOMQtElementHandleLayout) getParent();
            parent.addChild(this, props.cell);
        }
    }

    @Override
    protected void whenUpdated(IProps previousProps, IStates previousStates) {
        val props = (DOMQtWidgetProps) props();
        val pProps = (DOMQtWidgetProps) previousProps;
        if(notEquals(pProps.layout, props.layout)) {
            getChildren().forEach(child -> configureLayout((DOMQtElement<?>) child));
        }
        if(notEquals(pProps.cell, props.cell)) {
            val parent = (IDOMQtElementHandleLayout) getParent();
            parent.addChild(this, props.cell);
        }
    }

    @Override
    public void addChild(DOMQtWidget<?> domQtWidget, int[] cell) {
        childrenCells.put(domQtWidget, cell);
        if(getComponent().layout() == null) {
            domQtWidget.getComponent().setParent((QWidget) getComponent());
            return;
        }

        if(getComponent().layout() instanceof QGridLayout) {
            QGridLayout gridLayout = (QGridLayout) getComponent().layout();
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
        getComponent().layout().addWidget((QWidget) domQtWidget.getComponent());
    }

    @Override
    public void addChild(DOMQtLayout<?> domQtLayout, int[] cell) {
        childrenCells.put(domQtLayout, cell);
        if(getComponent().layout() == null) return;
        if(getComponent().layout() instanceof QGridLayout) {
            QGridLayout gridLayout = (QGridLayout) getComponent().layout();
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
        getComponent().layout().addItem((QLayout) domQtLayout.getComponent());
    }

    @Override
    public void addChild(DOMQtSpacer<?> domQtSpacer, int[] cell) {
        childrenCells.put(domQtSpacer, cell);
        if(getComponent().layout() == null) return;
        if(getComponent().layout() instanceof QGridLayout) {
            QGridLayout gridLayout = (QGridLayout) getComponent().layout();
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
        getComponent().layout().addItem((QSpacerItem) domQtSpacer.getComponent());
    }

}
