package game.engine.core;

import org.joml.Vector2d;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Objects;

public class InputManager {

    private WindowManager windowManager;

    private final static HashMap<Integer, Boolean> keys;
    private final static HashMap<Integer, Boolean> mouseButtons;
    private final static Vector2d mousePosition, previousMousePosition, mouseMovement;

    static {
        keys = new HashMap<>();
        mouseButtons = new HashMap<>();
        mousePosition = new Vector2d();
        previousMousePosition = new Vector2d();
        mouseMovement = new Vector2d();
    }

    InputManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public void start() {
        createListeners();
    }

    public void update() {
        GLFW.glfwPollEvents();
        mouseMovement.x = mousePosition.x - previousMousePosition.x;
        mouseMovement.y = mousePosition.y - previousMousePosition.y;
        previousMousePosition.x = mousePosition.x;
        previousMousePosition.y = mousePosition.y;
    }

    public void finish() {
        Callbacks.glfwFreeCallbacks(windowManager.getId());
        Objects.requireNonNull(GLFW.glfwSetErrorCallback(null)).free();
    }

    private void createListeners() {
        createKeyListener();
        createMouseListener();
    }

    private void createKeyListener() {
        GLFW.glfwSetKeyCallback(windowManager.getId(), (window, key, scancode, action, mods) -> {
            keys.put(key, action != GLFW.GLFW_RELEASE);
            if(key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
                GLFW.glfwSetInputMode(windowManager.getId(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
            }
        });
    }

    private void createMouseListener() {
        GLFW.glfwSetMouseButtonCallback(windowManager.getId(), (window, button, action, mods) -> {
            mouseButtons.put(button, action != GLFW.GLFW_RELEASE);
            GLFW.glfwSetInputMode(windowManager.getId(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
        });
        GLFW.glfwSetCursorPosCallback(windowManager.getId(), (window, xpos, ypos) -> {
            mousePosition.x = xpos;
            mousePosition.y = ypos;
        });
    }

    /*======================================
               STATIC INPUT GETTERS
    =======================================*/

    public static boolean isKeyPressed(int key) {
        return keys.containsKey(key) && keys.get(key);
    }

    public static boolean isKeyReleased(int key) {
        return !keys.containsKey(key) || !keys.get(key);
    }

    public static boolean isMouseButtonPressed(int key) {
        return mouseButtons.containsKey(key) && mouseButtons.get(key);
    }

    public static double getMouseX() {
        return mousePosition.x;
    }

    public static double getMouseY() {
        return mousePosition.y;
    }

    public static double getMouseMovementX() {
        return mouseMovement.x;
    }

    public static double getMouseMovementY() {
        return mouseMovement.y;
    }

}
