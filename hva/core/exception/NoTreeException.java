package hva.core.exception;

public class NoTreeException extends Exception {

    private String _id;

    public NoTreeException(String id) {
        super("No such tree: " + id);
        _id = id;
    }

    public String getId() {
        return _id;
    }
}


