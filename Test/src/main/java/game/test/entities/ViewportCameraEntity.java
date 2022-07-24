package game.test.entities;

import game.engine.core.Camera;
import game.engine.core.InputManager;
import game.engine.core.entities.Entity;
import game.engine.core.scenes.Scene;
import game.engine.rendering.Renderer;
import lombok.Getter;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class ViewportCameraEntity extends Entity {

    @Getter
    private Camera camera;
    private float speed;
    private float sensitivity;

    public ViewportCameraEntity(String name, Renderer renderer) {
        super(name, null, null, renderer);
        this.camera = new Camera(new Vector3f(), new Vector3f());
        this.camera.getTransform().setParent(this.getTransform());
        this.speed = 10f;
        this.sensitivity = 10f;
    }

    @Override
    public void update(float delta, Scene scene) {

        float x = (float) Math.sin(Math.toRadians(this.getTransform().getGlobalTransform().getRotation().y)) * delta * speed;
        float z = (float) Math.cos(Math.toRadians(this.getTransform().getGlobalTransform().getRotation().y)) * delta * speed;

        if(InputManager.isKeyPressed(GLFW.GLFW_KEY_W)){ // FRONT
            this.getTransform().getGlobalTransform().getTranslation().x += x;
            this.getTransform().getGlobalTransform().getTranslation().z += z;
        }

        if(InputManager.isKeyPressed(GLFW.GLFW_KEY_S)){ // BACK
            this.getTransform().getGlobalTransform().getTranslation().x += -x;
            this.getTransform().getGlobalTransform().getTranslation().z += -z;
        }

        if(InputManager.isKeyPressed(GLFW.GLFW_KEY_A)){ // LEFT
            this.getTransform().getGlobalTransform().getTranslation().x += z;
            this.getTransform().getGlobalTransform().getTranslation().z += -x;
        }

        if(InputManager.isKeyPressed(GLFW.GLFW_KEY_D)){ // RIGHT
            this.getTransform().getGlobalTransform().getTranslation().x += -z;
            this.getTransform().getGlobalTransform().getTranslation().z += x;
        }

        if(InputManager.isKeyPressed(GLFW.GLFW_KEY_SPACE)){ // UP
            this.getTransform().getGlobalTransform().getTranslation().y += delta * -speed;
        }

        if(InputManager.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)){ // DOWN
            this.getTransform().getGlobalTransform().getTranslation().y += delta * speed;
        }

        this.getTransform().getGlobalTransform().getRotation().x +=
                (float) -InputManager.getMouseMovementY() * delta * sensitivity;
        this.getTransform().getGlobalTransform().getRotation().y +=
                (float) -InputManager.getMouseMovementX() * delta * sensitivity;

    }

}
