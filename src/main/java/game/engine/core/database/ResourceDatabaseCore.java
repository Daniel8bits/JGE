package game.engine.core.database;

import lombok.Getter;

import java.util.HashMap;

public final class ResourceDatabaseCore {

    private HashMap<String, ResourceCollection> resourceCollections;

    ResourceDatabaseCore(){
        this.resourceCollections = new HashMap<>();
    }

    public ResourceDatabaseCore createCollection(String name){

        if(resourceCollections.containsKey(name)){
            throw new RuntimeException();
        }

        resourceCollections.put(name, new ResourceCollection(name));

        return this;

    }

    public ResourceCollection createAndGetCollection(String name){
        this.createCollection(name);
        return this.getCollection(name);
    }

    public ResourceCollection getCollection(String name){
        if(!resourceCollections.containsKey(name)){
            throw new RuntimeException();
        }
        return resourceCollections.get(name);
    }

    public ResourceDatabaseCore removeCollection(String name){

        if(!resourceCollections.containsKey(name)){
            throw new RuntimeException();
        }

        resourceCollections.remove(new ResourceCollection(name));

        return this;

    }

    public ResourceCollection popCollection(String name){

        if(!resourceCollections.containsKey(name)){
            throw new RuntimeException();
        }

        ResourceCollection collection = new ResourceCollection(name);
        resourceCollections.remove(name);

        return collection;

    }

}
