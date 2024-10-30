package hva.core.vaccine;
import hva.core.Manager;
import hva.core.SpeciesManager;
import hva.core.exception.DupVaccineKeyException;
import hva.core.exception.NoVaccine;
import hva.core.exception.UnknownSpeciesCoreException;
//import javafx.css.SimpleStyleableIntegerProperty;
import hva.core.Species;
import java.util.*;
public class VaccineManager extends Manager<Vaccine> {
    
    private SpeciesManager _speciesManager;
    
    
    public VaccineManager(SpeciesManager speciesManager) {
        super();
        _speciesManager= speciesManager;
       

        

    }

    public void Register(Vaccine vaccine) {
        super.register(vaccine.getId(), vaccine);
    }


    public void addVaccine(String id, String name,String[] speciesIds)
        throws DupVaccineKeyException, UnknownSpeciesCoreException{

        List<Species> _speciesList= new ArrayList<>(0);

        if(idIsRegistered(id)){throw new DupVaccineKeyException(id);}
        for (String speciesId : speciesIds) {
            if(_speciesManager.idIsRegistered(speciesId)==false){throw new UnknownSpeciesCoreException(speciesId);}
            _speciesList.add(_speciesManager.getById(speciesId));
        }

        Vaccine vac = new Vaccine(id, name, _speciesList);
        Register(vac);

    }
    public void vaccineExists(String vaccineId)throws NoVaccine{
        if(!idIsRegistered(vaccineId)){throw new NoVaccine(vaccineId);}
    }

}