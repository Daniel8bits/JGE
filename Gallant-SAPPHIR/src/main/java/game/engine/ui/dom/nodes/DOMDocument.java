package game.engine.ui.dom.nodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;

import javax.swing.JFrame;
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

    public void pack(JFrame window) {
        window.setVisible(true);
        window.setLayout(null);
        window.setBounds(100, 100, 800, 500);
        window.setExtendedState(window.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setAutoRequestFocus(true);
        window.setIgnoreRepaint(false);
        body.pack();
        window.add(body.getComponent());
        body.initializeComponent();

        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                window.requestFocus();
                body.recalculateBounds();
                window.repaint();
            }
        });

        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                window.requestFocus();
                body.recalculateBounds();
                window.repaint();
            }
        });
    }

}
