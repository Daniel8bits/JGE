package game.engine.ui.dom.nodes;

import lombok.*;
import org.w3c.dom.Node;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;


public abstract class DOMNode<T extends DOMNode<?>> {

    private Node node;
    @Getter
    private List<T> children;
    @Getter
    @Setter
    private DOMNode<?> parent;

    public DOMNode(Node node) {
        this.node = node;
        this.children = new ArrayList<>();
    }

    protected Node getNode() {
        return node;
    }

}
