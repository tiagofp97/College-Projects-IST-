package hva.core.employee;

import java.util.function.BiPredicate;
import java.io.*;

/**
 * Represents a condition that checks the compatibility between an employee and a responsibility.
 * This condition is used to determine if a specific employee type can fulfill a specific responsibility.
 */
public class Condition implements Serializable {
    private static final long serialVersionUID = 1L; // Version control for serialization

    private final Class<? extends Employee> _employeeType; // The type of employee this condition applies to
    private final Class<? extends Responsibility> _responsibilityType; // The type of responsibility this condition applies to
    private final BiPredicate<Employee, Responsibility> _check; // A predicate that defines the condition check

    /**
     * Constructs a new Condition with the specified employee type, responsibility type, and check.
     *
     * @param employeeType      The class of the employee type.
     * @param responsibilityType The class of the responsibility type.
     * @param check             The predicate that checks if the employee can fulfill the responsibility.
     */
    public Condition(Class<? extends Employee> employeeType, Class<? extends Responsibility> responsibilityType, BiPredicate<Employee, Responsibility> check) {
        _employeeType = employeeType;
        _responsibilityType = responsibilityType;
        _check = check;
    }

    /**
     * Checks if the specified employee satisfies this condition with the specified responsibility.
     *
     * @param emp The employee to check.
     * @param resp The responsibility to check.
     * @return true if the employee satisfies the condition with the responsibility; false otherwise.
     */
    public boolean isSatisfied(Employee emp, Responsibility resp) {
        return _employeeType.isInstance(emp) && _responsibilityType.isInstance(resp) && _check.test(emp, resp);
    }
}
