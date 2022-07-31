package game.engine.ui.dom.nodes;

import org.w3c.dom.Node;

public abstract class DOMItem extends DOMNode<DOMItem> {

    public DOMItem(Node node) {
        super(node);
    }

    protected abstract void pack();
    protected void initializeComponent() {
        this.getChildren().forEach(DOMItem::initializeComponent);
    }
    protected void recalculateBounds() {
        this.getChildren().forEach(DOMItem::recalculateBounds);
    }
}
