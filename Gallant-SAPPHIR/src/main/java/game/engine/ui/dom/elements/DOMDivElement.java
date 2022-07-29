package game.engine.ui.dom.elements;

import game.engine.ui.components.Div;
import game.engine.ui.dom.nodes.DOMElement;
import org.w3c.dom.Node;

public class DOMDivElement extends DOMElement<Div> {

    public final static String TAG_NAME = "div";

    public DOMDivElement(Node node, String parentHierarchyName) {
        super(new Div(), node, parentHierarchyName + "_" + TAG_NAME);
    }

    @Override
    protected void initializeComponent() {
        this.getComponent().getQWidget().setStyleSheet("background-color: blue;");
        super.initializeComponent();
    }

    @Override
    protected void calculateBounds() {
        int width = this.getParentAsElement().getComponent().getQWidget().width();
        int height = this.getParentAsElement().getComponent().getQWidget().height();
        this.getComponent().getQWidget().move(0, 0);
        this.getComponent().getQWidget().resize((width/100) * 60, (height/100) * 60);
    }
}
