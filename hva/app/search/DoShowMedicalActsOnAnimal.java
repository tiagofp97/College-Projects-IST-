package hva.app.search;

import hva.core.Hotel;
import hva.core.employee.Veterinarian;
import hva.core.exception.AnimalNotFound;
import hva.core.exception.NoEmployeeKey;
import hva.core.vaccine.VaccineApplication;
import pt.tecnico.uilib.forms.Form;
//import java.text.Normalizer.Form;
import java.util.List;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Animal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

  DoShowMedicalActsOnAnimal(Hotel receiver) {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    //FIXME add command fields
  }

    @Override
  protected void execute() throws CommandException {
   String animalId = Form.requestString(hva.app.animal.Prompt.animalKey());
   try{
    _receiver.getAnimalManager().animalExists(animalId);
    Animal animal =_receiver.getAnimalManager().getById(animalId);
    List<VaccineApplication>_medicalActs = animal.getMedicalActs();

    for(VaccineApplication va:_medicalActs){
      _display.addLine(va.toString());

    }
    _display.display();
   }catch(AnimalNotFound e){
      throw new UnknownAnimalKeyException(e.getId());
   }

  }
}


