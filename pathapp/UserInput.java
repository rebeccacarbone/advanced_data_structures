/*Project #3
 *Source code file: UserInput.java
 *Programmer: Rebecca Carbone
 *Due: 11/26/2019
 *Description: UserInput class handles any user input needed in the PathApp class
*/
package pathapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    
    /**
     * Method to ensure user enters a valid file name
     * @return 
     */
    public String getFileName() {
        Scanner userInput = new Scanner(System.in);
        String name;
        try {
            //take user input for file name and check that it ends with .txt
            name = userInput.nextLine();
            if (!name.substring(name.length()-4).equals(".txt")) {
                System.out.println("Please enter a valid file name: ");
                name = getFileName();
            }
            return name;
                
        }
        catch(Exception e) {
            System.out.println("Not a valid file name");
        }
        return "";
    }

    /**
     * Method to handle user input of int values
     * @return 
     */
    public int getInt() {
        Scanner userInput = new Scanner(System.in);
        int input;

        try {
            //ensure user enters a valid int value and not a different data type
            input = userInput.nextInt();
            return input;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            System.out.println("Please try again: ");
        }
        return getInt();
    }

    /**
     * Method to handle user input of char values
     * @param typeInput
     * @return 
     */
    public char getChar(String typeInput) {
        Scanner userInput = new Scanner(System.in);
        char input;

        try {
            input = userInput.next().charAt(0);
            //if "choice" is sent to getChar - check that the input matches menu options
            if (typeInput.equals("choice") && !isChoice(input)) {
                System.out.println("Not a valid choice");
                System.out.println("Please try again: ");
                input = getChar(typeInput);
            //if "vertex" is sent to getChar - check that the input matches vertex values
            } else if (typeInput.equals("vertex") && !isVertex(input)) {
                System.out.println("Not a valid choice");
                System.out.println("Please try again: ");
                input = getChar(typeInput);
            }
            return Character.toUpperCase(input);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
        }

        System.out.println("Please try again: ");
        char validInput = getChar(typeInput);
        return validInput;
    }

    /**
     * Method to check char input matches menu options
     * @param input
     * @return 
     */
    public boolean isChoice(char input) {
        input = Character.toUpperCase(input);
        boolean valid = false;
        if (input == 'C' || input == 'A' || input == 'D' || input == 'F' || 
                input == 'W' || input == 'R' || input == 'Q') {
            valid = true;
        }
        return valid;
    }

    /**
     * Method to check char input matches vertex values
     * @param input
     * @return 
     */
    public boolean isVertex(char input) {
        input = Character.toUpperCase(input);
        boolean valid = false;
        if (input == 'A' || input == 'B' || input == 'C' || input == 'D'
                || input == 'E') {
            valid = true;
        }
        return valid;
    }
}
