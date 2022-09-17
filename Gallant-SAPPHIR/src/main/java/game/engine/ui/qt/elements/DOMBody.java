package game.engine.ui.qt.elements;


import game.engine.ui.qt.components.widgets.Div;
import game.engine.ui.dom.nodes.DOMAtomicElement;
import org.w3c.dom.Node;

public class DOMBody extends DOMAtomicElement<Div> {

    public final static String TAG_NAME = "body";

    public DOMBody(Node node) {
        super(new Div(), null, node, TAG_NAME);
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
