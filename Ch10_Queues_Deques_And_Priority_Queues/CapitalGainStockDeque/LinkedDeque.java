package CapitalGainStockDeque;

/**
   A class that implements a queue of objects by using
   a chain of linked nodes.
   @author Frank M. Carrano
*/
public final class LinkedDeque<T> implements DequeInterface<T>
{
    private Node firstNode; // References node at front of queue
    private Node lastNode;  // References node at back of queue
    
    public LinkedDeque()
    {
        firstNode = null;
        lastNode = null;
    } // end default constructor

    /** Adds a new entry to the front/back of this deque.
       @param newEntry  An object to be added. */
    public void addToFront(T newEntry)
    {        
        Node newNode = new Node(newEntry);
        newNode.setNextNode(firstNode);
        firstNode = newNode;
    } // end addToFront
    public void addToBack(T newEntry)
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
    } // end addToBack

    /** Removes and returns the front/back entry of this deque.
       @return  The object at the front/back of the deque.
       @throws  EmptyQueueException if the deque is empty before the
                operation. */
    public T removeFront()
    {
        Node nodeToRemove = firstNode;
        firstNode = firstNode.getNextNode();
        return nodeToRemove.getData();
    } // end removeFront
    public T removeBack()
    {
        Node currentNode = firstNode;
        while (currentNode.getNextNode() != null)
        {
            currentNode = currentNode.getNextNode();
        } // end while
        // Assertion: currentNode is the last node.
        T lastData = currentNode.getData();
        currentNode = null; // removes the last node.
        
        return lastData;        
    } // end removeBack
    
    /** Retrieves the front/back entry of this deque.
       @return  The object at the front/back of the deque.
       @throws  EmptyQueueException if the deque is empty. */
    public T getFront()
    {
        if (firstNode == null)
            throw new EmptyQueueException();
        return firstNode.getData();
    } // end getFront
    
    public T getBack()
    {
        if (firstNode == null)
            throw new EmptyQueueException();
        return lastNode.getData();        
    } // end getBack
    
    /** Detects whether this deque is empty.
       @return  True if the deque is empty, or false otherwise. */
    public boolean isEmpty()
    {
        return (firstNode == null);
    } // end isEmpty
   
    /* Removes all entries from this deque. */
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
