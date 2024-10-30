package hva.core.exception;

public class DuplicateId extends Exception{
    private String _id;
    public DuplicateId(String id) {
        super("ID: " + id +" already exists." );
        _id=id;
    }

    public String getId() {
        return _id;
    }
}
