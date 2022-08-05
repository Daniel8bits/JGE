package game.engine.ui.dom.spacers;

import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
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
    protected void whenMounted() {

    }

    @Override
    protected void whenUpdated(IProps previousProps, IStates previousStates) {

    }

    @Override
    protected void whenUnmounted() {

    }
}
