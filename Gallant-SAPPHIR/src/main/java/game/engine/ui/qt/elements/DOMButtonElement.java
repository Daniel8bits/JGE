package game.engine.ui.qt.elements;

import game.engine.ui.framework.annotations.Childrenless;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.components.widgets.Button;
import game.engine.ui.framework.interfaces.IProps;
import lombok.val;

import java.util.Optional;

@Childrenless
public class DOMButtonElement extends DOMQtWidget<Button> {
    @Props
    public static class DOMButtonProps extends DOMQtWidget.DOMQtWidgetProps {
        public String label;
    }
    public DOMButtonElement() {
        setComponent(new Button());
    }

    @Override
    protected void whenMounted() {
        super.whenMounted();
        val props = (DOMButtonElement.DOMButtonProps) props();
        Optional.ofNullable(props.label).ifPresent(value -> {
            getComponent().setText(value);
        });
    }
}