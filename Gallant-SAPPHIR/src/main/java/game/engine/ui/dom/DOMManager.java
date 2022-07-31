package game.engine.ui.dom;

import game.engine.ui.dom.elements.*;
import game.engine.ui.dom.layouts.DOMGridLayout;
import game.engine.ui.dom.layouts.DOMHorizontalLayout;
import game.engine.ui.dom.layouts.DOMVerticalLayout;
import game.engine.ui.dom.spacers.DOMHorizontalSpacer;
import game.engine.ui.dom.spacers.DOMVerticalSpacer;

import java.util.Set;
import java.util.TreeSet;

public class DOMManager {

    private Set<String> elements;
    private Set<String> layouts;
    private Set<String> spacers;

    public DOMManager() {
        this.elements = new TreeSet<>();
        this.layouts = new TreeSet<>();
        this.spacers = new TreeSet<>();

        loadElements();
        loadLayouts();
        loadSpacers();
    }

    private void loadElements() {
        elements.add(DOMButtonElement.TAG_NAME);
        elements.add(DOMComboBoxElement.TAG_NAME);
        elements.add(DOMDivElement.TAG_NAME);
        elements.add(DOMTextFieldElement.TAG_NAME);
        elements.add(DOMLabelElement.TAG_NAME);
        elements.add(DOMCheckBoxElement.TAG_NAME);
    }

    private void loadLayouts() {
        layouts.add(DOMHorizontalLayout.TAG_NAME);
        layouts.add(DOMVerticalLayout.TAG_NAME);
        layouts.add(DOMGridLayout.TAG_NAME);
    }

    private void loadSpacers() {
        spacers.add(DOMHorizontalSpacer.TAG_NAME);
        spacers.add(DOMVerticalSpacer.TAG_NAME);
    }

    public boolean isElement(String nodeName) {
        return elements.contains(nodeName);
    }

    public boolean isLayout(String nodeName) {
        return layouts.contains(nodeName);
    }

    public boolean isSpacer(String nodeName) {
        return spacers.contains(nodeName);
    }

}
