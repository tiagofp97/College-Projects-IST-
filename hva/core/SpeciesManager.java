package hva.core;

import hva.core.exception.*;

/**
 * Manages the species within the hotel, providing methods to register and check species.
 */
public class SpeciesManager extends Manager<Species> {

    /**
     * Constructs a SpeciesManager instance.
     */
    public SpeciesManager() {
        super();
    }

    /**
     * Registers a species in the manager.
     *
     * @param species the species to be registered
     */
    public void Register(Species species) {
        super.register(species.getId(), species);
    }

    /**
     * Adds a new species to the manager.
     *
     * @param id the unique identifier for the species
     * @param name the name of the species
     * @throws DupSpeciesException if a species with the same ID already exists
     */
    public void addSpecies(String id, String name) throws DupSpeciesException {
        if (idIsRegistered(id)) {
            throw new DupSpeciesException(id);
        }

        Species sp = new Species(id, name);
        Register(sp);
    }

    /**
     * Checks if a species exists based on its ID.
     *
     * @param id the unique identifier of the species
     * @throws UnknownSpeciesCoreException if the species with the given ID does not exist
     */
    public void speciesExists(String id) throws UnknownSpeciesCoreException {
        if (!idIsRegistered(id)) {
            throw new UnknownSpeciesCoreException(id);
        }
    }
}
