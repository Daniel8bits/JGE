package game.engine.ui.components;

import game.engine.ui.events.EventType;
import game.engine.ui.events.MouseEvent;
import game.engine.ui.events.management.EventManager;
import io.qt.core.QChildEvent;
import io.qt.core.QEvent;
import io.qt.core.QTimerEvent;
import io.qt.core.Qt;
import io.qt.gui.*;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QWidget;

public class MainWindow extends QMainWindow implements IComponent {

    private EventManager eventManager;

    public MainWindow() {
        this.eventManager = new EventManager();
        this.setAttribute(Qt.WidgetAttribute.WA_StyledBackground);
    }

    @Override
    protected void actionEvent(QActionEvent event) {
        super.actionEvent(event);
    }

    @Override
    protected void changeEvent(QEvent arg__1) {
        super.changeEvent(arg__1);
    }

    @Override
    protected void closeEvent(QCloseEvent event) {
        super.closeEvent(event);
    }

    @Override
    protected void contextMenuEvent(QContextMenuEvent event) {
        super.contextMenuEvent(event);
    }

    @Override
    protected void dragEnterEvent(QDragEnterEvent event) {
        super.dragEnterEvent(event);
    }

    @Override
    protected void dragLeaveEvent(QDragLeaveEvent event) {
        super.dragLeaveEvent(event);
    }

    @Override
    protected void dragMoveEvent(QDragMoveEvent event) {
        super.dragMoveEvent(event);
    }

    @Override
    protected void dropEvent(QDropEvent event) {
        super.dropEvent(event);
    }

    @Override
    protected void enterEvent(QEnterEvent event) {
        super.enterEvent(event);
    }

    @Override
    protected void focusInEvent(QFocusEvent event) {
        super.focusInEvent(event);
    }

    @Override
    protected void focusOutEvent(QFocusEvent event) {
        super.focusOutEvent(event);
    }

    @Override
    protected void hideEvent(QHideEvent event) {
        super.hideEvent(event);
    }

    @Override
    protected void inputMethodEvent(QInputMethodEvent arg__1) {
        super.inputMethodEvent(arg__1);
    }

    @Override
    protected void keyPressEvent(QKeyEvent event) {
        super.keyPressEvent(event);
    }

    @Override
    protected void keyReleaseEvent(QKeyEvent event) {
        super.keyReleaseEvent(event);
    }

    @Override
    protected void leaveEvent(QEvent event) {
        super.leaveEvent(event);
    }

    @Override
    protected void mouseDoubleClickEvent(QMouseEvent event) {
        super.mouseDoubleClickEvent(event);
    }

    @Override
    protected void mouseMoveEvent(QMouseEvent event) {
        super.mouseMoveEvent(event);
    }

    @Override
    protected void mousePressEvent(QMouseEvent event) {
        getEventManager().fire(EventType.MOUSE_PRESS, new MouseEvent(event));
        super.mousePressEvent(event);
    }

    @Override
    protected void mouseReleaseEvent(QMouseEvent event) {
        super.mouseReleaseEvent(event);
    }

    @Override
    protected void moveEvent(QMoveEvent event) {
        super.moveEvent(event);
    }

    @Override
    protected void paintEvent(QPaintEvent event) {
        super.paintEvent(event);
    }

    @Override
    protected void resizeEvent(QResizeEvent event) {
        super.resizeEvent(event);
    }

    @Override
    protected void showEvent(QShowEvent event) {
        super.showEvent(event);
    }

    @Override
    protected void tabletEvent(QTabletEvent event) {
        super.tabletEvent(event);
    }

    @Override
    protected void wheelEvent(QWheelEvent event) {
        super.wheelEvent(event);
    }

    @Override
    protected void childEvent(QChildEvent event) {
        super.childEvent(event);
    }

    @Override
    protected void customEvent(QEvent event) {
        super.customEvent(event);
    }

    @Override
    protected void timerEvent(QTimerEvent event) {
        super.timerEvent(event);
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }

}
