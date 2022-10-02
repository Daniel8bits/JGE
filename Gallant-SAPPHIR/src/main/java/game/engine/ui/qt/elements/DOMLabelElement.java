package game.engine.ui.qt.elements;

import game.engine.ui.framework.annotations.Childrenless;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.qt.DOMQtElement;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.ELayoutType;
import game.engine.ui.qt.components.widgets.Label;
import game.engine.ui.framework.interfaces.IProps;
import lombok.val;

import java.util.Optional;

@Childrenless
public class DOMLabelElement extends DOMQtWidget<Label> {
    @Props
    public static class DOMLabelProps extends DOMQtWidget.DOMQtWidgetProps {
        public String value;
    }
    public DOMLabelElement() {
        setComponent(new Label());
    }

    @Override
    protected void whenMounted() {
        super.whenMounted();
        val props = (DOMLabelProps) props();
        Optional.ofNullable(props.value).ifPresent(value -> {
            getComponent().setText(value);
        });
    }
}
