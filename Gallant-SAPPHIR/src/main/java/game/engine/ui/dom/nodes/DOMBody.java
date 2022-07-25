package game.engine.ui.dom.nodes;


import game.engine.ui.dom.VirtualDOM;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

public class DOMBody extends DOMElement<JPanel> {

    public final static String TAG_NAME = "body";

    public DOMBody(Node node) {
        super(new JPanel(), node);
    }

    @Override
    protected void initializeComponent() {
        this.getComponent().setBackground(Color.RED);
        super.initializeComponent();
    }
}
