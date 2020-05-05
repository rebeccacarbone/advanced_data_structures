/*Project #4
 *Source code file: Queue.java
 *Programmer: Rebecca Carbone
 *Due: 4/24/2019
 *Description: Queue ADT class for a linearly linked queue
 *Worked with Nestor Colindres for certain portions of the project, so some
 *methods may be similar
*/
package queue;

public class Queue {
    
    private Node head;
    private Node tail;
    private int count = 0;
    
    public Queue() {
        head = null;
        tail = null;
    } //default constructor
    
    public Queue(Node newHead) {
        head = newHead;
        tail = newHead;
        count++;

    } //constructor with one input
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void dequeueAll() {
        head = null;
        tail = null;
    }
    
    public void enqueue(Object newItem) {
        Node newNode = new Node(newItem);
        if (isEmpty()) {
            head = newNode;
            count++;
        }
        
        else {
            tail.next = newNode;
            count++;
        }
        
        tail = newNode;
    }
    
    public Object dequeue() throws QueueException {
        Object item = head.item;
        
        if(!isEmpty()) {
           
            if (head == tail) {
                head = null;
                tail = null;
                count = 0;

            }
            
            else {
               head = head.next;
            }
        }
        
        else {
            throw new QueueException("QueueException in dequeue: " + "queue is empty");
        }
        
        return item;
    }
    
    public Object peek() throws QueueException {
        
        if (!isEmpty()) {
            return tail.item;
        }
        
        else {
            throw new QueueException("QueueException in peek: " + "queue is empty");
        }
    }
    
    public void printQueue() {
        Node temp = head;
        for (int i = 0; i < count-2; i++) {
            System.out.println(temp.item);
            temp = temp.next;
        }
    }
    
}
