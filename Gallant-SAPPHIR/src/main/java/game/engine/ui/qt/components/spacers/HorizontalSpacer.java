package game.engine.ui.qt.components.spacers;

import game.engine.ui.qt.components.ISpacerComponent;
import io.qt.widgets.QSizePolicy;
import io.qt.widgets.QSpacerItem;

public class HorizontalSpacer extends QSpacerItem implements ISpacerComponent {
    public HorizontalSpacer(int w, int h) {
        super(w, h, QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Fixed);
    }
}
