package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.DupVaccineKeyException;
import hva.core.exception.UnknownSpeciesCoreException;
import hva.core.vaccine.VaccineManager;
//import javafx.util.converter.FormatStringConverter;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.exception.*;
import hva.app.exception.DuplicateVaccineKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {
  private VaccineManager _vaccineManager;
  
  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    _vaccineManager = receiver.getVaccineManager();
  }

  @Override
  protected final void execute() throws CommandException {
    String vaccId = Form.requestString(Prompt.vaccineKey());
    String vaccName = Form.requestString(Prompt.vaccineName());
    String speciesList = Form.requestString(Prompt.listOfSpeciesKeys());
    String[] speciesIds = speciesList.split(",");
    try{
      _receiver.registerVaccine(vaccId,vaccName,speciesIds);
    }catch(DupVaccineKeyException e){
      throw new DuplicateVaccineKeyException(e.getId());
    }catch(UnknownSpeciesCoreException e){
      throw new UnknownSpeciesKeyException(e.getSpeciesId());
    }
  }
}
