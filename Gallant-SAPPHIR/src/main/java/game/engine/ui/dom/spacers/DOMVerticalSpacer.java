package game.engine.ui.dom.spacers;

import io.qt.widgets.QSizePolicy;
import io.qt.widgets.QSpacerItem;
import org.w3c.dom.Node;

public class DOMVerticalSpacer extends DOMSpacer {

    public final static String TAG_NAME = DOMSpacer.PREFIX + "vertical";

    public DOMVerticalSpacer(Node node, String hierarchyName) {
        super(new QSpacerItem(20, 40, QSizePolicy.Policy.Fixed, QSizePolicy.Policy.Expanding), node, hierarchyName);
    }
}
