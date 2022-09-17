package game.engine.ui.qt;

import game.engine.ui.dom.IComponent;
import game.engine.ui.dom.nodes.DOMAtomicElement;
import game.engine.ui.dom.nodes.DOMElement;
import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import game.engine.ui.qt.components.IWidgetComponent;
import io.qt.widgets.QGridLayout;
import io.qt.widgets.QLayout;
import io.qt.widgets.QSpacerItem;
import io.qt.widgets.QWidget;
import org.w3c.dom.Node;

import java.util.Optional;

public class DOMQtWidget<T extends IWidgetComponent> extends DOMQtElement<T> {

    @Props
    public static class DOMQtWidgetProps extends DOMQtElement.DOMQtElementProps {
        public String layout;
    }

    public DOMQtWidget(IProps props, String hierarchyName) {
        super(props, hierarchyName);
    }

    @Override
    public void pack() {
        System.out.println(getHierarchyName());
        this.getChildren().forEach(domItem -> {
            ((DOMAtomicElement<?>) domItem).pack();
            configureLayout(domItem);
        });
    }

    private void configureLayout(DOMItem domItem) {

        DOMQtElementProps props = (DOMQtElementProps) this.props();

        if(getComponent().layout() == null) {
            if(!(domItem instanceof DOMQtWidget<?>)) {
                return;
            }
            DOMQtWidget<?> domWidget = (DOMQtWidget<?>) domItem;
            domWidget.getComponent().setParent((QWidget) getComponent());
        } else {
            if(domItem instanceof DOMQtWidget<?>) {
                DOMQtWidget<?> domWidget = (DOMQtWidget<?>) domItem;
                if(getComponent().layout() instanceof QGridLayout) {
                    QGridLayout gridLayout = (QGridLayout) getComponent().layout();
                    int[] cell = props.cell;
                    if(cell != null) {
                        domWidget.removeFromParentComponent();
                        gridLayout.addWidget((QWidget) domWidget.getComponent(), cell[0], cell[1], cell[2], cell[3]);
                    }
                    return;
                }
                getComponent().layout().addWidget((QWidget) domWidget.getComponent());
            } else if (domItem instanceof DOMQtLayout<?>) {
                DOMQtLayout<?> domLayout = (DOMQtLayout<?>) domItem;
                if(getComponent().layout() instanceof QGridLayout) {
                    QGridLayout gridLayout = (QGridLayout) getComponent().layout();
                    int[] cell = props.cell;
                    if(cell != null) {
                        domLayout.removeFromParentComponent();
                        gridLayout.addLayout((QLayout) domLayout.getComponent(), cell[0], cell[1], cell[2], cell[3]);
                    }
                    return;
                }
                getComponent().layout().addItem((QLayout) domLayout.getComponent());
            } else if (domItem instanceof DOMQtSpacer<?>) {
                DOMQtSpacer<?> domSpacer = (DOMQtSpacer<?>) domItem;
                if(getComponent().layout() instanceof QGridLayout) {
                    QGridLayout gridLayout = (QGridLayout) getComponent().layout();
                    int[] cell = props.cell;
                    if(cell != null) {
                        domSpacer.removeFromParentComponent();
                        gridLayout.addItem((QSpacerItem) domSpacer.getComponent(), cell[0], cell[1], cell[2], cell[3]);
                    }
                    return;
                }
                getComponent().layout().addItem((QSpacerItem) domSpacer.getComponent());
            }
        }

    }

    public void removeFromParentComponent() {
        DOMItem parent = (DOMItem) getParent();
        if(parent == null) {
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
        System.out.println(((DOMQtWidgetProps) props()).layout);
    }

    @Override
    protected void whenUpdated(IProps previousProps, IStates previousStates) {
        DOMQtWidgetProps props = (DOMQtWidgetProps) props();
        DOMQtWidgetProps pProps = (DOMQtWidgetProps) previousProps;
        if(notEquals(pProps.layout, props.layout)) {
            getChildren().forEach(this::configureLayout);
        }
    }

}
