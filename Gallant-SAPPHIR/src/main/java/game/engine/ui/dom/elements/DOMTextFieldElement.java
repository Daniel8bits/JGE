package game.engine.ui.dom.elements;

import game.engine.ui.components.TextField;
import game.engine.ui.dom.nodes.DOMElement;
import io.qt.widgets.QLineEdit;
import org.w3c.dom.Node;

//public class DOMJTextFieldElement extends DOMElement<JTextField> {
public class DOMTextFieldElement extends DOMElement<TextField> {

    public final static String TAG_NAME = "textfield";

    public DOMTextFieldElement(Node node, String parentHierarchyName) {
        super(new TextField(), node, parentHierarchyName + "_" + TAG_NAME);
    }
}