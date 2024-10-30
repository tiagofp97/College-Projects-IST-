package hva.core;
import java.io.Serializable;
import java.util.*;
import java.util.function.BiFunction;

import hva.core.exception.*;

public class Manager<T extends BaseEntity> implements Serializable{
    private Map<String, T> _entitiesMap;
    

    public Manager() {
        _entitiesMap = new HashMap<>();
        
    }

    public void checkDuplicateId(String id)throws DuplicateId{
        if(idIsRegistered(id)){throw new DuplicateId(id);}
    }

    public void checkNull(Object obj) throws NullObject {
        if (obj == null) {
            throw new NullObject();
        }
    }
    

     // Overloaded CheckNull for BiFunction<String, String, Employee>
    public void checkNull(BiFunction<String, String, T> function, String empType) throws NullObject {
        if (function == null) {
            throw new NullObject();
        }
    }

    

    // General register method for any type T
    public void register(String id, T entity){
        _entitiesMap.put(id.toUpperCase(), entity);
    }

    public T getById(String id) {
        return _entitiesMap.get(id.toUpperCase());
    }

    public Optional<T> getByIdOptional(String id) {
        return Optional.ofNullable(_entitiesMap.get(id.toUpperCase()));
    }

    public Boolean idIsRegistered(String id) {
        return _entitiesMap.containsKey(id.toUpperCase());
    }

    public void remove(String id) {
        _entitiesMap.remove(id.toUpperCase());
    }


    public Map<String, T> getAll() {
    return Collections.unmodifiableMap(_entitiesMap);
    }

    public List<T> getSortedEntities() {
        List<T> sortedList = new ArrayList<>(_entitiesMap.values()); // Extract the values
        Collections.sort(sortedList); // Sort using the compareTo method in BaseEntity
        return Collections.unmodifiableList(sortedList);
    }
    public void showAll(){
        for(T t :getSortedEntities()){
            System.out.println(t.toString());
        }
    }

}
