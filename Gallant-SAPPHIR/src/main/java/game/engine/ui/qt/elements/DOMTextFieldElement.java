package game.engine.ui.qt.elements;

import game.engine.ui.qt.components.widgets.TextField;
import game.engine.ui.dom.nodes.DOMAtomicElement;
import game.engine.ui.framework.interfaces.IProps;
import org.w3c.dom.Node;

public class DOMTextFieldElement extends DOMAtomicElement<TextField> {

    public final static String TAG_NAME = "textfield";

    public DOMTextFieldElement(IProps props, Node node, String parentHierarchyName) {
        super(new TextField(), props, node, parentHierarchyName + "_" + TAG_NAME);
    }

    @Override
    protected void calculateBounds() {
        getComponent().bounds(0, 0, 100, 32);
    }
}