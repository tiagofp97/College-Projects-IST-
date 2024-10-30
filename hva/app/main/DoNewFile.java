package hva.app.main;

import hva.core.Hotel;
import hva.core.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  HotelManager _hotelManager;
    DoNewFile(HotelManager receiver) {
        super(Label.NEW_FILE, receiver);
        _hotelManager=receiver;
    }

    @Override
    protected final void execute() throws CommandException {
        // Cria uma nova instância do hotel

        _hotelManager.setHotel(new Hotel()); 
        
    }
    
}
