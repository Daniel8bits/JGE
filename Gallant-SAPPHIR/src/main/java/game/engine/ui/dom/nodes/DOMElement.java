package game.engine.ui.dom.nodes;

import game.engine.ui.components.Component;
import game.engine.ui.dom.VirtualDOM;
import game.engine.ui.layout.DOMLayout;
import io.qt.widgets.QWidget;
import lombok.Getter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class DOMElement<T extends Component<?>> extends DOMNode<DOMElement<?>> {

    @Getter
    private String hierarchyName;

    @Getter
    private T component;

    public DOMElement(T component, Node node, String hierarchyName) {
        super(node);
        this.component = component;
        this.hierarchyName = hierarchyName;
        //component.setLayout(new DOMLayout(this));
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
        initializeAttributes();
        System.out.println(hierarchyName);
        this.getChildren().forEach(domElement -> {
            domElement.pack();
            component.add(domElement.component);
        });
    }

    protected void initializeComponent() {

        //calculateBounds();
        this.getChildren().forEach(DOMElement::initializeComponent);
    }

    private void initializeAttributes() {
        NamedNodeMap attributes = getNode().getAttributes();
        for(int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            reduceAttributes(attribute.getNodeName(), attribute.getNodeValue());
        }
    }

    protected void reduceAttributes(String attribute, String value) {
        switch (attribute) {
            case "layout":
                component.setLayout(value);
                break;
        }
    }

    protected void calculateBounds() {
        this.component.getQWidget().move(0, 0);
        this.component.getQWidget().resize(VirtualDOM.getWindowWidth(), VirtualDOM.getWindowHeight());
    }

    protected void recalculateBounds() {
        calculateBounds();
        this.getChildren().forEach(DOMElement::recalculateBounds);
    }

    public DOMElement<?> getParentAsElement() {
        return (DOMElement<?>) this.getParent();
    }

}
