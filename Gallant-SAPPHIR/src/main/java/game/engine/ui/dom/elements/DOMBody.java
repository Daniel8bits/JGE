package game.engine.ui.dom.elements;


import game.engine.ui.components.Div;
import org.w3c.dom.Node;

public class DOMBody extends DOMElement<Div> {

    public final static String TAG_NAME = "body";

    public DOMBody(Node node) {
        super(new Div(), node, TAG_NAME);
    }

    @Override
    public void pack() {
        super.pack();
    }

    @Override
    public void initializeComponent() {
        super.initializeComponent();
    }

    @Override
    public void recalculateBounds() {
        super.recalculateBounds();
    }
}
