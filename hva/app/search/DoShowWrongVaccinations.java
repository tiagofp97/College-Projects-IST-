package hva.app.search;

import java.util.ArrayList;
import java.util.List;

import hva.core.Hotel;
import hva.core.vaccine.Vaccine;
import hva.core.vaccine.VaccineApplication;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all vaccines applied to animals belonging to an invalid species.
 **/
class DoShowWrongVaccinations extends Command<Hotel> {

  DoShowWrongVaccinations(Hotel receiver) {
    super(Label.WRONG_VACCINATIONS, receiver);
    //FIXME add command fields
  }

  @Override
  protected void execute() throws CommandException {
    {
    List<VaccineApplication> vaccineApplications = new ArrayList<>(0);
    List<Vaccine> vaccines = _receiver.getVaccineManager().getSortedEntities();
    
    // Iterate over each vaccine and collect its applications
    for (Vaccine v : vaccines) {
        // Add all vaccine applications from the current vaccine to the list
        vaccineApplications.addAll(v.getVaccineApplications());
    }

    // Iterate over the collected vaccine applications and display their information
    for (VaccineApplication va : vaccineApplications) {
        if(!va.isVaccineAdequate()){_display.addLine(va.toString());}
        
    }

    _display.display(); // Call display to render output
}
  }
}
