package hva.core;

import java.util.*;
import hva.core.vaccine.VaccineApplication;

/**
 * Represents an animal in the hotel, including its species, habitat, and health status.
 * This class implements the MedicalActHandler interface for managing medical acts.
 */
public class Animal extends BaseEntity implements MedicalActHandler {
    
    // The species of the animal
    private Species _species;

    private int _satisfaction = 0;
    
    // The habitat where the animal resides
    private Habitat _habitat;
    
    // The current health status of the animal
    private String _health;
    
    // Terms associated with the health status of the animal
    private String _terms = "";
    
    // List of medical acts (vaccine applications) performed on the animal
    private List<VaccineApplication> _medicalActs;

    /**
     * Constructs an Animal instance with the given ID, name, species, and habitat.
     *
     * @param id the unique identifier for the animal
     * @param name the name of the animal
     * @param species the species of the animal
     * @param habitat the habitat of the animal
     */
    public Animal(String id, String name, Species species, Habitat habitat) {
        super(id, name);
        _species = species;
        _habitat = habitat;
        _health = "VOID";
        _medicalActs = new ArrayList<>();
    }

    /**
     * Gets the current health status of the animal.
     *
     * @return the health status of the animal
     */
    public String getHealth() {
        return _health;
    }

    /**
     * Gets the species of the animal.
     *
     * @return the species of the animal
     */
    public Species getSpecies() {
        return _species;
    }

    public int getSatisfaction(){
        return _satisfaction;
    }
    public void setSatisfaction(int s){
        _satisfaction=s;
    }
    /**
     * Gets the habitat of the animal.
     *
     * @return the habitat of the animal
     */
    public Habitat getHabitat() {
        return _habitat;
    }

    /**
     * Sets the habitat of the animal.
     *
     * @param habitat the new habitat for the animal
     */
    public void setHabitat(Habitat habitat) {
        _habitat = habitat;
    }

    /**
     * Adds a medical act (vaccine application) to the animal's records.
     *
     * @param vApplication the vaccine application to be added
     */
    @Override
    public void addMedicalAct(VaccineApplication vApplication) {
        _medicalActs.add(vApplication);
    }

    /**
     * Gets an unmodifiable list of medical acts performed on the animal.
     *
     * @return a list of vaccine applications
     */
    @Override
    public List<VaccineApplication> getMedicalActs() {
        return Collections.unmodifiableList(_medicalActs);
    }

    /**
     * Changes the health status of the animal based on the damage taken and species differences.
     *
     * @param damage the amount of damage taken
     * @param speciesDifferent indicates whether the species is different
     */
    public void changeAnimalHealth(int damage, Boolean speciesDifferent) {
        // Define a map that associates damage and species differences to health statuses
        Map<Integer, Map<Boolean, HealthStatus>> healthMap = new HashMap<>();

        // Fill the map with health statuses
        for (int i = 0; i <= 4; i++) {
            healthMap.computeIfAbsent(i, k -> new HashMap<>())
                     .put(false, HealthStatus.NORMAL);
            healthMap.get(i).put(true, HealthStatus.CONFUSÃO);
        }

        healthMap.put(1, Map.of(false, HealthStatus.ACIDENTE, true, HealthStatus.ACIDENTE));
        healthMap.put(2, Map.of(false, HealthStatus.ACIDENTE, true, HealthStatus.ACIDENTE));
        healthMap.put(3, Map.of(false, HealthStatus.ACIDENTE, true, HealthStatus.ACIDENTE));
        healthMap.put(4, Map.of(false, HealthStatus.ACIDENTE, true, HealthStatus.ACIDENTE));
        healthMap.put(5, Map.of(false, HealthStatus.ERRO, true, HealthStatus.ERRO));

        // Determine health status based on damage
        _health = healthMap.getOrDefault(damage, new HashMap<>()).getOrDefault(speciesDifferent, HealthStatus.ERRO).toString();
        updateHealthStatus(_health);
    }

    /**
     * Updates the health status terms of the animal.
     *
     * @param term the term to be added to the health status
     */
    public void updateHealthStatus(String term) {
        _terms += term;
        _health = _terms;
    }

    /**
     * Returns a string representation of the animal.
     *
     * @return a formatted string with the animal's details
     */
    @Override
    public String toString() {
        return String.format("ANIMAL|%s|%s|%s|%s|%s|%s",
            getId(), getName(), getSpecies().getId(), getHealth(), getHabitat().getId(),getHabitat().computeSatisfaction(this));
    }
}
