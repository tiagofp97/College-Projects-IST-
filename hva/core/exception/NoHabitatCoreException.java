package hva.core.exception;

public class NoHabitatCoreException extends Exception {
    private String _habitatId;

    public NoHabitatCoreException(String habitatId) {
        super("Unknown habitat with ID: " + habitatId);
        _habitatId = habitatId;
    }

    public String getId() {
        return _habitatId;
    }
   
}
