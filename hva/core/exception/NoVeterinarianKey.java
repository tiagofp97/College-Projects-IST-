package hva.core.exception;

public class NoVeterinarianKey extends Exception {
    private String _veterinarianId;
 
    public NoVeterinarianKey(String veterinarianId) {
        super("No such veterinarian with ID: " + veterinarianId);
        _veterinarianId = veterinarianId;
    }

    public String getId() {
        return _veterinarianId;
    }
}

