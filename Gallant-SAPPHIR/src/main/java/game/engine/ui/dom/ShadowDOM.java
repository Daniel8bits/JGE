package game.engine.ui.dom;

import game.engine.ui.dom.elements.*;
import game.engine.ui.dom.layouts.DOMGridLayout;
import game.engine.ui.dom.layouts.DOMHorizontalLayout;
import game.engine.ui.dom.layouts.DOMLayout;
import game.engine.ui.dom.layouts.DOMVerticalLayout;
import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.dom.spacers.DOMHorizontalSpacer;
import game.engine.ui.dom.spacers.DOMSpacer;
import game.engine.ui.dom.spacers.DOMVerticalSpacer;
import game.engine.ui.framework.annotations.Props;
import game.engine.ui.framework.interfaces.IProps;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class ShadowDOM {

    private DOMTemplate domTemplate;
    private DOMItem root;

    public ShadowDOM(DOMTemplate domTemplate) {
        this.domTemplate = domTemplate;
        root = generateDOMItem(domTemplate, domTemplate.getType().getSimpleName());
    }

    public void pack() {

    }

    private DOMItem generateDOMItem(DOMTemplate template, String hierarchyName) {
        DOMItem item = newDOMItem(template, newProps(template), hierarchyName);
        Arrays.stream(template.getChildren())
                .forEach(child -> {
                    item.getChildren().add(generateDOMItem(child, template.getType().getSimpleName()));
                });
        return item;
    }

    private DOMItem newDOMItem(DOMTemplate template, IProps props, String hierarchyName) {
        Class<? extends DOMItem> type = getBaseClassOf(template);
        if (type == DOMElement.class) {
            return newDOMElement(template, props, hierarchyName);
        } else if (type == DOMLayout.class) {
            return newDOMLayout(template, props, hierarchyName);
        } else if (type == DOMSpacer.class) {
            return newDOMSpacer(template, props, hierarchyName);
        }
        return null;
    }

    private DOMElement<?> newDOMElement(DOMTemplate template, IProps props, String hierarchyName) {
        if (template.getType() == DOMDivElement.class) {
            return new DOMDivElement(props, null, hierarchyName);
        } else if (template.getType() == DOMTextFieldElement.class) {
            return new DOMTextFieldElement(props, null, hierarchyName);
        } else if (template.getType() == DOMButtonElement.class) {
            return new DOMButtonElement(props, null, hierarchyName);
        } else if (template.getType() == DOMComboBoxElement.class) {
            return new DOMComboBoxElement(props, null, hierarchyName);
        } else if (template.getType() == DOMLabelElement.class) {
            return new DOMLabelElement(props, null, hierarchyName);
        } else if (template.getType() == DOMCheckBoxElement.class) {
            return new DOMCheckBoxElement(props, null, hierarchyName);
        }
        return null;
    }

    private DOMLayout<?> newDOMLayout(DOMTemplate template, IProps props, String hierarchyName) {
        if (template.getType() == DOMHorizontalLayout.class) {
            return new DOMHorizontalLayout(props, null, hierarchyName);
        } else if (template.getType() == DOMVerticalLayout.class) {
            return new DOMVerticalLayout(props, null, hierarchyName);
        } else if (template.getType() == DOMGridLayout.class) {
            return new DOMGridLayout(props, null, hierarchyName);
        }
        return null;
    }

    private DOMSpacer newDOMSpacer(DOMTemplate template, IProps props, String hierarchyName) {
        if (template.getType() == DOMHorizontalSpacer.class) {
            return new DOMHorizontalSpacer(props, null, hierarchyName);
        } else if (template.getType() == DOMVerticalSpacer.class) {
            return new DOMVerticalSpacer(props, null, hierarchyName);
        }
        return null;
    }

    private Class<? extends DOMItem> getBaseClassOf(DOMTemplate template) {
        Class<?> superClass = template.getType();
        while(superClass.getGenericSuperclass() != DOMItem.class) {
            superClass = (Class<?>) superClass.getGenericSuperclass();
        }
        return (Class<? extends DOMItem>) superClass;
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
