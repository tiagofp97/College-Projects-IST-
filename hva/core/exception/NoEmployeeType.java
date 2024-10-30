package hva.core.exception;

public class NoEmployeeType extends Exception {
    private String _type;

    public NoEmployeeType(String type) {
        super("No such employee type: " + type);
        _type=type;
    }

    public String getType() {
        return _type;
    }
}
