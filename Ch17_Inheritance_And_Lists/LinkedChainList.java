public class LinkedChainList<T> extends LinkedChainBase<T> implements ListInterface<T>
{
    public LinkedChainList()
    {
        super();    // Initializes the linked chain
    } // end default constructor
 
    /** Adds a new entry to the end of this list.
        Entries currently in the list are unaffected.
        The list's size is increased by 1.
        @param newEntry  An object to be added as a new entry. */
    public void add(T newEntry)
    {
        int numberOfEntries = getLength();
        Node newNode = new Node(newEntry);
        if (isEmpty())
            addFirstNode(newNode);
        else // Add to end of nonempty list
        {
            Node lastNode = getNodeAt(numberOfEntries);
            addAfterNode(lastNode, newNode); //Make last node reference new node
        } // end if           
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
        int numberOfEntries = getLength();
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
        {
            Node newNode = new Node(newEntry);
        
            if (newPosition == 1)   // Case 2: Adding a node at the beginning
            {
                addFirstNode(newNode);
            } 
            else    // Case 3: List is not empty and newPosition > 1
            {
                assert !isEmpty() && newPosition > 1;
                Node nodeBefore = getNodeAt(newPosition - 1);
                addAfterNode(nodeBefore,newNode);              
            } // end if
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
        int numberOfEntries = getLength();
        T result = null;    // Return value
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();            
            if (givenPosition == 1) // Case 1: Remove first entry
            {
                result = removeFirstNode();
            }
            else                    // Case 2: Not first entry
            {                
                Node nodeBefore = getNodeAt(givenPosition - 1);
                result = removeAfterNode(nodeBefore);
            } // end if
            return result;                  // Return reference to removed entry
        }
        else
            throw new IndexOutOfBoundsException(
                    "Illegal position given to remove operation.");
         // end if
    } // end remove
    
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
        int numberOfEntries = getLength();
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
        int numberOfEntries = getLength();
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
        Node currentNode = getFirstNode();
      
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
} // end LinkedChainList
