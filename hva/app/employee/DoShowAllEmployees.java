package hva.app.employee;

import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.employee.Employee;
import pt.tecnico.uilib.menus.Command;
import hva.core.employee.*;
import java.util.*;
//FIXME add more imports if needed

/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {
  private Hotel _hotel;
  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
    _hotel = receiver;
  }
  
  @Override
  protected void execute() {
    List<Employee> employees = _hotel.getEmployeeManager().getSortedEntities();  // Obtém os habitats ordenados
    
    
     
      // Itera sobre o mapa de habitats e exibe suas informações
      for (Employee emp : employees) {
        String output = emp.toString();
        _display.addLine(output);
      }
    

    // Exibe a lista no terminal
    _display.display();
  }
}
