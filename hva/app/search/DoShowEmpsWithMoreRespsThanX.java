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
class DoShowEmpsWithMoreRespsThanX extends Command<Hotel> {

    DoShowEmpsWithMoreRespsThanX(Hotel receiver) {
    super(Label.EMPLOYEE_WITH_MOST_RESPONSIBILITIES, receiver);
    
  }
  
  @Override
  protected void execute() throws CommandException {
   String respNumber = Form.requestString("Insert a number of responsibilities: ");
   List<Employee>_emps = _receiver.getEmployeeManager().getEmployees(Integer.valueOf(respNumber));
   for(Employee emp:_emps){
    _display.addLine(emp.toString());
   }
   _display.display();
  }
}
