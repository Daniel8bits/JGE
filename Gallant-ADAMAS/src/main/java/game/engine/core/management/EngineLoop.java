package game.engine.core.management;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class EngineLoop {

    private Thread loop;
    private EngineCore engineCore;

    public EngineLoop(EngineCore engineCore) {
        this.engineCore = engineCore;
    }

    public void start() {
        this.loop = new Thread(mainloop);
        this.loop.start();
    }

    private final Runnable mainloop = () -> {

        engineCore.getWindowManager().setupContext();
        engineCore.getEngineMain().start();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        float delta;
        long beginTime = System.currentTimeMillis();

        while( !GLFW.glfwWindowShouldClose(engineCore.getWindowManager().getId()) ){

            GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            delta = (float) (System.currentTimeMillis() - beginTime) / 1000f;

            // update
            engineCore.getEngineMain().update(delta);
            // render
            engineCore.getEngineMain().render();

            beginTime = System.currentTimeMillis();

            engineCore.getWindowManager().update();
            engineCore.getInputManager().update();

        }

        engineCore.getEngineMain().finish();
        engineCore.getInputManager().finish();
        engineCore.getWindowManager().finish();

    };



}
