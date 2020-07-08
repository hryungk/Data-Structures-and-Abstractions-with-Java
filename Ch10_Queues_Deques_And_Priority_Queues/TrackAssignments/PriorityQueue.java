package TrackAssignments;

/**
   A class that implements a queue of objects by using
   a chain of linked nodes.
   @author Frank M. Carrano
*/
public final class PriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T>
{
    private final T[] queue; 
    private int numberOfEntries;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    
    
    public PriorityQueue()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor
    
    /** Creates an empty queue having a given capacity.
        @param desiredCapacity  The integer capacity desired. */
    public PriorityQueue(int desiredCapacity)
    {
        if (desiredCapacity <= MAX_CAPACITY)
        {
            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[])new Comparable<?>[desiredCapacity]; // Unchecked cast
            queue = tempQueue;
            numberOfEntries = 0;
            integrityOK = true;
            // Test that contents are nulls - OK
            //      for (int index = 0; index < desiredCapacity; index++) 
            //         System.out.print(queue[index] + " ");
            //      System.out.println();
        }
        else
            throw new IllegalStateException("Attempt to create a queue " +
                                         "whose capacity exceeds " +
                                         "allowed maximum.");
    } // end constructor
    
    
    /** Adds a new entry to the front/back of this priority queue.
        @param newEntry  An object to be added. */
    public void add(T newEntry)
    {
        checkIntegrity();
        boolean result = true;
        if (isArrayFull())
        {
             result = false;
        }
        else
        {  // Assertion: result is true here
            queue[numberOfEntries] = newEntry;
            numberOfEntries++;
        } // end if
        
        SortArray.quickSort(queue, 0, numberOfEntries-1);
    } // end add
    
    /** Removes and returns the entry having the highest priority.
        @return Either the object having the highest priority or, if the
                priority queue is empty before the operation, null. */
    public T remove()
    {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    } // end remove
    /** Retrieves the entry having the highest priority.
        @return Either the object having the highest priority or, if the
                priority queue is empty, null. */
    public T peek()
    {
        return queue[0];
    } // end peek
    
    /** Detects whether this priority queue is empty.
       @return  True if the priority queue is empty, or false otherwise. */
    public boolean isEmpty()
    {
        return false;
    } // end isEmpty
    
    /** Gets the size of this priority queue.
        @return  The number of entries currently in the priority queue. */
    public int getSize()
    {
        return numberOfEntries;
    } // end getSize

    /* Removes all entries from this priority queue. */
    public void clear()
    {
        while (!isEmpty())
            remove();
    } // end clear
    
    // Removes and returns the entry at a given index within the array.
    // If no such entry exists, returns null.
    // Precondition: 0 <= givenIndex < numberOfEntries.
    // Precondition: checkInitialization has been called.
    private T removeEntry(int givenIndex)
    {
        T result = null;
      
        if (!isEmpty() && (givenIndex >= 0))
        {
            result = queue[givenIndex];          // Entry to remove
            int lastIndex = numberOfEntries - 1;
            queue[givenIndex] = queue[lastIndex];  // Replace entry to remove with last entry
            queue[lastIndex] = null;             // Remove reference to last entry
            numberOfEntries--;
        } // end if
      
        return result;
    } // end removeEntry
   
    // Throws an exception if this object is not initialized.
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("ArrayQueue object is corrupt.");
    } // end checkIntegrity
    
// Returns true if the array queue is full, or false if not.
    private boolean isArrayFull()
    {
        return numberOfEntries >= queue.length;
    } // end isArrayFull
} // end LinkedQueue
