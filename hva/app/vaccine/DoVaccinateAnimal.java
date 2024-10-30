package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.AnimalNotFound;
import hva.core.exception.NoAnimal;
import hva.core.exception.NoVaccine;
import hva.core.exception.NoVeterinarianKey;
import hva.core.exception.VaccineNotAdequate;
import hva.core.exception.VeterinarianNotAuthorized;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import hva.core.vaccine.*;
//FIXME add more imports if needed

/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    
  }

  @Override
  protected final void execute() throws CommandException {
    String vaccineId = Form.requestString(Prompt.vaccineKey());
    String vetId = Form.requestString(Prompt.veterinarianKey());
    String animalId = Form.requestString(hva.app.animal.Prompt.animalKey());
    try{
      _receiver.applyVaccine(vaccineId,vetId,animalId);

    }catch(NoVeterinarianKey e){
      throw new UnknownVeterinarianKeyException(e.getId());
    }catch(VeterinarianNotAuthorized e){
      throw new VeterinarianNotAuthorizedException(e.getId(), animalId);
    }catch(AnimalNotFound e){
      throw new UnknownAnimalKeyException(e.getId());
    }catch(NoVaccine e){
      throw new UnknownVaccineKeyException(e.getId());
    }
      
    try{
      VaccinationService vService = _receiver.getVaccinationService();
      vService.isVaccineAdequate();
    }catch(VaccineNotAdequate ve){
      _display.addLine(Message.wrongVaccine(ve.getVaccId(),ve.getAnimalId()));
      _display.display();
    }
  }
}
