package game.engine.ui.dom;

import game.engine.ui.dom.elements.DOMButtonElement;
import game.engine.ui.dom.elements.DOMComboBoxElement;
import game.engine.ui.dom.elements.DOMDivElement;
import game.engine.ui.dom.elements.DOMTextFieldElement;
import game.engine.ui.dom.nodes.*;
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
            domBody.getChildren().add(generateDOMElement(domElementChild, domBody));
        }
        return domBody;
    }

    private DOMElement<?> generateDOMElement(Node node, DOMElement<?> parent) {
        DOMElement<?> domElement = newDOMElement(node, parent);
        domElement.setParent(parent);
        NodeList elementNodes = node.getChildNodes();
        for(int i = 0; i < elementNodes.getLength(); i++) {
            Node domElementChild = elementNodes.item(i);
            if(domElementChild.getNodeName().equals("#text")) {
                continue;
            }
            domElement.getChildren().add(generateDOMElement(domElementChild, domElement));
        }
        return domElement;
    }

    private DOMElement<?> newDOMElement(Node node, DOMElement<?> parent) {
        switch (node.getNodeName()) {
            case DOMDivElement.TAG_NAME:
                return new DOMDivElement(node, parent.getHierarchyName());
            case DOMTextFieldElement.TAG_NAME:
                return new DOMTextFieldElement(node, parent.getHierarchyName());
            case DOMButtonElement.TAG_NAME:
                return new DOMButtonElement(node, parent.getHierarchyName());
            case DOMComboBoxElement.TAG_NAME:
                return new DOMComboBoxElement(node, parent.getHierarchyName());
        }
        return null;
    }

}
