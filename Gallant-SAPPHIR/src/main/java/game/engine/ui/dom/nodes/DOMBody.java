package game.engine.ui.dom.nodes;


import game.engine.ui.dom.VirtualDOM;
import io.qt.widgets.QFrame;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

public class DOMBody extends DOMElement<QFrame> {

    public final static String TAG_NAME = "body";

    public DOMBody(Node node) {
        super(new QFrame(), node, TAG_NAME);
    }

    @Override
    protected void initializeComponent() {
        //this.getComponent().setBackground(Color.RED);
        this.getComponent().setStyleSheet("background-color: red;");
        super.initializeComponent();
    }
}
