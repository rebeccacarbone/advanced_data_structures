/*Project #4
 *Source code file: TestQueue.java
 *Programmer: Rebecca Carbone
 *Due: 4/24/2019
 *Description: Test class for Queue ADT
 *Also worked with Nestor Colindres on this portion of the prjoect, so some 
 *parts may be similar.


 p
*/
package queue;

public class TestQueue {
    
    public static void main(String[] args) throws QueueException {
        
        Queue newQ = new Queue();
        
        System.out.println("The queue is empty: " + newQ.isEmpty());
        
        System.out.println("Test enqueue and peek: ");
        
        for (int i = 0; i < 10; i++) {
            newQ.enqueue(i);
            System.out.println(newQ.peek());
        }
        
        System.out.println("The queue is empty: " + newQ.isEmpty());
        
        System.out.println("Test dequeue by dequeueing two items: ");
        newQ.dequeue();
        newQ.dequeue();
        newQ.printQueue();
        
        System.out.println("Test dequeueAll");
        newQ.dequeueAll();
        System.out.println("The queue is empty: " + newQ.isEmpty());
        
    }
    
}
