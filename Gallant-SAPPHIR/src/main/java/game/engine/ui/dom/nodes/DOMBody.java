package game.engine.ui.dom.nodes;


import game.engine.ui.components.Div;
import game.engine.ui.dom.VirtualDOM;
import io.qt.widgets.QFrame;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

public class DOMBody extends DOMElement<Div> {

    public final static String TAG_NAME = "body";

    public DOMBody(Node node) {
        super(new Div(), node, TAG_NAME);
    }

    @Override
    protected void initializeComponent() {
        //this.getComponent().getQWidget().resize(VirtualDOM.getWindowWidth(), VirtualDOM.getWindowHeight());
        this.getComponent().getQWidget().setStyleSheet("background-color: red;");
        super.initializeComponent();
    }
}
