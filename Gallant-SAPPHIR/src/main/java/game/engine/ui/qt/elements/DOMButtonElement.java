package game.engine.ui.qt.elements;

import game.engine.ui.framework.annotations.Childrenless;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.components.widgets.Button;
import game.engine.ui.framework.interfaces.IProps;

@Childrenless
public class DOMButtonElement extends DOMQtWidget<Button> {

    public DOMButtonElement(IProps props, String parentHierarchyName) {
        super(props, parentHierarchyName);
        setComponent(new Button());
    }


}