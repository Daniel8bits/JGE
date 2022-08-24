package game.engine.ui.dom.layouts;

import game.engine.ui.dom.elements.DOMCustomElement;
import game.engine.ui.framework.interfaces.IProps;
import io.qt.widgets.QHBoxLayout;
import org.w3c.dom.Node;

public class DOMHorizontalLayout extends DOMLayout<QHBoxLayout> {

    public final static String TAG_NAME = DOMLayout.PREFIX + "horizontal";

    public DOMHorizontalLayout(IProps props, Node node, String hierarchyName) {
        super(new QHBoxLayout(), props, node, hierarchyName);
    }
}
