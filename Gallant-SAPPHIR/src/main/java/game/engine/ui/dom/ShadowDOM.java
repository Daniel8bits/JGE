package game.engine.ui.dom;

import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.interfaces.IProps;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class ShadowDOM {

    private DOMItem rootComponent;
    private DOMItem originalTree;
    private DOMItem diffingTree;

    private static ShadowDOM instance;

    private ShadowDOM() {
    }

    public static ShadowDOM getInstance() {
        if(instance == null) {
            instance = new ShadowDOM();
        }
        return instance;
    }

    public static void render(DOMTemplate domTemplate) {
        getInstance().startRendering(domTemplate);
    }

    private void startRendering(DOMTemplate template) {
        //root = generateDOMItem(domTemplate, domTemplate.getType().getSimpleName());
        rootComponent = newDOMItem(template, newProps(template), template.getType().getSimpleName());
    }

    public static DOMItem createItem(DOMTemplate template) {
        return getInstance().newDOMItem(template, getInstance().newProps(template), template.getType().getSimpleName());
    }

    /*****************************
        SHADOWDOM GENERATION
    ******************************/

    private DOMItem generateDOMItem(DOMTemplate template, String hierarchyName) {
        DOMItem item = newDOMItem(template, newProps(template), hierarchyName);
        Arrays.stream(template.getChildren())
                .forEach(child -> {
                    item.getChildren().add(generateDOMItem(child, template.getType().getSimpleName()));
                });
        return item;
    }

    private DOMItem newDOMItem(DOMTemplate template, IProps props, String hierarchyName) {
        DOMItem domItem = null;
        try {
            domItem = (DOMItem) template.getType().getConstructors()[0].newInstance(props, hierarchyName);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return domItem;
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
