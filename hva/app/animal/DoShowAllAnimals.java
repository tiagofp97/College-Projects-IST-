package hva.app.animal;
import hva.core.Animal;
import hva.core.AnimalManager;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import java.util.*;
//FIXME add more imports if needed

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  private AnimalManager _animalManager;

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
    _animalManager = receiver.getAnimalManager();
  }
  
  @Override
  protected final void execute() {
    
        List<Animal> animals = _animalManager.getSortedEntities(); // Retrieve sorted list of animals
        
        // Check if there are any registered animals
        
        
            // Iterate over the list of animals and display their information
            for (Animal animal : animals) {
                _display.addLine(animal.toString());
            }
        
        _display.display(); // Call display to render output
    
  }
  
}
