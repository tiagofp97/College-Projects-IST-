package hva.app.habitat;
import pt.tecnico.uilib.forms.Form;
import hva.core.Habitat;
import hva.core.HabitatManager;
import hva.core.Hotel;
import hva.core.SpeciesManager;
import hva.core.exception.NoHabitatCoreException;
import hva.core.exception.UnknownSpeciesCoreException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {
  private HabitatManager _habitatManager;
  private SpeciesManager _speciesManager;
  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    _habitatManager = receiver.getHabitatManager();
    _speciesManager =receiver.getSpeciesManager();
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(Prompt.habitatKey());
    String speciesId = Form.requestString(hva.app.animal.Prompt.speciesKey());

    String habitatInfluence; 
    while (true) {
      habitatInfluence = Form.requestString(Prompt.habitatInfluence()).toUpperCase();
      if (habitatInfluence.equals("NEG") || habitatInfluence.equals("NEU")|| habitatInfluence.equals("POS")) break;
    }
    try{
      _habitatManager.habitatExists(habitatId);
      _speciesManager.speciesExists(speciesId);
    }catch(NoHabitatCoreException e){
      throw new UnknownHabitatKeyException(e.getId());
    }catch(UnknownSpeciesCoreException e){
      throw new UnknownSpeciesKeyException(e.getSpeciesId());
    }
    Habitat hab = _habitatManager.getById(habitatId);
    hab.changeInfluence(speciesId,habitatInfluence);

  }
}
