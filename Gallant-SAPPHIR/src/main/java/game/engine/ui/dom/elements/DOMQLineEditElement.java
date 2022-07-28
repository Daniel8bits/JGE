package game.engine.ui.dom.elements;

import game.engine.ui.dom.nodes.DOMElement;
import io.qt.widgets.QLineEdit;
import org.w3c.dom.Node;

import javax.swing.*;

//public class DOMJTextFieldElement extends DOMElement<JTextField> {
public class DOMQLineEditElement extends DOMElement<QLineEdit> {

    public final static String TAG_NAME = "jtextfield";

    public DOMQLineEditElement(Node node, String parentHierarchyName) {
        super(new QLineEdit(), node, parentHierarchyName + "_" + TAG_NAME);
    }
}