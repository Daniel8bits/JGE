package game.engine.ui.dom.layouts;

import game.engine.ui.dom.elements.DOMElement;
import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.dom.spacers.DOMSpacer;
import game.engine.ui.framework.interfaces.IProps;
import game.engine.ui.framework.interfaces.IStates;
import io.qt.widgets.QLayout;
import io.qt.widgets.QWidget;
import lombok.Getter;
import org.w3c.dom.Node;

public class DOMLayout<T extends QLayout> extends DOMItem {

    public final static String PREFIX = "layout-";

    @Getter
    private T layout;

    public DOMLayout(T layout, Node node, String hierarchyName) {
        super(node, hierarchyName);
        this.layout = layout;
    }

    @Override
    public void pack() {
        getChildren().forEach(domItem -> {
            domItem.pack();
            if(domItem instanceof DOMElement<?>) {
                DOMElement<?> domElement = (DOMElement<?>) domItem;
                getLayout().addWidget((QWidget) domElement.getComponent());
            } else if (domItem instanceof DOMLayout<?>) {
                DOMLayout<?> domLayout = (DOMLayout<?>) domItem;
                getLayout().addItem(domLayout.getLayout());
            } else if (domItem instanceof DOMSpacer) {
                DOMSpacer domSpacer = (DOMSpacer) domItem;
                getLayout().addItem(domSpacer.getSpacer());
            }
        });
    }

    @Override
    protected void whenMounted() {

    }

    @Override
    protected void whenUpdated(IProps previousProps, IStates previousStates) {

    }

    @Override
    protected void whenUnmounted() {

    }

}
