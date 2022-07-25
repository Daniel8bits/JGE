package game.engine.ui.test;

import game.engine.ui.dom.VirtualDOM;
import game.engine.ui.dom.VirtualDOMFactory;
import game.engine.ui.dom.nodes.DOMElement;
import game.engine.ui.dom.nodes.DOMNode;
import game.engine.ui.parser.XMLParser;
import org.w3c.dom.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;

public class UITest {

    private VirtualDOM virtualDOM;

    public UITest() {
        init();
    }

    private void init() {

        Document document = new XMLParser().parse("/it.is.just.a.test/TheTest.xml");
        virtualDOM = new VirtualDOMFactory().generateVirtualDOM(document);

    }

    public static void main(String[] args) {
        new UITest();
    }
}
