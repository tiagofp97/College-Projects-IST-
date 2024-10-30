package hva.core.exception;

public class NoVaccine extends Exception {
    private String _id;
 
    public NoVaccine(String id) {
        super("No such vaccine with ID: " + id);
        _id = id;
    }

    public String getId() {
        return _id;
    }
}