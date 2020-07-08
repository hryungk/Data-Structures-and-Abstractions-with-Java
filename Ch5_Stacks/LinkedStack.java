//Note: We are using the class LinkedStack instead of 
//      the class OurStack that the book uses.
/**
   A class that checks whether the parentheses, brackets, and braces 
   in a string occur in left/right pairs.
   @author Frank M. Carrano, Timothy M. Henry
   @version 5.0 */
public class LinkedStack<T> implements StackInterface<T>
{
    private Node firstNode;       // Reference to first node
    private int numberOfEntries;

    public LinkedStack()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end default constructor
    
    /** Adds a new entry to the top of this stack.
        @param newEntry  An object to be added to the stack. */
    public void push(T newEntry)
    {
        // Add to beginning of chain:
        Node newNode = new Node(newEntry);
        newNode.setNextNode(firstNode);  // Make new node reference rest of chain
                         // (firstNode is null if chain is empty)
        firstNode = newNode;       // New node is at beginning of chain
        numberOfEntries++;       
    }

    /** Removes and returns this stack's top entry.
        @return  The object at the top of the stack. 
        @throws  EmptyStackException if the stack is empty before the operation. */
    public T pop()
    {
        T result = null;
        if (firstNode != null)
        {
            Node removingNode = firstNode;
            result = removingNode.getData();
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
        }
        return result;        
    }
  
    /** Retrieves this stack's top entry.
        @return  The object at the top of the stack.
        @throws  EmptyStackException if the stack is empty. */
    public T peek()
    {
        T result = null;
        if (firstNode != null)
            result = firstNode.getData();
        return result;
    }

    /** Detects whether this stack is empty.
        @return  True if the stack is empty. */
    public boolean isEmpty()
    {
         return numberOfEntries == 0;
    }
  
    /** Removes all entries from this stack. */
    public void clear()
    {
        firstNode = null;
    }  

    // @author Frank M. Carrano, Timothy M. Henry
    // @version 5.0 */
    private class Node
    {
        private T    data;  // Entry in bag
        private Node next;  // Link to next node

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        } // end constructor

        private T getData()
        {
            return data;
        } // end getData

        private void setData(T newData)
        {
            data = newData;
        } // end setData

        private Node getNextNode()
        {
            return next;
        } // end getNextNode

        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        } // end setNextNode
    } // end Node
   
} // end LinkedStack
