package game.engine.core.scenes;

import java.util.HashMap;
import java.util.Set;

public class SceneManager {


    /* All entities */
    private HashMap<String, Scene> scenes;
    /* Active scene: the scene that is being rendered currently */
    private Scene active;

    public SceneManager() {
        this.scenes = new HashMap<>();
        this.active = null;
    }

    public void update(float delta) {
        if(this.active != null) {
            this.active.update(delta);
        }
    }

    public void render(){
        if(this.active != null) {
            this.active.render();
        }
    }

    /**
     * Adds a new scene, not active by default
     * @param scene scene to be added
     * @param active set the scene to be active
     */
    public SceneManager add(Scene scene, boolean active) {
        if(this.scenes.containsKey(scene.getName())) {
            throw new RuntimeException();
        }
        this.scenes.put(scene.getName(), scene);
        if(active) {
            this.active = scene;
        }
        return this;
    }

    public SceneManager add(Scene scene) {
        return this.add(scene, false);
    }

    /**
     * Remove an existent scene
     * @param sceneName name of the scene to be removed
     * @return SceneManager instance
     */
    public SceneManager remove(String sceneName) {
        if(!this.scenes.containsKey(sceneName)) {
            throw new RuntimeException();
        }
        this.scenes.remove(sceneName);
        if(this.active.getName().equals(sceneName)) {
            this.active = null;
        }
        return this;
    }

    /**
     * Remove an existent scene
     * @param scene scene to be removed
     * @return SceneManager instance
     */
    public SceneManager remove(Scene scene) {
        return this.remove(scene.getName());
    }

    /**
     * Sets the active scene
     * @param sceneName name of the scene to become active
     */
    public SceneManager setActive(String sceneName) {
        if(!this.scenes.containsKey(sceneName)) {
            throw new RuntimeException();
        }
        this.active = this.scenes.get(sceneName);
        return this;
    }

    /**
     * Sets the active scene
     * @param scene scene to become active
     */
    public SceneManager setActive(Scene scene) {
        return this.setActive(scene.getName());
    }

    public Scene getActive() {
        return this.active;
    }

    /**
     * Returns an existent scene
     * @param sceneName the name of the scene to be returned
     */
    public Scene get(String sceneName) {
        if(!this.scenes.containsKey(sceneName)) {
            throw new RuntimeException();
        }
        return this.scenes.get(sceneName);
    }

    /**
     * Returns the keys of all scenes
     */
    public Set<String> getKeys() {
        return this.scenes.keySet();
    }

    /**
     * Iterates all scenes
     * @param fn do something with each scene
     */
    public SceneManager forEach(ISceneManagerCallback fn) {
        this.scenes.values().forEach(fn::fn);
        return this;
    }

    /**
     * Checks if there is a given scene
     * @param sceneName the name of the scene to be checked
     */
    public boolean has(String sceneName) {
        return this.scenes.containsKey(sceneName);
    }


}
