package game.engine.ui.dom.nodes;

import lombok.Getter;
import org.w3c.dom.Node;

public abstract class DOMItem extends DOMNode<DOMItem> {

    @Getter
    private String hierarchyName;
    public DOMItem(Node node, String hierarchyName) {
        super(node);
        this.hierarchyName = hierarchyName;
    }

    public abstract void pack();
    protected void initializeComponent() {
        this.getChildren().forEach(DOMItem::initializeComponent);
    }
    protected void recalculateBounds() {
        this.getChildren().forEach(DOMItem::recalculateBounds);
    }
}
