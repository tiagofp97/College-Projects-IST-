package hva.core.employee;
import java.util.function.BiPredicate;
import java.io.*;
import hva.core.Species;

public class VeterinarianCondition implements BiPredicate<Employee, Responsibility>,Serializable {
    @Override
    public boolean test(Employee emp, Responsibility resp) {
        return ((Veterinarian) emp).canVaccinate((Species) resp);
    }
}