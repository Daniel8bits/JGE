package game.engine.core.entities;

import game.engine.appearance.Material;
import game.engine.buffer.VAO;
import game.engine.core.scenes.Scene;
import game.engine.maths.Transform;
import game.engine.rendering.Renderer;
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
