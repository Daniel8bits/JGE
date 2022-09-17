package game.engine.ui.qt.layouts;

import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.qt.DOMQtLayout;
import game.engine.ui.qt.components.layouts.HorizontalLayout;
import io.qt.widgets.QHBoxLayout;
import org.w3c.dom.Node;

public class DOMHorizontalLayout extends DOMQtLayout<HorizontalLayout> {

    public DOMHorizontalLayout(IProps props, String hierarchyName) {
        super(props, hierarchyName);
    }
}
