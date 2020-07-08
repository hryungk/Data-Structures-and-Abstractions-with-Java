/**
   A class that implements a deque of objects by using
   a chain of doubly linked nodes.
   @author Frank M. Carrano
*/
public final class LinkedDeque<T> implements DequeInterface<T>
{
    private DLNode firstNode; // References node at front of queue
    private DLNode lastNode;  // References node at back of queue
    
    public LinkedDeque()
    {
        firstNode = null;
        lastNode = null;
    } // end default constructor
    
    /** Adds a new entry to the front/back of this deque.
       @param newEntry  An object to be added. */
    public void addToFront(T newEntry)
    {        
        DLNode newNode = new DLNode(null, newEntry, firstNode);
        
        if (isEmpty())
            lastNode = newNode;
        else
            firstNode.setPreviousNode(newNode);
        firstNode = newNode;
    } // end addToFront
    
    public void addToBack(T newEntry)
    {
        DLNode newNode = new DLNode(lastNode, newEntry, null);
        
        if (isEmpty())
            firstNode = newNode;
        else
            lastNode.setNextNode(newNode);
        // end if
        lastNode = newNode;        
    } // end addToBack

    /** Removes and returns the front/back entry of this deque.
       @return  The object at the front/back of the deque.
       @throws  EmptyQueueException if the deque is empty before the
                operation. */
    public T removeFront()
    {
        T front = getFront(); // Might throw an EmptyQueueException 
        assert firstNode != null;
        
        firstNode = firstNode.getNextNode();
        
        if (firstNode == null)
            lastNode = null;
        else
            firstNode.setPreviousNode(null);
        return front;
    } // end removeFront
    
    public T removeBack()
    {
        T back = getBack(); // Might throw an EmptyQueueException   
        assert lastNode != null;
        lastNode = lastNode.getPreviousNode();
        
        if (lastNode == null)
            firstNode = null;
        else
            lastNode.setNextNode(null);
        return back;        
    } // end removeBack
    
    /** Retrieves the front/back entry of this deque.
       @return  The object at the front/back of the deque.
       @throws  EmptyQueueException if the deque is empty. */
    public T getFront()
    {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return firstNode.getData();
    } // end getFront
    
    public T getBack()
    {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return lastNode.getData();        
    } // end getBack
    
    /** Detects whether this deque is empty.
       @return  True if the deque is empty, or false otherwise. */
    public boolean isEmpty()
    {
        return (firstNode == null && lastNode == null);
    } // end isEmpty
   
    /* Removes all entries from this deque. */
    public void clear()
    {
        firstNode = null;
        lastNode = null;
    } // end clear 
    
    // @author Frank M. Carrano, Timothy M. Henry
    // @version 5.0 */
    private class DLNode
    {
        private T    data;  // Entry in bag
        private DLNode previous;  // Link to previous node
        private DLNode next;  // Link to next node

        private DLNode(T dataPortion)
        {
            this(null, dataPortion, null);
        } // end constructor

        private DLNode(DLNode previousDLNode, T dataPortion, DLNode nextDLNode)
        {
            data = dataPortion;
            previous = previousDLNode;
            next = nextDLNode;
        } // end constructor

        private T getData()
        {
            return data;
        } // end getData

        private void setData(T newData)
        {
            data = newData;
        } // end setData

        private DLNode getNextNode()
        {
            return next;
        } // end getNextDLNode

        private void setNextNode(DLNode nextDLNode)
        {
            next = nextDLNode;
        } // end setNextDLNode
        
        private DLNode getPreviousNode()
        {
            return previous;
        } // end getPreviousDLNode

        private void setPreviousNode(DLNode previousDLNode)
        {
            previous = previousDLNode;
        } // end setPreviousDLNode
    } // end DLNode
} // end LinkedQueue
