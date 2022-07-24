package game.engine.core;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.system.MemoryUtil;

import java.awt.*;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class WindowManager {

    @Getter
    private long id;
    @Getter
    private Dimension size, frameBufferSize;
    @Getter
    private String title;

    public WindowManager(String title, Dimension size) {
        this.title = title;
        this.size = size;
    }

    public void create() {


        GLFWErrorCallback.createPrint(System.err).set();

        if(!GLFW.glfwInit()){
            System.err.println("Couldn't initialize GLFW!");
            System.exit(-1);
        }


        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 4);

        id = GLFW.glfwCreateWindow(size.width, size.height, title, MemoryUtil.NULL, MemoryUtil.NULL);

        if(id == MemoryUtil.NULL){
            System.err.println("Couldn't create the window!");
            System.exit(-1);
        }


        GLFWVidMode videomode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

        GLFW.glfwSetWindowPos(id, ( videomode.width() - size.width ) / 2, ( videomode.height() - size.height ) / 2 );
        GLFW.glfwSetInputMode(id, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);

        GLFW.glfwSetWindowCloseCallback(id, l -> GLFW.glfwSetWindowShouldClose(id, true));

    }

    public void update() {
        GLFW.glfwSwapBuffers(id);
    }

    public void finish(){
        GLFW.glfwDestroyWindow(id);
        GLFW.glfwTerminate();
        System.exit(0);
    }

    public void setupContext() {

        GLFW.glfwMakeContextCurrent(id);

        GLFW.glfwSwapInterval(GLFW.GLFW_TRUE);

        GLFW.glfwShowWindow(id);

        GL.createCapabilities();

        IntBuffer fbs = BufferUtils.createIntBuffer(2);
        GLFW.nglfwGetFramebufferSize(id, MemoryUtil.memAddress(fbs), MemoryUtil.memAddress(fbs) + 4);
        frameBufferSize = new Dimension(fbs.get(0), fbs.get(1));

        //GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);

        GL11.glViewport(0, 0, size.width, size.height);

    }

}
