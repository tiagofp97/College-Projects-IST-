package hva.app.search;

public interface Prompt {
 
  static String employeeName() {
    return "Nome do funcionário: ";
  }
  
  static String employeeType() {
    return "Tipo do funcionário? (VET ou TRT) ";
  }
  
  static String responsibilityKey() {
    return "Identificador único da responsabilidade: ";
  }
  
}
