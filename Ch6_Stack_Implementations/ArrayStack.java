import java.util.*;
/**
   A class of stacks whose entries are stored in an array.
   @author Frank M. Carrano, Timothy M. Henry
   @version 5.0 
*/
public final class ArrayStack<T> implements StackInterface<T>
{
    private T[] stack;      // Array of stack entries
    private int topIndex;   // Index of top entry
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    /** Creates an empty stack whose initial capacity is 50. */
    public ArrayStack() 
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    /** Creates an empty stack having a given capacity.
        @param initialCapacity  The integer capacity desired. */
    public ArrayStack(int initialCapacity)
    {
        checkCapacity(initialCapacity);
        
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity]; // Unchecked cast
        stack = tempStack;
        topIndex = -1;
        initialized = true;
        // Test that contents are nulls - OK
        //      for (int index = 0; index < initialCapacity; index++) 
        //         System.out.print(stack[index] + " ");
        //      System.out.println();

    } // end constructor
   
    /** Adds a new entry to this stack.
        @param newEntry  The object to be added as a new entry.
        @return  True if the addition is successful, or false if not. */
    public void push(T newEntry)
    {
        checkInitialization();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
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
           return stack[topIndex];
   } // end peek  

   /** Removes and returns this stack's top entry.
       @return  The object at the top of the stack. 
       @throws  EmptyStackException if the stack is empty before the operation. */
    public T pop()
    {
        T top = peek(); // Might throw EmptyStackException
        assert !isEmpty();
        stack[topIndex] = null;
        topIndex--;

        return top;   
    } // end pop

    /** Detects whether this stack is empty.
        @return  True if the stack is empty. */
    public boolean isEmpty()
    {
        return topIndex < 0;
    } // end isEmpty

    /** Removes all entries from this stack. */
    public void clear() 
    {
        while (!isEmpty())
            pop();
    } // end clear
    
    /** Retrieves all entries that are in this stack.
        @return  A newly allocated array of all the entries in this stack. */
    //	public <T> T[] toArray() //OK
    public T[] toArray() //OK
    {
        checkInitialization();
      
        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[topIndex+1]; // Unchecked cast

        for (int index = 0; index < topIndex+1; index++) 
        {
            result[index] = stack[index];
        } // end for
        return result;
        // Note: The body of this method could consist of one return statement,
        // if you call Arrays.copyOf
    } // end toArray
    
    /** Gets the current number of entries in this stack.
        @return  The integer number of entries currently in this stack. */
    public int getCurrentSize()
    {
        return topIndex+1;
    } // end getCurrentSize

    /** Counts the number of times a given entry appears in this stack.
        @param anEntry  The entry to be counted.
        @return  The number of times anEntry appears in this ba. */
    public int getFrequencyOf(T anEntry)
    {
        checkInitialization();
        int counter = 0;

        for (int index = 0; index < topIndex+1; index++) 
        {
            if (anEntry.equals(stack[index]))
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
      
        while (!found && (index < topIndex+1))
        {
            if (anEntry.equals(stack[index]))
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
    // Precondition: 0 <= givenIndex < topIndex+1.
    // Precondition: checkInitialization has been called.
    private T removeEntry(int givenIndex)
    {
        T result = null;
      
        if (!isEmpty() && (givenIndex >= 0))
        {
            result = stack[givenIndex];          // Entry to remove
            int lastIndex = topIndex;
            stack[givenIndex] = stack[lastIndex];  // Replace entry to remove with last entry
            stack[lastIndex] = null;             // Remove reference to last entry
            topIndex--;
        } // end if
      
        return result;
    } // end removeEntry

    
    // Throws an exception if this object is not initialized.
    private void checkInitialization()
    {
        if (!initialized)
            throw new SecurityException("ArrayStack object is corrupt.");
    } // end checkInitialization  
    
    // Returns true if the array stack is full, or false if not.
    private void ensureCapacity()
    {
        if (topIndex >= stack.length - 1)
            doubleCapacity();
        // end if
    } // end ensureCapacity
    
    // Doubles the size of the array stack.
    // Precondition: checkInitialization has been called.
    private void doubleCapacity()
    {
        int newLength = 2 * stack.length;
        checkCapacity(newLength);
        stack = Arrays.copyOf(stack, newLength);
    } // end doubleCapacity    

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int initialCapacity)
    {
        if (initialCapacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a stack " +
                                         "whose capacity exceeds " +
                                         "allowed maximum of ." + MAX_CAPACITY);
    } 
} // end LinkedStack
