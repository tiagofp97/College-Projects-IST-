package hva.core.exception;

public class DuplicateAnimalKeyCoreException extends Exception {
    private String _animalId;
    public DuplicateAnimalKeyCoreException(String animalId) {
        super("ID: " + animalId+" already exists." );
        _animalId = animalId;
    }

    public String getId() {
        return _animalId;
    }
}
