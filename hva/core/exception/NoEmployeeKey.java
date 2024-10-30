package hva.core.exception;

public class NoEmployeeKey extends Exception {
  
    private String _id;

    public NoEmployeeKey(String id) {
        super("Employee with ID: " + id + "doesn't exist.");
        _id = id;
    }

    public String getId() {
        return _id;
    }
}


