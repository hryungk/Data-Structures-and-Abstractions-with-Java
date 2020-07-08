/** 
    An implementation of the ADT sorted list that uses the ADT list.
    @author Frank M. Carrano
 */
public class SortedList<T extends Comparable<? super T>> extends LList<T> implements SortedListInterface<T>
{    
    public SortedList()
    {
        super();
    } // end default constructor
    
    /** Adds a new entry to the sorted list in its proper order.
        The list's size is increased by 1.
        @param newEntry  An object to be added as a new entry. */
    public void add(T newEntry)
    {
        int newPosition = Math.abs(getPosition(newEntry));
        super.add(newPosition, newEntry);
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
            super.remove(position);
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
        int length = super.getLength();
        // Find position of an entry
        while ((position <= length) && 
                (anEntry.compareTo(super.getEntry(position)) > 0))
        {
            position++;
        } // end while            
        // See whether anEntry is in list
        if ((position > length) || 
                (anEntry.compareTo(super.getEntry(position)) != 0))
        {
            position = -position;   // anEntry is not in list
        } // end if        
        return position;
    } // end getPosition

    /** Sees whether this list contains a given entry.
        @param anEntry  The object that is the desired entry.
        @return  True if the list contains anEntry, or false if not. */
    public boolean contains(T anEntry)
    {
        return getPosition(anEntry) > 0;
    } // end contains
    
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
            result[index] = super.getEntry(index);
        // end for
        return result;
    } // end toArray
    
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
        throw new UnsupportedOperationException("Illegal attempt to add at a " + 
                                    "specified position within a sorted list.");
    } // end add
    
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
        throw new UnsupportedOperationException("Illegal attempt to replace an " + 
                                                "entry within a sorted list.");       
    } // end replace
} // end SortedList
