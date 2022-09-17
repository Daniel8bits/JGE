package game.engine.ui.qt.layouts;

import game.engine.ui.dom.nodes.DOMAtomicElement;
import game.engine.ui.qt.DOMQtLayout;
import game.engine.ui.qt.DOMQtSpacer;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.components.layouts.GridLayout;
import io.qt.widgets.QGridLayout;
import io.qt.widgets.QLayout;
import io.qt.widgets.QSpacerItem;
import io.qt.widgets.QWidget;
import org.w3c.dom.Node;

public class DOMGridLayout extends DOMQtLayout<GridLayout> {

    public DOMGridLayout(IProps props, String hierarchyName) {
        super(props, hierarchyName);
    }

    @Override
    public void pack() {
        this.getChildren().forEach(domItem -> {
            DOMQtElementProps props = (DOMQtElementProps) this.props();
            ((DOMAtomicElement<?>) domItem).pack();
            if(domItem instanceof DOMQtWidget<?>) {
                DOMQtWidget<?> domWidget = (DOMQtWidget<?>) domItem;
                int[] cell = props.cell;
                if(cell != null) {
                    getComponent().addWidget((QWidget) domWidget.getComponent(), cell[0], cell[1], cell[2], cell[3]);
                }
            } else if (domItem instanceof DOMQtLayout<?>) {
                DOMQtLayout<?> domLayout = (DOMQtLayout<?>) domItem;
                int[] cell = props.cell;
                if(cell != null) {
                    getComponent().addLayout((QLayout) domLayout.getComponent(), cell[0], cell[1], cell[2], cell[3]);
                }
            } else if (domItem instanceof DOMQtSpacer<?>) {
                DOMQtSpacer<?> domSpacer = (DOMQtSpacer<?>) domItem;
                int[] cell = props.cell;
                if(cell != null) {
                    getComponent().addItem((QSpacerItem) domSpacer.getComponent(), cell[0], cell[1], cell[2], cell[3]);
                }
            }
        });
    }

}
