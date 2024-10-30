package hva.app.employee;

import hva.core.Hotel;
import hva.core.employee.EmployeeManager;
import hva.core.exception.NoEmployeeKey;
import hva.core.employee.*;
//import java.text.Normalizer.Form;
import pt.tecnico.uilib.forms.Form;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {
  
  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
      String employeeId = Form.requestString(Prompt.employeeKey());
      EmployeeManager empManager = _receiver.getEmployeeManager();
      try {
      
          empManager.employeeExists(employeeId);

      } catch (NoEmployeeKey ne) {
          throw new UnknownEmployeeKeyException(ne.getId());
      }
      Employee employee = empManager.getById(employeeId);
      int satisfaction = employee.getSatisfaction(empManager);
      _display.addLine(satisfaction);
      _display.display();
    }

    

  }
