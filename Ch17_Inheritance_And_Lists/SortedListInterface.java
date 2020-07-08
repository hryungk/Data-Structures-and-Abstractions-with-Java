/** An interface for the ADT sorted list.
    Entries in a list have positions that begin with 1.
    @author Frank M. Carrano
 */
public interface SortedListInterface<T extends Comparable<? super T>>
{
    /** Adds a new entry to the sorted list in its proper order.
        The list's size is increased by 1.
        @param newEntry  An object to be added as a new entry. */
    public void add(T newEntry);    

    /** Removes the first or the only occurrence of a specified entry 
        from this sorted list.
        @param anEntry  The object to be removed.
        @return  True if anEntry was located and removed; 
                 otherwise returns false. */
    public boolean remove(T anEntry);
    
    /** Gets the position of an entry in this sorted list.
        @param anEntry  The object to be found.
        @return  The position of the first or only occurrence of anEntry
                 if it occurs in the list; otherwise returns the position
                 where anEntry would occur in the list, but as a negative
                 integer. */
    public int getPosition(T anEntry);    
    
    /** Retrieves the entry at a given position in this list.
        Any givenPosition is invalid if empty.
        @param givenPosition  An integer that indicates the position of the 
                              desired entry.
        @return  A reference to the indicated entry.
        @throws  IndexOutOfBoundsException if either
                  givenPosition < 1 or givenPosition > getLength(). */
    public T getEntry(int givenPosition);
    
    /** Sees whether this list contains a given entry.
        @param anEntry  The object that is the desired entry.
        @return  True if the list contains anEntry, or false if not. */
    public boolean contains(T anEntry);     

    /** Removes the entry at a given position from this list.
        Entries originally at positions higher than the given position are at the
        next lower position within the list, and the list’s size is decreased by 1.
        @param givenPosition  An integer that indicates the position of the entry
                              to be removed. Any givenPosition is invalid if empty.
        @return  A reference to the removed entry.
        @throws  IndexOutOfBoundsException if either
                 givenPosition < 1 or givenPosition > getLength(). */
    public T remove(int givenPosition);
    
    /** Removes all entries from this list. */
    public void clear();   
    
    /** Gets the length of this list.
        @return  The integer number of entries currently in the list. */
    public int getLength();

    /** Sees whether this list is empty.
        @return  True if the list is empty, or false if not. */
    public boolean isEmpty();       
    
    /** Retrieves all entries that are in this list in the order in which they
        occur in the list.
        @return  A newly allocated array of all the entries in the list.
                 If the list is empty, the returned array is empty. */
    public T[] toArray();
} // end SortedListInterface
