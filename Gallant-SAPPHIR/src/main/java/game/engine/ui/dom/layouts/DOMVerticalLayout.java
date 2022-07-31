package game.engine.ui.dom.layouts;

import io.qt.widgets.QVBoxLayout;
import org.w3c.dom.Node;

public class DOMVerticalLayout extends DOMLayout<QVBoxLayout> {

    public final static String TAG_NAME = DOMLayout.PREFIX + "vertical";

    public DOMVerticalLayout(Node node, String hierarchyName) {
        super(new QVBoxLayout(), node, hierarchyName);
    }
}
