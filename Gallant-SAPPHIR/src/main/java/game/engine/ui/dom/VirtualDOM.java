package game.engine.ui.dom;


import game.engine.ui.dom.nodes.DOMDocument;
import lombok.Getter;

public class VirtualDOM {

    @Getter
    private DOMDocument domDocument;

    @Getter
    private final Window window;
    private final static VirtualDOM instance;

    static {
        instance = new VirtualDOM();
    }

    private VirtualDOM() {
        window = new Window();
    }

    public static VirtualDOM getInstance() {
        return instance;
    }

    public void setDomDocument(DOMDocument domDocument) {
        if(this.domDocument != null) throw new RuntimeException("DOM Document is already loaded!");
        this.domDocument = domDocument;
        this.domDocument.pack(window);
    }


    public static int getWindowWidth() {
        //return instance.window.getBounds().width;
        //return instance.window.getWidth();
        return instance.window.width();
    }

    public static int getWindowHeight() {
        //return instance.window.getBounds().height;
        //return instance.window.getHeight();
        return instance.window.height();
    }


}
