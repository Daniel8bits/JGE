package game.engine.ui.qt;

import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.qt.components.ISpacerComponent;
import io.qt.widgets.QLayout;
import io.qt.widgets.QSpacerItem;

public abstract class DOMQtSpacer<T extends ISpacerComponent> extends DOMQtElement<T> {

    public DOMQtSpacer(IProps props, String hierarchyName) {
        super(props, hierarchyName);
    }

    @Override
    public void pack() {

    }
    public void removeFromParentComponent() {
        DOMItem parent = (DOMItem) getParent();
        if(parent == null) {
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

}
