import java.util.Arrays;
/**
   A class that implements the ADT maxheap by using an array.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public final class MaxHeap<T extends Comparable<? super T>>
             implements MaxHeapInterface<T>
{
    private T[] heap;      // Array of heap entries; ignore heap[0]
    private int lastIndex; // Index of last entry and number of entries
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    
    public MaxHeap()
    {
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end default constructor

    public MaxHeap(int initialCapacity)
    {
        // Is initialCapacity too small?
        if (initialCapacity < DEFAULT_CAPACITY)
           initialCapacity = DEFAULT_CAPACITY;
        else // Is initialCapacity too big?
           checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[])new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
    } // end constructor
    
    public MaxHeap(T[] entries)
    {
        this(entries.length);   // Call other constructor
        lastIndex = entries.length;
        assert initialized = true;
        // Copy given array to data field
        for (int index = 0; index < lastIndex; index++)
            heap[index + 1] = entries[index];
        // Create heap
        for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
            reheap(rootIndex);        
    } // end constructor

    public void add(T newEntry)
    {
        checkInitialization();  // Ensure initialization of data fields
        int newIndex = lastIndex + 1;   // Index of next available array location
        int parentIndex = newIndex / 2; // Index of parent of available location
        while ((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
        {
            // Move parent to available location
            heap[newIndex] = heap[parentIndex];
            // Update indices
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        } // end while
        heap[newIndex] = newEntry;  // Place new entry in correct location
        lastIndex++;
        ensureCapacity();
    } // end add

    public T removeMax()
    {
        checkInitialization();          // Ensure initialization of data fields        
        T root = null;
        if (!isEmpty())
        {
            root = heap[1];             // Return a value
            heap[1] = heap[lastIndex];  // Form a semiheap
            lastIndex--;                // Decrease size
            reheap(1);                  // Transform to a heap
        } /// end if
        return root;    
    } // end removeMax
    
    // Transforms the semiheap at rootIndex into a heap.
    private void reheap(int rootIndex)
    {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;
        while (!done && (leftChildIndex <= lastIndex))
        {
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if ((rightChildIndex <= lastIndex) && 
                    heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
            {
                largerChildIndex = rightChildIndex;
            } // end if
            
            if (orphan.compareTo(heap[largerChildIndex]) < 0)
            {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
                done = true;
        } // end while
        heap[rootIndex] = orphan;
    } // end reheap

    /** Revised reheap method.
        @param <T> Data type of the entry
        @param heap The array containing the heap entries
        @param rootIndex The index of the root of semiheap
        @param lastIndex The last index that the array heap ranges. */
    private static <T extends Comparable<? super T>> 
            void reheap(T[] heap, int rootIndex, int lastIndex)
    {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex + 1;
        while (!done && (leftChildIndex <= lastIndex))
        {
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if ((rightChildIndex <= lastIndex) && 
                    heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
            {
                largerChildIndex = rightChildIndex;
            } // end if
            
            if (orphan.compareTo(heap[largerChildIndex]) < 0)
            {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex + 1;
            }
            else
                done = true;
        } // end while
        heap[rootIndex] = orphan;
    } // end reheap
    
    public static <T extends Comparable<? super T>> void heapSort(T[] array, int n)
    {
        // Create heap
        for (int rootIndex = n / 2 - 1; rootIndex >= 0; rootIndex--)
            reheap(array, rootIndex, n - 1);    
        swap(array, 0, n - 1);
        
        for (int lastIndex = n - 2; lastIndex > 0; lastIndex--)
        {
            reheap(array, 0, lastIndex);
            swap(array, 0, lastIndex);
        } // end for
    } // end heapSort
            
    private static <T extends Comparable<? super T>> 
            void swap(T[] array, int firstIndex, int secondIndex)
    {
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    } // end swap
            
    public T getMax()
    {
        checkInitialization();
        T root = null;
        if (!isEmpty())
           root = heap[1];
        return root;
    } // end getMax

    public boolean isEmpty()
    {
        return lastIndex < 1;
    } // end isEmpty

    public int getSize()
    {
        return lastIndex;
    } // end getSize

    public void clear()
    {
        checkInitialization();
        while (lastIndex > -1)
        {
           heap[lastIndex] = null;
           lastIndex--;
        } // end while
        lastIndex = 0;
    } // end clear
   
    // Private methods
    
    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity) 
    {
        if (capacity > MAX_CAPACITY)
        throw new IllegalStateException("Attempt to create a bag whose " +
                                        "capacity exeeds allowed " +
                                        "maximum of " + MAX_CAPACITY);
    } // end checkCapacity
    
    // Throws an exception if this object is not initialized.
    private void checkInitialization()
    {
        if (!initialized)
            throw new SecurityException("ArrayQueue object is corrupt.");
    } // end checkInitialization
    
    // Returns true if the array stack is full, or false if not.
    private void ensureCapacity()
    {
        if (lastIndex >= heap.length - 1)
        {
            // Double the capacity
            int newCapacity = 2 * (heap.length - 1);
            checkCapacity(newCapacity);   // Is capacity too big?
            heap = Arrays.copyOf(heap, newCapacity);
        }
        // end if
    } // end ensureCapacity      
} // end MaxHeap
