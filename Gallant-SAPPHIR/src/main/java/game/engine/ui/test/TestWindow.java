package game.engine.ui.test;

import game.engine.ui.dom.DOMTemplate;
import game.engine.ui.qt.DOMQtElement;
import game.engine.ui.qt.DOMQtWidget;
import game.engine.ui.qt.ELayoutType;
import game.engine.ui.qt.containers.DOMQtMainWindow;
import game.engine.ui.qt.elements.DOMButtonElement;
import game.engine.ui.qt.elements.DOMDivElement;
import game.engine.ui.qt.elements.DOMLabelElement;
import game.engine.ui.qt.elements.DOMTextFieldElement;
import game.engine.ui.qt.spacers.DOMVerticalSpacer;
import lombok.val;

public class TestWindow extends DOMQtMainWindow {
    @Override
    protected DOMTemplate body() {
        return
        $(DOMDivElement.class,
            (props -> {
                val p = (DOMQtWidget.DOMQtWidgetProps) props;
                p.layout = ELayoutType.GRID_LAYOUT;
            }),
            $(DOMLabelElement.class,
                (props) -> {
                    val p = (DOMLabelElement.DOMLabelProps) props;
                    p.value = "Username:";
                    p.cell = new int[] {0, 0};
                }
            ),
            $(DOMTextFieldElement.class,
                (props) -> {
                    val p = (DOMQtWidget.DOMQtWidgetProps) props;
                    p.cell = new int[] {0, 1};
                }
            ),
            $(DOMLabelElement.class,
                (props) -> {
                    val p = (DOMLabelElement.DOMLabelProps) props;
                    p.value = "Password:";
                    p.cell = new int[] {1, 0};
                }
            ),
            $(DOMTextFieldElement.class,
                (props) -> {
                    val p = (DOMQtWidget.DOMQtWidgetProps) props;
                    p.cell = new int[] {1, 1};
                }
            ),
            $(DOMButtonElement.class,
                (props) -> {
                    val p = (DOMQtWidget.DOMQtWidgetProps) props;
                    p.cell = new int[] {2, 0};
                }
            ),
            $(TestComponent.class),
            $(DOMVerticalSpacer.class,
                (props) -> {
                    val p = (DOMQtElement.DOMQtElementProps) props;
                    p.cell = new int[] {3, 0};
                }
            )
        );
    }
}
