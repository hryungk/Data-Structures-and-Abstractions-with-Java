/** 
    A linked implementation of the ADT list.
    @author Frank M. Carrano
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
public class ListWithTraversal<T> implements ListInterface<T>, Iterator<T>
{
    private Node firstNode; // Reference to first ndoe of chain
    private int numberOfEntries;
    private Node nextNode;  // Node containing next entry in iteration
    
    public ListWithTraversal()
    {
        initializeDataFields();
    } // end default constructor
    
    /** Adds a new entry to the end of this list.
        Entries currently in the list are unaffected.
        The list's size is increased by 1.
        @param newEntry  An object to be added as a new entry. */
    public void add(T newEntry)
    {
        Node newNode = new Node(newEntry);
        if (isEmpty())
            firstNode = newNode;
        else // Add to end of nonempty list
        {
            Node lastNode = getNodeAt(numberOfEntries);
            lastNode.setNextNode(newNode); //Make last node reference new node
        } // end if        
        numberOfEntries++;        
    } // end add with an unspecified position
    
    /** Adds a new entry at a specified position within this list.
        Entries originally at and above the specified position are at the next higher
        position within the list.
        The list’s size is increased by 1.
        Precondition: The array list has room for another entry.
        @param newPosition  An integer that specifies the desired position of the
                            new entry.
        @param newEntry     The object to be added as a new entry.
        @throws  IndexOutOfBoundsException if either
                 newPosition < 1 or newPosition > getLength() + 1. */
    public void add(int newPosition, T newEntry)
    {   
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
        {
            Node newNode = new Node(newEntry);
        
            if (newPosition == 1)   // Case 2: Adding a node at the beginning
            {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            } 
            else    // Case 3: List is not empty and newPosition > 1
            {
                assert !isEmpty() && newPosition > 1;
                Node nodeBefore = getNodeAt(newPosition - 1);
                Node nodeAfter =  nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);                
            } // end if
            numberOfEntries++;
        } 
        else
            throw new IndexOutOfBoundsException(
                    "Illegal position given to add operation.");
        // end if        
    } // end add with a specific position

    /** Removes the entry at a given position from this list.
        Entries originally at positions higher than the given position are at the
        next lower position within the list, and the list’s size is decreased by 1.
        @param givenPosition  An integer that indicates the position of the entry
                              to be removed. Any givenPosition is invalid if empty.
        @return  A reference to the removed entry.
        @throws  IndexOutOfBoundsException if either
                 givenPosition < 1 or givenPosition > getLength(). */
    public T remove(int givenPosition)
    {
        T result = null;    // Return value
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();            
            if (givenPosition == 1) // Case 1: Remove first entry
            {
                result = firstNode.getData();       // Save entry to be removed
                firstNode = firstNode.getNextNode(); // Remove entry
            }
            else                    // Case 2: Not first entry
            {                
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData();    // Save entry to be removed
                Node nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);  // Remove entry
            } // end if
            numberOfEntries--;                      // Update count
            return result;                  // Return reference to removed entry
        }
        else
            throw new IndexOutOfBoundsException(
                    "Illegal position given to remove operation.");
         // end if
    } // end remove
    
    /** Removes all entries from this list. */
    public void clear()
    {
        initializeDataFields();
    } // end clear
    
    /** Replaces the entry at a given position in this list.
        Any givenPosition is invalid if empty.
        @param givenPosition  An integer that indicates the position of the entry
                              to be replaced.
        @param newEntry  The object that will replace the entry at the position
                         givenPosition.
        @return  The original entry that was replaced.
        @throws  IndexOutOfBoundsException if either
             givenPosition < 1 or givenPosition > getLength(). */
    public T replace(int givenPosition, T newEntry)
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            Node desiredNode =  getNodeAt(givenPosition);
            T originalEntry = desiredNode.getData();
            desiredNode.setData(newEntry);
            return originalEntry;
        } 
        else
            throw new IndexOutOfBoundsException(
                    "Illegal position given to replace operation.");
        // end if
    } // end replace

    /** Retrieves the entry at a given position in this list.
        Any givenPosition is invalid if empty.
        @param givenPosition  An integer that indicates the position of the 
                              desired entry.
        @return  A reference to the indicated entry.
        @throws  IndexOutOfBoundsException if either
                  givenPosition < 1 or givenPosition > getLength(). */
    public T getEntry(int givenPosition)
    {     
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            return getNodeAt(givenPosition).getData();
        }
        else
            throw new IndexOutOfBoundsException(
                    "Illegal position given to getEntry operation.");
    } // end getEntry
    
    /** Retrieves all entries that are in this list in the order in which they
        occur in the list.
        @return  A newly allocated array of all the entries in the list.
                 If the list is empty, the returned array is empty. */
    public T[] toArray()
    {
        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast

        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null))
        //OR for (int index = 0; index < numberOfEntries; index++) 
        {
            result[index] = currentNode.getData();
            currentNode.getNextNode();
            index++;
        } // end for
        return result;
    } // end toArray
    
    /** Sees whether this list contains a given entry.
        @param anEntry  The object that is the desired entry.
        @return  True if the list contains anEntry, or false if not. */
    public boolean contains(T anEntry)
    {
        boolean found = false;    
        int where = -1;
        Node currentNode = firstNode;
      
        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
            // end if
        } // end while
        return found;
    } // end contains
    
    /** Gets the length of this list.
        @return  The integer number of entries currently in the list. */
    public int getLength()
    {
        return numberOfEntries;
    } // end getLength
    
    /** Sees whether this list is empty.
        @return  True if the list is empty, or false if not. */
    public boolean isEmpty()
    {
        boolean result;
        if (numberOfEntries == 0)   // Or getLength(0 == 0
        {
            assert firstNode == null;
            result = true;
        }
        else
        {
            assert firstNode != null : "numberOfEntries is not 0 but firstNode is null";
            result = false;
        } // end if
        return result;
    } // end isEmpty
    

    // Initializes the class's data fields to indicate an empty list.
    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
        nextNode = null;
    } // end initializeDataFields    
    
    // Returns a reference to the node at a given position.
    // Precondition: The chain is not empty;
    //               1 <= givenPosition <= numberOfEntries.
    private Node getNodeAt(int givenPosition)
    {
        assert (firstNode != null) && 
                (1 <= givenPosition) && (givenPosition <= numberOfEntries);
        Node currentNode = firstNode;
        // Traverse the chain to locate the desired node
        // (skipped if givenPostiion is 1)
        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();
        assert currentNode != null;
        
        return currentNode;
    } // end getNodeAt    
    
    public boolean hasNext()
    {
        return nextNode != null;
    } // end hasNext

    /** Retrieves the next entry in the collection and
        advances this iterator by one position.
        @return  A reference to the next entry in the iteration,
                 if one exists.
        @throws  NoSuchElementException if the iterator had reached the
                 end already, that is, if hasNext() is false. */
    public T next()
    {
        if (hasNext())
        {                
            Node returnedNode = nextNode;
            nextNode = nextNode.getNextNode();
            return returnedNode.getData();
        } 
        else
            throw new NoSuchElementException("Illegal call to next(); " + 
                                        "iterator is after end of list.");
        // end if
    } // end next

    /** Removes from the collection of data the last entry that
        next() returned. A subsequent call to next() will behave
        as it would have before the removal.
        Precondition: next() has been called, and remove() has not
        been called since then. The collection has not been altered
        during the iteration except by calls to this method.
        @throws IllegalStateException if next() has not been called, or
                if remove() was called already after the last call to next().
        @throws UnsupportedOperationException if the iterator does
                not permit a remove operation. */
    public void remove()
    {
        throw new UnsupportedOperationException("remove() is not supported "
                                                + "by this iterator");     
    } // end remove
    
    public void resetTraversal()
        {            
            nextNode = firstNode;
        } // end resetTraversal
    
        /** Detects whether this iterator has completed its traversal
            and gone beyond the last entry in the collection of data. 
            @return True if the iterator has another entry to return. */
    
    
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
} // end ListInterface
