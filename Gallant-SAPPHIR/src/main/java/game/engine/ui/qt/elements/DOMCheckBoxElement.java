package game.engine.ui.qt.elements;

import game.engine.ui.framework.annotations.Childrenless;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.components.widgets.CheckBox;
import game.engine.ui.framework.interfaces.IProps;
import lombok.val;

import java.util.Optional;

@Childrenless
public class DOMCheckBoxElement extends DOMQtWidget<CheckBox> {

    @Props
    public static class DOMCheckBoxProps extends DOMQtWidget.DOMQtWidgetProps {
        public String label;
    }
    public DOMCheckBoxElement() {
        setComponent(new CheckBox());
    }

    @Override
    protected void whenMounted() {
        super.whenMounted();
        val props = (DOMCheckBoxElement.DOMCheckBoxProps) props();
        Optional.ofNullable(props.label).ifPresent(value -> {
            getComponent().setText(value);
        });
    }

}