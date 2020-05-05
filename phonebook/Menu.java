/*Project #5
 *Source code file: Menu.java
 *Programmer: Rebecca Carbone
 *Due: 5/29/2019
 *Description: Menu class to access the phonebook
*/
package phonebook;
import java.util.Scanner;

public class Menu {
    
    private static PhoneBook book;
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Enter first contact name: ");
        String name = input.next();
        System.out.println("Enter " + name + "'s phone number: ");
        String number = input.next();
        System.out.println();
        
        Node root = new Node(name, number);
        book = new PhoneBook(root);
        menu();
    }
    
    public static void menu() {
        
        int choice = -1;
        
        while (choice != 0) {
            
            System.out.println("What would you like to do?");
            System.out.println("0 - Quit");
            System.out.println("1 - Add contact");
            System.out.println("2 - Remove contact");
            System.out.println("3 - Change contact number");
            choice = input.nextInt();
            
            switch(choice) {
                case 0: book.print(); break;
                case 1: add(); break;
                case 2: remove(); break;
                case 3: change(); break;
            }
        }
    }
    
    public static void add() {
        System.out.println("Enter contact name: ");
        String name = input.next();
        System.out.println("Enter " + name + "'s phone number: ");
        String number = input.next();
        book.add(name, number);
    }
    
    public static void remove() {
        System.out.println("Enter name of contact to delete: ");
        String name = input.next();
        book.delete(name);
    }
    
    public static void change() {
        System.out.println("Enter name of contact to change: ");
        String name = input.next();
        System.out.println("Enter new phone number: ");
        String number = input.next();
        book.change(name, number);
    }
    
}
