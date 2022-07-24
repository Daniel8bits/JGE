package game.engine.rendering;

import game.engine.core.scenes.Scene;

import java.util.HashMap;
import java.util.Set;

public class RenderStrategy {

    private HashMap<String, Renderer> renderers;

    public RenderStrategy() {
        this.renderers = new HashMap<>();
    }

    public void render(Scene scene) {
        this.forEach((renderer) -> {
            renderer.setCurrentScene(scene);
            renderer.render();
        });
    }

    /**
     * Adds a new renderer
     * @param renderer renderer or the name of the renderer to be added
     */
    public RenderStrategy add(Renderer renderer, boolean active) {
        this.renderers.put(renderer.getName(), renderer);
        return this;
    }

    public RenderStrategy add(Renderer renderer) {
        return this.add(renderer, false);
    }

    /**
     * Remove an existent renderer
     * @param rendererName the name of the renderer to be removed
     */
    public RenderStrategy remove(String rendererName) {
        this.renderers.remove(rendererName);
        return this;
    }

    /**
     * Remove an existent renderer
     * @param renderer renderer to be removed
     */
    public RenderStrategy remove(Renderer renderer) {
        return this.remove(renderer.getName());
    }

    /**
     * Returns an existent renderer
     * @param rendererName the name of the renderer to be returned
     */
    public Renderer get(String rendererName) {
        return this.renderers.get(rendererName);
    }

    /**
     * Returns the keys of all scenes
     */
    public Set<String> getKeys() {
        return this.renderers.keySet();
    }

    /**
     * Iterates all scenes
     * @param fn do something with each scene
     */
    public RenderStrategy forEach(IRenderStrategyCallback fn) {
        this.renderers.values().forEach(fn::fn);
        return this;
    }

    /**
     * Checks if there is a given renderer
     * @param rendererName name of the renderer to be checked
     */
    public boolean has(String rendererName) {
        return this.renderers.containsKey(rendererName);
    }

    /**
     * Checks if there is a given renderer
     * @param renderer renderer to be checked
     */
    public boolean has(Renderer renderer) {
        return this.has(renderer.getName());
    }

}
