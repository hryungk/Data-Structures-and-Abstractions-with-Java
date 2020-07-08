import java.util.Iterator;
import java.util.NoSuchElementException;
public class SeparateIterator<T> implements Iterator<T>
{

    private ListInterface<T> list;
    private int nextPosition; // Position of the entry last returned by next.
    private boolean wasNextCalled;  // Needed by remove

    public SeparateIterator(ListInterface<T> myList)
    {
        list = myList;
        nextPosition = 0;
        wasNextCalled = false;
    } // end constructor

    /** Detects whether this iterator has completed its traversal
        and gone beyond the last entry in the collection of data. 
        @return True if the iterator has another entry to return. */
    public boolean hasNext()
    {
        return (nextPosition < list.getLength());
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
            wasNextCalled = true;
            nextPosition++;            
            return list.getEntry(nextPosition);
        } 
        else
            throw new NoSuchElementException("Illegal call to next(); " + 
                                            "iterator is after end of list.");
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
        if (wasNextCalled)
        {
            // nextPosition was incremented by the call to next(), so it is the 
            // position number of the entry to be removed.
            list.remove(nextPosition);
            nextPosition--; // A subsequent call to next() must be unaffected by
                            // this removal
            wasNextCalled = false; // Reset flag
        }
        else
        {
            if (nextPosition == 0)
                throw new IllegalStateException("Illegal call to remove(); " + 
                                                "Empty list.");
            else
                throw new IllegalStateException("Illegal call to remove(); " + 
                                                "next() has not been called");
        } // end if
    } // end remove
} // end SeparateIterator
