package hva.core;
import java.io.IOException;
import java.util.*;
import hva.core.employee.Responsibility;


public class Habitat extends BaseEntity implements Responsibility{
    
    private int _area;
    private LinkedList<Animal>_animals;
    private HashMap<String,Integer>_influenceOnSpecie;
    private HashMap<String,Tree>_trees;

     public Habitat (String id, String name,int area){
        super(id,name);
        _area = area;
        _animals = new LinkedList<>();
        _influenceOnSpecie = new HashMap<>();
        _trees = new HashMap<>();
    }

    /*
     * Logic for the Animal management in an habitat
     */
    public List<Animal> getAnimals() {
        List<Animal> sortedList = new LinkedList<>(_animals);  
        Collections.sort(sortedList); 
        return Collections.unmodifiableList(sortedList);
    }


    /*
     * Logic of adding entities to the habitat
     */
    public void addAnimal(Animal animal){_animals.add(animal);}
    public void removeAnimal(String animalId){
        _animals.removeIf(animal -> animal.getId().equals(animalId));
    }
    public void addTree(Tree tree){_trees.put(tree.getId(),tree);}



    /*
     * Logic for the tree management
     */

    public Tree getById(String id) {
        for (String key : _trees.keySet()) {
            if (key.equals(id)) {
                return _trees.get(key); 
            }
        }
        return null; 
    }
    public void increaseAge(){
        for(Tree tree : _trees.values()){
            tree.increaseAge();
        }
    }

    /*
     * Satisfaction logic
     */
    public int InfluenceOnSpecies(Animal animal){
        int influence =0;
        for (String speciesId : _influenceOnSpecie.keySet()) {
            if (speciesId.equals(animal.getSpecies().getId())) {
                influence = _influenceOnSpecie.get(speciesId);
            }
        }
        return influence; //Can only return 1, 0 , or -1 | 1 FOR POSITIVE INFLUENCE, 0 FOR NEUTRAL, -1 FOR NEGATIVE 
    }

    public int computeSatisfaction(Animal animal){
        int satisfaction;
        int population = _animals.size();
        int equalSpecies = getEqualSpecies(animal)-1;
        int difSpecies = population-equalSpecies-1;

        int adequation = InfluenceOnSpecies(animal) *20;
        //System.out.println("POPULATION-> "+population+" EQSPCS-> "+equalSpecies+" DIFSPCS-> "+difSpecies+" ADQ-> "+adequation+" AREA-> "+_area);
    
        satisfaction = 20 + 3*equalSpecies-2*difSpecies+(_area/population) + adequation;
        animal.setSatisfaction(Math.round(satisfaction));
        return Math.round(satisfaction);
    }
    public Animal getMostSatisfactAnimal(){
     
       Animal outputAnimal = null;
        int biggerSat = 0;
        for(Animal animal:_animals){
            int satisfaction = computeSatisfaction(animal);
            if((satisfaction)>biggerSat){
                outputAnimal = animal;
                biggerSat=satisfaction;
            }
            if(satisfaction == biggerSat){
                if(outputAnimal.getName().length()<animal.getName().length()){
                    outputAnimal=animal;
                }
            }
        }
        return outputAnimal;
    }
    private int getEqualSpecies(Animal animal) {
        int count = 0;
        String targetSpeciesId = animal.getSpecies().getId(); // Get the species ID of the input animal
        
        // Iterate over the list of animals in the habitat
        for (Animal a : _animals) {
            if (a.getSpecies().getId().equals(targetSpeciesId)) {
                count++; // Increment the count if the species ID matches
            }
        }
        return count; // Return the total count of animals with the same species
    }
    
    public void changeInfluence(String speciesId, String influence){
        int val;
        switch (influence) { // Convert to uppercase for case-insensitive matching
            case "NEG":
                val = -1;
                break;
            case "POS":
                val = 1;
                break;
            case "NEU":
                val = 0;
                break;
            default:
                throw new IllegalArgumentException("Invalid influence value: " + influence);
        }
        
        _influenceOnSpecie.put(speciesId, val);
        
    }

    /*
     * Getters and setters
     */
    public int getArea(){return _area;}
    public int getNumberOfTrees(){return _trees.size();}
    public void changeArea(int area){_area = area;}

    public Map<String, Tree> getTreeMap() {
        return Collections.unmodifiableMap(_trees);
    }
    public List<Tree> getTreeList() {
        List<Tree> treeList = new ArrayList<>(_trees.values()); // Create a list from the map values
        Collections.sort(treeList); // Sort the list using the compareTo method defined in Tree
        return Collections.unmodifiableList(treeList); // Return an unmodifiable list
    }

    public int getWork(){
        int sumCleaningEffort=0;
        for(Tree tree:_trees.values()){
            sumCleaningEffort+=tree.computeCleaningEffort();
        }
        return _area + 3* _animals.size() + sumCleaningEffort;
    }

   
   
    @Override 
    public String toString(){
        return String.format("HABITAT|%s|%s|%d|%d",
        getId(),
        getName(),
        getArea(),
        getNumberOfTrees());
    }
}