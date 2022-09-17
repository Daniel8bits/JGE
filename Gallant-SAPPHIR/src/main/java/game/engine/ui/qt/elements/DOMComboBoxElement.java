package game.engine.ui.qt.elements;

import game.engine.ui.qt.components.widgets.ComboBox;
import game.engine.ui.dom.nodes.DOMAtomicElement;
import game.engine.ui.framework.interfaces.IProps;
import org.w3c.dom.Node;

public class DOMComboBoxElement extends DOMAtomicElement<ComboBox> {

    public final static String TAG_NAME = "combobox";

    public DOMComboBoxElement(IProps props, Node node, String parentHierarchyName) {
        super(new ComboBox(), props, node, parentHierarchyName + "_" + TAG_NAME);
    }
}