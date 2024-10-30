package hva.core.employee;
import java.util.*;

import hva.core.exception.*;
import hva.core.Habitat;
import hva.core.Species;

public class ZooKeeper extends Employee{
    private List<Habitat> _responsabList;
   
    public ZooKeeper(String id, String name){
        super(id,name);
        _responsabList = new ArrayList<>();
       
    }
    public ZooKeeper(String id, String name, List<Habitat> responsibilities){
        super(id, name);
        _responsabList = new ArrayList<>(); // Initialize the list
        _responsabList.addAll(responsibilities); // Copy the responsibilities list
    }
    @Override
    public String toString() {
        StringBuilder responsibilities = new StringBuilder();

        // Get all the species this veterinarian is responsible for
        for (Habitat habitat : _responsabList) {
            responsibilities.append(habitat.getId()).append(",");
        }

        // Remove the trailing comma if the list is not empty
        if (responsibilities.length() > 0) {
            responsibilities.setLength(responsibilities.length() - 1); // Remove last comma
        }

        // If responsibilities are not empty, include them, otherwise omit that part
        if (responsibilities.length() > 0) {
            return String.format("TRT|%s|%s|%s",
                                getId(),
                                getName(),
                                responsibilities.toString());
        } else {
            return String.format("TRT|%s|%s",
                                getId(),
                                getName());
        }
}
    
    @Override
    public boolean hasThisResponsibility(Responsibility responsibility){
        return _responsabList.contains(responsibility);
    }

    @Override
    public void addResponsibility(Responsibility responsibility) throws NoResponsibilityCoreException {
        Habitat hab = (Habitat) responsibility;
      _responsabList.add(hab);
        
    }

    @Override
    public void removeResponsibility(Responsibility responsibility) throws NoResponsibilityCoreException {
        Habitat hab = (Habitat) responsibility;
        _responsabList.remove(hab);
    }




    public List<Habitat> getResponsibilityList(){
        return Collections.unmodifiableList(_responsabList);
    }
    @Override
    public int getRespCount(){
        return this.getResponsibilityList().size();
    }
    

    public boolean worksInHabitat(Habitat hab) {
        return _responsabList.contains(hab);
    }

    
    
}