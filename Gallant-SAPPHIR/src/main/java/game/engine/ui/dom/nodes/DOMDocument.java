package game.engine.ui.dom.nodes;

import game.engine.ui.qt.components.containers.MainWindow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;

@AllArgsConstructor
@Getter
@Setter
public class DOMDocument implements IManageableNode {

    private Document document;
    private DOMHeader header;
    //private DOMBody body;

    @Override
    public void update() {

        //header.update();
        //body.update();
    }

    public void pack(MainWindow window) {


        window.resize(800, 500);
        //body.getComponent().setLayout("horizontal");
        //body.pack();
        //body.initializeComponent();

        //window.setCentralWidget(body.getComponent());
        window.show();

        //window.setStyleSheet("QMainWindow {background-color: red;}");

    }

    public void recalculateBounds() {
        //body.recalculateBounds();
    }

}
