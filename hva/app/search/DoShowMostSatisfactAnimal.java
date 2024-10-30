
package hva.app.search;

import hva.core.Hotel;
import hva.core.employee.Veterinarian;
import hva.core.exception.AnimalNotFound;
import hva.core.exception.NoEmployeeKey;
import hva.core.vaccine.VaccineApplication;
import pt.tecnico.uilib.forms.Form;

import java.util.ArrayList;
//import java.text.Normalizer.Form;
import java.util.List;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Animal;
import hva.core.Habitat;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMostSatisfactAnimal extends Command<Hotel> {

  DoShowMostSatisfactAnimal(Hotel receiver) {
    super("Show most satisfact animal.", receiver);
    //FIXME add command fields
  }

    @Override
  protected void execute() throws CommandException {
   List<Habitat>_habitats = _receiver.getHabitatManager().getSortedEntities();
   List<Animal>_animals = new ArrayList<>(0);
   for(Habitat hab: _habitats){
    for(Animal an : hab.getAnimals()){
        _animals.add(an);
    }
    
   }
   Animal output= null;
   int maxSatisfaction =0;
   for(Animal animal : _animals){
    int satisfaction = animal.getHabitat().computeSatisfaction(animal);
    if(satisfaction >maxSatisfaction){
        maxSatisfaction=satisfaction;
        output = animal;
    }
    if(satisfaction == maxSatisfaction){
        if(output!=null){
            if(animal.getName().length() > output.getName().length()){
                output = animal;
                maxSatisfaction = satisfaction;
            }
        }
    }
   }
   
   if(output==null){
    return;
   }else{
    _display.addLine(output.toString());
   }
   
   _display.display();
  }
}


