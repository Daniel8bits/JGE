package game.engine.ui.qt.elements;

import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.components.widgets.Div;
import game.engine.ui.framework.interfaces.IProps;

public class DOMDivElement extends DOMQtWidget<Div> {

    public DOMDivElement() {
        setComponent(new Div());
    }


}
