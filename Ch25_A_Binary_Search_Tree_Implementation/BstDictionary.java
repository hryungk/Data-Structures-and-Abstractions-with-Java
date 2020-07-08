import TreePackage.SearchTreeInterface;
import TreePackage.BinarySearchTree;
import java.util.Iterator;
/**
   A class that implements the ADT dictionary by using a binary search tree.
   The dictionary is sorted and has distinct search keys.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class BstDictionary<K extends Comparable<? super K>, V>
       implements DictionaryInterface<K, V>
{
    private SearchTreeInterface<Entry<K, V>> bst;

    public BstDictionary()
    {
       bst = new BinarySearchTree<>();
    } // end default constructor

    /* Methods that implement dictionary operations are here. */
    /** Adds a new entry to this dictionary. If the given search key already
       exists in the dictionary, replaces the corresponding value.
       @param key    An object search key of the new entry.
       @param value  An object associated with the search key.
       @return  Either null if the new entry was added to the dictionary
                or the value that was associated with key if that value
                was replaced. */
    public V add(K key, V value)
    {
        Entry<K, V> newEntry = new Entry(key, value);
        Entry<K, V> returnedEntry = bst.add(newEntry);
        
        V result = null;
        if (returnedEntry != null)
            result = returnedEntry.getValue();
        return result;
    } // end add
    
    /** Removes a specific entry from this dictionary.
        @param key An object search key of the entry to be removed.
        @return Either the value that was associated with the search key
                or null if no such object exists. */
    public V remove(K key)
    {
        Entry<K, V> findEntry = new Entry(key, null);
        Entry<K, V> removedEntry = bst.remove(findEntry);
       
        V result = null;
        if (removedEntry != null)
            result = removedEntry.getValue();
        return result;        
    } // end remove
    
    /** Retrieves from this dictionary the value associated with a given
        search key.
        @param key An object search key of the entry to be retrieved.
        @return Either the value that is associated with the search key
         or null if no such object exists. */
    public V getValue(K key)
    {
        Entry<K, V> findEntry = new Entry(key, null);
        Entry<K, V> foundEntry = bst.getEntry(findEntry);
       
        V result = null;
        if (foundEntry != null)
            result = foundEntry.getValue();
        return result;  
    } // end getValue
    
    /** Sees whether a specific entry is in this dictionary.
        @param key  An object search key of the desired entry.
        @return  True if key is associated with an entry in the dictionary. */
    public boolean contains(K key)
    {
        Entry<K, V> findEntry = new Entry(key, null);
        return  bst.contains(findEntry);
        
        // Or
        // return getValue(key) != null;
    } // end contains
    
    /** Creates an iterator that traverses all search keys in this dictionary.
        @return  An iterator that provides sequential access to the search
                 keys in the dictionary. */
    public Iterator<K> getKeyIterator()
    {
        return new KeyIterator();
    } // end getKeyIterator
    
    /** Creates an iterator that traverses all values in this dictionary.
        @return  An iterator that provides sequential access to the values
                 in this dictionary. */
    public Iterator<V> getValueIterator()
    {
        return new ValueIterator();
    } // end getValueIterator
    
    /** Sees whether this dictionary is empty.
        @return  True if the dictionary is empty. */
    public boolean isEmpty()
    {
        return bst.isEmpty();
    } // end isEmpty
           
    /** Gets the size of this dictionary.
        @return  The number of entries (key-value pairs) currently
                 in the dictionary. */
    public int getSize()
    {
        return bst.getNumberOfNodes();
    } // end getSize
    
    /** Removes all entries from this dictionary. */
    public void clear()
    {
        bst.clear();
    } // end clear
    
    private class KeyIterator implements Iterator<K>
    {
        Iterator<Entry<K, V>> localIterator;
        
        public KeyIterator()
        {
            localIterator = bst.getInorderIterator();
        } // end default constructor
        
        public boolean hasNext()
        {
            return localIterator.hasNext();
        } // end hasNext
        
        public K next()
        {
            Entry<K, V> nextEntry = localIterator.next();
            return nextEntry.getKey();
        } // end next
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove                
    } // end KeyIterator
    
    private class ValueIterator implements Iterator<V>
    {
        Iterator<Entry<K, V>> localIterator;
        
        public ValueIterator()
        {
            localIterator = bst.getInorderIterator();
        } // end default constructor
        
        public boolean hasNext()
        {
            return localIterator.hasNext();
        } // end hasNext
        
        public V next()
        {
            Entry<K, V> nextEntry = localIterator.next();
            return nextEntry.getValue();
        } // end next
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove                
    } // end ValueIterator
    

    private class Entry<S extends Comparable<? super S>, T> 
            implements Comparable<Entry<S, T>>
    {
        private S key;
        private T value;

        private Entry(S searchKey, T dataValue)
        {
           key = searchKey;
           value = dataValue;
        } // end constructor
        
        /*  The class Entry also defines the methods equals, toString, getKey,
            getValue, and setValue; no setKey method is provided.   */        
        
        public S getKey()
        {
            return key;
        } // end getKey
        
        public T getValue()
        {
            return value;
        } // end getValue
        
        public void setValue(T newValue)
        {
            value = newValue;
        } // end setValue        
        
        public int compareTo(Entry<S, T> other)
        {
           return key.compareTo(other.getKey());
        } // end compareTo
        
        public boolean equals(Entry<S, T> other)
        {
            return key.equals(other.getKey());            
        } // end equals
        
        public String toString()
        {
            return key + "\t" + value;
        } // end toString

    } // end Entry
} // end BstDictionary
