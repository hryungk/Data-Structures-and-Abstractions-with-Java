/** 
    A linked implementation of the ADT sorted list.
    @author Frank M. Carrano
 */
public class LinkedSortedList<T extends Comparable<? super T>> implements SortedListInterface<T>
{
    private Node firstNode; // Reference to first ndoe of chain
    private int numberOfEntries;
    
    public LinkedSortedList()
    {
        initializeDataFields();
    } // end default constructor
    
    /** Adds a new entry to the sorted list in its proper order.
        The list's size is increased by 1.
        @param newEntry  An object to be added as a new entry. */
    public void add(T newEntry)
    {
        Node newNode = new Node(newEntry);
        Node nodeBefore = getNodeBefore(newEntry);        

        if (nodeBefore == null) // || isEmpty())
        {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
        }
        else // Add to end of nonempty list
        {   
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);            
        } // end if        
        numberOfEntries++;        
    } // end add
    
    // Finds the node that is before the node that should or does contain
    //  a given entry.
    // Returns either a reference to the node that is before the node
    // that does or should contain anEntry, or null if no prior node exists 
    // (that is, if anEntry is or belongs at the beginning of the list).
    private Node getNodeBefore(T anEntry)
    {
        Node currentNode = firstNode;
        Node nodeBefore = null;        
        while ((currentNode != null) && (anEntry.compareTo(currentNode.getData()) > 0))
        {
            nodeBefore = currentNode;
            currentNode.setNextNode(currentNode);         
        } // end while
        return nodeBefore;
    } // end getNodeBefore
    
   // Recursive implementation of add    
    public void addR(T newEntry)
    {
        firstNode = addR(newEntry, firstNode);
        numberOfEntries++;
    } // end addR (recursive)
 
    private Node addR(T newEntry, Node currentNode)
    {              
        //Node currentNode = firstNode;
        if ((currentNode == null) || (newEntry.compareTo(currentNode.getData()) <= 0))
        {
            currentNode = new Node(newEntry, currentNode);
        }
        else // Add to end of nonempty list
        {                           
            Node nodeAfter = addR(newEntry, currentNode.getNextNode());    
            currentNode.setNextNode(nodeAfter);
        } // end if        
        return currentNode;
    } // end add
    
    /** Removes the first or the only occurrence of a specified entry 
        from this sorted list.
        @param anEntry  The object to be removed.
        @return  True if anEntry was located and removed; 
                 otherwise returns false. */
    public boolean remove(T anEntry)
    {
        boolean result = false;
        int position = getPosition(anEntry);
        if (position > 0)
        {
            remove(position);
            result = true;
        } // end if
        return result;
    } // end remove
    
    /** Gets the position of an entry in this sorted list.
        @param anEntry  The object to be found.
        @return  The position of the first or only occurrence of anEntry
                 if it occurs in the list; otherwise returns the position
                 where anEntry would occur in the list, but as a negative
                 integer. */
    public int getPosition(T anEntry)
    {
        int position = 1;
        
        // Find position of an entry
        while ((position <= numberOfEntries) && 
                (anEntry.compareTo(getEntry(position)) > 0))
        {
            position++;
        } // end while            
        // See whether anEntry is in list
        if ((position > numberOfEntries) || 
                (anEntry.compareTo(getEntry(position)) != 0))
        {
            position = -position;   // anEntry is not in list
        } // end if        
        return position;
    } // end getPosition

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
    
    
    // Initializes the class's data fields to indicate an empty list.
    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
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
} // end LinkedSortedList
