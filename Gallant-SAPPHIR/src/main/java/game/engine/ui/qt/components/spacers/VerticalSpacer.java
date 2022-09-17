package game.engine.ui.qt.components.spacers;

import game.engine.ui.qt.components.ISpacerComponent;
import io.qt.widgets.QSizePolicy;
import io.qt.widgets.QSpacerItem;

public class VerticalSpacer extends QSpacerItem implements ISpacerComponent {
    public VerticalSpacer(int w, int h) {
        super(w, h, QSizePolicy.Policy.Fixed, QSizePolicy.Policy.Expanding);
    }
}
