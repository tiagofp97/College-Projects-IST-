package hva.core.vaccine;
import hva.core.employee.*;
import hva.core.exception.*;
import hva.core.*;
import java.util.*;

public class VaccineApplication{
    private Vaccine _vaccine;
    private Veterinarian _vet;
    private Species _species;
    private Animal _animal;
    private Boolean _sameSpecies;

    public VaccineApplication(Vaccine vaccine, Veterinarian vet, Animal animal){
        _vaccine = vaccine;
        _vet = vet;
        _species = animal.getSpecies();
        _animal=animal;
        changeAnimalHealth();

    }

    public void changeAnimalHealth(){
        int dam = Damage(_species);
        _animal.changeAnimalHealth(dam, !_sameSpecies);

    }
   
    public boolean isVaccineAdequate(){
        return _sameSpecies;
    }

    

     /**
     * Calculates the maximum damage between the species the vaccine is applied to and
     * the allowed species for the vaccine. 
     * 
     * Sets the isCorrect flag to true if the damage is 0.
     * Sets the _sameSpecies flag to true if any allowed species name matches the applied species name exactly.
     * 
     * @param specieApplied the species to which the vaccine is applied
     * @return the maximum damage value, or 0 if no allowed species are found
     */
    public int Damage(Species specieApplied){
        String name1 = specieApplied.getName();

        // Check if any species in the allowed species list has the same name
        _sameSpecies = _vaccine.getAllowedSpecies().stream()
                .anyMatch(species -> species.getName().equals(name1));
                
        if (_sameSpecies) {
            return 0;
        }

        // Calculate the maximum damage
        int maxDamage = _vaccine.getAllowedSpecies().stream()
                .mapToInt(species -> sizeNames(name1, species.getName()) - equalCharacteres(name1, species.getName()))
                .max()
                .orElse(0);

        
        return maxDamage;
    }

    /**
     * Returns the length of the largest string between the two provided.
     *
     * @param str1 the first string
     * @param str2 the second string
     * @return the length of the largest string
     */
    private int sizeNames(String str1, String str2) {
        // Compare the lengths of the two strings and return the largest one
        return Math.max(str1.length(), str2.length());
    }

    /**
     * Counts the number of unique equal characters between two strings.
     * This method converts both input strings into sets of characters to remove duplicates,
     * then checks how many characters are common between both sets.
     * 
     * @param str1 the first input string
     * @param str2 the second input string
     * @return the number of equal characters found in both strings (ignoring duplicates)
     */
    private int equalCharacteres(String str1,String str2){
        // Convert strings to sets to eliminate duplicates
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        // Add characters from the first string to set1
        for (char c : str1.toCharArray()) {
            set1.add(c);
        }

        // Add characters from the second string to set2
        for (char c : str2.toCharArray()) {
            set2.add(c);
        }

        // Retain only the common elements between the two sets
        set1.retainAll(set2);

        // The size of set1 is the number of common characters
        return set1.size();
    }
    
    @Override 
    public String toString() {
        return String.format("REGISTO-VACINA|%s|%s|%s",
                             getVaccineId(),
                             getVetId(),
                             getSpeciesId()
                             );
    }
    public String getVaccineId(){return _vaccine.getId();}
    public String getVetId(){return _vet.getId();}
    public String getSpeciesId(){return _species.getId();}
   
}