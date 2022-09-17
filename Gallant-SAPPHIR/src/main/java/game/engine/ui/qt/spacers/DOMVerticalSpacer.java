package game.engine.ui.qt.spacers;

import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.qt.DOMQtSpacer;
import game.engine.ui.qt.components.spacers.VerticalSpacer;
import io.qt.widgets.QSizePolicy;
import io.qt.widgets.QSpacerItem;
import org.w3c.dom.Node;

public class DOMVerticalSpacer extends DOMQtSpacer<VerticalSpacer> {
    public DOMVerticalSpacer(IProps props, String hierarchyName) {
        super(props, hierarchyName);
    }
}
