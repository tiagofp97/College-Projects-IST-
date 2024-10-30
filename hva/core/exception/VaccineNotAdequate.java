package hva.core.exception;

public class VaccineNotAdequate extends Exception {

    private String _vaccId;
    private String _animalId;
    public VaccineNotAdequate(String vaccId,String animalId) {
        super("Vaccine "+vaccId +"not adequate to " +animalId + " animal");
        _animalId = animalId;
        _vaccId = vaccId;
    }

    public String getAnimalId() {return _animalId;}

    public String getVaccId(){return _vaccId;}


}
