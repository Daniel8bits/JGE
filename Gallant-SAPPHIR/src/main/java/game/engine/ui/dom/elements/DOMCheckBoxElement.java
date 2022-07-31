package game.engine.ui.dom.elements;

import game.engine.ui.components.CheckBox;
import org.w3c.dom.Node;

public class DOMCheckBoxElement extends DOMElement<CheckBox> {

    public final static String TAG_NAME = "checkbox";

    public DOMCheckBoxElement(Node node, String hierarchyName) {
        super(new CheckBox(), node, hierarchyName);
    }

    @Override
    protected void initializeComponent() {
        this.getComponent().setText(this.getNode().getTextContent());
        super.initializeComponent();
    }
}