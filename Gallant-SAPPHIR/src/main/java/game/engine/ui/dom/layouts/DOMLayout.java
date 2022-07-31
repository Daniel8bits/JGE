package game.engine.ui.dom.layouts;

import game.engine.ui.dom.nodes.DOMItem;
import io.qt.widgets.QLayout;
import lombok.Getter;
import org.w3c.dom.Node;

public class DOMLayout<T extends QLayout> extends DOMItem {

    public final static String PREFIX = "layout-";

    @Getter
    private T layout;

    public DOMLayout(T layout, Node node) {
        super(node);
        this.layout = layout;
    }

    @Override
    protected void pack() {

    }

    @Override
    public void update() {

    }
}
