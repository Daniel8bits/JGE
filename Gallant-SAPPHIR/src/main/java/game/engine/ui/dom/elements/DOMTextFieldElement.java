package game.engine.ui.dom.elements;

import game.engine.ui.components.TextField;
import game.engine.ui.framework.interfaces.IProps;
import org.w3c.dom.Node;

public class DOMTextFieldElement extends DOMElement<TextField> {

    public final static String TAG_NAME = "textfield";

    public DOMTextFieldElement(IProps props, Node node, String parentHierarchyName) {
        super(new TextField(), props, node, parentHierarchyName + "_" + TAG_NAME);
    }

    @Override
    protected void calculateBounds() {
        getComponent().bounds(0, 0, 100, 32);
    }
}