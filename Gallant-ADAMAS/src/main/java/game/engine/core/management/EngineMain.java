package game.engine.core.management;

import game.engine.core.management.scenes.SceneManager;
import lombok.Getter;

public abstract class EngineMain {

    @Getter
    private SceneManager sceneManager;

    public EngineMain() {
        this.sceneManager = new SceneManager();
    }

    public abstract void start();

    public void update(float delta) {
        this.sceneManager.update(delta);
    }

    public void render() {
        this.sceneManager.render();
    }

    public abstract void finish();

}
