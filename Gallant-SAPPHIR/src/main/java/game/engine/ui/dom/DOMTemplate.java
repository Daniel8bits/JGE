package game.engine.ui.dom;

import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.interfaces.IProps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Consumer;

@AllArgsConstructor
@Getter
public class DOMTemplate {

    private Class<? extends DOMItem> type;
    private Consumer<IProps> props;
    private DOMTemplate[] children;

}
