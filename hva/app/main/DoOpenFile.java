package hva.app.main;

import hva.core.HotelManager;
import hva.app.exception.FileOpenFailedException;
import hva.core.exception.ImportFileException;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException; 
import hva.core.exception.UnrecognizedEntryException; // Add this import
import hva.core.Parser;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.core.Hotel;
import java.io.IOException;
import hva.app.exception.FileOpenFailedException;

// Command to open a file
class DoOpenFile extends Command<HotelManager> {
    private HotelManager _hotelManager;
    
    private Hotel _hotel;

    DoOpenFile(HotelManager receiver) {
        super(Label.OPEN_FILE, receiver);
        _hotelManager = receiver; 
        
    }

    @Override
    protected final void execute() throws CommandException {

        //System.out.println(_receiver.getChangesMade());
        String fileName = Form.requestString(Prompt.openFile());
            if(_receiver.getChangesMade()){
                String input = Form.requestString(Prompt.saveBeforeExit());
                boolean save = input.equals("sim") || input.equals("s");
                if(save){
                    save();
                }
            }
          //loadFile(fileName);
          importFile(fileName);
    
    }
    protected final void save(){
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
    protected final void importFile(String filename)throws CommandException{
        try{
            _hotelManager.importFile(filename);
        }catch(ImportFileException ie){
            throw new FileOpenFailedException(ie);
        }
      
    }
    protected final void loadFile(String filename)throws CommandException{
        try {
               
            _hotelManager.load(filename);
            
       } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
    }
   
     
    }

