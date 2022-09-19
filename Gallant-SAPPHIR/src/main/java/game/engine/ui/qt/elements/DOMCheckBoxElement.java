package game.engine.ui.qt.elements;

import game.engine.ui.framework.annotations.Childrenless;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.components.widgets.CheckBox;
import game.engine.ui.framework.interfaces.IProps;

@Childrenless
public class DOMCheckBoxElement extends DOMQtWidget<CheckBox> {

    public DOMCheckBoxElement(IProps props, String hierarchyName) {
        super(props, hierarchyName);
        setComponent(new CheckBox());
    }

}