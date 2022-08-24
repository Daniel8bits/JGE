package game.engine.ui.dom.layouts;

import game.engine.ui.dom.elements.DOMCustomElement;
import game.engine.ui.framework.interfaces.IProps;
import io.qt.widgets.QVBoxLayout;
import org.w3c.dom.Node;

public class DOMVerticalLayout extends DOMLayout<QVBoxLayout> {

    public final static String TAG_NAME = DOMLayout.PREFIX + "vertical";

    public DOMVerticalLayout(IProps props, Node node, String hierarchyName) {
        super(new QVBoxLayout(), props, node, hierarchyName);
    }
}
