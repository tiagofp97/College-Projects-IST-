package hva.app.habitat;
import hva.core.HabitatManager;
import hva.core.Hotel;
import hva.core.exception.NoHabitatCoreException;
import pt.tecnico.uilib.forms.Form;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.core.Tree;
import java.util.*;
//FIXME add more imports if needed

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {
  private HabitatManager _habitatManager;
  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    _habitatManager =receiver.getHabitatManager();
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(Prompt.habitatKey());
    try{
      _habitatManager.habitatExists(habitatId);
    }catch(NoHabitatCoreException ne){throw new UnknownHabitatKeyException(ne.getId());}
    
    List<Tree> trees = _habitatManager.getById(habitatId).getTreeList();
    for(Tree tree:trees){
      _display.addLine(tree.toString());
    }
    _display.display();
  }
}
