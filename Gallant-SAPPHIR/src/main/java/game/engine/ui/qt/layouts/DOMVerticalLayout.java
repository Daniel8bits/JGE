package game.engine.ui.qt.layouts;

import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.qt.DOMQtLayout;
import game.engine.ui.qt.components.layouts.VerticalLayout;
import io.qt.widgets.QVBoxLayout;
import org.w3c.dom.Node;

public class DOMVerticalLayout extends DOMQtLayout<VerticalLayout> {
    public DOMVerticalLayout(IProps props, Node node, String hierarchyName) {
        super(props, hierarchyName);
    }
}
