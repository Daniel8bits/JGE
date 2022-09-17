package game.engine.ui.test;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.dom.nodes.DOMCustomElement;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.elements.DOMButtonElement;
import game.engine.ui.qt.elements.DOMDivElement;
import game.engine.ui.qt.elements.DOMLabelElement;

public class TestComponent extends DOMCustomElement {


    public TestComponent(IProps props, String hierarchyName) {
        super(props, hierarchyName);
    }

    @Override
    public DOMTemplate render() {
        return $(
            DOMDivElement.class,
            props -> {
                DOMQtWidget.DOMQtWidgetProps p = (DOMQtWidget.DOMQtWidgetProps) props;
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
