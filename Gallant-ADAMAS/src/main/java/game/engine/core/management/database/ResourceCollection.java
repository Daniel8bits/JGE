package game.engine.core.management.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public final class ResourceCollection implements IResource {
    private String name;

    private HashMap<Integer, IResource> resources = new LinkedHashMap<>();
    private HashMap<String, Integer> ids = new LinkedHashMap<>();

    public ResourceCollection(String name) {
        this.name = name;
    }

    public IResource getResource(String resourceName) {
        if(!has(resourceName)) {
            throw new RuntimeException();
        }
        return resources.get(ids.get(resourceName));
    }
    public IResource getResource(int id){
        if(!has(id)) {
            throw new RuntimeException();
        }
        return resources.get(id);
    }
    public <T extends IResource> T getResource(int id, Class<T> resourceType) {
        IResource resource = getResource(id);
        if(!resource.getClass().getClass().isInstance(resourceType)) {
            throw new RuntimeException();
        }
        return (T) resource;
    }
    public <T extends IResource> T getResource(String resourceName, Class<T> resourceType) {
        IResource resource = getResource(resourceName);
        if(!resource.getClass().getClass().isInstance(resourceType)) {
            throw new RuntimeException();
        }
        return (T) resource;
    }

    public int getId(String resourceName){
        if(!has(resourceName)) {
            throw new RuntimeException();
        }
        return ids.get(resourceName);
    }
    public int getId(IResource resource){

        if(!has(resource)) {
            throw new RuntimeException();
        }

        return ids.get(resource.getName());
    }

    public IResource getLast(){
        return resources.get(resources.size()-1);
    }

    public ResourceCollection register(IResource resource){

        if(has(resource.getName())){
            throw new RuntimeException();
        }

        ids.put(resource.getName(), ids.size());
        resources.put(ids.get(resource.getName()), resource);

        return this;

    }

    public ResourceCollection remove(String resourceName){

        if(!ids.containsKey(resourceName)) {
            throw new RuntimeException();
        }

        return remove(ids.get(resourceName));
    }

    public ResourceCollection remove(int resourceId){

        if(!has(resourceId)) {
            throw new RuntimeException();
        }

        resources.remove(resourceId);

        String resourceName = ids.keySet().stream()
                .filter(name -> ids.get(name) == resourceId)
                .collect(Collectors.toList())
                        .get(0);

        ids.remove(resourceName);

        sort();

        return this;
    }

    private void sort(){
        ArrayList<IResource> rcs = new ArrayList<>(resources.values());
        ArrayList<String> names = new ArrayList<>(ids.keySet());

        resources.clear();
        ids.clear();

        for(int i = 0; i < rcs.size(); i++){
            ids.put(names.get(i), i);
            resources.put(i, rcs.get(i));
        }
    }

    public boolean has(IResource resource){
        return getResource(resource.getName()).equals(resource);
    }

    public boolean has(int id){
        return resources.containsKey(id);
    }

    public boolean has(String name){
        return ids.containsKey(name);
    }

    public Stream<IResource> stream() {
        return resources.values().stream();
    }

    @Override
    public String getName() {
        return name;
    }
}
