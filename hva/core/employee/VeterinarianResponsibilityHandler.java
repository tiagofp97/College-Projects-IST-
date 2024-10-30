package hva.core.employee;
import hva.core.Species;
import hva.core.exception.NoResponsibilityCoreException;
import java.io.*;

public class VeterinarianResponsibilityHandler implements ResponsibilityHandler,Serializable {
    @Override
    public void addResponsibility(Employee emp, Responsibility responsibility) throws NoResponsibilityCoreException{
    
        try{
            checkInstance(responsibility);
            emp.addResponsibility(responsibility);
        }catch (NoResponsibilityCoreException ne){
            throw new NoResponsibilityCoreException(ne.getId());
        }
    }
    @Override
    public void removeResponsibility(Employee emp, Responsibility responsibility)throws NoResponsibilityCoreException {
        try{
            checkInstance(responsibility);
            emp.removeResponsibility(responsibility);
        }catch (NoResponsibilityCoreException ne){
            throw new NoResponsibilityCoreException(ne.getId());
        }
    }
    void checkInstance(Responsibility responsibility)throws NoResponsibilityCoreException{
        if (responsibility instanceof Species == false)throw new NoResponsibilityCoreException("No Responsibility");
    }
}