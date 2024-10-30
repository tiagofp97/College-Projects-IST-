package hva.app.employee;
import pt.tecnico.uilib.forms.Form;
import hva.core.Hotel;
import hva.core.exception.DupEmployeeKeyException;
import hva.core.exception.NoEmployeeType;
import hva.app.exception.DuplicateEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {
  private Hotel _hotel;
  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    _hotel = receiver;

  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeId = Form.requestString(Prompt.employeeKey());
    String employeeName = Form.requestString(Prompt.employeeName());
    String employeeType;
    
    
    //Won't accept 
    while (true) {
      employeeType = Form.requestString(Prompt.employeeType());
      if (employeeType.equals("TRT") || employeeType.equals("VET")) break;
      
    }
    try{
      _hotel.registerEmployee(employeeId,employeeName,employeeType);
    } catch(DupEmployeeKeyException de) {
      throw new DuplicateEmployeeKeyException(de.getId());
    }catch(NoEmployeeType ne){
      System.out.println("Não existe tipo de funcionario");
    }
  }
}
