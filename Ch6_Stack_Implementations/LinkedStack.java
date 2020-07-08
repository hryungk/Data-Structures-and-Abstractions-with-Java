/**
   A class of stacks whose entries are stored in a chain of nodes.
   @author Frank M. Carrano, Timothy M. Henry
   @version 5.0 
*/
import java.util.EmptyStackException;
public final class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode;       // References the first node in the chain

    public LinkedStack()
    {
        topNode = null;
    } // end default constructor
    
    /** Adds a new entry to the top of this stack.
        @param newEntry  An object to be added to the stack. */
    public void push(T newEntry)
    {
        // Add to beginning of chain:
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode; 
        // or all-in-one: topNode = new Node(newEntyr, topNode);
    } // end push

    /** Removes and returns this stack's top entry.
        @return  The object at the top of the stack. 
        @throws  EmptyStackException if the stack is empty before the operation. */
    public T pop()
    {
        T top = peek(); // Might throw EmptyStackException
        assert topNode != null;
        topNode = topNode.getNextNode();
        
        return top;        
    } // end pop
  
    /** Retrieves this stack's top entry.
        @return  The object at the top of the stack.
        @throws  EmptyStackException if the stack is empty. */
    public T peek()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.getData();
    } // end peek

    /** Detects whether this stack is empty.
        @return  True if the stack is empty. */
    public boolean isEmpty()
    {
         return topNode == null;
    } // end isEmpty
  
    /** Removes all entries from this stack. */
    public void clear()
    {
        topNode = null;
    }  // end clear

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
