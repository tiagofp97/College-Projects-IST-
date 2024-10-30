package hva.core.employee;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import hva.core.BaseEntity;
import hva.core.Habitat;
import hva.core.HabitatManager;
import hva.core.Manager;
import hva.core.exception.*;
import hva.core.Species;
import hva.core.SpeciesManager;

/**
 * Manages employees in the system, providing functionality for registration, responsibility assignment,
 * and condition handling for different types of employees (e.g., ZooKeepers and Veterinarians).
 */
public class EmployeeManager extends Manager<Employee> {

    private ResponsibilityManager _responsibilityManager;
    private final List<Condition> conditions = new ArrayList<>();
    private List<Manager> _managers = new ArrayList<>();

   
    /**
     * Constructs an EmployeeManager with the specified managers for species, habitats, and responsibilities.
     *
     * @param speciesManager the manager for species
     * @param habitatManager the manager for habitats
     * @param responsibilityManager the manager for responsibilities
     */
    public EmployeeManager(SpeciesManager speciesManager, HabitatManager habitatManager, ResponsibilityManager responsibilityManager) {
        super();
        _responsibilityManager = responsibilityManager;
        _managers.add(habitatManager);
        _managers.add(speciesManager);
        conditions.add(new Condition(ZooKeeper.class, Habitat.class, new ZooKeeperCondition()));
        conditions.add(new Condition(Veterinarian.class, Species.class, new VeterinarianCondition()));
    }

    /**
     * Registers an employee in the system.
     *
     * @param emp the employee to register
     */
    public void Register(Employee emp) {
        super.register(emp.getId(), emp);
    }

    /**
     * Adds a condition for employee responsibilities.
     *
     * @param employeeType the class of the employee type
     * @param responsibilityType the class of the responsibility type
     * @param check the predicate to evaluate the condition
     */
    public void addCondition(Class<? extends Employee> employeeType, Class<? extends Responsibility> responsibilityType, BiPredicate<Employee, Responsibility> check) {
        conditions.add(new Condition(employeeType, responsibilityType, check));
    }

    private static final Map<String, BiFunction<String, String, Employee>> employeeCreators = Map.of(
        "TRT", (id, name) -> new ZooKeeper(id, name),
        "VET", (id, name) -> new Veterinarian(id, name)
    );

    /**
     * Adds a new employee of a specified type to the system.
     *
     * @param id the ID of the employee
     * @param name the name of the employee
     * @param empType the type of the employee (e.g., "TRT" for ZooKeeper or "VET" for Veterinarian)
     * @throws DupEmployeeKeyException if an employee with the same ID already exists
     * @throws NoEmployeeType if the specified employee type is invalid
     */
    public void addEmployee(String id, String name, String empType) throws DupEmployeeKeyException, NoEmployeeType {
        try{
            checkDuplicateId(id);
        }catch(DuplicateId de){
            throw new DupEmployeeKeyException(id);
        }

        BiFunction<String, String, Employee> creator = employeeCreators.get(empType.toUpperCase());
        try{
            checkNull(creator,empType);
        }catch(NullObject e){
            throw new NoEmployeeType(empType);
        }

        Employee emp = creator.apply(id, name);
        emp.setSatisfactionStrategy(empType.equalsIgnoreCase("TRT") ? new ZooKeeperSatisfactionStrategy() : new VeterinarianSatisfactionStrategy());
        Register(emp);
    }

    /**
     * Adds a responsibility to a specified employee.
     *
     * @param empId the ID of the employee
     * @param responsibilityId the ID of the responsibility to add
     * @throws NoEmployeeKey if the employee ID is not found
     * @throws NoResponsibilityCoreException if the responsibility ID is invalid
     */
    public void addResponsibility(String empId, String responsibilityId) throws NoEmployeeKey, NoResponsibilityCoreException {
        try {
            checkNull(getById(empId));
        } catch (NullObject ne) {
            throw new NoEmployeeKey(empId);
        }

        Employee emp = getById(empId);
        Responsibility responsibility = getResponsibilityById(responsibilityId); // This retrieves either Species or Habitat
        
        try{
            Objects.requireNonNull(responsibility);
        }catch(NullPointerException e){
            throw new NoResponsibilityCoreException("Invalid Responsibility ID: " + responsibilityId);
        }
        if (!emp.hasThisResponsibility(responsibility)) {
            _responsibilityManager.addResponsibility(emp, responsibility);
        } else {
            throw new NoResponsibilityCoreException("Invalid Responsibility ID: " + responsibilityId);
        }
    }

