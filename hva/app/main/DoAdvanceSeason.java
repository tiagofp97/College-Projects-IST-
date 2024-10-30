package hva.app.main;
import hva.core.Hotel;
import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for advancing the season of the system.
 **/


class DoAdvanceSeason extends Command<HotelManager> {
  private Hotel _hotel;
  DoAdvanceSeason(HotelManager receiver) {
    super(Label.ADVANCE_SEASON, receiver);
    _hotel = receiver.getHotel();
    
  }

  @Override
  protected final void execute() {
    int index = _hotel.advanceSeason();

    _display.addLine(index);
    _display.display();
  }
}
