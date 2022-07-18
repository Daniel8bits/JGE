package game.engine.rendering;

import game.engine.appearance.Material;
import game.engine.appearance.Shader;
import game.engine.core.Camera;
import game.engine.core.entities.Entity;
import game.engine.core.scenes.Scene;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public abstract class Renderer {

    private String name;
    private Scene currentScene;
    @Setter
    private Camera camera;

    protected int maximumRenderDistance;

    public Renderer(String name, Camera camera) {
        this.name = name;
        this.camera = camera;
        this.maximumRenderDistance = -1;
    }

    public abstract void render();

    private List<Entity> getEntitiesByMaterial(Material material) {
        return this.currentScene.filterVisible(entity ->
                entity.getRenderer() != null &&
                entity.getName().equals(name) &&
                entity.getMaterial() != null &&
                entity.getMaterial().getName().equals(material.getName())
        );
    }

    void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

}
