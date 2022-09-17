package game.engine.ui.qt.components.widgets;

import game.engine.ui.events.*;
import game.engine.ui.events.management.EventManager;
import game.engine.ui.qt.components.IWidgetComponent;
import io.qt.core.QChildEvent;
import io.qt.core.QEvent;
import io.qt.core.QTimerEvent;
import io.qt.core.Qt;
import io.qt.gui.*;
import io.qt.widgets.QPushButton;

public class Button extends QPushButton implements IWidgetComponent {

    private EventManager eventManager;

    public Button() {
        this.eventManager = new EventManager();
        this.setAttribute(Qt.WidgetAttribute.WA_StyledBackground);
    }

    @Override
    protected void actionEvent(QActionEvent event) {
        getEventManager().fire(EventType.ACTION, new ActionEvent(event));
        super.actionEvent(event);
    }

    @Override
    protected void changeEvent(QEvent event) {
        getEventManager().fire(EventType.CHANGE, new Event<>(event));
        super.changeEvent(event);
    }

    @Override
    protected void closeEvent(QCloseEvent event) {
        getEventManager().fire(EventType.CLOSE, new CloseEvent(event));
        super.closeEvent(event);
    }

    @Override
    protected void contextMenuEvent(QContextMenuEvent event) {
        getEventManager().fire(EventType.CONTEXT_MENU, new ContextMenuEvent(event));
        super.contextMenuEvent(event);
    }

    @Override
    protected void dragEnterEvent(QDragEnterEvent event) {
        getEventManager().fire(EventType.DRAG_ENTER, new DragEnterEvent(event));
        super.dragEnterEvent(event);
    }

    @Override
    protected void dragLeaveEvent(QDragLeaveEvent event) {
        getEventManager().fire(EventType.DRAG_LEAVE, new DragLeaveEvent(event));
        super.dragLeaveEvent(event);
    }

    @Override
    protected void dragMoveEvent(QDragMoveEvent event) {
        getEventManager().fire(EventType.DRAG_MOVE, new DragMoveEvent(event));
        super.dragMoveEvent(event);
    }

    @Override
    protected void dropEvent(QDropEvent event) {
        getEventManager().fire(EventType.DROP, new DropEvent(event));
        super.dropEvent(event);
    }

    @Override
    protected void enterEvent(QEnterEvent event) {
        getEventManager().fire(EventType.ENTER, new EnterEvent(event));
        super.enterEvent(event);
    }

    @Override
    protected void focusInEvent(QFocusEvent event) {
        getEventManager().fire(EventType.FOCUS_IN, new FocusEvent(event));
        super.focusInEvent(event);
    }

    @Override
    protected void focusOutEvent(QFocusEvent event) {
        getEventManager().fire(EventType.FOCUS_OUT, new FocusEvent(event));
        super.focusOutEvent(event);
    }

    @Override
    protected void hideEvent(QHideEvent event) {
        getEventManager().fire(EventType.HIDE, new HideEvent(event));
        super.hideEvent(event);
    }

    @Override
    protected void inputMethodEvent(QInputMethodEvent event) {
        getEventManager().fire(EventType.INPUT_METHOD, new InputMethodEvent(event));
        super.inputMethodEvent(event);
    }

    @Override
    protected void keyPressEvent(QKeyEvent event) {
        getEventManager().fire(EventType.KEY_PRESS, new KeyEvent(event));
        super.keyPressEvent(event);
    }

    @Override
    protected void keyReleaseEvent(QKeyEvent event) {
        getEventManager().fire(EventType.KEY_RELEASE, new KeyEvent(event));
        super.keyReleaseEvent(event);
    }

    @Override
    protected void leaveEvent(QEvent event) {
        getEventManager().fire(EventType.LEAVE, new Event<>(event));
        super.leaveEvent(event);
    }

    @Override
    protected void mouseDoubleClickEvent(QMouseEvent event) {
        getEventManager().fire(EventType.MOUSE_DOUBLE_CLICK, new MouseEvent(event));
        super.mouseDoubleClickEvent(event);
    }

    @Override
    protected void mouseMoveEvent(QMouseEvent event) {
        getEventManager().fire(EventType.MOUSE_MOVE, new MouseEvent(event));
        super.mouseMoveEvent(event);
    }

    @Override
    protected void mousePressEvent(QMouseEvent event) {
        getEventManager().fire(EventType.MOUSE_PRESS, new MouseEvent(event));
        super.mousePressEvent(event);
    }

    @Override
    protected void mouseReleaseEvent(QMouseEvent event) {
        getEventManager().fire(EventType.MOUSE_RELEASE, new MouseEvent(event));
        super.mouseReleaseEvent(event);
    }

    @Override
    protected void moveEvent(QMoveEvent event) {
        getEventManager().fire(EventType.MOVE, new MoveEvent(event));
        super.moveEvent(event);
    }

    @Override
    protected void paintEvent(QPaintEvent event) {
        getEventManager().fire(EventType.PAINT, new PaintEvent(event));
        super.paintEvent(event);
    }

    @Override
    protected void resizeEvent(QResizeEvent event) {
        getEventManager().fire(EventType.RESIZE, new ResizeEvent(event));
        super.resizeEvent(event);
    }

    @Override
    protected void showEvent(QShowEvent event) {
        getEventManager().fire(EventType.SHOW, new ShowEvent(event));
        super.showEvent(event);
    }

    @Override
    protected void tabletEvent(QTabletEvent event) {
        getEventManager().fire(EventType.TABLET, new TabletEvent(event));
        super.tabletEvent(event);
    }

    @Override
    protected void wheelEvent(QWheelEvent event) {
        getEventManager().fire(EventType.WHEEL, new WheelEvent(event));
        super.wheelEvent(event);
    }

    @Override
    protected void childEvent(QChildEvent event) {
        getEventManager().fire(EventType.CHILD, new ChildEvent(event));
        super.childEvent(event);
    }

    @Override
    protected void customEvent(QEvent event) {
        getEventManager().fire(EventType.CUSTOM, new Event<>(event));
        super.customEvent(event);
    }

    @Override
    protected void timerEvent(QTimerEvent event) {
        getEventManager().fire(EventType.TIMER, new TimerEvent(event));
        super.timerEvent(event);
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }


}
