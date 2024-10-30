package hva.core.vaccine;

import hva.core.employee.Veterinarian;
import hva.core.*;
import java.util.*;

public class Vaccine extends BaseEntity{
    
    private List<Species> _compatible;
    private List<Species> _compatibleUnsorted;
    private List<VaccineApplication>_vaccineApplications;

    // Constructor
    public Vaccine(String id,String name,List<Species> compatible) {
      super(id,name);
      _compatibleUnsorted = (compatible != null) ? compatible : new ArrayList<>(0);

        // Create a new list from _compatibleUnsorted and sort it using Collections.sort
        _compatible = new ArrayList<>(_compatibleUnsorted);
        Collections.sort(_compatible); // This uses the Comparable interface of BaseEntity

        _vaccineApplications = new ArrayList<>(0);
    }
    
    public boolean addVaccineApplication(VaccineApplication vaccineApplication){
      _vaccineApplications.add(vaccineApplication);
      return vaccineApplication.isVaccineAdequate();
    }
    public List<VaccineApplication> getVaccineApplications(){return Collections.unmodifiableList(_vaccineApplications);}

    public List<Species> getAllowedSpecies(){return Collections.unmodifiableList(_compatible);}
    public int getApplicationCount(){return _vaccineApplications.size();}
    public String getSpeciesCompatible() {
      StringBuilder compatible = new StringBuilder();
      
      for (Species species : _compatible) {
          compatible.append(species.getId()).append(",");
      }
  
      // Remove the trailing comma and space if compatible is not empty
      if (compatible.length() > 0) {
          compatible.setLength(compatible.length() - 1); // Remove last ", "
      }
  
      return compatible.toString();
    }
    @Override
    public String toString() {
        String speciesCompatible = getSpeciesCompatible();
        
        // If speciesCompatible is not empty, include it with the last '|', otherwise omit it.
        if (speciesCompatible.isEmpty()) {
            return String.format("VACINA|%s|%s|%d",
                                 getId(),
                                 getName(),
                                 getApplicationCount());
        } else {
            return String.format("VACINA|%s|%s|%d|%s",
                                 getId(),
                                 getName(),
                                 getApplicationCount(),
                                 getSpeciesCompatible());
        }
    }
    
  }