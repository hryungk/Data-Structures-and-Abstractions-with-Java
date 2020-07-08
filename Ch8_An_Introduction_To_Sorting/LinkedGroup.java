/** 
    A class of a group whose entries are stored in a chain of linked nodes.
*/
public class LinkedGroup<T extends Comparable<? super T>>
{
    private Node firstNode;
    int length; // Number of objects in the group.
    
    public LinkedGroup()
    {
        firstNode = null;
        length = 0;
    } // end default constructor
    
    /** Sorts the array entries. */
    public void insertionSort()
    {
        // If fewer than two items are in the chain, there is nothing to do
        if (length > 1)
        {
            assert firstNode != null;
            // Break chain into 2 pieces: sorted and unsorted
            Node unsortedPart = firstNode.getNextNode();
            assert unsortedPart != null;
            firstNode.setNextNode(null);
            
            while (unsortedPart != null)
            {
                Node nodeToInsert = unsortedPart;
                unsortedPart = unsortedPart.getNextNode();
                insertInOrder(nodeToInsert);
            } // end while            
        } // end if
    } // end insertionSort
    
    // Insert the node to the sorted array in the correct position.
    private void insertInOrder(Node nodeToInsert)
    {
        T item = nodeToInsert.getData();
        Node currentNode = firstNode;
        Node previousNode = null;
        
        //Locate insertion point
        while (currentNode != null && (item.compareTo(currentNode.getData())>0))
        {
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        } // end while
        
        // Make the insertion
        if (previousNode != null)
        {
            previousNode.setNextNode(nodeToInsert);
            nodeToInsert.setNextNode(currentNode);
        }
        else // Insert at beginning
        {
            nodeToInsert.setNextNode(firstNode);
            firstNode = nodeToInsert;            
        } // end if        
    } // end insertInOrder
    
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
          
} // end LinkedGroup
