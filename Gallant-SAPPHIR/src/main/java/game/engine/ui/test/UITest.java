package game.engine.ui.test;

import game.engine.ui.dom.VirtualDOM;
import game.engine.ui.dom.VirtualDOMFactory;
import game.engine.ui.parser.XMLParser;
import io.qt.widgets.QApplication;
import io.qt.widgets.QMessageBox;
import org.w3c.dom.Document;

import java.util.Arrays;

public class UITest {

    private VirtualDOM virtualDOM;

    public UITest() {
        init();
    }

    private void init() {

        //Arrays.stream(Module.).forEach(System.out::println);

        Document document = new XMLParser().parse("/it.is.just.a.test/TheTest.xml");
        virtualDOM = new VirtualDOMFactory().generateVirtualDOM(document);

    }

    public static void main(String[] args) {
        QApplication.initialize(args);
        new UITest();
        QApplication.exec();
        QApplication.shutdown();
        //new UITest();
    }
}
