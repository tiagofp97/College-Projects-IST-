package hva.app.animal;
import pt.tecnico.uilib.forms.Form;
import hva.core.AnimalManager;
import hva.core.HabitatManager;
import hva.core.Hotel;
import hva.core.exception.AnimalNotFound;
import hva.core.exception.NoHabitatCoreException;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {
  private AnimalManager _animalManager;
  private HabitatManager _habitatManager;
  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    _animalManager=hotel.getAnimalManager();
    _habitatManager =hotel.getHabitatManager();
  }
  
  @Override
  protected final void execute() throws CommandException {
    String animalId = Form.requestString(Prompt.animalKey());
    String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());
    try{
      _animalManager.animalExists(animalId);

    }catch(AnimalNotFound e){
      throw new UnknownAnimalKeyException(e.getId());
    }
    
    try{
      _habitatManager.habitatExists(habitatId);
    }catch(NoHabitatCoreException e){
      throw new UnknownHabitatKeyException(e.getId());
    }
    
    _habitatManager.transferToHabitat(_animalManager.getById(animalId),_habitatManager.getById(habitatId));

  }
}
