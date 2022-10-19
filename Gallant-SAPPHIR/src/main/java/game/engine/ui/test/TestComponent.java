package game.engine.ui.test;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.dom.nodes.DOMCustomElement;
import game.engine.ui.qt.DOMQtElement;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.elements.DOMCheckBoxElement;
import game.engine.ui.qt.layouts.DOMHorizontalLayout;
import game.engine.ui.qt.spacers.DOMHorizontalSpacer;
import lombok.val;

public class TestComponent extends DOMCustomElement {
    @Override
    public DOMTemplate render() {
        return
        $(DOMHorizontalLayout.class, props -> {
                val p = (DOMQtElement.DOMQtElementProps) props;
                p.cell = new int[] {2, 1};
            },
            $(DOMCheckBoxElement.class, (props) -> {
                val p = (DOMCheckBoxElement.DOMCheckBoxProps) props;
                p.cell = new int[] {2, 0};
                p.label = "Remember me";
            }),
            $(DOMCheckBoxElement.class, (props) -> {
                val p = (DOMCheckBoxElement.DOMCheckBoxProps) props;
                p.cell = new int[] {2, 0};
                p.label = "Do another stuff";
            }),
            $(DOMHorizontalSpacer.class)
        );
    }
}
