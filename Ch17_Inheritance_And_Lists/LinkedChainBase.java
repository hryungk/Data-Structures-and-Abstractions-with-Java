public abstract class LinkedChainBase<T>
{
    private Node firstNode; // Reference to first ndoe of chain
    private int numberOfEntries;
    
    public LinkedChainBase()
    {
        initializeDataFields();
    } // end default constructor
 
    /** Removes all entries from this list. */
    public void clear()
    {
        initializeDataFields();
    } // end clear
    
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
        boolean result;
        if (numberOfEntries == 0)   // Or getLength(0 == 0
        {
            assert firstNode == null;
            result = true;
        }
        else
        {
            assert firstNode != null : "numberOfEntries is not 0 but firstNode is null";
            result = false;
        } // end if
        return result;
    } // end isEmpty

    /** Retrieves all entries that are in this list in the order in which they
        occur in the list.
        @return  A newly allocated array of all the entries in the list.
                 If the list is empty, the returned array is empty. */
    public T[] toArray()
    {
        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast

        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null))
        //OR for (int index = 0; index < numberOfEntries; index++) 
        {
            result[index] = currentNode.getData();
            currentNode.getNextNode();
            index++;
        } // end for
        return result;
    } // end toArray

    
    // Returns a reference to the node at a given position.
    // Precondition: The chain is not empty;
    //               1 <= givenPosition <= numberOfEntries.
    protected final Node getNodeAt(int givenPosition)
    {
        assert (firstNode != null) && 
                (1 <= givenPosition) && (givenPosition <= numberOfEntries);
        Node currentNode = firstNode;
        // Traverse the chain to locate the desired node
        // (skipped if givenPostiion is 1)
        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();
        assert currentNode != null;
        
        return currentNode;
    } // end getNodeAt
            
    protected final Node getFirstNode()
    {
        return firstNode;
    } // end getFirstNode
    
    /** Adds a node to the beginning of a chain. 
        @param newNode The new node to be inserted. */
    protected final void addFirstNode(Node newNode)
    {
        assert newNode != null : "null argument in addFirstNode";
        newNode.setNextNode(firstNode);
        firstNode = newNode;
        numberOfEntries++;
    } // end addFirstNode
    
    /** Adds a node to a chain after a given node. 
    @param nodeBefore The node before the new node.
    @param newNode The node to be inserted. */    
    protected final void addAfterNode(Node nodeBefore, Node newNode) 
    {
        assert newNode != null : "null argument in addFirstNode";  
        Node nodeAfter =  nodeBefore.getNextNode();
        newNode.setNextNode(nodeAfter);
        nodeBefore.setNextNode(newNode); 
        numberOfEntries++;
    } // end addAfterNode
    
    /** Removes a chain’s first node. 
        @return A datum that is removed. */
    protected final T removeFirstNode()
    {
        T result = firstNode.getData();       // Save entry to be removed
        firstNode = firstNode.getNextNode(); // Remove entry
        numberOfEntries--;                      // Update count
        return result;
    } // end removeFirstNode
    
    /** Removes the node after a given one.
        @param nodeBefore The node before the node to be removed.
        @return The datum that is removed. */    
    protected final T removeAfterNode(Node nodeBefore) 
    {        
        Node nodeToRemove = nodeBefore.getNextNode();
        T result = nodeToRemove.getData();    // Save entry to be removed
        Node nodeAfter = nodeToRemove.getNextNode();
        nodeBefore.setNextNode(nodeAfter);  // Remove entry   
        numberOfEntries--;                      // Update count
        return result;
    } // removeAfterNode    
    
    // Initializes the class's data fields to indicate an empty list.
    protected final void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end initializeDataFields    
    
    // @author Frank M. Carrano, Timothy M. Henry
    // @version 5.0 */
    protected final class Node
    {
        private T    data;  // Entry in bag
        private Node next;  // Link to next node

        protected Node(T dataPortion)
        {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        } // end constructor

        protected final T getData()
        {
            return data;
        } // end getData

        protected final void setData(T newData)
        {
            data = newData;
        } // end setData

        protected final Node getNextNode()
        {
            return next;
        } // end getNextNode

        private final void setNextNode(Node nextNode)
        {
            next = nextNode;
        } // end setNextNode
    } // end Node    
} // end ListInterface
