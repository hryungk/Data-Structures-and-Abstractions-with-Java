import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** A class that implements a list of objects by using an array.
    Entries in a list have positions that begin with 1.
    * Duplicate entries are allowed.
    @author Hyunryung Kim
 */
public class MyList<T> implements ListInterface<T>
{
    private T[] list;   // Array of list entries; ignore list[0]
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    
    public MyList()
    {
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end default constructor
    
    public MyList(int initialCapacity)
    {
        // Is initialCapacity too small?
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else    // Is initialCapacity too big?
            checkCapacity(initialCapacity);
        
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[initialCapacity + 1];
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
        list[numberOfEntries + 1] = newEntry;
        numberOfEntries++;
        ensureCapacity();
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
        checkInitialization();
        
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
        {
            if (newPosition <= numberOfEntries)
                makeRoom(newPosition);
            list[newPosition] = newEntry;
            numberOfEntries++;
            ensureCapacity(); // Ensure enough room for next add
        } 
        else
            throw new IndexOutOfBoundsException();
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
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            T result = list[givenPosition]; // Get entry to remove
            // Move subsequent entries toward entry to be removed.
            // unles sit is last in list
            if (givenPosition < numberOfEntries)
                removeGap(givenPosition);
            numberOfEntries--;
            return result;  // Return reference to removed entry
        }
        else
            throw new IndexOutOfBoundsException(
                    "Given position of add's new entry is out of bounds.");
         // end if
    } // end remove
    
    /** Removes all entries from this list. */
    public void clear()
    {
        while(numberOfEntries > 0)
        {
            remove(numberOfEntries);
            numberOfEntries--;
        } // end while
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

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            T originalEntry =  list[givenPosition];
            list[givenPosition] = newEntry;
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
        checkInitialization();        
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            assert !isEmpty();
            return list[givenPosition];
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
        checkInitialization();
      
        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast

        for (int index = 0; index < numberOfEntries; index++) 
        {
            result[index] = list[index+1];
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
        return getIndexOf(anEntry) > -1;
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
        boolean found = false;    
        int where = -1;
        int index = 1;
      
        while (!found && (index <= numberOfEntries))
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
        int newCapacity = 2 * list.length;
        checkCapacity(newCapacity);   // Is capacity too big?
        list = Arrays.copyOf(list, newCapacity + 1);
    } // end doubleCapacity  
    
    // Returns true if the array stack is full, or false if not.
    private void ensureCapacity()
    {
        if (numberOfEntries >= list.length - 1)
            doubleCapacity();
        // end if
    } // end ensureCapacity    
    
    // Makes room for a new entry at newPosition.
    // Precondition: 1 <= newPosition <= numberOfEntries + 1;
    //               numberOfEntries is list’s length before addition;
    //               checkInitialization has been called.
    private void makeRoom(int newPosition)
    {
        assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
        int newIndex = newPosition;
        int lastIndex = numberOfEntries;
        // Move each entry to next higher index, starting at the end of list and 
        // continuing until entry at nexindex is moved.
        for (int index = lastIndex; index >= newIndex; index--)
            list[index + 1] = list[index];
        // end for        
    } // end makeRoom
    
    // Shifts entries that are beyond the entry to be removed to the
    // next lower position.
    // Precondition: 1 <= givenPosition < numberOfEntries;
    //               numberOfEntries is list’s length before removal;
    //               checkInitialization has been called.
    private void removeGap(int givenPosition)
    {
        assert (givenPosition >= 1) && (givenPosition <= numberOfEntries);
        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;
        for (int index = removedIndex; index < lastIndex; index++)
            list[index] = list[index + 1];
        // end for        
    } // end makeRoom    
    
    /** @return An iterator for a collection of objects of type T.*/
    public Iterator<T> iterator()
    {
        Iterator<T> myIterator = new MyIterator<T>(this);
        return myIterator;        
    }// end iterator
    
    public class MyIterator<T> implements Iterator<T>
    {
        private int cursor; // Current location of the cursor; 0 means beginning
        private MyList<T> list;
        private T nextEntry;
        
        public MyIterator(MyList newList)
        {
            list = newList;
            cursor = 0;
            nextEntry = null;
        } // end constructor
        
        /** Detects whether this iterator has completed its traversal
            and gone beyond the last entry in the collection of data. 
            @return True if the iterator has another entry to return. */
        public boolean hasNext()
        {
            return (!isEmpty() && (cursor  <= numberOfEntries));
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
                T currentEntry;
                if (cursor == 0)
                {
                    currentEntry = list.getEntry(cursor+1);
                    cursor++;
                }
                else
                    currentEntry = list.getEntry(cursor);
                cursor++;
                nextEntry = currentEntry;
                return currentEntry;
            } 
            else
                throw new NoSuchElementException();
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
            if (nextEntry == null)
            {
                if (cursor == 0)
                    throw new IllegalStateException("Empty list.");
                else
                    throw new IllegalStateException("next() has not been called "
                                + "or remove() was already called after the last"
                                + " call to next()");
            }
            else
            {
                list.remove(cursor-1);
                cursor--;
                nextEntry = null;
            } // end if
            
        } // end remove
    } // end MyIterator
} // end MyList
