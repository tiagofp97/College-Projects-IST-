package hva.core.exception;

public class DupHabitatException extends Exception{
    private String _habitatId;

    public DupHabitatException(String habitatId) {
        super("Habitat with ID: " + habitatId + "already exists.");
        _habitatId = habitatId;
    }

    public String getId() {
        return _habitatId;
    }
}
