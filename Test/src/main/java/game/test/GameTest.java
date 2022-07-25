package game.test;

import game.engine.core.appearance.DefaultMaterial;
import game.engine.core.appearance.Material;
import game.engine.core.appearance.Shader;
import game.engine.core.buffer.VAO;
import game.engine.core.management.EngineCore;
import game.engine.core.management.EngineMain;
import game.engine.core.management.WindowManager;
import game.engine.core.management.database.ResourceDatabase;
import game.engine.core.management.scenes.Scene;
import game.engine.core.loader.OBJLoader;
import game.test.entities.TestEntity;
import game.test.entities.ViewportCameraEntity;
import game.test.renderer.TestRenderer;

import java.awt.*;

public class GameTest extends EngineMain {

    @Override
    public void start() {

        String vaoPath = "/home/daniel/Projetos/razor-interface/public/resources/objects/cube/cube.obj";

        ResourceDatabase.getVAOCollection()
                .register(new OBJLoader().load(vaoPath))
                .stream()
                .forEach(vao -> ((VAO) vao).create());

        Shader testShader = new Shader(
                "test-shader",
                "/test/shaders/test.vert.glsl",
                "/test/shaders/test.frag.glsl"
        );

        ResourceDatabase.getShaderCollection().register(testShader);

        ResourceDatabase.getMaterialCollection()
                .register(new DefaultMaterial("test-material", testShader))
                .stream()
                .forEach(material -> ((Material) material).create());

        TestRenderer renderer = new TestRenderer("test-renderer", null);

        this.getSceneManager().add(new Scene("test-scene"), true);
        this.getSceneManager().getActive().getRenderStrategy()
                .add(renderer);

        TestEntity testEntity = new TestEntity(
                "test-entity_1",
                renderer,
                true
        );

        TestEntity testEntity2 = new TestEntity(
                "test-entity_2",
                renderer,
                false
        );

        this.getSceneManager().getActive()
                .add(testEntity)
                .add(testEntity2);

        ViewportCameraEntity cameraEntity = new ViewportCameraEntity(
                "viewport-camera-entity",
                null
        );

        this.getSceneManager().getActive().add(cameraEntity);

        renderer.setCamera(cameraEntity.getCamera());

        testEntity.getTransform().getGlobalTransform().getTranslation().z -= 5;
        testEntity.getTransform().getGlobalTransform().getRotation().y += 45;
    }

    @Override
    public void finish() {
        ResourceDatabase.getShaderCollection().stream()
                .forEach(shader -> ((Shader) shader).destroy());
    }

    public static void main(String[] args) {
        new EngineCore(
                new WindowManager(
                        "game test",
                        new Dimension(800, 650)
                ),
                new GameTest()
        ).start();
    }
}
