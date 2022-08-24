package game.engine.ui.test;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.dom.elements.*;
import game.engine.ui.framework.interfaces.IProps;
import org.w3c.dom.Node;

public class TestComponent extends DOMCustomElement {


    public TestComponent(IProps props, Node node, String hierarchyName) {
        super(props, node, hierarchyName);
    }

    @Override
    public DOMTemplate render() {
        return $(
            DOMDivElement.class,
            props -> {
                DOMElement.DOMElementProps p = (DOMElement.DOMElementProps) props;
                p.layout = "horizontal";
            },
            new DOMTemplate[] {
                $(DOMButtonElement.class,
                    props -> {

                    }
                ),
                $(DOMLabelElement.class),
                $(DOMButtonElement.class),
            }
        );
    }
}
