/**
    A class that implements a queue of objects by using an array.
    @author Frank M. Carrano
*/
import java.util.Arrays;

public final class ArrayQueue<T> implements QueueInterface<T>
{
    private T[] queue; 
    private int frontIndex;
    private int backIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    /** Creates an empty queue whose initial capacity is 25. */
    public ArrayQueue() 
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    /** Creates an empty queue having a given capacity.
        @param initialCapacity  The integer capacity desired. */
    public ArrayQueue(int initialCapacity)
    {
        checkCapacity(initialCapacity);
        
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[])new Object[initialCapacity + 1]; // Unchecked cast
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
        initialized = true;
    } // end constructor
   
    /** Adds a new entry to this queue.
        @param newEntry  The object to be added as a new entry. */
    public void enqueue(T newEntry)
    {
        checkInitialization();
        ensureCapacity();
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
    } // end enqueue


    /** Removes one unspecified entry from this queue, if possible.
        @return  Either the removed entry, if the removal
                 was successful, or null. */
    public T dequeue()
    {
        checkInitialization();
        T front = getFront();
        queue[frontIndex] = null;
        frontIndex = (frontIndex + 1) % queue.length;
        return front;
    } // end dequeue

    /** Retrieves the entry at the front of this queue.
       @return  The object at the front of the queue.
       @throws  EmptyQueueException if the queue is empty. */
    public T getFront()
    {
        checkInitialization();
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return queue[frontIndex];
    } // end getFront

    /** Sees whether this queue is empty.
   @return  True if this queue is empty, or false if not. */
    public boolean isEmpty()
    {
        return frontIndex == (backIndex + 1) % queue.length;
    } // end isEmpty
   
    /** Removes all entries from this queue. */
    public void clear() 
    {
        // Optional deallocation
        while (!isEmpty())
            dequeue();  // Only if dequeue sets dequeued location null.
        
        frontIndex = 0;
        backIndex = queue.length - 1;
        
    } // end clear
   
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

    private void ensureCapacity() 
    {
        if (frontIndex == (backIndex + 2) % queue.length) // If array is full, double its size 
        {
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize-1);
            
            // The case is safe because the new entry contains null entries
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            queue = tempQueue;
            
            for (int index = 0; index < newSize; index++)
            {
                queue[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            } // end for
            frontIndex = 0;
            backIndex = oldSize - 2;
        } // end if
    } // end ensureCapacity    
} // end ArrayQueue
