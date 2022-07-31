package game.engine.ui.dom.layouts;

import game.engine.ui.dom.elements.DOMElement;
import game.engine.ui.dom.nodes.DOMElementFacade;
import game.engine.ui.dom.spacers.DOMSpacer;
import io.qt.widgets.QGridLayout;
import io.qt.widgets.QWidget;
import org.w3c.dom.Node;

public class DOMGridLayout extends DOMLayout<QGridLayout> {

    public final static String TAG_NAME = DOMLayout.PREFIX + "grid";

    public DOMGridLayout(Node node, String hierarchyName) {
        super(new QGridLayout(), node, hierarchyName);
    }

    @Override
    public void pack() {
        this.getChildren().forEach(domItem -> {
            domItem.pack();
            if(domItem instanceof DOMElement<?>) {
                DOMElement<?> domElement = (DOMElement<?>) domItem;
                int[] cell = new DOMElementFacade().getCell(domElement);
                if(cell != null) {
                    getLayout().addWidget((QWidget) domElement.getComponent(), cell[0], cell[1], cell[2], cell[3]);
                }
            } else if (domItem instanceof DOMLayout<?>) {
                DOMLayout<?> domLayout = (DOMLayout<?>) domItem;
                int[] cell = new DOMElementFacade().getCell(domLayout);
                if(cell != null) {
                    getLayout().addLayout(domLayout.getLayout(), cell[0], cell[1], cell[2], cell[3]);
                }
            } else if (domItem instanceof DOMSpacer) {
                DOMSpacer domSpacer = (DOMSpacer) domItem;
                int[] cell = new DOMElementFacade().getCell(domSpacer);
                if(cell != null) {
                    getLayout().addItem(domSpacer.getSpacer(), cell[0], cell[1], cell[2], cell[3]);
                }
            }
        });
    }

}
