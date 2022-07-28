package game.engine.ui.layout;

import game.engine.ui.dom.VirtualDOM;
import game.engine.ui.dom.nodes.DOMElement;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;

public class DOMLayout implements LayoutManager {

    private DOMElement<?> element;

    public DOMLayout(DOMElement<?> element) {
        this.element = element;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getPreferredSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(10, 10);
    }

    @Override
    public void layoutContainer(Container parent) {

        log(parent);

        int width = parent.getWidth();
        int height = parent.getHeight();

        if(element == null && parent.getComponentCount() > 0) {
            parent.getComponent(0).setBounds(0, 0, width, height);
            return;
        }

        int componentCount = parent.getComponentCount();
        for(int i = 0; i < componentCount; i++) {
            Component child = parent.getComponent(i);
            if(child instanceof JPanel) {
                child.setBounds(0, 0, (width/100) * 60, (height/100) * 60);
            } else if (child instanceof JButton) {
                child.setBounds(0, 0, 100, 32);
            }
        }

    }

    private void log(Container parent) {
        StringBuilder tabs = new StringBuilder();
        int depth = 0;
        Component p = parent.getParent();
        while(p != null) {
            tabs.append("\t");
            depth++;
            p = p.getParent();
        }
        String identifier = element == null ? "jframe" : element.getHierarchyName();
        System.out.println(tabs + "component#" + depth + ": " + identifier);
        System.out.println();
    }
}
