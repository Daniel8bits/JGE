package game.engine.ui.qt.elements;

import game.engine.ui.framework.annotations.Childrenless;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.components.widgets.ComboBox;
import game.engine.ui.framework.interfaces.IProps;
import lombok.val;

import java.util.Optional;

@Childrenless
public class DOMComboBoxElement extends DOMQtWidget<ComboBox> {
    public DOMComboBoxElement() {
        setComponent(new ComboBox());
    }
}