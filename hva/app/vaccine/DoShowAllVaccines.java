package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.vaccine.Vaccine;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.*;
//FIXME add more imports if needed

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {
  
  private Hotel _hotel;

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
    _hotel = receiver;
  }
  
  @Override
  protected final void execute() {
    List<Vaccine> vaccines = _hotel.getVaccineManager().getSortedEntities(); // Retrieve sorted list of vaccines
        
        // Check if there are any registered vaccines
        
        
            // Iterate over the list of vaccines and display their information
            for (Vaccine vaccine : vaccines) {
                _display.addLine(vaccine.toString());
            }
        
        
        _display.display(); // Call display to render output
  }
}
