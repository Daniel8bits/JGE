package game.engine.ui.dom;

import io.qt.gui.QResizeEvent;
import io.qt.widgets.QMainWindow;

public class Window extends QMainWindow {

    @Override
    protected void resizeEvent(QResizeEvent event) {
        VirtualDOM.getInstance().getDomDocument().recalculateBounds();
        super.resizeEvent(event);



    }
}
