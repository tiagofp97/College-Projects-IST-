package hva.app.main;

import hva.core.Hotel;
import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
    DoSaveFile(HotelManager receiver) {
        super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
    }

    @Override
protected final void execute() {
    try {
        // Check if there is an associated file
        if (_receiver.hasFileAssociation()) {
            // Save using the associated file name
            _receiver.save();
            
        } else {
            // Prompt user for a new filename
            String fileName = Form.requestString(Prompt.newSaveAs());
            _receiver.saveAs(fileName);
            
        }
    } catch (IOException | MissingFileAssociationException e) {
        System.err.println("Error saving file: " + e.getMessage());
    }
}
}
