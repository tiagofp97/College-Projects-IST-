package hva.app.habitat;
import hva.core.HabitatManager;
import hva.core.Hotel;
import hva.core.exception.DupHabitatException;
import hva.core.Habitat;
//import javafx.scene.control.Label;
//import java.text.Normalizer.Form;
import pt.tecnico.uilib.forms.Form;
import hva.app.exception.DuplicateHabitatKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {
  private HabitatManager _habitatManager;
  private Hotel _hotel;
  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    _habitatManager =receiver.getHabitatManager();
    _hotel = receiver;
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(Prompt.habitatKey());
    String habitatName = Form.requestString(Prompt.habitatName());
    if(habitatName.length()>habitatId.length()){throw new UnknownHabitatKeyException( "número inválido de animais no habitat.");}
    String area = Form.requestString(Prompt.habitatArea());
    

    try{
      int _area = Integer.valueOf(area);
      _hotel.registerHabitat(habitatId, habitatName,_area);
      
    }catch(DupHabitatException e){
      throw new DuplicateHabitatKeyException(e.getId());
    }
  }
}
