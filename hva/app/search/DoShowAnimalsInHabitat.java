package hva.app.search;

import hva.core.Animal;
import hva.core.Hotel;
import hva.core.exception.NoHabitatCoreException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.core.HabitatManager;
import hva.core.Habitat;
import pt.tecnico.uilib.forms.Form;
import java.util.*;
//FIXME add more imports if needed

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {
  private HabitatManager _habitatManager;
  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
     _habitatManager = receiver.getHabitatManager();
  }

  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());
    try{
      _habitatManager.habitatExists(habitatId);
    }catch (NoHabitatCoreException e){
      throw new UnknownHabitatKeyException(e.getId());
    }
    Habitat hab = _habitatManager.getById(habitatId);
    List<Animal> animals = hab.getAnimals(); // Retrieve sorted list of animals
        
        
            for (Animal animal : animals) {
                _display.addLine(animal.toString());
            }
        
        
        _display.display(); // Call display to render output
  }
}
