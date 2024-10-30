package hva.app.vaccine;

import java.util.ArrayList;
import java.util.List;

import hva.core.Hotel;
import hva.core.vaccine.Vaccine;
import hva.core.vaccine.VaccineApplication;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all applied vacines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
  }
  
  @Override
  protected final void execute() {
    List<VaccineApplication> vaccineApplications = new ArrayList<>(0);
    List<Vaccine> vaccines = _receiver.getVaccineManager().getSortedEntities();
    
    // Iterate over each vaccine and collect its applications
    for (Vaccine v : vaccines) {
        // Add all vaccine applications from the current vaccine to the list
        vaccineApplications.addAll(v.getVaccineApplications());
    }

    // Iterate over the collected vaccine applications and display their information
    for (VaccineApplication va : vaccineApplications) {
        _display.addLine(va.toString());
    }

    _display.display(); // Call display to render output
}
}