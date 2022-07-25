package game.engine.ui.dom.elements;

import game.engine.ui.dom.nodes.DOMElement;
import org.w3c.dom.Node;

import javax.swing.*;

public class DOMJTextFieldElement extends DOMElement<JTextField> {
    public DOMJTextFieldElement(Node node) {
        super(new JTextField(), node);
    }
}