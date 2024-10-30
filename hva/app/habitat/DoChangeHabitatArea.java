package hva.app.habitat;

import hva.core.HabitatManager;
import hva.core.Hotel;
import hva.core.exception.NoHabitatCoreException;
import pt.tecnico.uilib.forms.Form;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {
  private HabitatManager _habitatManager;
  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    _habitatManager =receiver.getHabitatManager();
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(Prompt.habitatKey());
    String area = Form.requestString(Prompt.habitatArea());
    try{
      _habitatManager.habitatExists(habitatId);
    }catch(NoHabitatCoreException ne){
      throw new UnknownHabitatKeyException(ne.getId());
    }
    
    _habitatManager.getById(habitatId).changeArea(Integer.valueOf(area));
  }
}
