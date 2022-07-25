package game.engine.core.management.database;

import game.engine.core.appearance.Material;
import game.engine.core.appearance.Shader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public final class ResourceDatabase {

    @RequiredArgsConstructor
    public enum DefaultCollections {
        VAO(game.engine.core.buffer.VAO.class.getName()),
        SHADER(Shader.class.getName()),
        MATERIAL(Material.class.getName());

        @Getter
        private final String collectionName;
    }

    private static ResourceDatabase instance;

    @Getter
    private ResourceDatabaseCore resourceDatabaseCore;

    private ResourceDatabase(){
        this.resourceDatabaseCore = new ResourceDatabaseCore();
        this.resourceDatabaseCore
                .createCollection(DefaultCollections.VAO.getCollectionName())
                .createCollection(DefaultCollections.SHADER.getCollectionName())
                .createCollection(DefaultCollections.MATERIAL.getCollectionName());
    }

    public static ResourceDatabaseCore getDatabase(){
        if(ResourceDatabase.instance == null)
            ResourceDatabase.instance = new ResourceDatabase();
        return instance.getResourceDatabaseCore();
    }

    public static ResourceDatabaseCore createCollection(String name){
        return getDatabase().createCollection(name);
    }

    public static ResourceCollection createAndGetCollection(String name){
        return getDatabase().createAndGetCollection(name);
    }

    public static ResourceCollection getCollection(String name){
        return getDatabase().getCollection(name);
    }

    public static ResourceDatabaseCore removeCollection(String name){
        return getDatabase().removeCollection(name);
    }

    public static ResourceCollection popCollection(String name){
        return getDatabase().popCollection(name);
    }


    public static ResourceCollection getVAOCollection() {
        return getDatabase().getCollection(DefaultCollections.VAO.getCollectionName());
    }

    public static ResourceCollection getShaderCollection() {
        return getDatabase().getCollection(DefaultCollections.SHADER.getCollectionName());
    }

    public static ResourceCollection getMaterialCollection() {
        return getDatabase().getCollection(DefaultCollections.MATERIAL.getCollectionName());
    }

}
