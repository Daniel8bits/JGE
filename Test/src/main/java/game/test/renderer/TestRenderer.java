package game.test.renderer;

import game.engine.core.appearance.Shader;
import game.engine.core.management.Camera;
import game.engine.core.gl.GLUtils;
import game.engine.core.rendering.Renderer;
import game.test.entities.TestEntity;
import org.joml.Matrix4f;

public class TestRenderer extends Renderer {

    private Matrix4f projection;

    public TestRenderer(String name, Camera camera) {
        super(name, camera);
        this.projection = new Matrix4f()
                .perspective(
                        (float) Math.toRadians(70),
                        1,
                        1,
                        1000
                );
    }

    @Override
    public void render() {

        TestEntity testEntity = (TestEntity) this.getCurrentScene().get("test-entity_1");
        TestEntity testEntity2 = (TestEntity) this.getCurrentScene().get("test-entity_2");

        testEntity.getVao().bind();
        testEntity.getMaterial().bind();
        Shader shader = testEntity.getMaterial().getShader();

        // ENTITY 1
        shader.setUniform("u_model", testEntity.getTransform().worldMatrix());
        shader.setUniform("u_view", this.getCamera().getView());
        shader.setUniform("u_projection", this.projection);

        GLUtils.draw(testEntity.getVao().getVertexAmount());


        // ENTITY 2
        shader.setUniform("u_model", testEntity2.getTransform().worldMatrix());
        shader.setUniform("u_view", this.getCamera().getView());
        shader.setUniform("u_projection", this.projection);

        GLUtils.draw(testEntity2.getVao().getVertexAmount());



        testEntity.getVao().unbind();

        testEntity.getMaterial().unbind();

    }
}
