package game.engine.appearance;

import game.engine.core.IGBuffer;
import game.engine.core.database.IResource;
import game.engine.utils.FileUtils;
import org.joml.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

public class Shader implements IGBuffer, IResource {

    private String name;
    private int program;
    private String vertexShaderPath;
    private String fragmentShaderPath;

    private boolean created;

    public Shader(String name, String vertexShaderPath, String fragmentShaderPath) {
        this.name = name;
        this.vertexShaderPath = vertexShaderPath;
        this.fragmentShaderPath = fragmentShaderPath;
        this.created = false;
    }

    @Override
    public void create() {
        if(!this.created){

            this.program = GL20.glCreateProgram();

            int vertexShaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
            GL20.glShaderSource(vertexShaderID, FileUtils.getFileAsString(this.vertexShaderPath));
            GL20.glCompileShader(vertexShaderID);

            if( checkShaderErrorStatus(vertexShaderID, "Vertex Shader") ) return;

            int fragmentShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
            GL20.glShaderSource(fragmentShaderID, FileUtils.getFileAsString(this.fragmentShaderPath));
            GL20.glCompileShader(fragmentShaderID);

            if( checkShaderErrorStatus(fragmentShaderID, "Fragment Shader") ) return;

            GL20.glAttachShader(program, vertexShaderID);
            GL20.glAttachShader(program, fragmentShaderID);

            //if(attributes != null) bindAttributes(attributes);

            if( checkShaderProgramErrorStatus(program) ) return;

            GL20.glDetachShader(program, vertexShaderID);
            GL20.glDetachShader(program, fragmentShaderID);
            GL20.glDeleteShader(vertexShaderID);
            GL20.glDeleteShader(fragmentShaderID);

            this.created = true;

        }
    }

    @Override
    public void bind() {
        GL20.glUseProgram(program);
    }

    @Override
    public void unbind() {
        GL20.glUseProgram(0);
    }

    @Override
    public void destroy() {
        GL20.glDeleteProgram(program);
    }

    @Override
    public String getName() {
        return name;
    }

    private boolean checkShaderErrorStatus(int id, String type){
        if(GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.err.println("[ Error ] On: " + type + " Compiling\n");
            System.err.println(GL20.glGetShaderInfoLog(id));
            return true;
        }
        return false;
    }

    private boolean checkShaderProgramErrorStatus(int id) {

        GL20.glLinkProgram(id);
        if( GL20.glGetProgrami(id, GL20.GL_LINK_STATUS) == GL11.GL_FALSE ){
            System.err.println("[ ERROR ] On: Program Linking\n");
            System.err.println(GL20.glGetProgramInfoLog(id));
            return true;
        }

        GL20.glValidateProgram(id);
        if( GL20.glGetProgrami(id, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE ){
            System.err.println("[ ERROR ] On: Program Validation\n");
            System.err.println(GL20.glGetProgramInfoLog(id));
            return true;
        }

        return false;
    }

    public int getUniformLocation(String name) {
        return GL20.glGetUniformLocation(program, name);
    }

    public void setUniform(String name, float value) {
        GL20.glUniform1f(getUniformLocation(name), value);
    }

    public void setUniform(String name, int value) {
        GL20.glUniform1i(getUniformLocation(name), value);
    }

    public void setUniform(String name, boolean value) {
        GL20.glUniform1f(getUniformLocation(name), value ? 1 : 0);
    }

    public void setUniform(String name, float[] value) {
        GL20.glUniform1fv(getUniformLocation(name), value);
    }

    public void setUniform(String name, int[] value) {
        GL20.glUniform1iv(getUniformLocation(name), value);
    }

    public void setUniform(String name, Vector2f value) {
        GL20.glUniform2f(getUniformLocation(name), value.x, value.y);
    }

    public void setUniform(String name, Vector3f value) {
        GL20.glUniform3f(getUniformLocation(name), value.x, value.y, value.z);
    }

    public void setUniform(String name, Matrix2f value) {
        GL20.glUniform2fv(
                getUniformLocation(name),
                value.get(MemoryUtil.memAllocFloat(4))
        );
    }

    public void setUniform(String name, Matrix3f value) {
        GL20.glUniform3fv(
                getUniformLocation(name),
                value.get(MemoryUtil.memAllocFloat(9))
        );
    }

    public void setUniform(String name, Matrix4f value) {
        GL20.glUniformMatrix4fv(
                getUniformLocation(name),
                false,
                value.get(MemoryUtil.memAllocFloat(16))
        );
    }

}
