package game.engine.ui.dom.spacers;

import game.engine.ui.dom.nodes.DOMItem;
import io.qt.widgets.QSpacerItem;
import lombok.Getter;
import org.w3c.dom.Node;

public class DOMSpacer extends DOMItem {

    public final static String PREFIX = "spacer-";

    @Getter
    private QSpacerItem spacer;

    public DOMSpacer(QSpacerItem spacer, Node node, String hierarchyName) {
        super(node, hierarchyName);
        this.spacer = spacer;
    }

    @Override
    public void pack() {

    }

    @Override
    public void update() {

    }
}
