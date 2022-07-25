package game.engine.ui.dom.nodes;

import org.w3c.dom.Node;


public class DOMHeader extends DOMNode<DOMProperty> implements IManageableNode {

    public final static String TAG_NAME = "head";

    public DOMHeader(Node node) {
        super(node);
    }

    @Override
    public void update() {

        this.getChildren().forEach(DOMProperty::update);
    }

}
