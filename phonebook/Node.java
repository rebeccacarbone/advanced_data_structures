/*Project #5
 *Source code file: Node.java
 *Programmer: Rebecca Carbone
 *Due: 5/29/2019
 *Description: Node class used by the Binary Search Tree
*/
package phonebook;

public class Node {
    
    Object name;
    Object number;
    Node left, right;
    
    Node(Object newName, Object newNumber) {
        name = newName;
        number = newNumber;
    }
    
    public Object getName() {
        return name;
    }
    
    public Object getNumber() {
        return number;
    }
    
    public void setLeft(Node newLeft) {
        left = newLeft;
    }
    
    public void setRight(Node newRight) {
        right = newRight;
    }
}
