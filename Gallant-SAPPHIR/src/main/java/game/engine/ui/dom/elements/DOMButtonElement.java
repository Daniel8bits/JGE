package game.engine.ui.dom.elements;

import game.engine.ui.components.Button;
import game.engine.ui.dom.nodes.DOMElement;
import io.qt.widgets.QPushButton;
import org.w3c.dom.Node;

public class DOMButtonElement extends DOMElement<Button> {

    public final static String TAG_NAME = "button";

    public DOMButtonElement(Node node, String parentHierarchyName) {
        super(new Button(), node, parentHierarchyName + "_" + TAG_NAME);
    }

    @Override
    protected void initializeComponent() {
        this.getComponent().getQWidget().setText(this.getNode().getTextContent());
        super.initializeComponent();
    }

    @Override
    protected void calculateBounds() {
        this.getComponent().getQWidget().move(0, 0);
        this.getComponent().getQWidget().resize(100, 32);
    }
}