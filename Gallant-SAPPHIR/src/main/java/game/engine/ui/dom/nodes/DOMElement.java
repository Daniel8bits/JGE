package game.engine.ui.dom.nodes;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.interfaces.IProps;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Node;

import java.util.List;

public abstract class DOMElement extends DOMItem {

    @Props
    public static class DOMElementProps implements IProps {
        @Setter
        @Getter
        public List<DOMTemplate> children;
    }

    public DOMElement(IProps props, String hierarchyName) {
        super(props, hierarchyName);
    }
}
