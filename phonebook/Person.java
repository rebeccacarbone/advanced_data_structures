/*Project #5
 *Source code file: Person.java
 *Programmer: Rebecca Carbone
 *Due: 5/29/2019
 *Description: Person class used by the PhoneBook
*/
package phonebook;

public class Person {
    String name;
    double number;
    
    Person(String newName, double newNumber) {
        setName(newName);
        setNumber(newNumber);
    }
    
    public void setName(String newName) {
        name = newName;
    }
    
    public void setNumber(double newNumber) {
        number = newNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public double getNumber() {
        return number;
    }
    
    
}
