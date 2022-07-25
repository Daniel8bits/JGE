package game.engine.ui.dom.elements;

import game.engine.ui.dom.nodes.DOMElement;
import org.w3c.dom.Node;

import javax.swing.*;

public class DOMJComboBoxElement extends DOMElement<JComboBox<?>> {
    public DOMJComboBoxElement(Node node) {
        super(new JComboBox<>(), node);
    }
}