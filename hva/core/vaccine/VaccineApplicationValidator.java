package hva.core.vaccine;
import hva.core.employee.Veterinarian;
import hva.core.exception.NoVeterinarianKey;
import hva.core.*;
import hva.core.exception.*;
public interface VaccineApplicationValidator {
    void validate(String vetId, String animalId, String vaccineId) throws NoVeterinarianKey,NoAnimal,NoVaccine,VeterinarianNotAuthorized;
}