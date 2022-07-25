package game.engine.core.management;

import lombok.Getter;

@Getter
public class EngineCore {

    private WindowManager windowManager;
    private EngineLoop engineLoop;
    private EngineMain engineMain;
    private InputManager inputManager;

    public EngineCore(WindowManager windowManager, EngineMain engineMain) {
        this.engineMain = engineMain;
        this.windowManager = windowManager;
        this.engineLoop = new EngineLoop(this);
        this.inputManager = new InputManager(windowManager);
    }

    public void start() {
        this.windowManager.create();
        this.inputManager.start();
        this.engineLoop.start();
    }

}
