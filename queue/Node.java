/*Project #4
 *Source code file: Node.java
 *Programmer: Rebecca Carbone
 *Due: 4/24/2019
 *Description: Node class from previous project
*/
package queue;

public class Node {
    Node next;
    Object item;
    
    public Node(Object newItem) {
        next = null;
        item = newItem;
    }
    
    public Node(Node newNext, Object newItem) {
        next = newNext;
        item = newItem;
    }
    
}
