package hva.app.animal;
import hva.core.Habitat;
import hva.core.Animal;
import hva.core.AnimalManager;
import hva.core.Hotel;
import hva.core.exception.AnimalNotFound;
import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

//FIXME add more imports if needed

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {
  private AnimalManager _animalManager;
  
  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    _animalManager = receiver.getAnimalManager();
  }
  
  @Override
  protected final void execute() throws CommandException {
    String animalId = Form.requestString(Prompt.animalKey());
    try{
      _animalManager.animalExists(animalId);
    }catch(AnimalNotFound e){
      throw new UnknownAnimalKeyException(e.getId());
    }
    Animal animal = _animalManager.getById(animalId);
    Habitat hab = animal.getHabitat();
    int satisfaction = hab.computeSatisfaction(animal);
    _display.addLine(satisfaction);
    _display.display();
  }
}
