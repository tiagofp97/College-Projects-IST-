package hva.core;

import hva.core.employee.EmployeeManager;
import hva.core.employee.Responsibility;
import hva.core.employee.ResponsibilityManager;
import hva.core.employee.Veterinarian;
import hva.core.exception.*;
import hva.core.Parser;
import java.io.*;
import java.util.*;
import hva.core.vaccine.*;
import hva.core.employee.*;

// Class representing a hotel that manages various entities such as animals, employees, vaccines, and habitats.
public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    // Core attributes of the hotel
    private Species species; // Placeholder for species if needed in future
    private AnimalManager _animalManager; // Manages animals within the hotel
    private SpeciesManager _speciesManager; // Manages species
    private HabitatManager _habitatManager; // Manages habitats
    private EmployeeManager _employeeManager; // Manages employees
    private ResponsibilityManager _respManager; // Manages employee responsibilities
    private VaccineManager _vaccineManager; // Manages vaccines
    private Season _currentSeason; // Current season of the hotel
    private Species sp; // Placeholder for species if needed in future
    private VaccinationService _vaccinationService; // Service to handle vaccinations
    private transient Parser _parser; // Responsible for parsing input files
    private Boolean _changesMade = false; // Tracks if changes have been made

    // Constructor initializes all managers and sets the initial season
    public Hotel() {
        setSeason(Season.SPRING);
        _speciesManager = new SpeciesManager();
        _habitatManager = new HabitatManager();
        _animalManager = new AnimalManager(_speciesManager, _habitatManager);
        _respManager = new ResponsibilityManager();
        _employeeManager = new EmployeeManager(_speciesManager, _habitatManager, _respManager);
        _vaccineManager = new VaccineManager(_speciesManager);
        _vaccinationService = new VaccinationService(_animalManager, _vaccineManager, _employeeManager);
        _parser = new Parser(this);
    }

    // Getters for managers and services
    public AnimalManager getAnimalManager() { return _animalManager; }
    public SpeciesManager getSpeciesManager() { return _speciesManager; }
    public HabitatManager getHabitatManager() { return _habitatManager; }
    public EmployeeManager getEmployeeManager() { return _employeeManager; }
    public VaccineManager getVaccineManager() { return _vaccineManager; }
    public Season getCurrentSeason() { return _currentSeason; }
    public VaccinationService getVaccinationService() { return _vaccinationService; }

    // Increments the age of all habitats
    public void increaseAge() {
        _habitatManager.increaseAge();
    }

    // Registers a new animal and updates species population
    public void registerAnimal(String id, String name, String speciesId, String habitatId) 
            throws DuplicateAnimalKeyCoreException, UnknownSpeciesCoreException, NoHabitatCoreException {
        _animalManager.addAnimal(id, name, speciesId, habitatId);
        _speciesManager.getById(speciesId).addToPopulation();
    }

    // Computes global employee and habitat satisfaction
    public int getGlobalSatisfaction() {
        int globalSatisfaction = 0;
        for (Employee emp : _employeeManager.getAll().values()) {
            globalSatisfaction += emp.getSatisfaction(_employeeManager);
        }
        for (Animal an : _animalManager.getAll().values()) {
            globalSatisfaction += an.getHabitat().computeSatisfaction(an);
        }
        return Math.round(globalSatisfaction);
    }

    // Registers a new species
    public void registerSpecies(String id, String name) throws DupSpeciesException {
        _speciesManager.addSpecies(id, name);
    }

    // Registers a new employee
    public void registerEmployee(String id, String name, String empType) 
            throws DupEmployeeKeyException, NoEmployeeType {
        _employeeManager.addEmployee(id, name, empType);
    }

    // Adds a responsibility to an employee
    public void addResponsibility(String empId, String responsibilityId) 
            throws NoResponsibilityCoreException, NoEmployeeKey {
        _employeeManager.addResponsibility(empId, responsibilityId);
    }

    // Removes a responsibility from an employee
    public void removeResponsibility(String empId, String responsibilityId) 
            throws NoResponsibilityCoreException, NoEmployeeKey {
        _employeeManager.removeResponsibility(empId, responsibilityId);
    }

    // Registers a new vaccine
    public void registerVaccine(String id, String name, String[] speciesIds) 
            throws DupVaccineKeyException, UnknownSpeciesCoreException {
        _vaccineManager.addVaccine(id, name, speciesIds);
    }

    // Creates a new tree and registers it in the habitat
    public void createTree(String id, String name, String type, int age, int diff) 
            throws DupTreeException {
        _habitatManager.registerTree(id, name, age, type, diff, this);
    }

    // Registers a new habitat
    public Habitat registerHabitat(String id, String name, int area) 
            throws DupHabitatException {
        return _habitatManager.addHabitat(id, name, area);
    }

    // Adds a tree to a specified habitat
    public void addTreeToHabitat(String treeId, Habitat habitat) throws NoTreeException {
        if (_habitatManager.findTree(treeId) == null) {
            throw new NoTreeException(treeId);
        }
        _habitatManager.associateTreeToHabitat(habitat, treeId);
    }

    // Applies a vaccine to an animal by a veterinarian
    public void applyVaccine(String vaccineId, String vetId, String animalId) 
            throws NoVeterinarianKey, VeterinarianNotAuthorized, AnimalNotFound, NoVaccine {
        _vaccinationService.applyVaccine(vaccineId, vetId, animalId);
    }

    // Sets the current season
    public void setSeason(Season season) {
        _currentSeason = season;
    }

    // Advances the season and increases habitat ages
    public int advanceSeason() {
        _currentSeason = _currentSeason.nextSeason(); // Advances to the next season
        increaseAge();
        return _currentSeason.ordinal(); // Returns the index of the last season
    }

    // Gets whether any changes have been made
    public Boolean getChangesMade() {
        return _changesMade;
    }

    // Sets the change status
    public void setChangesMade(Boolean val) {
        _changesMade = val;
    }

    /**
     * Reads text input file and creates corresponding domain entities.
     * 
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws IOException if there is an IO error while processing the text file
     */
    public void importFile(String filename) throws UnrecognizedEntryException, IOException {
        _parser.parseFile(filename);
    }
}
