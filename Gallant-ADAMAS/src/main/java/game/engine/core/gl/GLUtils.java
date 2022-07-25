package game.engine.core.gl;

import org.lwjgl.opengl.GL11;

public class GLUtils {

    public static void draw(int vertexAmount) {
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexAmount);
    }

}
