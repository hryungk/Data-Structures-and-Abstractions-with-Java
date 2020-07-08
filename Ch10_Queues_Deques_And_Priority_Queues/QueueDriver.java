/*
    Test the Queue class
 */
public class QueueDriver 
{
    public static void main(String[] args)
    {
        QueueInterface<String> myQueue = new LinkedQueue<>(); 
        myQueue.enqueue("Jim");
        myQueue.enqueue("Jess");
        myQueue.enqueue("Jill");
        myQueue.enqueue("Jane");
        myQueue.enqueue("Joe");
        String front = myQueue.getFront();  // Returns "Jim"
        System.out.println(front + " is at the front of the queue.");
        front = myQueue.dequeue();          // Removes and returns "Jim"
        System.out.println(front + " is removed from the queue.");
        myQueue.enqueue("Jerry");
        front = myQueue.getFront();         // Returns "Jess"
        System.out.println(front + " is at the front of the queue.");
        front = myQueue.dequeue();          // Removes and returns "Jess"
        System.out.println(front + " is removed from the queue.");    
    } // end main    
} // end QueueDriver
