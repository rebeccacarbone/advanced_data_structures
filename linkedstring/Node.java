/*Project #2
 *Source code file: Node.java
 *Programmer: Rebecca Carbone
 *Due: 3/18/2019
 *Description: Node class provides an outline for the elements of the LinkedString linked list
*/
package linkedstring;

public class Node {
    Object item;
    Node next;
    
    Node(Object newItem) {
        item = newItem;
        next = null;
    }
    
    Node(Object newItem, Node newNext) {
        item = newItem;
        next = newNext;
    }
    
}
