package hva.core.employee;
import java.util.*;
import hva.core.Species;
import java.io.*;

public class VeterinarianSatisfactionStrategy implements SatisfactionStrategy,Serializable {
    @Override
    public int calculateSatisfaction(Employee employee, EmployeeManager empManager) {
        Veterinarian vet = (Veterinarian) employee;
        int sum = 0;
        List<Species> speciesList = vet.getResponsibilityList();
        
        for (Species sp : speciesList) {
            int n_vets = empManager.findAllResponsibleEmployees(Veterinarian.class, sp);
            int pop = sp.getPopulation();
            sum += pop / n_vets;
        }
        return Math.round(20 - sum);
    }
}