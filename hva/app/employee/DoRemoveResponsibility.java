package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.NoEmployeeKey;
import hva.core.exception.NoResponsibilityCoreException;
import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeId = Form.requestString(Prompt.employeeKey());
    String respoString = Form.requestString(Prompt.responsibilityKey());
    try{
      _receiver.removeResponsibility(employeeId, respoString);
    }catch(NoResponsibilityCoreException nr){
      throw new NoResponsibilityException(employeeId, respoString);
    }catch(NoEmployeeKey ne){
      throw new UnknownEmployeeKeyException(ne.getId());
    }
  }
}
