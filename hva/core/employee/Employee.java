package hva.core.employee;
import java.util.*;
import hva.core.BaseEntity;
import hva.core.exception.NoResponsibilityCoreException;

/**
 * Represents an abstract employee entity within the system.
 * This class serves as a base for different types of employees (e.g., Veterinarian, ZooKeeper),
 * providing common functionality and enforcing specific behaviors related to responsibilities
 * and satisfaction calculations.
 */
public abstract class Employee extends BaseEntity {
    private SatisfactionStrategy satisfactionStrategy; // Strategy for calculating employee satisfaction

    /**
     * Constructs an Employee with the specified ID and name.
     *
     * @param id   The unique identifier for the employee.
     * @param name The name of the employee.
     */
    public Employee(String id, String name) {
        super(id, name);
    }

    /**
     * Sets the satisfaction strategy for this employee.
     *
     * @param strategy The satisfaction strategy to be set.
     */
    public void setSatisfactionStrategy(SatisfactionStrategy strategy) {
        this.satisfactionStrategy = strategy;
    }

    /**
     * Adds a responsibility to this employee.
     *
     * @param responsibility The responsibility to be added.
     * @throws NoResponsibilityCoreException if the responsibility cannot be added.
     */
    public abstract void addResponsibility(Responsibility responsibility) throws NoResponsibilityCoreException;

    /**
     * Removes a responsibility from this employee.
     *
     * @param responsibility The responsibility to be removed.
     * @throws NoResponsibilityCoreException if the responsibility cannot be removed.
     */
    public abstract void removeResponsibility(Responsibility responsibility) throws NoResponsibilityCoreException;

    /**
     * Checks if this employee has a specific responsibility.
     *
     * @param responsibility The responsibility to check.
     * @return true if this employee has the specified responsibility; false otherwise.
     */
    public abstract boolean hasThisResponsibility(Responsibility responsibility);

    /**
     * Calculates the satisfaction of this employee based on the current satisfaction strategy.
     *
     * @param empManager The EmployeeManager managing this employee.
     * @return The calculated satisfaction score.
     * @throws IllegalStateException if the satisfaction strategy is not set.
     */
    public int getSatisfaction(EmployeeManager empManager) {
        if (satisfactionStrategy != null) {
            return satisfactionStrategy.calculateSatisfaction(this, empManager);
        }
        throw new IllegalStateException("Satisfaction strategy not set");
    }

    public abstract int getRespCount();
}
