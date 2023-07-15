package adoptapet;

/**
 * @author Jamal Kamareddine
 * 
 * This file holds general data about a pet
 */
public class Pet implements Comparable<Pet> {
    private String name;
    private String species;
    private int dateOfBirth;
    private long microchipNumber;
    
    /**
     * Constructor to set each parameter
     * 
     * @param name The name of the pet
     * @param species The type of pet
     * @param dateOfBirth The date of birth of the pet
     * @param microchipNumber The microchip number for the pet
     */
    public Pet(String name, String species, int dateOfBirth, long microchipNumber) {
        this.name = name;
        this.species = species;
        this.dateOfBirth = dateOfBirth;
        this.microchipNumber = microchipNumber;
    }
    
    /**
     * @return The name of the pet
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the pet name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The type of pet
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Sets the pet type
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * @return The date of birth of the pet
     */
    public int getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the pet
     */
    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    /**
     * @return The microchip number for the pet
     */
    public long getMicrochipNumber() {
        return microchipNumber;
    }

    /**
     * Sets the microchip number
     */
    public void setMicrochipNumber(long microchipNumber) {
        this.microchipNumber = microchipNumber;
    }
    
    /**
     * @return The pet information
     */
    public String toString() {
        String str = "Pet Name: " + name + "\n" 
                    + "Species: " + species + "\n" 
                    + "Date of Birth: " + dateOfBirth + "\n" 
                    + "Microchip Number: " + microchipNumber;
        
        return str;
    }
    
    /**
     * Compares two pet objects by their date of birth
     * 
     * @param otherPet Another pet object
     * 
     * @return The date of birth of the pets in ascending order
     */
    public int compareTo(Pet otherPet) {
        if (this.dateOfBirth > otherPet.dateOfBirth) {
            return 1;
        }
        else if (this.dateOfBirth < otherPet.dateOfBirth) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
