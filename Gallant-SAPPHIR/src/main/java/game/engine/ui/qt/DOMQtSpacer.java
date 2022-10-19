package game.engine.ui.qt;

import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.qt.components.ISpacerComponent;
import game.engine.ui.qt.layouts.ELayoutType;
import io.qt.widgets.QLayout;
import io.qt.widgets.QSpacerItem;
import lombok.val;

public abstract class DOMQtSpacer<T extends ISpacerComponent> extends DOMQtElement<T> {

    @Override
    public void pack() {

    }

    @Override
    public void removeFromParentComponent() {
        DOMItem parent = (DOMItem) getParent();
        if(parent == null || getComponent().parent() == null) {
            return;
        }
        if (parent instanceof DOMQtLayout<?>) {
            DOMQtLayout<?> pDomLayout = (DOMQtLayout<?>) parent;
            ((QLayout) pDomLayout.getComponent()).removeItem((QSpacerItem) getComponent());
        }
    }

    @Override
    public void removeFromParent() {
        removeFromParentComponent();
        super.removeFromParent();
    }

    @Override
    protected void whenMounted() {
        val props = (DOMQtElement.DOMQtElementProps) props();
        if(getParent() instanceof IDOMQtElementHandleLayout) {
            val parent = (IDOMQtElementHandleLayout) getParent();
            parent.addChild(this, props.cell);
        }
    }

}
