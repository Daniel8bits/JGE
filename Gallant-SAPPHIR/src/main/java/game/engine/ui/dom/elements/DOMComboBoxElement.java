package game.engine.ui.dom.elements;

import game.engine.ui.components.ComboBox;
import org.w3c.dom.Node;

public class DOMComboBoxElement extends DOMElement<ComboBox> {

    public final static String TAG_NAME = "combobox";

    public DOMComboBoxElement(Node node, String parentHierarchyName) {
        super(new ComboBox(), node, parentHierarchyName + "_" + TAG_NAME);
    }
}