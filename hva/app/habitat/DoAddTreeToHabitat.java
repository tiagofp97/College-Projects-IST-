package hva.app.habitat;

import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.HabitatManager;
import hva.core.Hotel;
import hva.core.exception.DupTreeException;
import hva.core.exception.NoHabitatCoreException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.core.Habitat;

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {
  private HabitatManager _habitatManager;
  private Hotel _hotel;

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    _habitatManager = receiver.getHabitatManager();
    _hotel = receiver;
  }

  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(Prompt.habitatKey());
    
    

    // Check if habitat exists
    
    
    
    // Input other tree data
    String treeId = Form.requestString(Prompt.treeKey());
    String name = Form.requestString(Prompt.treeName());
    String age = Form.requestString(Prompt.treeAge());
    String treeDifficulty = Form.requestString(Prompt.treeDifficulty());
    String treeType;
    
    //Won't accept 
    while (true) {
      treeType = Form.requestString(Prompt.treeType());
      if (treeType.equals("PERENE") || treeType.equals("CADUCA")) break;
      
    }
    try{
      _hotel.getHabitatManager().habitatExists(habitatId);
    }catch(NoHabitatCoreException ne){
      throw new UnknownHabitatKeyException(ne.getId());
    }
    try{
      Habitat hab = _hotel.getHabitatManager().getById(habitatId);
      _hotel.getHabitatManager().plantTree(
        hab, treeId,name, Integer.valueOf(age), treeType, Integer.valueOf(treeDifficulty), _hotel);
      _display.addLine(hab.getById(treeId).toString());
      _display.display();
    }catch(DupTreeException de){
      throw new DuplicateTreeKeyException(de.getId());
    }
    
    }

    
  }
