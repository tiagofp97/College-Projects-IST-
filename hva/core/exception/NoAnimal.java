package hva.core.exception;

public class NoAnimal extends Exception {
    private String _animalId;
 
    public NoAnimal(String animalId) {
        super("No such veterinarian with ID: " + animalId);
        _animalId = animalId;
    }

    public String getId() {
        return _animalId;
    }
}