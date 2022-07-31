package game.engine.ui.dom.nodes;

import game.engine.ui.components.IComponent;
import game.engine.ui.events.EventType;
import game.engine.ui.events.MouseEvent;
import game.engine.ui.events.callbacks.IMouseEventCallback;
import game.engine.ui.events.management.EventManager;
import game.engine.ui.events.management.EventRegistry;
import io.qt.core.QList;
import io.qt.core.QObject;
import io.qt.widgets.*;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class DOMElementFacade {

    public enum AttributeEnum {
        LAYOUT("layout"),
        CELL("cell"),
        WIDTH("width"),
        HEIGHT("height");

        public final String VALUE;
        AttributeEnum(String attribute) {
            this.VALUE = attribute;
        }

        public static AttributeEnum reduce(String layout) {
            if(layout.equals(LAYOUT.VALUE)) {
                return LAYOUT;
            } else if(layout.equals(WIDTH.VALUE)) {
                return WIDTH;
            } else if(layout.equals(HEIGHT.VALUE)) {
                return HEIGHT;
            }
            return null;
        }
    }

    public void initializeAttributes(DOMElement<?> element) {
        NamedNodeMap attributes = element.getNode().getAttributes();
        for(int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            reduceAttributes(element, attribute.getNodeName(), attribute.getNodeValue());
        }
    }

    private void reduceAttributes(DOMElement<?> element, String attributeName, String value) {
        if(attributeName.matches("^on[a-zA-Z]+$")) {
            /*
                Dentro do reduceEvents é necessário checar o attribute para
                identificar o tipo de evento
            */
            reduceEvents(element, value);
        }
        AttributeEnum attribute = AttributeEnum.reduce(attributeName);
        if(attribute == null) {
            return;
        }
        switch (attribute) {
            case WIDTH -> setWidth(element, value);
            case HEIGHT -> setHeight(element, value);
        }
    }

    private void setWidth(DOMElement<?> element, String value) {
        if(value.matches("[0-9]+px")) {
            element.getComponent().resize(
                    Integer.parseInt(value.split("px")[0]),
                    element.getComponent().height()
            );
        }
    }

    private void setHeight(DOMElement<?> element, String value) {
        if(value.matches("[0-9]+px")) {
            element.getComponent().resize(
                    element.getComponent().width(),
                    Integer.parseInt(value.split("px")[0])
            );
        }
    }

    public void reduceLayouts(IComponent component, String layoutName) {
        IComponent.LayoutEnum layoutType = IComponent.LayoutEnum.reduce(layoutName);
        QLayout layout = null;
        switch (layoutType) {
            case HORIZONTAL -> layout = new QHBoxLayout();
            case VERTICAL -> layout = new QVBoxLayout();
            case GRID -> layout = new QGridLayout();
        }
        if(layout != null) {
            QList<QObject> children = ((QWidget) component).children();
            for(int i = 0; i < children.count(); i++) {
                layout.addWidget((QWidget) children.get(i));
            }
            children.removeAll(((QWidget) component));
        }
        component.setLayout(layout);
    }



    private void reduceEvents(DOMElement<?> element, String eventName) {
        EventManager eventManager = element.getComponent().getEventManager();
        if(!eventManager.isRegistered(EventType.MOUSE_PRESS)) {
            eventManager.register(EventType.MOUSE_PRESS, getCallback(element, eventName), new EventRegistry<MouseEvent>());
        } else {
            eventManager.register(EventType.MOUSE_PRESS, getCallback(element, eventName));
        }
    }

    // just for test
    @SuppressWarnings("unchecked")
    private IMouseEventCallback getCallback(DOMElement<?> element, String eventName) {
        Class<DOMElement> elementClass = (Class<DOMElement>) element.getClass();
        Method method;

        try {
            method = elementClass.getMethod(eventName);
            if(!Modifier.toString(method.getModifiers()).equals(Modifier.toString(Modifier.PUBLIC))) {
                throw new NoSuchMethodException();
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return mouseEvent -> {
            try {
                method.invoke(element);
            }catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public boolean hasAttribute(DOMItem element, String attributeName) {
        NamedNodeMap attributes = element.getNode().getAttributes();
        for(int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            if(attribute.getNodeName().equals(attributeName)) {
                return true;
            }
        }
        return false;
    }

    public String getAttributeValue(DOMItem element, String attributeName) {
        NamedNodeMap attributes = element.getNode().getAttributes();
        for(int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            if(attribute.getNodeName().equals(attributeName)) {
                return attribute.getNodeValue();
            }
        }
        return null;
    }

    public int[] getCell(DOMItem element) {
        int[] cell = null;
        String value = getAttributeValue(element, AttributeEnum.CELL.VALUE);
        if(value != null && value.matches("^\\d+,\\s*\\d+((,\\s*\\d){2})?$")) {

            String[] values = value.split(",");
            int row = Integer.parseInt(values[0].trim());
            int column = Integer.parseInt(values[1].trim());
            int rowSpan = 1;
            int columnSpan = 1;
            if(values.length > 2) {
                rowSpan = Integer.parseInt(values[2].trim());
                columnSpan = Integer.parseInt(values[3].trim());
            }
            cell = new int[] {row, column, rowSpan, columnSpan};

        }
        return cell;
    }



/*
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
*/
}
