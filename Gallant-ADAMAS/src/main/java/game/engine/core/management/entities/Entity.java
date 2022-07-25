package game.engine.core.management.entities;

import game.engine.core.appearance.Material;
import game.engine.core.buffer.VAO;
import game.engine.core.management.scenes.Scene;
import game.engine.core.maths.Transform;
import game.engine.core.rendering.Renderer;
import lombok.Getter;

@Getter
public abstract class Entity {

    private String name;
    private Transform transform;
    private VAO vao;
    private Material material;
    private Renderer renderer;

    public Entity(String name, VAO vao, Material material, Renderer renderer) {
        this.name = name;
        this.transform = new Transform();
        this.vao = vao;
        this.material = material;
        this.renderer = renderer;
    }

    public abstract void update(float delta, Scene scene);


}
