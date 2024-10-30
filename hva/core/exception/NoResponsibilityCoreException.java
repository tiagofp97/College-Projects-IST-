package hva.core.exception;

public class NoResponsibilityCoreException extends Exception {
    private String _id;

    public NoResponsibilityCoreException(String id) {
        super("No Responsibility with this id : " + id);
        _id = id;
    }

    public String getId() {
        return _id;
    }
}
