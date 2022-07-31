package game.engine.ui.dom.layouts;

import io.qt.widgets.QHBoxLayout;
import org.w3c.dom.Node;

public class DOMHorizontalLayout extends DOMLayout<QHBoxLayout> {

    public final static String TAG_NAME = DOMLayout.PREFIX + "horizontal";

    public DOMHorizontalLayout(Node node, String hierarchyName) {
        super(new QHBoxLayout(), node, hierarchyName);
    }
}
