package ADTPackage;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
    A class that implements a dictionary by using a resizable sorted array.
    The dictionary is unsorted and has distinct search keys.
    @author Frank M. Carrano
*/
public class LinkedDictionary<K, V> implements DictionaryInterface<K, V>
{
    private Node firstNode; // Reference to first ndoe of chain
    private int numberOfEntries;    
    
    public LinkedDictionary()
    {
        initializeDataFields();
    } // end default constructor
    
    /** Adds a new entry to this dictionary. If the given search key already
       exists in the dictionary, replaces the corresponding value.
       @param key    An object search key of the new entry.
       @param value  An object associated with the search key.
       @return  Either null if the new entry was added to the dictionary
                or the value that was associated with key if that value
                was replaced. */
    public V add(K key, V value)
    {                
        V result = null;   
        // Search chain until you find a node containing key
        Node existingNode = getNodeOf(key);
        if (existingNode != null)   
        {   // already exist in the dictionary
            result = existingNode.getValue();
            existingNode.setValue(value);
        }
        else
        {   // Doesn't exist in the dictionary
            Node newNode = new Node(key, value); // Create new node
            if (isEmpty()) // Add to the empty dictionary    
                firstNode = newNode;            
            else // Add at the end of the nonempty dictionary 
            {
                Node lastNode = getNodeAt(numberOfEntries);
                lastNode.setNextNode(newNode); //Make last node reference new node                
            }                           
            numberOfEntries++;
        }// end if        
        return result;        
    } // end add
    
    /** Removes a specific entry from this dictionary.
        @param key An object search key of the entry to be removed.
        @return Either the value that was associated with the search key
                or null if no such object exists. */
    public V remove(K key)
    {
        V result = null;   
        // Search chain until you find a node containing key
        Node nodeBefore = getNodeBefore(key);
        Node currentNode = null;        
        if (nodeBefore != null)
            currentNode = nodeBefore.getNextNode();
        else
        {
            if (key == firstNode.getKey())
                currentNode = firstNode;
        } // end if                
        
        if ((currentNode != null) && key.equals(currentNode.getKey()))
        {
            result = currentNode.getValue();
            Node nodeAfter = currentNode.getNextNode();
            nodeBefore.setNextNode(nodeAfter);
        }     
        return result;
    } // end remove
    
    /** Retrieves from this dictionary the value associated with a given
        search key.
        @param key An object search key of the entry to be retrieved.
        @return Either the value that is associated with the search key
         or null if no such object exists. */
    public V getValue(K key)
    {
        V result = null;        
        // Search chain until you find a node containing key
        Node currentNode = getNodeOf(key);
        if (currentNode != null)
            result = currentNode.getValue();        
        return result;
    } // end getValue
    
    /** Sees whether a specific entry is in this dictionary.
        @param key  An object search key of the desired entry.
        @return  True if key is associated with an entry in the dictionary. */
    public boolean contains(K key)    
    {
        boolean result = false;
        // Search chain until you find a node containing key        
        Node currentNode = getNodeOf(key);
        result = currentNode != null;
        return result;
    } // end contains
    
    /** Creates an iterator that traverses all search keys in this dictionary.
        @return  An iterator that provides sequential access to the search
                 keys in the dictionary. */
    public Iterator<K> getKeyIterator()    
    {            
        Iterator<K> myIterator = new KeyIterator();
        return myIterator;  
    } // end getKeyIterator   
    
    /** Creates an iterator that traverses all values in this dictionary.
        @return  An iterator that provides sequential access to the values
                 in this dictionary. */
    public Iterator<V> getValueIterator()
    {
        Iterator<V> myIterator = new ValueIterator();
        return myIterator;  
    } // end getValueIterator
    
    /** Sees whether this dictionary is empty.
        @return  True if the dictionary is empty. */
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
           
    /** Gets the size of this dictionary.
        @return  The number of entries (key-value pairs) currently
                 in the dictionary. */
    public int getSize()    
    {   
        return numberOfEntries;
    } // end getSize
    
    /** Removes all entries from this dictionary. */
    public void clear()   
    {    
        initializeDataFields();
    } // end clear
        
    // Initializes the class's data fields to indicate an empty list.
    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end initializeDataFields  
        
