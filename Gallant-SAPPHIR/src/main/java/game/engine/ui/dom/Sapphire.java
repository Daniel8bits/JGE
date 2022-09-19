package game.engine.ui.dom;

import game.engine.ui.dom.nodes.DOMAtomicElement;
import game.engine.ui.dom.nodes.DOMElement;
import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.interfaces.IProps;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Sapphire {

    private DOMElement rootComponent;
    private DOMAtomicElement originalTree;
    private DOMItem diffingTree;

    private static Sapphire instance;

    private Sapphire() {
    }

    public static Sapphire getInstance() {
        if(instance == null) {
            instance = new Sapphire();
        }
        return instance;
    }

    public static void render(DOMTemplate domTemplate) {
        getInstance().startRendering(domTemplate);
    }

    private void startRendering(DOMTemplate template) {
        rootComponent = newDOMElement(template, newProps(template), template.getType().getSimpleName());
        originalTree = rootComponent.getAtomicElements().get(0);
    }

    public static DOMElement createElement(DOMTemplate template) {
        return getInstance().newDOMElement(template, getInstance().newProps(template), template.getType().getSimpleName());
    }

    public static List<DOMElement> createElements(List<DOMTemplate> templates) {
        Sapphire sapphire = getInstance();
        ArrayList<DOMElement> elements = new ArrayList<>();
        templates.forEach(t -> {
            elements.add(sapphire.newDOMElement(t, sapphire.newProps(t), t.getType().getSimpleName()));
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
            domElement = (DOMElement) template.getType().getConstructors()[0].newInstance(props, hierarchyName);
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
        AtomicReference<IProps> props = null;

        Arrays.stream(template.getType().getClasses())
                .filter(clazz -> Arrays.stream(clazz.getAnnotations()).anyMatch(annotation -> annotation instanceof Props))
                .findFirst()
                .ifPresent(propsClass -> {
                    try {
                        IProps p = (IProps) propsClass.getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                        template.getProps().accept(p);
                        p.setChildren(Arrays.asList(template.getChildren()));
                        props.set(p);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                });

        return props.get();
    }

}
