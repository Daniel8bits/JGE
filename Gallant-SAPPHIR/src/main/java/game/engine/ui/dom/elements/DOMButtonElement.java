package game.engine.ui.dom.elements;

import game.engine.ui.components.Button;
import org.w3c.dom.Node;

public class DOMButtonElement extends DOMElement<Button> {

    public final static String TAG_NAME = "button";

    public DOMButtonElement(Node node, String parentHierarchyName) {
        super(new Button(), node, parentHierarchyName + "_" + TAG_NAME);
    }

    @Override
    protected void initializeComponent() {
        this.getComponent().setText(this.getNode().getTextContent());
        super.initializeComponent();
    }

    @Override
    protected void calculateBounds() {
        this.getComponent().move(0, 0);
        this.getComponent().resize(100, 32);
    }

    public void sayHello() {
        System.out.println("hello");
    }

}