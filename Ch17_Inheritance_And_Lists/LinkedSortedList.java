public class LinkedSortedList<T extends Comparable<? super T>> 
             extends LinkedChainBase<T> 
             implements SortedListInterface<T>
{
    public LinkedSortedList()
    {
        super();    // Initializes the linked chain
    } // end default constructor
    
    public LinkedSortedList(ListInterface<T> list)
    {
        this();
        for (int i = 1; i <= list.getLength(); i++)
        {
            add(list.getEntry(i));
        }
    } // end constructor
 
    /** Adds a new entry to the end of this list.
        Entries currently in the list are unaffected.
        The list's size is increased by 1.
        @param newEntry  An object to be added as a new entry. */
    public void add(T newEntry)
    {        
        Node newNode = new Node(newEntry);
        Node nodeBefore = getNodeBefore(newEntry);
        if (nodeBefore == null)
            addFirstNode(newNode);
        else // Add to end of nonempty list
            addAfterNode(nodeBefore, newNode); //Make last node reference new node
        // end if                      
    } // end add with an unspecified position

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
        int numberOfEntries = getLength();
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
                if (anEntry.compareTo(currentNode.getData()) > 0)
                    currentNode = currentNode.getNextNode();
                else
                    break;
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
        int numberOfEntries = getLength();
        T result = null;    // Return value
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();            
            if (givenPosition == 1) // Case 1: Remove first entry
            {
                removeFirstNode();
            }
            else                    // Case 2: Not first entry
            {                
                Node nodeBefore = getNodeAt(givenPosition - 1);
                result = removeAfterNode(nodeBefore);
            } // end if
            return result;          // Return reference to removed entry
        }
        else
            throw new IndexOutOfBoundsException(
                    "Illegal position given to remove operation.");
         // end if
    } // end remove
    
    /** Retrieves all entries that are in this list in the order in which they
        occur in the list.
        @return  A newly allocated array of all the entries in the list.
                 If the list is empty, the returned array is empty. */
    public T[] toArray()
    {
        int numberOfEntries = getLength();
        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Comparable[numberOfEntries];  // Warning: [unchecked]
                                                            // unchecked cast
        for (int index = 0; index < numberOfEntries; index++)
            result[index] = getEntry(index);
        // end for
        return result;
    } // end toArray
    
    public boolean equals(LinkedSortedList aLinkedSortedList)
    {
        boolean result = true;
        for (int index = 1; index <= getLength(); index++)
        {
            result = result && getEntry(index).equals(aLinkedSortedList.getEntry(index));
        } // end for
        return result;
    } // end equals
    
    // Finds the node that is before the node that should or does contain
    //  a given entry.
    // Returns either a reference to the node that is before the node
    // that does or should contain anEntry, or null if no prior node exists 
    // (that is, if anEntry is or belongs at the beginning of the list).
    private Node getNodeBefore(T anEntry)
    {
        Node currentNode = getFirstNode();
        Node nodeBefore = null;        
        while ((currentNode != null) && 
                (anEntry.compareTo(currentNode.getData()) > 0))
        {
            nodeBefore = currentNode;
            currentNode = currentNode.getNextNode();      
        } // end while
        return nodeBefore;
    } // end getNodeBefore    
} // end ListInterface