    // Finds the node that is before the node that should contain a given entry.
    // Returns either a reference to the node that is before the node
    // that contains key, or null if no node exist containing key or no prior node  
    // exists (that is, if key is or belongs at the beginning of the list).
    private Node getNodeBefore(K key)
    {
        Node result = null;
        boolean found = false;         
        Node nodeBefore = null;        
        Node currentNode = firstNode;
        while (!found && (currentNode != null))
        {
            if (key == currentNode.getKey())
            {
                found = true;
                result = nodeBefore;                
            }
            else
            {
                nodeBefore = currentNode;
                currentNode = currentNode.getNextNode();         
            }
        } // end while
        return result;
    } // end getNodeBefore
    
    // Finds the node that contains the key
    // Returns either a reference to the node that contains key, 
    //             or null if no node exists 
    private Node getNodeOf(K key)
    {        
        Node result = null;
        Node nodeBefore = getNodeBefore(key);
        if (nodeBefore != null)
            result = nodeBefore.getNextNode();
        else
        {
            if (!isEmpty() && key == firstNode.getKey())
                result = firstNode;
        } // end if
        return result;
    } // end getNodeOf
    
    // Returns a reference to the node at a given position.
    // Precondition: The chain is not empty;
    //               1 <= givenPosition <= numberOfEntries.
    private Node getNodeAt(int givenPosition)
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
    
    private class KeyIterator implements Iterator<K>
    {
        private Node nextNode; // Node containing next entry in iteration
        
        public KeyIterator()
        {
            nextNode = firstNode;
        } // end default constructor
        
        /** Detects whether this iterator has completed its traversal
            and gone beyond the last entry in the collection of data. 
            @return True if the iterator has another entry to return. */
        public boolean hasNext()
        {
            return nextNode != null;
        } // end hasNext

        /** Retrieves the next entry in the collection and
            advances this iterator by one position.
            @return  A reference to the next entry in the iteration,
                     if one exists.
            @throws  NoSuchElementException if the iterator had reached the
                     end already, that is, if hasNext() is false. */
        public K next()
        {
            K result;
            if (hasNext())
            {                
                result = nextNode.getKey();
                nextNode = nextNode.getNextNode();  
                return result;              
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
            throw new UnsupportedOperationException("remove() is not supported "
                                                    + "by this iterator");            
        } // end remove
    } // end KeyIterator
    
    private class ValueIterator implements Iterator<V>
    {
        private Node nextNode; // Node containing next entry in iteration
        
        public ValueIterator()
        {
            nextNode = firstNode;
        } // end default constructor
        
        /** Detects whether this iterator has completed its traversal
            and gone beyond the last entry in the collection of data. 
            @return True if the iterator has another entry to return. */
        public boolean hasNext()
        {
            return nextNode != null;
        } // end hasNext

        /** Retrieves the next entry in the collection and
            advances this iterator by one position.
            @return  A reference to the next entry in the iteration,
                     if one exists.
            @throws  NoSuchElementException if the iterator had reached the
                     end already, that is, if hasNext() is false. */
        public V next()
        {
            V result;
            if (hasNext())
            {                
                result = nextNode.getValue();
                nextNode = nextNode.getNextNode();  
                return result;              
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
            throw new UnsupportedOperationException("remove() is not supported "
                                                    + "by this iterator");            
        } // end remove
    } // end ValueIterator
    
    
    // @author Frank M. Carrano, Timothy M. Henry
    // @version 5.0 */
    private class Node
    {
        private K    key;  // Search key in the dictionary
        private V   value; // Associated value
        private Node next;  // Link to next node

        private Node(K keyData, V valueData)
        {
            this(keyData, valueData, null);
        } // end constructor

        private Node(K keyData, V valueData, Node nextNode)
        {
            key = keyData;
            value = valueData;
            next = nextNode;
        } // end constructor
        
        private K getKey() 
        {
            return key;
        } // end getKey
        
        private V getValue() 
        {
            return value; 
        } // end getValue
        
        private void setValue(V newValue) 
        {
            value = newValue;
        } // end setValue

        private Node getNextNode()
        {
            return next;
        } // end getNextNode

        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        } // end setNextNode
    } // end Node    
} // end Dictionary
