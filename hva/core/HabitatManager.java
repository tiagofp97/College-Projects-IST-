package hva.core;

import hva.core.exception.DupHabitatException;
import hva.core.exception.DupTreeException;
import hva.core.exception.NoHabitatCoreException;
//import javafx.scene.layout.HBox;

import java.util.*;

public class HabitatManager extends Manager<Habitat>{
    private HashMap<String, TreeFactory> treeFactories;
    private HashMap<String,Tree>_treeMap;
    public HabitatManager() {
        super();
        treeFactories = new HashMap<>();
        _treeMap = new HashMap<>();
        treeFactories.put("PERENE", new PerennialTreeFactory());
        treeFactories.put("CADUCA", new DeciduousTreeFactory());
    }
    
    public void Register(Habitat habitat){super.register(habitat.getId(),habitat);}


    public void registerTree(String id,String name,int age, String type, int cleaningDifficulty, Hotel hotel) 
    throws DupTreeException{
        if (_treeMap.get(id) != null) { // tree id already exists
            throw new DupTreeException(id);
        }
        TreeFactory factory = treeFactories.get(type);
        if (factory != null) {
            Tree tree = factory.createTree(id,name, age, cleaningDifficulty, hotel);
            _treeMap.put(id, tree);  // Register globally
        }
    }
    
    public void plantTree(Habitat habitat, String id,String name, int age, String type, int cleaningDifficulty, Hotel hotel)
    throws DupTreeException{
        try{
            registerTree(id,name, age, type, cleaningDifficulty, hotel);
        }catch(DupTreeException de){
            
            throw new DupTreeException(id);
        }
        associateTreeToHabitat(habitat, id);
        
    }
    
    public void habitatExists(String habitatId) throws NoHabitatCoreException{
        if(getById(habitatId)== null){throw new NoHabitatCoreException(habitatId);}
    }
   public Tree findTree(String id){
    return _treeMap.get(id);
   }


    public void associateTreeToHabitat(Habitat habitat, String treeId){
        Tree tree = _treeMap.get(treeId);
        habitat.addTree(tree);  // Associate tree with the habitat
    }

    public void increaseAge(){
        for(Habitat habitat : super.getAll().values()){
            habitat.increaseAge();
        }
    }

    public Habitat addHabitat(String id, String name, int area)throws DupHabitatException{
        if(idIsRegistered(id)){throw new DupHabitatException(id);}
        Habitat hab = new Habitat(id, name, area);
        Register(hab);
        return hab;
    }

    public void transferToHabitat(Animal animal,Habitat newHabitat){
        animal.getHabitat().removeAnimal(animal.getId());
        animal.setHabitat(newHabitat);
        newHabitat.addAnimal(animal);
    }

   
    


   
}
