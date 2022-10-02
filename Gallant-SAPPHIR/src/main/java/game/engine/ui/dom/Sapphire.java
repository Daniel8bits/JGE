package game.engine.ui.dom;

import game.engine.ui.dom.nodes.DOMAtomicElement;
import game.engine.ui.dom.nodes.DOMContainer;
import game.engine.ui.dom.nodes.DOMElement;
import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.interfaces.IProps;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class Sapphire {

    private DOMElement rootComponent;
    private DOMAtomicElement originalTree;
    private DOMItem diffingTree;

    /*
    private void startRendering(DOMTemplate template) {
        rootComponent = newDOMElement(template, newProps(template), template.getType().getSimpleName());
        originalTree = rootComponent.getAtomicElements().get(0);
        originalTree.callWhenMounted();
    }
    */

    public DOMContainer<?> createRoot(Class<? extends DOMContainer> containerClass, Consumer<IProps> props) {
        DOMContainer<?> container = null;
        try {
            container = (DOMContainer<?>) containerClass.getConstructors()[0].newInstance();
            container.init(newProps(containerClass, props, new DOMTemplate[0]), containerClass.getSimpleName());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return container;
    }

    public DOMElement createElement(DOMTemplate template) {
        return newDOMElement(template, newProps(template), template.getType().getSimpleName());
    }

    public List<DOMElement> createElements(List<DOMTemplate> templates) {
        ArrayList<DOMElement> elements = new ArrayList<>();
        templates.forEach(t -> {
            if(t == null) return;
            elements.add(newDOMElement(t, newProps(t), t.getType().getSimpleName()));
        });
        return elements;
    }

    /*****************************
        SHADOWDOM GENERATION
    ******************************/

    private DOMElement generateDOMElement(DOMTemplate template, String hierarchyName) {
        DOMElement item = newDOMElement(template, newProps(template), hierarchyName);
        Arrays.stream(template.getChildren())
                .forEach(child -> {
                    item.getChildren().add(generateDOMElement(child, template.getType().getSimpleName()));
                });
        return item;
    }

    private DOMElement newDOMElement(DOMTemplate template, IProps props, String hierarchyName) {
        DOMElement domElement = null;
        try {
            domElement = (DOMElement) template.getType().getConstructors()[0].newInstance();
            domElement.init(props, hierarchyName);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return domElement;
    }

    private IProps newProps(DOMTemplate template) {
        return newProps(template.getType(), template.getProps(), template.getChildren());
    }

    private IProps newProps(Class<? extends DOMItem> type, Consumer<IProps> propsConsumer, DOMTemplate[] children) {
        AtomicReference<IProps> props = new AtomicReference<>();

        Arrays.stream(type.getClasses())
                .filter(clazz -> Arrays.stream(clazz.getAnnotations()).anyMatch(annotation -> annotation instanceof Props))
                .findFirst()
                .ifPresent(propsClass -> {
                    try {
                        IProps p = (IProps) propsClass.getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                        if(propsConsumer != null) propsConsumer.accept(p);
                        p.setChildren(children != null ? Arrays.asList(children) : new ArrayList<>());
                        props.set(p);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                });

        return props.get();
    }

}
