package hva.core.employee;
import java.util.*;
import java.io.*;
import hva.core.exception.NoResponsibilityCoreException;

public class ResponsibilityManager implements Serializable{
    private final Map<Class<? extends Employee>, ResponsibilityHandler> handlerMap = new HashMap<>();

    public ResponsibilityManager() {
        handlerMap.put(Veterinarian.class, new VeterinarianResponsibilityHandler());
        handlerMap.put(ZooKeeper.class, new ZooKeeperResponsibilityHandler());
        // Add other employee types if needed
    }

    public void addResponsibility(Employee emp, Responsibility responsibility) throws NoResponsibilityCoreException{
        ResponsibilityHandler handler = handlerMap.get(emp.getClass());
        if(handler==null)throw new NoResponsibilityCoreException("No responsibility with such id.");
        try{
            handler.addResponsibility(emp, responsibility);
        }catch(NoResponsibilityCoreException ne){throw new NoResponsibilityCoreException(ne.getId());}

    }
    public void removeResponsibility(Employee emp, Responsibility responsibility)throws NoResponsibilityCoreException {
        ResponsibilityHandler handler = handlerMap.get(emp.getClass());
        if(handler==null)throw new NoResponsibilityCoreException("No responsibility with such id.");
        try{
            handler.removeResponsibility(emp, responsibility);
        }catch(NoResponsibilityCoreException ne){throw new NoResponsibilityCoreException(ne.getId());}
    }

    
}
