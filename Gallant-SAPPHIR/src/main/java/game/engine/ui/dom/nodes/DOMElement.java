package game.engine.ui.dom.nodes;

import game.engine.ui.dom.VirtualDOM;
import lombok.Getter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class DOMElement<T extends JComponent> extends DOMNode<DOMElement<?>> {

    @Getter
    private T component;

    public DOMElement(T component, Node node) {
        super(node);
        this.component = component;
    }

    public void setAttribute(String name, Object value, Class<?>... parameterTypes) {
        Class<T> componentClass = (Class<T>) component.getClass();
        try {

            Method method = componentClass.getMethod(name, parameterTypes);
            if(!Modifier.toString(method.getModifiers()).equals(Modifier.toString(Modifier.PUBLIC))) {
                throw new NoSuchMethodException();
            }

            method.invoke(component, value);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        this.getChildren().forEach(DOMElement::update);
    }

    void pack() {
        this.getChildren().forEach(domElement -> {
            domElement.pack();
            component.add(domElement.getComponent());
        });
    }

    protected void initializeComponent() {
        component.setLayout(null);
        calculateBounds();
        this.getChildren().forEach(DOMElement::initializeComponent);
    }

    protected void calculateBounds() {
        this.getComponent().setBounds(0, 0, VirtualDOM.getWindowWidth(), VirtualDOM.getWindowHeight());
    }

    protected void recalculateBounds() {
        calculateBounds();
        this.getChildren().forEach(DOMElement::recalculateBounds);
    }

    public DOMElement<?> getParentAsElement() {
        return (DOMElement<?>) this.getParent();
    }

}
