package hva.core;

import hva.core.exception.*;
import java.io.*;

// FIXME import classes

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager {
  /** The current zoo hotel */ // Should we initialize this field?
  private String fileName;
  private Parser parser; // Parser instance
  private Hotel _hotel = new Hotel();
  public Boolean _changesMade = false;
  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    _changesMade=false;
    
    if (getFileName() == null) {
      
        throw new MissingFileAssociationException();
    }
    

    saveAs(getFileName()); // Chama o método saveAs com o nome do arquivo associado
  }
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    _changesMade=false;
    
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
        oos.writeObject(_hotel); // Serializa o objeto Hotel
    }
    setFileName(filename); // Atualiza o nome do arquivo associado
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    _changesMade=false;
    

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
        
        Hotel loadedHotel = (Hotel) ois.readObject();
        // Retrieve the current season from the deserialized hotel object
        Season loadedSeason = loadedHotel.getCurrentSeason();
        
        // Recreate the hotel object using the loaded season
        _hotel.setSeason(loadedSeason);
        setHotel(loadedHotel);
         
        
        
        
    } catch (FileNotFoundException e) {
        throw new UnavailableFileException(filename); // Somente o nome do arquivo
    } catch (IOException | ClassNotFoundException e) {
        throw new UnavailableFileException(filename); // Somente o nome do arquivo
    }
  }
  
  /**
   * Read text input file and initializes the current zoo hotel (which should be empty)
   * with the domain entities representeed in the import file.
   *
   * @param filename name of the text input file
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    _changesMade=true;
    try{

      _hotel.importFile(filename);
    }catch(IOException e){
      throw new ImportFileException(filename);
    }catch(UnrecognizedEntryException e){
      
      throw new ImportFileException(filename);
    }
     
  } 

    public String getFileName() {return fileName;}

  public void setFileName(String fileName) {this.fileName = fileName;}
  public boolean hasFileAssociation() {
    return fileName != null; // Retorna true se há um nome de arquivo associado
  }
  public void loadFromFile() throws UnavailableFileException {
    
    if (fileName == null) {
        throw new UnavailableFileException("No file associated."); // Lança exceção se não houver associação
    }
    load(fileName); // Carrega o hotel do arquivo associado
}

public Boolean getChangesMade(){return _changesMade;}

public int getGlobalSatisfaction(Hotel hotel){return hotel.getGlobalSatisfaction();}

public void setChangesMade(Boolean val){_changesMade = val;}
  

  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    return _hotel;
  }
  public void setHotel(Hotel hotel){
    _hotel = hotel;
    
  }
  
}
