package game.engine.ui.qt;

public interface IDOMQtElementHandleLayout {
    void addChild(DOMQtWidget<?> domQtElement, int[] cell);
    void addChild(DOMQtLayout<?> domQtElement, int[] cell);
    void addChild(DOMQtSpacer<?> domQtElement, int[] cell);


}
