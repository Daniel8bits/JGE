package game.test.entities;

import game.engine.core.appearance.Material;
import game.engine.core.buffer.VAO;
import game.engine.core.management.database.ResourceDatabase;
import game.engine.core.management.entities.Entity;
import game.engine.core.management.scenes.Scene;
import game.engine.core.rendering.Renderer;

public class TestEntity extends Entity {

    private boolean spin;

    public TestEntity(String name, Renderer renderer, boolean spin) {
        super(
                name,
                ResourceDatabase.getVAOCollection().getResource("cube", VAO.class),
                ResourceDatabase.getMaterialCollection().getResource("test-material", Material.class),
                renderer
        );
        this.spin = spin;
    }

    @Override
    public void update(float delta, Scene scene) {
        if(!spin) return;
        this.getTransform().getGlobalTransform().getRotation().y += 50 * delta;

    }
}
