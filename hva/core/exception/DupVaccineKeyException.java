package hva.core.exception;

public class DupVaccineKeyException extends Exception{

    private String _vaccineId;

    public DupVaccineKeyException(String vaccineId) {
        super("Vaccine with ID: " + vaccineId + "already exists.");
        _vaccineId = vaccineId;
    }

    public String getId() {
        return _vaccineId;
    }
}


