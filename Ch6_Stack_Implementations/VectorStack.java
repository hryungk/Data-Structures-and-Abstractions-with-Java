import java.util.*;
/**
   A class of stacks whose entries are stored in a vector.
   @author Frank M. Carrano, Timothy M. Henry
   @version 5.0 
*/
public final class VectorStack<T> implements StackInterface<T>
{
    private Vector<T> stack;      // Last elements is the top entry in stack
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    /** Creates an empty stack whose initial capacity is 50. */
    public VectorStack() 
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    /** Creates an empty stack having a given capacity.
        @param initialCapacity  The integer capacity desired. */
    public VectorStack(int initialCapacity)
    {
        checkCapacity(initialCapacity);
        stack = new Vector<>(initialCapacity);// Size doubles as needed
        initialized = true;
    } // end constructor
   
    /** Adds a new entry to this stack.
        @param newEntry  The object to be added as a new entry.
        @return  True if the addition is successful, or false if not. */
    public void push(T newEntry)
    {
        checkInitialization();
        stack.add(newEntry);
    } // end push

   /** Retrieves this stack's top entry.
       @return  The object at the top of the stack.
       @throws  EmptyStackException if the stack is empty. */
   public T peek()
   {
        checkInitialization();
        if (isEmpty())
            throw new EmptyStackException();
        else
           return stack.lastElement();
   } // end peek  

   /** Removes and returns this stack's top entry.
       @return  The object at the top of the stack. 
       @throws  EmptyStackException if the stack is empty before the operation. */
    public T pop()
    {
        checkInitialization();
        if (isEmpty())
            throw new EmptyStackException();
        else
            return stack.remove(stack.size() - 1);

    } // end pop

    /** Detects whether this stack is empty.
        @return  True if the stack is empty. */
    public boolean isEmpty()
    {
        return stack.isEmpty();
    } // end isEmpty

    /** Removes all entries from this stack. */
    public void clear() 
    {
        stack.clear();
    } // end clear
    
    /** Retrieves all entries that are in this stack.
        @return  A newly allocated array of all the entries in this stack. */
    //	public <T> T[] toArray() //OK
    public T[] toArray() //OK
    {
        checkInitialization();
      
        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[stack.size()]; // Unchecked cast

        for (int index = 0; index < stack.size(); index++) 
        {
            result[index] = stack.get(index);
        } // end for
        return result;
        // Note: The body of this method could consist of one return statement,
        // if you call Arrays.copyOf
    } // end toArray
    
    /** Gets the current number of entries in this stack.
        @return  The integer number of entries currently in this stack. */
    public int getCurrentSize()
    {
        return stack.size();
    } // end getCurrentSize

    /** Counts the number of times a given entry appears in this stack.
        @param anEntry  The entry to be counted.
        @return  The number of times anEntry appears in this ba. */
    public int getFrequencyOf(T anEntry)
    {
        checkInitialization();
        int counter = 0;

        for (int index = 0; index < stack.size(); index++) 
        {
            if (anEntry.equals(stack.get(index)))
            {
                counter++;
            } // end if
        } // end for

        return counter;
    } // end getFrequencyOf

    /** Tests whether this stack contains a given entry.
        @param anEntry  The entry to locate.
        @return  True if this stack contains anEntry, or false otherwise. */
    public boolean contains(T anEntry)
    {
        checkInitialization();
        return getIndexOf(anEntry) > -1; // or >= 0
    } // end contains
    
    // Locates a given entry within the array stack.
    // Returns the index of the entry, if located,
    // or -1 otherwise.
    // Precondition: checkInitialization has been called.
    private int getIndexOf(T anEntry)
    {
        int where = -1;
        boolean found = false;      
        int index = 0;
      
        while (!found && (index < stack.size()))
        {
            if (anEntry.equals(stack.get(index)))
            {
                found = true;
                where = index;
            } // end if
            index++;
        } // end while
      
        // Assertion: If where > -1, anEntry is in the array stack, and it
        // equals stack[where]; otherwise, anEntry is not in the array.
      
        return where;
    } // end getIndexOf
   
    // Removes and returns the entry at a given index within the array.
    // If no such entry exists, returns null.
    // Precondition: 0 <= givenIndex < stack.size().
    // Precondition: checkInitialization has been called.
    private T removeEntry(int givenIndex)
    {
        T result = null;
      
        if (!isEmpty() && (givenIndex >= 0))
        {
            result = stack.get(givenIndex);          // Entry to remove
            int lastIndex = stack.size()-1;
            stack.set(lastIndex, stack.get(lastIndex));  // Replace entry to remove with last entry
            stack.set(lastIndex, null);             // Remove reference to last entry
        } // end if
      
        return result;
    } // end removeEntry

    
    // Throws an exception if this object is not initialized.
    private void checkInitialization()
    {
        if (!initialized)
            throw new SecurityException("ArrayStack object is corrupt.");
    } // end checkInitialization   

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int initialCapacity)
    {
        if (initialCapacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a stack " +
                                         "whose capacity exceeds " +
                                         "allowed maximum of ." + MAX_CAPACITY);
    } 
} // end LinkedStack
