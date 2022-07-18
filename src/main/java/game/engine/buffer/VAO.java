package game.engine.buffer;

import game.engine.core.IGBuffer;
import game.engine.core.database.IResource;
import lombok.Getter;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class VAO implements IGBuffer, IResource {

    private String name;
    private int id;

    private ArrayList<VBO> vbos = new ArrayList<>();
    @Getter
    private VBO vertices, ibo;
    private int vertexOffset;

    public VAO(String name, VBO vertices, VBO[] vbos){
        this(name, vertices, vbos, 3);
    }

    public VAO(String name, VBO vertices, VBO[] vbos, int vertexOffset){

        this.name = name;
        this.vertices = vertices;
        this.vbos.add(vertices);
        Arrays.stream(vbos).forEach(vbo -> {
            if(vbo != null) {
                this.vbos.add(vbo);
                if(vbo.getType() == GL15.GL_ELEMENT_ARRAY_BUFFER) {
                    this.ibo = vbo;
                }
            }
        });

        this.vertexOffset = vertexOffset;

    }

    @Override
    public void create(){

        id = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(id);

        for(int i = 0; i < vbos.size(); i++){
            createVBO(i, vbos.get(i));
        }

    }

    private void createVBO(int target, VBO vbo){

        vbo.setId(GL15.glGenBuffers());
        GL15.glBindBuffer(vbo.getType(), vbo.getId());

        if(vbo.getBuffer() instanceof IntBuffer){
            IntBuffer buffer = (IntBuffer) vbo.getBuffer();
            GL15.glBufferData(vbo.getType(), buffer, GL15.GL_STATIC_DRAW);

            if(vbo.getType() == GL15.GL_ARRAY_BUFFER){
                GL20.glVertexAttribPointer(target, vbo.getOffset(), GL11.GL_INT, false, 0, 0);
            }
        } else {
            FloatBuffer buffer = (FloatBuffer) vbo.getBuffer();
            GL15.glBufferData(vbo.getType(), buffer, GL15.GL_STATIC_DRAW);

            if(vbo.getType() == GL15.GL_ARRAY_BUFFER) {
                GL20.glVertexAttribPointer(target, vbo.getOffset(), GL11.GL_FLOAT, false, 0, 0);
            }
        }

        GL15.glBindBuffer(vbo.getType(), 0);

    }

    @Override
    public void bind(){
        GL30.glBindVertexArray(id);
        for(int i = 0; i < vbos.size(); i++){
            if(vbos.get(i).getType() == GL15.GL_ELEMENT_ARRAY_BUFFER) {
                GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbos.get(i).getId());
            } else {
                GL30.glEnableVertexAttribArray(i);
            }
        }
    }

    @Override
    public void unbind(){
        for(int i = vbos.size()-1; i > -1; i--){
            if(vbos.get(i).getType() == GL15.GL_ELEMENT_ARRAY_BUFFER) {
                GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
            } else {
                GL30.glDisableVertexAttribArray(i);
            }
        }
        GL30.glBindVertexArray(0);
    }

    @Override
    public void destroy(){
        for(int i = vbos.size()-1; i > -1; i--){
            GL20.glDeleteBuffers(i);
        }
        GL30.glDeleteVertexArrays(id);
    }

    @Override
    public String getName() {
        return name;
    }

    public VBO getVBO(int index) {
        return this.vbos.get(index);
    }

    public int getVertexAmount() {
        if(this.ibo != null) {
            return this.ibo.getLength();
        }
        return this.vertices.getLength()/this.vertexOffset;
    }

}
