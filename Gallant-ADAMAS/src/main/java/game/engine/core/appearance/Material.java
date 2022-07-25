package game.engine.core.appearance;

import game.engine.core.management.IGBuffer;
import game.engine.core.management.database.IResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public abstract class Material implements IGBuffer, IResource {

    private String name;
    @Getter
    @Setter
    private Shader shader;

    @Override
    public void create() {
        shader.create();
    }

    @Override
    public void bind() {
        shader.bind();
    }

    @Override
    public void unbind() {
        shader.unbind();
    }

    @Override
    public void destroy() {
        shader.destroy();
    }

    @Override
    public String getName() {
        return name;
    }
}
