package game.engine.ui.test;

import game.engine.ui.dom.Sapphire;
import game.engine.ui.qt.ELayoutType;
import game.engine.ui.qt.containers.DOMQtMainWindow;
import io.qt.widgets.QApplication;
import lombok.val;

public class UITest {

    private TestWindow testWindow;

    public UITest() {
        init();
    }

    private void init() {

        //Arrays.stream(Module.).forEach(System.out::println);

        //Document document = new XMLParser().parse("/it.is.just.a.test/TheTest.xml");
        //virtualDOM = new VirtualDOMFactory().generateVirtualDOM(document);

        testWindow = (TestWindow) new Sapphire().createRoot(TestWindow.class, (props) -> {
            val p = (DOMQtMainWindow.DOMQtMainWindowProps) props;
            p.layout = ELayoutType.HORIZONTAL_LAYOUT;
            p.windowWidth = 800;
            p.windowHeight = 500;
        });

    }

    public static void main(String[] args) {
        QApplication.initialize(args);
        new UITest();
        QApplication.exec();
        QApplication.shutdown();
        //new UITest();
    }
}
