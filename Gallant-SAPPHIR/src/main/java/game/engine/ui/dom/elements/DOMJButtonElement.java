package game.engine.ui.dom.elements;

import game.engine.ui.dom.nodes.DOMElement;
import org.w3c.dom.Node;

import javax.swing.*;

public class DOMJButtonElement extends DOMElement<JButton> {
    public DOMJButtonElement(Node node) {
        super(new JButton(), node);
    }

    @Override
    protected void initializeComponent() {
        this.getComponent().setText(this.getNode().getTextContent());
        super.initializeComponent();
    }

    @Override
    protected void calculateBounds() {
        this.getComponent().setBounds(0, 0, 100, 32);
    }
}