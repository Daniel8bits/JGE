package game.engine.ui.dom.elements;

import game.engine.ui.dom.nodes.DOMElement;
import io.qt.widgets.QComboBox;
import org.w3c.dom.Node;

import javax.swing.*;

//public class DOMJComboBoxElement extends DOMElement<JComboBox<?>> {
public class DOMQComboBoxElement extends DOMElement<QComboBox> {

    public final static String TAG_NAME = "qcombobox";

    public DOMQComboBoxElement(Node node, String parentHierarchyName) {
        super(new QComboBox(), node, parentHierarchyName + "_" + TAG_NAME);
    }
}