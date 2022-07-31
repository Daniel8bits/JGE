package game.engine.ui.dom.nodes;


import game.engine.ui.components.Div;
import org.w3c.dom.Node;

public class DOMBody extends DOMElement<Div> {

    public final static String TAG_NAME = "body";

    public DOMBody(Node node) {
        super(new Div(), node, TAG_NAME);
    }

    @Override
    protected void initializeComponent() {
        this.getComponent().setStyleSheet("QWidget {background-color: red;}");
        super.initializeComponent();
    }
}
