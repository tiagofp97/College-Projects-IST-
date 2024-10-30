package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for show the global satisfation of the current zoo hotel.
 **/
class DoShowGlobalSatisfaction extends Command<HotelManager> {
  private HotelManager _hotelManager;
  DoShowGlobalSatisfaction(HotelManager receiver) {
    super(hva.app.main.Label.SHOW_GLOBAL_SATISFACTION, receiver);
    _hotelManager = receiver;
  }
  
  @Override
  protected final void execute() {
    //FIXME implement command
    _display.addLine(_receiver.getGlobalSatisfaction(_receiver.getHotel()));
    _display.display();
  }
}
