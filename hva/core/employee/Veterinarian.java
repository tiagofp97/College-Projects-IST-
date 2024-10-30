package hva.core.employee;
import java.util.*;
import hva.core.exception.*;
import hva.core.vaccine.VaccineApplication;
import hva.core.MedicalActHandler;
import hva.core.Species;

public class Veterinarian extends Employee implements MedicalActHandler{
    private List<Species> _responsabList;
    private List<VaccineApplication>_medicalActs;

    public Veterinarian(String id, String name){
        super(id,name);
        _responsabList = new ArrayList<>();
        _medicalActs = new ArrayList<>();
    }
    public Veterinarian(String id, String name, List<Species> responsibilities) {
        super(id, name);
        _responsabList = new ArrayList<>(); // Initialize the list
        _responsabList.addAll(responsibilities); // Copy the responsibilities list
    }

    @Override
    public void addMedicalAct(VaccineApplication vApplication){_medicalActs.add(vApplication);}
    @Override
    public List<VaccineApplication> getMedicalActs(){
        return Collections.unmodifiableList(_medicalActs);
    }
    
    @Override
    public void addResponsibility(Responsibility responsibility) throws NoResponsibilityCoreException {
        Species sp = (Species) responsibility;
        _responsabList.add(sp);
        
    }

    public void removeResponsibility(Responsibility responsibility) throws NoResponsibilityCoreException {
        Species sp = (Species) responsibility;
        _responsabList.remove(sp);

    }

    @Override
    public boolean hasThisResponsibility(Responsibility responsibility){
        return _responsabList.contains(responsibility);
    }
    
    public Responsibility getResponsibilityById(String id) throws NoResponsibilityCoreException{
        for(Responsibility resp :_responsabList){
            if(resp.getId().equals(id)){
                return resp;
            }
        }
        throw new NoResponsibilityCoreException(id);
    }
 
    @Override
    public String toString() {
        StringBuilder responsibilities = new StringBuilder();

        // Get all the species this veterinarian is responsible for
        for (Species species : _responsabList) {
            responsibilities.append(species.getId()).append(",");
        }

        // Remove the trailing comma if the list is not empty
        if (responsibilities.length() > 0) {
            responsibilities.setLength(responsibilities.length() - 1); // Remove last comma
        }

        // If responsibilities are not empty, include them, otherwise omit that part
        if (responsibilities.length() > 0) {
            return String.format("VET|%s|%s|%s",
                                getId(),
                                getName(),
                                responsibilities.toString());
        } else {
            return String.format("VET|%s|%s",
                                getId(),
                                getName());
        }
        }
    
    
    public List<Species> getResponsibilityList(){
        return Collections.unmodifiableList(_responsabList);
    }
    @Override
    public int getRespCount(){
        return getResponsibilityList().size();
    }
    
    public boolean canVaccinate(Species sp) {
        return _responsabList.contains(sp);
    }


}