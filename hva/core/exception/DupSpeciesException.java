package hva.core.exception;

public class DupSpeciesException extends Exception {
    private String _id;
    public DupSpeciesException(String id) {
        super("Species with ID: " + id+" already exists." );
        _id = id;
    }

    public String getId() {
        return _id;
    }
}
