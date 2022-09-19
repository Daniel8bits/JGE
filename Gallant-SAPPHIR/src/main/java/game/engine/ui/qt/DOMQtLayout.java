package game.engine.ui.qt;

import game.engine.ui.dom.nodes.DOMAtomicElement;
import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import game.engine.ui.qt.components.ILayoutComponent;
import io.qt.widgets.QLayout;
import io.qt.widgets.QSpacerItem;
import io.qt.widgets.QWidget;
import lombok.val;

public abstract class DOMQtLayout<T extends ILayoutComponent> extends DOMQtElement<T>
    implements IDOMQtElementHandleLayout {

    public DOMQtLayout(IProps props, String hierarchyName) {
        super(props, hierarchyName);
    }

    @Override
    public void pack() {
        getChildren().forEach(domItem -> {
            ((DOMAtomicElement<?>) domItem).pack();
        });
    }

    public void removeFromParentComponent() {
        DOMItem parent = (DOMItem) getParent();
        if(parent == null) {
            return;
        }
        if(parent instanceof DOMQtWidget<?>) {
            DOMQtWidget<?> pDomElement = (DOMQtWidget<?>) parent;
            pDomElement.getComponent().layout().removeItem((QLayout) getComponent());
        } else if (parent instanceof DOMQtLayout<?>) {
            DOMQtLayout<?> pDomLayout = (DOMQtLayout<?>) parent;
            ((QLayout) pDomLayout.getComponent()).removeItem((QLayout) getComponent());
        }
    }

    @Override
    public void removeFromParent() {
        removeFromParentComponent();
        super.removeFromParent();
    }

    @Override
    protected void whenMounted() {
        DOMQtWidget.DOMQtWidgetProps props = (DOMQtWidget.DOMQtWidgetProps) props();
        if(getParent() instanceof IDOMQtElementHandleLayout) {
            val parent = (IDOMQtElementHandleLayout) getParent();
            parent.addChild(this, props.cell);
        }
    }

    @Override
    protected void whenUpdated(IProps previousProps, IStates previousStates) {
        DOMQtElement.DOMQtElementProps props = (DOMQtWidget.DOMQtWidgetProps) props();
        DOMQtElement.DOMQtElementProps pProps = (DOMQtWidget.DOMQtWidgetProps) previousProps;
        if(notEquals(pProps.cell, props.cell)) {
            val parent = (IDOMQtElementHandleLayout) getParent();
            parent.addChild(this, props.cell);
        }
    }

    @Override
    public void addChild(DOMQtWidget<?> domQtWidget, int[] cell) {
        domQtWidget.removeFromParentComponent();
        ((QLayout) getComponent()).addWidget((QWidget) domQtWidget.getComponent());
    }

    @Override
    public void addChild(DOMQtLayout<?> domQtLayout, int[] cell) {
        domQtLayout.removeFromParentComponent();
        ((QLayout) getComponent()).addItem((QLayout) domQtLayout.getComponent());
    }

    @Override
    public void addChild(DOMQtSpacer<?> domQtSpacer, int[] cell) {
        domQtSpacer.removeFromParentComponent();
        ((QLayout) getComponent()).addItem((QSpacerItem) domQtSpacer.getComponent());
    }

}
