package game.engine.ui.dom.elements;

import game.engine.ui.components.Label;
import org.w3c.dom.Node;

public class DOMLabelElement extends DOMElement<Label> {

    public final static String TAG_NAME = "label";

    public DOMLabelElement(Node node, String hierarchyName) {
        super(new Label(), node, hierarchyName);
    }

    @Override
    protected void initializeComponent() {
        this.getComponent().setText(this.getNode().getTextContent());
        super.initializeComponent();
    }
}
