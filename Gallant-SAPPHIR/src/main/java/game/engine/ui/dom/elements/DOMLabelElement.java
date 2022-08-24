package game.engine.ui.dom.elements;

import game.engine.ui.components.Label;
import game.engine.ui.framework.interfaces.IProps;
import org.w3c.dom.Node;

public class DOMLabelElement extends DOMElement<Label> {

    public final static String TAG_NAME = "label";

    public DOMLabelElement(IProps props, Node node, String hierarchyName) {
        super(new Label(), props,  node, hierarchyName);
    }

    @Override
    protected void initializeComponent() {
        this.getComponent().setText(this.getNode().getTextContent());
        super.initializeComponent();
    }
}
