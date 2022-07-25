package game.engine.ui.dom.elements;

import game.engine.ui.dom.nodes.DOMElement;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

public class DOMJPanelElement extends DOMElement<JPanel> {
    public DOMJPanelElement(Node node) {
        super(new JPanel(), node);
    }

    @Override
    protected void initializeComponent() {

        String text = this.getNode().getTextContent();
        JLabel label = new JLabel(text);
        this.getComponent().add(label);

        this.getComponent().setBackground(Color.BLUE);
        super.initializeComponent();
    }

    @Override
    protected void calculateBounds() {
        Rectangle rectangle = this.getParentAsElement().getComponent().getBounds();
        this.getComponent().setBounds(
                0,
                0,
                (rectangle.width/100) * 60,
                (rectangle.height/100) * 60
                //(text.split("\n").length - 1) * 16
        );
    }
}
