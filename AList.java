
import java.util.Arrays;

/** A class for the ADT list.
    Entries in a list have positions that begin with 1.
    @author Hyunryung Kim
 */
public class AList<T> implements ListInterface<T>
{
    private T[] list;
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    
    public AList()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor
    
    public AList(int initialCapacity)
    {
        checkCapacity(initialCapacity);
        
        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[initialCapacity];
        list = tempList;
        numberOfEntries = 0;
        initialized = true;
    } // end constructor
    
    /** Adds a new entry to the end of this list.
        Entries currently in the list are unaffected.
        The list's size is increased by 1.
        @param newEntry  An object to be added as a new entry. */
    public void add(T newEntry)
    {   
        checkInitialization();
        ensureCapacity();
        list[numberOfEntries] = newEntry;
        numberOfEntries++;
    } // end add with an unspecified position
    
    /** Adds a new entry at a specified position within this list.
        Entries originally at and above the specified position are at the next higher
        position within the list.
        The list’s size is increased by 1.
        @param newPosition  An integer that specifies the desired position of the
                            new entry.
        @param newEntry     The object to be added as a new entry.
        @throws  IndexOutOfBoundsException if either
                 newPosition < 1 or newPosition > getLength() + 1. */
    public void add(int newPosition, T newEntry)
    {
        checkInitialization();
        ensureCapacity();
        
        if (newPosition < 1 || newPosition > getLength()+1)
            throw new IndexOutOfBoundsException();
        else
        {
            for (int i = numberOfEntries-1; i >= newPosition-1; i--)
            {
                list[i+1] = list[i];
            } // end for
            list[newPosition-1] = newEntry;
        } // end if
        numberOfEntries++;
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
        if (isEmpty())
            throw new EmptyListException();
        else
            if (givenPosition < 1 || givenPosition > getLength())
                throw new IndexOutOfBoundsException();
            else
            {
                T toRemove = getEntry(givenPosition);
                
                for (int i = givenPosition-1; i < numberOfEntries;i++)
                {
                    list[i] = list[i+1];
                } // end for
                
                numberOfEntries--;
                return toRemove;
            } // end else if
    } // end remove
    
    /** Removes all entries from this list. */
    public void clear()
    {
        int i = numberOfEntries;
        while(!isEmpty())
        {
            remove(i);
            i--;
        }
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
        checkInitialization();
        if (isEmpty())
            throw new EmptyListException();
        else
            if (givenPosition < 1 || givenPosition > getLength())
                throw new IndexOutOfBoundsException();
            else
            {
                T toReplace =  getEntry(givenPosition);
                list[givenPosition-1] = newEntry;
                return toReplace;
            } // end else if
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
        checkInitialization();
        if (isEmpty())
            throw new EmptyListException();
        else
            if (givenPosition < 1 || givenPosition > getLength())
                throw new IndexOutOfBoundsException();
            else
                return list[givenPosition-1];
    } // end getEntry
    
    /** Retrieves all entries that are in this list in the order in which they
        occur in the list.
        @return  A newly allocated array of all the entries in the list.
                 If the list is empty, the returned array is empty. */
    public T[] toArray()
    {
        checkInitialization();
      
        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries+1]; // Unchecked cast

        for (int index = 0; index < numberOfEntries+1; index++) 
        {
            result[index] = list[index];
        } // end for
        return result;
        // Note: The body of this method could consist of one return statement,
        // if you call Arrays.copyOf
    } // end toArray
    
    /** Sees whether this list contains a given entry.
        @param anEntry  The object that is the desired entry.
        @return  True if the list contains anEntry, or false if not. */
    public boolean contains(T anEntry)
    {
        checkInitialization();
        return getIndexOf(anEntry) > -1; // or >= 0
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
        return numberOfEntries == 0;
    } // end isEmpty

    // Locates a given entry within the array bag.
    // Returns the index of the entry, if located,
    // or -1 otherwise.
    // Precondition: checkInitialization has been called.
    private int getIndexOf(T anEntry)
    {
        int where = -1;
        boolean found = false;      
        int index = 0;
      
        while (!found && (index < numberOfEntries))
        {
            if (anEntry.equals(list[index]))
            {
                found = true;
                where = index;
            } // end if
            index++;
        } // end while
      
        // Assertion: If where > -1, anEntry is in the array bag, and it
        // equals bag[where]; otherwise, anEntry is not in the array.
      
        return where;
    } // end getIndexOf 
    
    // Throws an exception if this object is not initialized.
    private void checkInitialization()
    {
        if (!initialized)
            throw new SecurityException("ArrayQueue object is corrupt.");
    } // end checkInitialization
       
    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity) 
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose " + 
                    "capacity exeeds allowed " + "maximum of " + MAX_CAPACITY);
    } // end checkCapacity
    
    // Doubles the size of the array list.
    // Precondition: checkInitialization has been called.
    private void doubleCapacity()
    {
        int newLength = 2 * list.length;
        checkCapacity(newLength);
        list = Arrays.copyOf(list, newLength);
    } // end doubleCapacity  
    
    // Returns true if the array stack is full, or false if not.
    private void ensureCapacity()
    {
        if (numberOfEntries >= list.length - 1)
            doubleCapacity();
        // end if
    } // end ensureCapacity    
} // end ListInterface