    /**
     * Removes a responsibility from a specified employee.
     *
     * @param empId the ID of the employee
     * @param responsibilityId the ID of the responsibility to remove
     * @throws NoEmployeeKey if the employee ID is not found
     * @throws NoResponsibilityCoreException if the responsibility ID is invalid
     */
    public void removeResponsibility(String empId, String responsibilityId) throws NoEmployeeKey, NoResponsibilityCoreException {
        try {
            employeeExists(empId);
        } catch (NoEmployeeKey ne) {
            throw new NoEmployeeKey(ne.getId());
        }

        Employee emp = getById(empId);
        Responsibility responsibility = getResponsibilityById(responsibilityId); // This retrieves either Species or Habitat

        try {
            employeeHasThisResponsibility(emp, responsibility);
        } catch (NoResponsibilityCoreException ne) {
            throw new NoResponsibilityCoreException(ne.getId());
        }
        _responsibilityManager.removeResponsibility(emp, responsibility);
    }


    public List<Employee> getEmployees(int numResponsibilities){
        List<Employee>_output = new ArrayList<>(0);
        List<Employee>_emps = getSortedEntities();
        for(Employee emp : _emps){
           if(emp.getRespCount() > numResponsibilities){
                _output.add(emp);
           }
        }
        return _output;
    }

    /**
     * Retrieves a responsibility by its ID.
     *
     * @param responsibilityId the ID of the responsibility
     * @return the Responsibility object associated with the given ID
     * @throws NoResponsibilityCoreException if no responsibility is found with the specified ID
     */
    private Responsibility getResponsibilityById(String responsibilityId) throws NoResponsibilityCoreException {
        for (Manager manager : _managers) {
            BaseEntity baseEntity = manager.getById(responsibilityId);
            
            try {
                // If baseEntity is null, it will throw NullPointerException
                Objects.requireNonNull(baseEntity, "Responsibility not found");
                
                // If we reach this point, baseEntity is not null, so we return it
                return (Responsibility) baseEntity;
            } catch (NullPointerException e) {
                // Continue to the next manager if the entity is null
                // No need for handling as we simply continue the loop
            }
        }
        
        // If none of the managers returned a valid entity, throw the custom exception
        throw new NoResponsibilityCoreException("Responsibility not found");
    }

   
    public void employeeHasThisResponsibility(Employee emp, Responsibility resp) throws NoResponsibilityCoreException {
        if (!emp.hasThisResponsibility(resp)) {
            throw new NoResponsibilityCoreException(resp.getId());
        }
        
    }

    
    public Boolean employeeExists(String empId) throws NoEmployeeKey {
        if (!idIsRegistered(empId)) { // Check if the ID is not registered
            throw new NoEmployeeKey(empId); // Throw the exception if not found
        }
        return true; // Return true if the ID is registered
    }

  
    public void employeeExists(String empId, Class<?> employeeType) throws NoEmployeeKey {
        try{
            employeeExists(empId);
        }catch(NoEmployeeKey ne){
            throw new NoEmployeeKey(empId);
        }
        Employee emp = getById(empId); // Get the employee by ID
        
        if (employeeType.isInstance(emp)) {
            return; // Employee exists and is of the correct type
        }
        throw new NoEmployeeKey(empId); // Throw exception if not found or not the right type
    }

    
    /**
     * Counts all veterinarians that can vaccinate a specific species.
     *
     * @param sp the species to check against
     * @return the count of veterinarians that can vaccinate the specified species
     */
    public int findAllVets(Species sp) {
        return (int) getAll().values().stream()
            .filter(employee -> employee instanceof Veterinarian && ((Veterinarian) employee).canVaccinate(sp))
            .count();
    }

    /**
     * Counts all zookeepers working in a specific habitat.
     *
     * @param habitat the habitat to check against
     * @return the count of zookeepers working in the specified habitat
     */
    public int findAllZooKeepers(Habitat habitat) {
        return (int) getAll().values().stream()
            .filter(employee -> employee instanceof ZooKeeper && ((ZooKeeper) employee).worksInHabitat(habitat))
            .count();
    }

    /**
     * Counts all employees of a specific type that meet a specific responsibility condition.
     *
     * @param employeeType the class of the employee type
     * @param responsibility the responsibility to check
     * @return the count of responsible employees matching the specified type and conditions
     */
    public int findAllResponsibleEmployees(Class<? extends Employee> employeeType, Responsibility responsibility) {
        return (int) getAll().values().stream()
            .filter(employee -> employeeType.isInstance(employee)) // Check if it's the right employee type
            .filter(emp -> conditions.stream().anyMatch(condition -> condition.isSatisfied(emp, responsibility))) // Check if any condition is satisfied
            .count();
    }

}
