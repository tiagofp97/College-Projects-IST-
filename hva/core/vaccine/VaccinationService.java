package hva.core.vaccine;
import java.util.*;
import hva.core.employee.EmployeeManager;
import hva.core.employee.Responsibility;
import hva.core.employee.Veterinarian;
import hva.core.exception.*;
import java.io.Serializable;

import hva.core.*;

public class VaccinationService implements Serializable {
    private Boolean _isVaccineAdequate;
    private final AnimalManager _animalManager;
    private final VaccineManager _vaccineManager;
    private final EmployeeManager _employeeManager;

    Veterinarian _vet;
    Animal _animal;
    Vaccine _v;

    public VaccinationService(AnimalManager animalManager, VaccineManager vaccineManager, EmployeeManager employeeManager) {
        _animalManager = animalManager;
        _vaccineManager = vaccineManager;
        _employeeManager = employeeManager;
    }

    public void applyVaccine(String vaccineId, String vetId, String animalId) 
        throws NoVeterinarianKey, VeterinarianNotAuthorized, AnimalNotFound, NoVaccine {

        try{
            _animalManager.animalExists(animalId);
            _vaccineManager.vaccineExists(vaccineId);
            _employeeManager.employeeExists(vetId,Veterinarian.class);
            Veterinarian vet = (Veterinarian) _employeeManager.getById(vetId);
            Responsibility resp =_animalManager.getById(animalId).getSpecies();
            _employeeManager.employeeHasThisResponsibility(vet,resp);
            
        }catch(NoEmployeeKey e){
            throw new NoVeterinarianKey(e.getId());
        }catch(NoResponsibilityCoreException e){
            throw new VeterinarianNotAuthorized(e.getId());
        }catch(AnimalNotFound e){
            throw new AnimalNotFound(animalId);
        }catch(NoVaccine e){
            throw new NoVaccine(e.getId());
        }
    
    
    _v = _vaccineManager.getById(vaccineId);
    _vet = (Veterinarian)_employeeManager.getById(vetId);
    _animal = _animalManager.getById(animalId);
    VaccineApplication vaccineApplication = new VaccineApplication(_v, _vet, _animal);
    _isVaccineAdequate= _v.addVaccineApplication(vaccineApplication);
    _vet.addMedicalAct(vaccineApplication);
    _animal.addMedicalAct(vaccineApplication);
    // Proceed to apply the vaccine (increment application count or other logic)
    // vaccine.incrementApplicationCount();
    }
    public void isVaccineAdequate()throws VaccineNotAdequate{
        if(!_isVaccineAdequate){throw new VaccineNotAdequate(_v.getId(), _animal.getId());}
    }
    
}
