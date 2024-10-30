package hva.core.exception;

public class UnknownSpeciesCoreException extends Exception {
    private String speciesId;

    public UnknownSpeciesCoreException(String speciesId) {
        super("Unknown species with ID: " + speciesId);
        this.speciesId = speciesId;
    }

    public String getSpeciesId() {
        return speciesId;
    }
}
