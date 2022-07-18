package game.engine.buffer;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@Getter
public class VBO {

    private int id;

    private Buffer buffer;
    private int length;
    private int offset;
    private boolean isCreated;
    private boolean isAttribute;

    public VBO(Integer[] data, int offset, boolean isAttribute){
        this(VBO.store(data), data.length, offset, isAttribute);
    }

    public VBO(Float[] data, int offset, boolean isAttribute){
        this(VBO.store(data), data.length, offset, isAttribute);
    }

    public VBO(int[] data, int offset, boolean isAttribute){
        this(VBO.store(data), data.length, offset, isAttribute);
    }

    public VBO(float[] data, int offset, boolean isAttribute){
        this(VBO.store(data), data.length, offset, isAttribute);
    }

    public VBO(Buffer buffer, int length, int offset, boolean isAttribute){
        this.buffer = buffer;
        this.length = length;
        this.offset = offset;
        this.isAttribute = isAttribute;
        this.isCreated = false;
    }

    public int getType() {
        return isAttribute ? GL15.GL_ARRAY_BUFFER : GL15.GL_ELEMENT_ARRAY_BUFFER;
    }

    private static Buffer store(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data).flip();
        return buffer;
    }

    private static Buffer store(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data).flip();
        return buffer;
    }

    private static Buffer store(Integer[] data) {

        int[] dataAsPrimitive = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            dataAsPrimitive[i] = data[i];
        }

        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(dataAsPrimitive).flip();
        return buffer;
    }

    private static Buffer store(Float[] data) {

        float[] dataAsPrimitive = new float[data.length];
        for (int i = 0; i < data.length; i++) {
            dataAsPrimitive[i] = data[i];
        }

        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(dataAsPrimitive).flip();
        return buffer;
    }

/*
    public static VBO store(int[] data, int offset, boolean isAttribute){

        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);

        buffer.put(data).flip();

        return new VBO(buffer, offset, isAttribute);

    }

    public static VBO store(float[] data, int offset, boolean isAttribute){

        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);

        buffer.put(data).flip();

        return new VBO(buffer, offset, isAttribute);

    }
*/
    void setId(int id) {
        this.id = id;
    }

}
