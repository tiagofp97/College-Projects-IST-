package hva.app.habitat;

import hva.core.HabitatManager;
import hva.core.Hotel;
import hva.core.Habitat;
import hva.core.Tree;
import pt.tecnico.uilib.menus.Command;
import java.util.Collection;
import java.util.*;

// Show all habitats of this zoo hotel.
class DoShowAllHabitats extends Command<Hotel> {

    private HabitatManager _habitatManager;

    DoShowAllHabitats(Hotel receiver) {
        super(Label.SHOW_ALL_HABITATS, receiver);
        _habitatManager = receiver.getHabitatManager();
    }

    @Override
    protected void execute() {
      List<Habitat> habitats = _habitatManager.getSortedEntities();  // Obtém os habitats ordenados
  
    // Verifica se existem habitats registrados
    
      // Itera sobre o mapa de habitats e exibe suas informações
      for (Habitat habitat : habitats) {
        _display.addLine(habitat.toString());
        for(Tree tree : habitat.getTreeList()){
          _display.addLine(tree.toString());
        }
        
        
      }
    

    // Exibe a lista no terminal
    _display.display();
  }
}
