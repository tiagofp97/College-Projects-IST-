package hva.app.animal;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.*;
import hva.core.exception.DuplicateAnimalKeyCoreException;
import hva.core.exception.NoHabitatCoreException;
import hva.core.exception.UnknownSpeciesCoreException;
import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {
    private Hotel _hotel;
    DoRegisterAnimal(Hotel receiver) {
        super(Label.REGISTER_ANIMAL, receiver);
        _hotel = receiver;
    }

    @Override
    protected final void execute() throws CommandException {
        // Gather animal information from user
        String id = Form.requestString(Prompt.animalKey());
        String name = Form.requestString(Prompt.animalName());
        String speciesId = Form.requestString(Prompt.speciesKey());
        String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());

        try {
            
            _hotel.registerAnimal(id, name, speciesId, habitatId);

        } catch (DuplicateAnimalKeyCoreException e) {

            throw new DuplicateAnimalKeyException(e.getId());

        } catch (NoHabitatCoreException e) {

            throw new UnknownHabitatKeyException(e.getId());

        } catch (UnknownSpeciesCoreException e) {
            
            String speciesName = Form.requestString(Prompt.speciesName());
            Species species = new Species(speciesId, speciesName);
            _hotel.getSpeciesManager().Register(species); 

            
            try {
                _hotel.registerAnimal(id, name, speciesId, habitatId); // Reattempt adding the animal
                
            } catch (DuplicateAnimalKeyCoreException e2) {

                throw new DuplicateAnimalKeyException(e2.getId());

            } catch (NoHabitatCoreException e2) {

                throw new UnknownHabitatKeyException(e2.getId());

            } catch (UnknownSpeciesCoreException e2) {
                
                throw new UnknownSpeciesKeyException(e2.getSpeciesId()); 
            }
        }
    }
}
