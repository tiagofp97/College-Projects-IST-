package hva.core;
import hva.core.exception.*;
import hva.core.Habitat;

public class AnimalManager extends Manager<Animal>{
    private SpeciesManager _speciesManager;
    private HabitatManager _habitatManager;

    public AnimalManager(SpeciesManager speciesManager, HabitatManager habitatManager) {
        super();
        _speciesManager = speciesManager;
        _habitatManager= habitatManager;
    }

    public void Register(Animal animal,Habitat habitat) {
        super.register(animal.getId(), animal);
        habitat.addAnimal(animal);
    }


    public void addAnimal(String id, String name, String speciesId, String habitatId) 
        throws DuplicateAnimalKeyCoreException,UnknownSpeciesCoreException,NoHabitatCoreException{
        
        if (idIsRegistered(id)) {
            throw new DuplicateAnimalKeyCoreException(id);
        }
        if(_speciesManager.idIsRegistered(speciesId) == false){
            throw new UnknownSpeciesCoreException(speciesId);
            
        }
        if(_habitatManager.idIsRegistered(habitatId)==false){throw new NoHabitatCoreException(habitatId);}

        Habitat habitat = _habitatManager.getById(habitatId);

        Animal animal = new Animal(id, name, _speciesManager.getById(speciesId),habitat); // Create new animal
        
        Register(animal,habitat); // Add animal to animals list and to an habitat
    }
    public void animalExists(String animalId) throws AnimalNotFound{
        if(getById(animalId)== null){throw new AnimalNotFound(animalId);}
    }

    
}
