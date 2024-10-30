package hva.app.search;
import pt.tecnico.uilib.forms.Form;
import hva.core.Hotel;
import hva.app.exception.UnknownVeterinarianKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.core.employee.*;
import hva.core.exception.NoEmployeeKey;
import hva.core.vaccine.*;
import java.util.*;
//FIXME add more imports if needed

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    
  }
  
  @Override
  protected void execute() throws CommandException {
   String vetId = Form.requestString(hva.app.employee.Prompt.employeeKey());
   try{
    _receiver.getEmployeeManager().employeeExists(vetId, Veterinarian.class);
    Veterinarian vet =(Veterinarian) _receiver.getEmployeeManager().getById(vetId);
    List<VaccineApplication>_medicalActs = vet.getMedicalActs();

    for(VaccineApplication va:_medicalActs){
      _display.addLine(va.toString());

    }
    _display.display();
   }catch(NoEmployeeKey ne){
    throw new UnknownVeterinarianKeyException(ne.getId());
   }

  }
}
