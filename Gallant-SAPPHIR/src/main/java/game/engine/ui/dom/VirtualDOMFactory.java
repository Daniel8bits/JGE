package game.engine.ui.dom;

import game.engine.ui.dom.elements.*;
import game.engine.ui.dom.layouts.DOMGridLayout;
import game.engine.ui.dom.layouts.DOMHorizontalLayout;
import game.engine.ui.dom.layouts.DOMLayout;
import game.engine.ui.dom.layouts.DOMVerticalLayout;
import game.engine.ui.dom.nodes.*;
import game.engine.ui.dom.spacers.DOMHorizontalSpacer;
import game.engine.ui.dom.spacers.DOMSpacer;
import game.engine.ui.dom.spacers.DOMVerticalSpacer;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


@NoArgsConstructor
public class VirtualDOMFactory {

    public VirtualDOM generateVirtualDOM(Document document) {
        VirtualDOM virtualDOM = VirtualDOM.getInstance();

        DOMHeader domHeader = generateDOMHeader(document);
        DOMBody domBody = generateDOMBody(document);

        DOMDocument domDocument = new DOMDocument(document, domHeader, domBody);
        domHeader.setParent(null);
        domBody.setParent(null);

        virtualDOM.setDomDocument(domDocument);

        return virtualDOM;
    }

    private DOMHeader generateDOMHeader(Document document) {
        Node headerNode = document.getElementsByTagName(DOMHeader.TAG_NAME).item(0);
        if(headerNode == null) {
            throw new RuntimeException("No header node found!");
        }
        DOMHeader domHeader = new DOMHeader(headerNode);
        NodeList propertyNodes = headerNode.getChildNodes();
        for(int i = 0; i < propertyNodes.getLength(); i++) {
            DOMProperty domProperty = new DOMProperty(propertyNodes.item(i));
            domProperty.setParent(domHeader);
            domHeader.getChildren().add(domProperty);
        }
        return domHeader;
    }

    private DOMBody generateDOMBody(Document document) {
        Node bodyNode = document.getElementsByTagName(DOMBody.TAG_NAME).item(0);
        if(bodyNode == null) {
            throw new RuntimeException("No body node found!");
        }
        DOMBody domBody = new DOMBody(bodyNode);
        NodeList elementNodes = bodyNode.getChildNodes();
        for(int i = 0; i < elementNodes.getLength(); i++) {
            Node domElementChild = elementNodes.item(i);
            if(domElementChild.getNodeName().equals("#text")) {
                continue;
            }
            domBody.getChildren().add(generateDOMItem(domElementChild, domBody));
        }
        return domBody;
    }

    private DOMItem generateDOMItem(Node node, DOMItem parent) {
        DOMItem domItem = newDOMItem(node, parent);
        domItem.setParent(parent);
        NodeList elementNodes = node.getChildNodes();
        for(int i = 0; i < elementNodes.getLength(); i++) {
            Node domItemChild = elementNodes.item(i);
            if(domItemChild.getNodeName().equals("#text")) {
                continue;
            }
            domItem.getChildren().add(generateDOMItem(domItemChild, domItem));
        }
        return domItem;
    }

    private DOMElement<?> configureDOMElement(DOMElement<?> domElement) {
        String layout = new DOMElementFacade().getAttributeValue(domElement, DOMElementFacade.AttributeEnum.LAYOUT.VALUE);
        if(layout != null) {
            new DOMElementFacade().reduceLayouts(domElement.getComponent(), layout);
        }
        return domElement;
    }
/*
    private DOMLayout<?> generateDOMLayout(Node node, DOMItem parent) {
        return null;
    }

    private DOMSpacer generateDOMSpacer(Node node, DOMItem parent) {
        return null;
    }
*/
    private DOMItem newDOMItem(Node node, DOMItem parent) {
        if(VirtualDOM.getDOMManager().isElement(node.getNodeName())) {
            return configureDOMElement(newDOMElement(node, parent));
        } else if(VirtualDOM.getDOMManager().isLayout(node.getNodeName())) {
            return newDOMLayout(node, parent);
        } else if(VirtualDOM.getDOMManager().isSpacer(node.getNodeName())) {
            return newDOMSpacer(node, parent);
        }
        return null;
    }

    private DOMElement<?> newDOMElement(Node node, DOMItem parent) {
        return switch (node.getNodeName()) {
            case DOMDivElement.TAG_NAME -> new DOMDivElement(node, parent.getHierarchyName());
            case DOMTextFieldElement.TAG_NAME -> new DOMTextFieldElement(node, parent.getHierarchyName());
            case DOMButtonElement.TAG_NAME -> new DOMButtonElement(node, parent.getHierarchyName());
            case DOMComboBoxElement.TAG_NAME -> new DOMComboBoxElement(node, parent.getHierarchyName());
            case DOMLabelElement.TAG_NAME -> new DOMLabelElement(node, parent.getHierarchyName());
            case DOMCheckBoxElement.TAG_NAME -> new DOMCheckBoxElement(node, parent.getHierarchyName());
            default -> null;
        };
    }

    private DOMLayout<?> newDOMLayout(Node node, DOMItem parent) {
        return switch (node.getNodeName()) {
            case DOMHorizontalLayout.TAG_NAME -> new DOMHorizontalLayout(node, parent.getHierarchyName());
            case DOMVerticalLayout.TAG_NAME -> new DOMVerticalLayout(node, parent.getHierarchyName());
            case DOMGridLayout.TAG_NAME -> new DOMGridLayout(node, parent.getHierarchyName());
            default -> null;
        };
    }

    private DOMSpacer newDOMSpacer(Node node, DOMItem parent) {
        return switch (node.getNodeName()) {
            case DOMHorizontalSpacer.TAG_NAME -> new DOMHorizontalSpacer(node, parent.getHierarchyName());
            case DOMVerticalSpacer.TAG_NAME -> new DOMVerticalSpacer(node, parent.getHierarchyName());
            default -> null;
        };
    }

}
