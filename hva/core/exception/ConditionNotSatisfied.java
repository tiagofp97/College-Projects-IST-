package hva.core.exception;


public class ConditionNotSatisfied extends Exception{
    private String _message;

    public ConditionNotSatisfied(String message) {
        super("Condition ");
        _message = message;
    }

    public String getMessage() {
        return _message;
    }
}
