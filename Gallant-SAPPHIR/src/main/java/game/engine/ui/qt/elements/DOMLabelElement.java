package game.engine.ui.qt.elements;

import game.engine.ui.framework.annotations.Childrenless;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.components.widgets.Label;
import game.engine.ui.framework.interfaces.IProps;

@Childrenless
public class DOMLabelElement extends DOMQtWidget<Label> {
    public DOMLabelElement(IProps props, String hierarchyName) {
        super(props, hierarchyName);
        setComponent(new Label());
    }

}
