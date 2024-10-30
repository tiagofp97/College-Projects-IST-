package hva.core.employee;
public interface SatisfactionStrategy {
    int calculateSatisfaction(Employee employee, EmployeeManager empManager);
}