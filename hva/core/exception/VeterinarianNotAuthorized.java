package hva.core.exception;

public class VeterinarianNotAuthorized extends Exception {
    private String _veterinarianId;
 
    public VeterinarianNotAuthorized(String veterinarianId) {
        super("Veterinarian Not Authorized with id " + veterinarianId);
        _veterinarianId = veterinarianId;
    }

    public String getId() {
        return _veterinarianId;
    }
}