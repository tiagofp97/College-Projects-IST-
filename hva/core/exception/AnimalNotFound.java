package hva.core.exception;

public class AnimalNotFound extends Exception{
    private String _id;

    public AnimalNotFound(String id) {
        super("Animal with: " + id + " not found.");
        _id=id;
    }

    public String getId() {
        return _id;
    }
}
