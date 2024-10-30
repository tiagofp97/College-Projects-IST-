package hva.core.exception;

public class NoTreeTypeException extends Exception {

    private String _type;

    public NoTreeTypeException(String type) {
        super("No such tree type such as : " + type);
        _type=type;
    }

    public String getTreeType() {
        return _type;
    }


}
