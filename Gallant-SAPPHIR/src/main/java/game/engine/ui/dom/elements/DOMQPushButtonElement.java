package game.engine.ui.dom.elements;

import game.engine.ui.dom.nodes.DOMElement;
import io.qt.widgets.QPushButton;
import org.w3c.dom.Node;

import javax.swing.*;

//public class DOMJButtonElement extends DOMElement<JButton> {
public class DOMQPushButtonElement extends DOMElement<QPushButton> {

    public final static String TAG_NAME = "qpushbutton";

    public DOMQPushButtonElement(Node node, String parentHierarchyName) {
        //super(new JButton(), node, parentHierarchyName + "_" + TAG_NAME);
        super(new QPushButton(), node, parentHierarchyName + "_" + TAG_NAME);
    }

    @Override
    protected void initializeComponent() {
        this.getComponent().setText(this.getNode().getTextContent());
        super.initializeComponent();
    }

    @Override
    protected void calculateBounds() {
        //this.getComponent().setBounds(0, 0, 100, 32);
        this.getComponent().move(0, 0);
        this.getComponent().resize(100, 32);
    }
}