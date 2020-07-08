package StackAndQueuePackage;

/**
   A class that implements a queue of objects by using
   a chain of linked nodes.
   @author Frank M. Carrano
*/
public final class LinkedQueue<T> implements QueueInterface<T>
{
    private Node firstNode; // References node at front of queue
    private Node lastNode;  // References node at back of queue
    
    public LinkedQueue()
    {
        firstNode = null;
        lastNode = null;
    } // end default constructor
    
    /** Adds a new entry to the back of this queue.
       @param newEntry  An object to be added. */
    public void enqueue(T newEntry)
    {
        Node newNode = new Node(newEntry);
        
        if (isEmpty())
            firstNode = newNode;
        else
            lastNode.setNextNode(newNode);
        // end if
        lastNode = newNode;
    } // end enqueue
    
    /** Removes and returns the entry at the front of this queue.
       @return  The object at the front of the queue.
       @throws  EmptyQueueException if the queue is empty before the operation. */
    public T dequeue()
    {
        T front = getFront(); // Might throw an EmptyQueueException
        assert firstNode != null;
        firstNode.setData(null);
        firstNode = firstNode.getNextNode(); // Remove first node by setting second node as firstNode.
        if (firstNode == null)  // If the chain had only one node which is just removed.
            lastNode = null;
        return front;
    } // end dequeue
    
    /** Retrieves the entry at the front of this queue.
       @return  The object at the front of the queue.
       @throws  EmptyQueueException if the queue is empty. */
    public T getFront()
    {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return firstNode.getData();
    } // end getFront
    
    /** Detects whether this queue is empty.
       @return  True if the queue is empty, or false otherwise. */
    public boolean isEmpty()
    {
        return (firstNode == null) && (lastNode == null);
    } // end isEmpty
   
    /** Removes all entries from this queue. */
    public void clear()
    {
        firstNode = null;
        lastNode = null;
    } // end clear
    
    // @author Frank M. Carrano, Timothy M. Henry
    // @version 5.0 */
    private class Node
    {
        private T    data;  // Entry in bag
        private Node next;  // Link to next node

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        } // end constructor

        private T getData()
        {
            return data;
        } // end getData

        private void setData(T newData)
        {
            data = newData;
        } // end setData

        private Node getNextNode()
        {
            return next;
        } // end getNextNode

        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        } // end setNextNode
    } // end Node
} // end LinkedQueue
