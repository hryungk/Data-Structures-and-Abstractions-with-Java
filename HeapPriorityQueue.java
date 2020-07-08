/**
   A class that implements the ADT priority queue by using a maxheap.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public final class HeapPriorityQueue<T extends Comparable<? super T>>
                   implements PriorityQueueInterface<T>
{
    private MaxHeapInterface<T> pq;	

    public HeapPriorityQueue()
    {
            pq = new MaxHeap<>();
    } // end default constructor

    public void add(T newEntry)
    { 
            pq.add(newEntry);
    } // end add

/* < Implementations of remove, peek, isEmpty, getSize, and clear are here. >
   . . . */
} // end HeapPriorityQueue
