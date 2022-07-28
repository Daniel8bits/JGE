package game.engine.ui.dom.nodes;

import game.engine.ui.layout.DOMLayout;
import io.qt.gui.QScreen;
import io.qt.gui.QWindow;
import io.qt.widgets.QMainWindow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

@AllArgsConstructor
@Getter
@Setter
public class DOMDocument implements IManageableNode {

    private Document document;
    private DOMHeader header;
    private DOMBody body;

    @Override
    public void update() {

        header.update();
        body.update();
    }

    public void pack(QMainWindow window) {
        /*
        window.getContentPane().setLayout(new DOMLayout(null));
        //window.setBounds(100, 100, 800, 500);
        window.setPreferredSize(new Dimension(800, 500));
        window.setLocationRelativeTo(null);
        //window.setExtendedState(window.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setAutoRequestFocus(true);
        window.setIgnoreRepaint(false);
        body.initializeComponent();

        body.pack();
        window.add(body.getComponent());

        window.pack();

        window.setVisible(true);

         */

        window.resize(800, 500);

        body.pack();
        body.initializeComponent();
        body.getComponent().setParent(window);
        window.show();

    }

    public void recalculateBounds() {
        body.recalculateBounds();
    }

}
