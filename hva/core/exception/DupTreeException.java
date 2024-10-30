package hva.core.exception;

public class DupTreeException extends Exception{
    private String _treeId;

    public DupTreeException(String treeId) {
        super("Tree with ID: " + treeId + "already exists.");
        _treeId =treeId;
    }

    public String getId() {
        return _treeId;

    }
}
