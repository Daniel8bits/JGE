package game.engine.ui.qt.elements;

import game.engine.ui.qt.components.widgets.Button;
import game.engine.ui.dom.nodes.DOMAtomicElement;
import game.engine.ui.framework.interfaces.IProps;
import org.w3c.dom.Node;

public class DOMButtonElement extends DOMAtomicElement<Button> {

    public final static String TAG_NAME = "button";

    public DOMButtonElement(IProps props, Node node, String parentHierarchyName) {
        super(new Button(), props, node, parentHierarchyName + "_" + TAG_NAME);
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