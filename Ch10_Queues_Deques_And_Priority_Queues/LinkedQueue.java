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
        
        if (firstNode == null)
            firstNode = newNode;
        else
        {
            Node currentNode = firstNode;
            while(currentNode.getNextNode() != null)
            {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode);
            lastNode = newNode;
        } // end if
    } // end enqueue
    
    /** Removes and returns the entry at the front of this queue.
       @return  The object at the front of the queue.
       @throws  EmptyQueueException if the queue is empty before the operation. */
    public T dequeue()
    {
        Node nodeToRemove = firstNode;
        firstNode = firstNode.getNextNode();
        return nodeToRemove.getData();
    } // end dequeue
    
    /** Retrieves the entry at the front of this queue.
       @return  The object at the front of the queue.
       @throws  EmptyQueueException if the queue is empty. */
    public T getFront()
    {
        if (firstNode == null)
            throw new EmptyQueueException();
        return firstNode.getData();
    } // end getFront
    
    /** Detects whether this queue is empty.
       @return  True if the queue is empty, or false otherwise. */
    public boolean isEmpty()
    {
        return (firstNode == null);
    } // end isEmpty
   
    /** Removes all entries from this queue. */
    public void clear()
    {
        firstNode = null;
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
