/*Project #2
 *Source code file: UserInput.java
 *Programmer: Rebecca Carbone
 *Due: 10/28/2019
 *Description: Class to handle user input values 
*/
package project2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
   
    /**
     * Forces user to input an integer number
     * @return 
     */
    public int getInt() { 
        
        Scanner userInput = new Scanner(System.in);
        int input;
        
        try {
            input = userInput.nextInt();
            return input; 
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input.");
        }
        
        System.out.println("Please try again: ");
        int validNumber = getInt();
        return validNumber;
    }
    
    /**
     * Checks that the user input is between specified bounds
     * @param numToCheck
     * @param min
     * @param max
     * @return 
     */
    public boolean isValid(int numToCheck, int min, int max) {
    
        boolean validNum = numToCheck >= min && numToCheck <= max;
        if(validNum) 
            return true;
        
        System.out.println("Invalid entry");
        return false;
    }
    
    /**
     * Ensures that the user input matches one of the given data fields
     * @param records
     * @param recordNum
     * @return 
     */
    public boolean getDataField(Record[] records, int recordNum) {
    
        Scanner userInput = new Scanner(System.in);
        String fieldInput = userInput.nextLine();
        boolean isValid = false;

        if (fieldInput.equalsIgnoreCase("first name")) {
            System.out.println("Record # " + recordNum + " First Name: " + records[recordNum-1].getFirstName());
            isValid = true;
        }
        else if (fieldInput.equalsIgnoreCase("last name")){
            System.out.println("Record # " + recordNum + " Last Name: " + records[recordNum-1].getLastName());
            isValid = true;
        }
        else if (fieldInput.equalsIgnoreCase("email")){
            System.out.println("Record # " + recordNum + " Email: " + records[recordNum-1].getEmail());
            isValid = true;
        } 
        else if (fieldInput.equalsIgnoreCase("id number")){
            System.out.println("Record # " + recordNum + " ID Number: " + records[recordNum-1].getID());
            isValid = true;
        }
        else if (fieldInput.equalsIgnoreCase("color")){
            System.out.println("Record # " + recordNum + " Color: " + records[recordNum-1].getColor());
            isValid = true;
        }
        else if (fieldInput.equalsIgnoreCase("balance")){
            System.out.println("Record # " + recordNum + " Balance: " + records[recordNum-1].getBalance());
            isValid = true;
        }
        else {
            System.out.println(fieldInput + " field does not exist");
            isValid = false;
        }
        
        return isValid;
    }
    
    /**
     * Loops until user input is valid
     * @param entry
     * @return 
     */
     public int validEntry(int entry) {
        
        while(!isValid(entry, 80, 256)) {
            System.out.println("Please enter a number between 80-256");
            entry = getInt();
        }
        return entry;
    }
}

