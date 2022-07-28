package game.engine.ui.dom.elements;

import game.engine.ui.dom.nodes.DOMElement;
import io.qt.core.QPoint;
import io.qt.core.QRect;
import io.qt.core.QSize;
import io.qt.core.Qt;
import io.qt.gui.QIcon;
import io.qt.gui.QPainter;
import io.qt.gui.QPixmap;
import io.qt.widgets.*;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

//public class DOMJPanelElement extends DOMElement<JPanel> {
public class DOMQFrameElement extends DOMElement<QFrame> {

    public final static String TAG_NAME = "qframe";

    public DOMQFrameElement(Node node, String parentHierarchyName) {
        super(new QFrame(), node, parentHierarchyName + "_" + TAG_NAME);
    }

    @Override
    protected void initializeComponent() {

        /*
        String text = this.getNode().getTextContent();
        JLabel label = new JLabel(text);
        this.getComponent().add(label);
         */

        //this.getComponent().setBackground(Color.BLUE);
        this.getComponent().setStyleSheet("background-color: blue;");
        super.initializeComponent();
    }

    @Override
    protected void calculateBounds() {
        int width = this.getParentAsElement().getComponent().width();
        int height = this.getParentAsElement().getComponent().height();
        /*
        this.getComponent().setBounds(
                0,
                0,
                (rectangle.width/100) * 60,
                (rectangle.height/100) * 60
                //(text.split("\n").length - 1) * 16
        );
         */
        this.getComponent().move(0, 0);
        this.getComponent().resize((width/100) * 60, (height/100) * 60);
    }
}
