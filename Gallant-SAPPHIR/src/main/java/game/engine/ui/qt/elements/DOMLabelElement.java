package game.engine.ui.qt.elements;

import game.engine.ui.qt.components.widgets.Label;
import game.engine.ui.dom.nodes.DOMAtomicElement;
import game.engine.ui.framework.interfaces.IProps;
import org.w3c.dom.Node;

public class DOMLabelElement extends DOMAtomicElement<Label> {

    public final static String TAG_NAME = "label";

    public DOMLabelElement(IProps props, Node node, String hierarchyName) {
        super(new Label(), props,  node, hierarchyName);
    }

    @Override
    protected void initializeComponent() {
        this.getComponent().setText(this.getNode().getTextContent());
        super.initializeComponent();
    }
}
