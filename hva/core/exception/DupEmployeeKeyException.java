package hva.core.exception;

public class DupEmployeeKeyException extends Exception{
    private String _id;

    public DupEmployeeKeyException(String id) {
        super("Employee with ID: " + id + "already exists.");
        _id = id;
    }

    public String getId() {
        return _id;
    }
}
