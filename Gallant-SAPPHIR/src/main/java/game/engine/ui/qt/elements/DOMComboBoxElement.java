package game.engine.ui.qt.elements;

import game.engine.ui.framework.annotations.Childrenless;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.components.widgets.ComboBox;
import game.engine.ui.framework.interfaces.IProps;

@Childrenless
public class DOMComboBoxElement extends DOMQtWidget<ComboBox> {

    public DOMComboBoxElement() {
        setComponent(new ComboBox());
    }
}