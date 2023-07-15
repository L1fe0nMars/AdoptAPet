package adoptapet;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Jamal Kamareddine
 * 
 * This program uses queues and priority queues to simulate an animal shelter for adopting dogs and cats
 */
public class AdoptAPet {
    static Stack<Long> microchipStack = new Stack<Long>();
    static Queue<Pet> cats = new LinkedList<Pet>();
    static Queue<Pet> dogs = new LinkedList<Pet>();
    static PriorityQueue<Pet> animalShelter = new PriorityQueue<Pet>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int menuOption;
        
        System.out.println("Welcome to the Animal Shelter!");
        pushMicroChip();
        
        do { 
            menuOption = displayMenu();

            switch (menuOption) {
                case 0:
                    pushMicroChip();
                    break;
                case 1:
                    if (microchipStack.size() > 0) {
                        enqueuePet(cats, "Cat");
                    }
                    else {
                        System.out.println("Error: There are no microchips left. Use option 0 to generate more microchips.");
                    }
                    break;
                case 2:
                    if (microchipStack.size() > 0) {
                        enqueuePet(dogs, "Dog");
                    }
                    else {
                        System.out.println("Error: There are no microchips left. Use option 0 to generate more microchips.");
                    }
                    break;
                case 3:
                    dequeuePet(cats, "cat");
                    break;
                case 4:
                    dequeuePet(dogs, "dog");
                    break;
                case 5:
                    dequeueOldestPet();
                    break;
                case 6:
                    System.out.println("Thank you for visiting the Animal Shelter. Have a great day!");
                    break;
                default:
                    System.out.println("Sorry, you entered an invalid option. Please try again.");
                    System.out.println();
            }
        } while (menuOption != 6);
    }
    
    /**
     * Displays the menu of options
     */
    public static int displayMenu() {
        int menuOption;
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Please select an option:");
        System.out.println("0. Add New Microchips");
        System.out.println("1. Donate a Cat");
        System.out.println("2. Donate a Dog");
        System.out.println("3. Adopt a Cat");
        System.out.println("4. Adopt a Dog");
        System.out.println("5. Adopt Oldest Pet");
        System.out.println("6. Exit");
        
        menuOption = keyboard.nextInt();

        return menuOption;
    }
    
    /**
     * Asks the user for information about the pet they're donating and adds it to the
     * appropriate queue and animalt shelter priority queue
     * 
     * @param pet The queue associated with the type of pet being donated
     * @param species The species of the donated pet
     */
    public static void enqueuePet(Queue<Pet> pet, String species) {
        String name;
        int dateOfBirth;
        long microchipNumber;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter the name of the pet.");
        name = keyboard.nextLine();
        
        System.out.println("Enter the date of birth of the pet (Format: yyyymmdd).");
        dateOfBirth = keyboard.nextInt();
        keyboard.nextLine();
        
        // Pop the microchip stack and assign the microchip number to the pet
        microchipNumber = popMicroChip();
        
        // Create a pet object and add it to the queue of cats and the animal shelter queue
        Pet newPet = new Pet(name, species, dateOfBirth, microchipNumber);
        pet.add(newPet);
        enqueueAnimal(newPet);

        System.out.println("Thank you for donating to the Animal Shelter!");
    }

    /**
     * Adds a pet to the animal shelter priority queue
     * 
     * @param newPet A new pet that was donated
     */
    public static void enqueueAnimal(Pet newPet) {
        animalShelter.add(newPet);
    }
    
    /**
     * Removes the adopted pet from the appropriate queue and the animal shelter priority queue
     * 
     * @param pet The queue associated with the type of pet being adopted
     * @param species The species of the adopted pet
     */
    public static void dequeuePet(Queue<Pet> pet, String species) {
        if (pet.size() > 0) {
            PriorityQueue<Pet> animalShelterTemp = new PriorityQueue<Pet>();
            // Remove the adopted pet from the queue
            Pet adoptedAnimal = pet.remove();
            Pet adoptedPet;
            
            // Loop to remove the pet that was adopted from the animal shelter
            while(!animalShelter.isEmpty()) {
                adoptedPet = animalShelter.remove();
                
                // Add the objects to the temporary priority queue if they're not the cat that was adopted
                if (!adoptedAnimal.equals(adoptedPet)) {
                    animalShelterTemp.add(adoptedPet);
                }
            }
            // Point the original priority queue to the new one
            animalShelter = animalShelterTemp;
            
            System.out.println("Congratulations, you're now the proud owner of a new pet! Here's their information:");
            System.out.println(adoptedAnimal + "\n");
        }
        else {
            System.out.println("We're sorry, there are currently no " + species + "s available for adoption.");
            System.out.println();
        }
    }
    
    /**
     * Removes the oldest pet from the animal shelter queue and removes it from the appropriate cat or dog queue
     */
    public static void dequeueOldestPet() {
        if (animalShelter.size() > 0) {
            Queue<Pet> catsTemp = new LinkedList<Pet>();
            Queue<Pet> dogsTemp = new LinkedList<Pet>();
            Pet adoptedPet = animalShelter.remove();
            Pet adoptedCat;
            Pet adoptedDog;
            
            // Loop through the cat queue to see if the adopted pet was in that queue, and remove it if so
            while (!cats.isEmpty()) {
                adoptedCat = cats.remove();
                
                // Add the objects to the temporary queue if they're not the cat that was adopted
                if (!adoptedPet.equals(adoptedCat)) {
                    catsTemp.add(adoptedCat);
                }
            }
            // Point the original cat queue to the new one
            cats = catsTemp;
            
            // Loop through the dog queue to see if the adopted pet was in that queue, and remove it if so
            while (!dogs.isEmpty()) {
                adoptedDog = dogs.remove();
                
                // Add the objects to the temporary queue if they're not the dog that was adopted
                if (!adoptedPet.equals(adoptedDog)) {
                    dogsTemp.add(adoptedDog);
                }
            }
            // Point the original dog queue to the new one
            dogs = dogsTemp;
            
            System.out.println("Congratulations, you're now the proud owner of a new pet! Here's their information:");
            System.out.println(adoptedPet + "\n");
        }
        else {
            System.out.println("We're sorry, there are currently no pets available for adoption.");
            System.out.println();
        }
    }
    
    /**
     * Generates 100 microchip numbers and adds them to the stack of microchips
     */
    public static void pushMicroChip() {
        long microchipNumber;
        
        for (int i = 0; i < 100; i++) {
            microchipNumber = System.nanoTime();
            microchipStack.push(microchipNumber);
        }
        System.out.println("Microchips successfully generated.");
    }
    
    /**
     * Pops the stack of microchip numbers
     * 
     * @return A microchip number from the stack
     */
    public static long popMicroChip() {
        return microchipStack.pop();
    }
}
