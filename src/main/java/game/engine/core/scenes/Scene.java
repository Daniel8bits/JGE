package game.engine.core.scenes;

import game.engine.core.entities.Entity;
import game.engine.rendering.RenderStrategy;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scene {

    @Getter
    private String name;

    /* All entities */
    private HashMap<String, Entity> entities;
    /* Only visible entities */
    private HashMap<String, Entity> visible;
    /* Only hidden entities */
    private HashMap<String, Entity> hidden;

    @Getter
    private RenderStrategy renderStrategy;

    public Scene(String name) {
        this.name = name;
        this.entities = new HashMap<>();
        this.visible = new HashMap<>();
        this.hidden = new HashMap<>();
        this.renderStrategy = new RenderStrategy();
    }

    public void update(float delta) {
        this.forEachVisible((entity) -> {
            entity.update(delta, this);
        });
    }

    public void render() {
        this.renderStrategy.render(this);
    }

    /**
     * Adds a new entity, visible by default
     * @param entity entity or the name of the entity to be added
     * @param visible default visibility
     */
    public Scene add(Entity entity, boolean visible) {
        if(this.entities.containsKey(entity.getName())) {
            throw new RuntimeException();
        }
        this.entities.put(entity.getName(), entity);
        if(visible) {
            this.visible.put(entity.getName(), entity);
        }
        else {
            this.hidden.put(entity.getName(), entity);
        }
        return this;
    }

    public Scene add(Entity entity) {
        return this.add(entity, true);
    }

    /**
     * Remove an existent entity
     * @param entityName name of the entity to be removed
     */
    public Scene remove(String entityName) {
        if(!this.entities.containsKey(entityName)) {
            throw new RuntimeException();
        }
        this.entities.remove(entityName);
        this.visible.remove(entityName);
        this.hidden.remove(entityName);
        return this;
    }

    public Scene remove(Entity entity) {
        return this.remove(entity.getName());
    }

    /**
     * Sets the visibility of an existent entity
     * @param entityName name of the entity to have the visibility changed
     * @param visible the visibility value
     */
    public Scene setVisibility(String entityName, boolean visible) {
        if(!this.entities.containsKey(entityName)) {
            throw new RuntimeException();
        }
        if(visible) {
            this.visible.put(entityName, this.hidden.get(entityName));
            this.hidden.remove(entityName);
        } else {
            this.hidden.put(entityName, this.visible.get(entityName));
            this.visible.remove(entityName);
        }
        return this;
    }

    public Scene setVisibility(Entity entity, boolean visible) {
        return this.setVisibility(entity.getName(), visible);
    }

    /**
     * Returns an existent entity
     * @param entityName name of the entity to be returned
     */
    public Entity get(String entityName) {
        if(!this.entities.containsKey(entityName)) {
            throw new RuntimeException();
        }
        return this.entities.get(entityName);
    }

    /**
     * Returns the keys of all entities
     */
    public Set<String> getKeys() {
        return this.getKeys(this.entities);
    }

    /**
     * Returns the keys of only the visible entities
     */
    public Set<String> getKeysFromVisible() {
        return this.getKeys(this.visible);
    }

    /**
     * Returns the keys of only the hidden entities
     */
    public Set<String> getKeysFromHidden() {
        return this.getKeys(this.hidden);
    }

    /**
     * Common behavior pattern for 'getKeys' methods
     */
    private Set<String> getKeys(HashMap<String, Entity> map) {
        return map.keySet();
    }

    /**
     * Iterates all entities
     * @param fn do something with each entity
     */
    public Scene forEach(ISceneCallback fn) {
        return this.forEach(fn, this.entities);
    }

    /**
     * Iterates visible entities
     * @param fn do something with each entity
     */
    public Scene forEachVisible(ISceneCallback fn) {
        return this.forEach(fn, this.visible);
    }
    /**
     * Iterates hidden entities
     * @param fn do something with each entity
     */
    public Scene forEachHidden(ISceneCallback fn) {
        return this.forEach(fn, this.hidden);
    }

    /**
     * Common behavior pattern for 'forEach' methods
     * @param fn do something with each entity
     * @param map map to be iterated
     */
    private Scene forEach(ISceneCallback fn, HashMap<String, Entity> map) {
        map.values().forEach(fn::fn);
        return this;
    }

    /**
     * Checks if there is a given entity
     * @param entityName name of the entity to be checked
     */
    public boolean has(String entityName) {
        return this.has(entityName, this.entities);
    }

    /**
     * Checks if there is a given entity
     * @param entity entity to be checked
     */
    public boolean has(Entity entity) {
        return this.has(entity.getName());
    }

    /**
     * Checks if there is a given entity among the visibles
     * @param entityName name of the entity to be checked
     */
    public boolean hasInVisible(String entityName) {
        return this.has(entityName, this.visible);
    }

    /**
     * Checks if there is a given entity among the visibles
     * @param entity entity to be checked
     */
    public boolean hasInVisible(Entity entity) {
        return this.hasInVisible(entity.getName());
    }

    /**
     * Checks if there is a given entity among the hiddens
     * @param entityName name of the entity to be checked
     */
    public boolean hasInHidden(String entityName) {
        return this.has(entityName, this.hidden);
    }

    /**
     * Checks if there is a given entity among the hiddens
     * @param entity entity to be checked
     */
    public boolean hasInHidden(Entity entity) {
        return this.hasInHidden(entity.getName());
    }

    /**
     * Common behavior pattern for 'has' methods
     * @param entityName name of the entity to be checked
     * @param map map to be iterated
     */
    private boolean has(String entityName, HashMap<String, Entity> map) {
        return map.containsKey(name);
    }

    public List<Entity> filter(IScenePredicate predicate) {
        return this.filter(predicate, this.entities);
    }

    public List<Entity> filterVisible(IScenePredicate predicate) {
        return this.filter(predicate, this.visible);
    }

    public List<Entity> filterHidden(IScenePredicate predicate) {
        return this.filter(predicate, this.hidden);
    }

    private List<Entity> filter(IScenePredicate predicate, HashMap<String, Entity> map) {
        return map.values().stream()
            .filter(predicate::predicate)
            .collect(Collectors.toList());
    }

    public Stream<Entity> stream() {
        return this.stream(this.entities);
    }

    public Stream<Entity> streamVisible() {
        return this.stream(this.visible);
    }

    public Stream<Entity> streamHidden() {
        return this.stream(this.hidden);
    }

    private Stream<Entity> stream(HashMap<String, Entity> map) {
        return map.values().stream();
    }

}
