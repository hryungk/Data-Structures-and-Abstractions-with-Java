/**
   A class that implements a queue of objects by using
   a two-part circular chain of linked nodes.
   @author Frank M. Carrano
*/
public final class TwoPartCircularLinkedQueue<T> implements QueueInterface<T>
{
    private Node queueNode; // References node at front of queue
    private Node freeNode;  // References node at back of queue
    
    public TwoPartCircularLinkedQueue()
    {
        Node freeNode = new Node(null, null);   // Allcoate a node
        freeNode.setNextNode(freeNode); // Make the node reference itself
        queueNode = freeNode;   // Set queueNode and freeNode to reference the new node.        
    } // end default constructor
    
    /** Adds a new entry to the back of this queue.
       @param newEntry  An object to be added. */
    public void enqueue(T newEntry)
    {
        freeNode.setData(newEntry);
        if (isChainFull())
        {
            Node newNode = new Node(null,freeNode.getNextNode());
            freeNode.setNextNode(newNode);
        } // end if
        freeNode = freeNode.getNextNode();        
    } // end enqueue
    
    /** Removes and returns the entry at the front of this queue.
       @return  The object at the front of the queue.
       @throws  EmptyQueueException if the queue is empty before the operation. */
    public T dequeue()
    {
        T front = getFront(); // Might throw an EmptyQueueException
        assert !isEmpty();
        queueNode.setData(null);
        queueNode = queueNode.getNextNode();
        
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
            return queueNode.getData();
    } // end getFront
    
    /** Detects whether this queue is empty.
       @return  True if the queue is empty, or false otherwise. */
    public boolean isEmpty()
    {
        return queueNode == freeNode;
    } // end isEmpty
   
    /** Removes all entries from this queue. */
    public void clear()
    {
        Node currentNode = queueNode;
        while(currentNode.getNextNode() != null)
        {
            currentNode.setData(null);
            currentNode = currentNode.getNextNode();
        } // end while
        queueNode = freeNode;
    } // end clear
    
    private boolean isChainFull()
    {        
        return queueNode == freeNode.getNextNode();
    } // end isChainFull
    
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
