package hva.core.employee;
import hva.core.exception.NoResponsibilityCoreException;
public interface ResponsibilityHandler {
    void addResponsibility(Employee emp, Responsibility responsibility) throws NoResponsibilityCoreException;
    void removeResponsibility(Employee emp, Responsibility responsibility)throws NoResponsibilityCoreException;
   
}