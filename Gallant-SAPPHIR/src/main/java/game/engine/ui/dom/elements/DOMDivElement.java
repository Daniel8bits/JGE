package game.engine.ui.dom.elements;

import game.engine.ui.components.Div;
import game.engine.ui.framework.interfaces.IProps;
import org.w3c.dom.Node;

public class DOMDivElement extends DOMElement<Div> {

    public final static String TAG_NAME = "div";

    public DOMDivElement(IProps props, Node node, String parentHierarchyName) {
        super(new Div(), props, node, parentHierarchyName + "_" + TAG_NAME);
    }

    @Override
    protected void initializeComponent() {
        super.initializeComponent();
    }

    @Override
    protected void calculateBounds() {
        int width = this.getParentAsElement().getComponent().width();
        int height = this.getParentAsElement().getComponent().height();
        this.getComponent().move(0, 0);
        this.getComponent().resize((width/100) * 60, (height/100) * 60);
    }
}
