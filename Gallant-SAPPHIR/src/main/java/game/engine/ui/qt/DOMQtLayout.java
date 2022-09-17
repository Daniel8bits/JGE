package game.engine.ui.qt;

import game.engine.ui.dom.nodes.DOMAtomicElement;
import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.qt.components.ILayoutComponent;
import io.qt.widgets.QLayout;
import io.qt.widgets.QSpacerItem;
import io.qt.widgets.QWidget;

public abstract class DOMQtLayout<T extends ILayoutComponent> extends DOMQtElement<T> {

    public DOMQtLayout(IProps props, String hierarchyName) {
        super(props, hierarchyName);
    }

    @Override
    public void pack() {
        getChildren().forEach(domItem -> {
            ((DOMAtomicElement<?>) domItem).pack();
            if(domItem instanceof DOMQtWidget<?>) {
                DOMQtWidget<?> domWidget = (DOMQtWidget<?>) domItem;
                domWidget.removeFromParentComponent();
                ((QLayout) getComponent()).addWidget((QWidget) domWidget.getComponent());
            } else if (domItem instanceof DOMQtLayout<?>) {
                DOMQtLayout<?> domLayout = (DOMQtLayout<?>) domItem;
                domLayout.removeFromParentComponent();
                ((QLayout) getComponent()).addItem((QLayout) domLayout.getComponent());
            } else if (domItem instanceof DOMQtSpacer<?>) {
                DOMQtSpacer<?> domSpacer = (DOMQtSpacer<?>) domItem;
                domSpacer.removeFromParentComponent();
                ((QLayout) getComponent()).addItem((QSpacerItem) domSpacer.getComponent());
            }
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

}
