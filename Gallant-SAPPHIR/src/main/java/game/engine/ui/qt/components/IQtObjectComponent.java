package game.engine.ui.qt.components;

import game.engine.ui.dom.IComponent;
import io.qt.core.QObject;

public interface IQtObjectComponent extends IComponent {
    QObject parent();
}
