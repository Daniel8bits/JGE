package game.engine.ui.qt.elements;

import game.engine.ui.framework.annotations.Childrenless;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.components.widgets.TextField;
import game.engine.ui.framework.interfaces.IProps;
import lombok.val;

import java.util.Optional;

@Childrenless
public class DOMTextFieldElement extends DOMQtWidget<TextField> {
    public DOMTextFieldElement() {
        setComponent(new TextField());
    }
}