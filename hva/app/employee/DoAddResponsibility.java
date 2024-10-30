package hva.app.employee;
import pt.tecnico.uilib.forms.Form;
import hva.core.Hotel;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.NoResponsibilityException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.core.exception.*;
//FIXME add more imports if needed

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {
  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);

  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeId = Form.requestString(Prompt.employeeKey());
    String respoString = Form.requestString(Prompt.responsibilityKey());
    try{
      _receiver.addResponsibility(employeeId, respoString);
    }catch(NoResponsibilityCoreException nr){
      throw new NoResponsibilityException(employeeId, respoString);
    }catch(NoEmployeeKey ne){
      throw new UnknownEmployeeKeyException(ne.getId());
    }
  }
}
