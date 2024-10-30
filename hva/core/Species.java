package hva.core;

import hva.core.employee.Responsibility;

/**
 * Represents a species in the hotel, including its population and name.
 * This class implements the Responsibility interface.
 */
public class Species extends BaseEntity implements Responsibility {
    
    // Current population of the species
    private int _population;

    /**
     * Constructs a Species instance with the given ID and name.
     *
     * @param id the unique identifier for the species
     * @param name the name of the species
     */
    public Species(String id, String name) {
        super(id, name);
        _population = 0; // Initializes population to zero
    }

    /**
     * Gets the current population of the species.
     *
     * @return the population of the species
     */
    public int getPopulation() {
        return _population;
    }

    /**
     * Increases the population of the species by one.
     */
    public void addToPopulation() {
        _population++;
    }
}
