import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
    A class that implements a dictionary by using a resizable array.
    The dictionary is unsorted and has distinct search keys.
    @author Frank M. Carrano
*/
public class ArrayDictionary<K, V> implements DictionaryInterface<K, V>
{
    private Entry<K, V>[] dictionary; // Array of unsorted entries 
    private int numberOfEntries;
    private boolean initialized = false;
    private final static int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    
    public ArrayDictionary()
    {
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end constructor
    
    public ArrayDictionary(int initialCapacity) 
    {
        checkCapacity(initialCapacity);
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        Entry<K, V>[] tempDictionary = (Entry<K, V>[])new Entry[initialCapacity]; 
        dictionary = tempDictionary;
        numberOfEntries = 0;
        initialized = true;
    } // end constructor
    
    /** Adds a new entry to this dictionary. If the given search key already
       exists in the dictionary, replaces the corresponding value.
       @param key    An object search key of the new entry.
       @param value  An object associated with the search key.
       @return  Either null if the new entry was added to the dictionary
                or the value that was associated with key if that value
                was replaced. */
    public V add(K key, V value)
    {        
        checkInitialization();
        if ((key == null) || (value == null))
            throw new IllegalArgumentException();
        else
        {        
            V result = null;
            int keyIndex = locateIndex(key);
            if (keyIndex < numberOfEntries)
            {
                // Key found; return and replace entry's value
                result = dictionary[keyIndex].getValue();
                dictionary[keyIndex].setValue(value);                      
            }
            else    // Key not found; add new entry to dictionary
            {
                // Add at end of array
                dictionary[numberOfEntries] =  new Entry<>(key, value);
                numberOfEntries++;
                ensureCapacity(); // Ensure enough room for next add
            } // end if                    
            return result;
        } // end if
    } // end add
    
    /** Removes a specific entry from this dictionary.
        @param key An object search key of the entry to be removed.
        @return Either the value that was associated with the search key
                or null if no such object exists. */
    public V remove(K key)
    {
        checkInitialization();
        if ((key == null))
            throw new IllegalArgumentException();
        else
        {       
            V result = null;
            int keyIndex = locateIndex(key);
            if (keyIndex < numberOfEntries)
            {        
                // Key found; remove entry and return its value
                result = dictionary[keyIndex].getValue();
                dictionary[keyIndex] = dictionary[numberOfEntries-1];
                dictionary[numberOfEntries-1] = null;    
                numberOfEntries--;
            } // end if
            // Else result is null
            return result;
        } // end if
    } // end remove
    
    /** Retrieves from this dictionary the value associated with a given
        search key.
        @param key An object search key of the entry to be retrieved.
        @return Either the value that is associated with the search key
         or null if no such object exists. */
    public V getValue(K key)
    {
        V result = null;
        int keyIndex = locateIndex(key);
        if (keyIndex < numberOfEntries)
            result = dictionary[keyIndex].getValue();
        return result;
    } // end getValue
    
    /** Sees whether a specific entry is in this dictionary.
        @param key  An object search key of the desired entry.
        @return  True if key is associated with an entry in the dictionary. */
    public boolean contains(K key)    
    {
        boolean result = false;
        int keyIndex = locateIndex(key);
        if (keyIndex < numberOfEntries)
            result = true;
        return result;
    } // end contains
    
    /** Creates an iterator that traverses all search keys in this dictionary.
        @return  An iterator that provides sequential access to the search
                 keys in the dictionary. */
    public Iterator<K> getKeyIterator()    
    {            
        Iterator<K> myIterator = new KeyIterator<>(this);
        return myIterator;  
    } // end getKeyIterator   
    
    /** Creates an iterator that traverses all values in this dictionary.
        @return  An iterator that provides sequential access to the values
                 in this dictionary. */
    public Iterator<V> getValueIterator()
    {
        Iterator<V> myIterator = new ValueIterator<>(this);
        return myIterator;  
    } // end getValueIterator
    
    /** Sees whether this dictionary is empty.
        @return  True if the dictionary is empty. */
    public boolean isEmpty()    
    {       
        return numberOfEntries == 0;
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
        int count = 0;
        while (!isEmpty())
        {
            dictionary[count] = null;
            count++;
            numberOfEntries--;
        }
    } // end clear
        
    // Throws an exception if this object is not initialized.
    private void checkInitialization()
    {
        if (!initialized)
            throw new SecurityException("ArrayQueue object is corrupt.");
    } // end checkInitialization
    
    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity) 
    {
        if (capacity > MAX_CAPACITY)
        throw new IllegalStateException("Attempt to create a bag whose " +
                                        "capacity exeeds allowed " +
                                        "maximum of " + MAX_CAPACITY);
    } // end checkCapacity

    // Doubles the size of the array of entries if it is full.
    private void ensureCapacity()
    {
        if (numberOfEntries >= dictionary.length - 1)
        {
            int newCapacity = 2 * dictionary.length;
            checkCapacity(newCapacity);   // Is capacity too big?
            dictionary = Arrays.copyOf(dictionary, newCapacity + 1);
        } // end if
    } // end ensureCapacity 
    
    // Returns the index of the entry that contains key or 
    // returns numberOfEntries if no such entry exists. 
    private int locateIndex(K key)
    {        
        int index = 0;
        while ((index < numberOfEntries) || !key.equals(dictionary[index].getKey()))
            index++;
        // end while        
        return index;
    } // end locateIndex
    
    public class KeyIterator<K> implements Iterator<K>
    {
        private int cursor; // Current location of the cursor; 0 means beginning
        private ArrayDictionary<K, V> arrayDictionary;
        private K nextEntry;
        
        public KeyIterator(ArrayDictionary<K, V> newDictionary)
        {
            arrayDictionary = newDictionary;
            cursor = 0;
            nextEntry = null;
        } // end constructor
        
        /** Detects whether this iterator has completed its traversal
            and gone beyond the last entry in the collection of data. 
            @return True if the iterator has another entry to return. */
        public boolean hasNext()
        {
            return (!isEmpty() && (cursor < numberOfEntries));
        } // end hasNext

        /** Retrieves the next entry in the collection and
            advances this iterator by one position.
            @return  A reference to the next entry in the iteration,
                     if one exists.
            @throws  NoSuchElementException if the iterator had reached the
                     end already, that is, if hasNext() is false. */
        public K next()
        {
            if (hasNext())
            {
                K currentEntry = arrayDictionary.dictionary[cursor].getKey();
                cursor++;
                nextEntry = currentEntry;
                return currentEntry;
            } 
            else
                throw new NoSuchElementException();
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
            if (nextEntry == null)
            {
                if (cursor == 0)
                    throw new IllegalStateException("Empty dictionary.");
                else
                    throw new IllegalStateException("next() has not been called "
                                + "or remove() was already called after the last"
                                + " call to next()");
            }
            else
            {
                K toRemove = arrayDictionary.dictionary[cursor-1].getKey();
                arrayDictionary.remove(toRemove);
                cursor--;
                nextEntry = null;
            } // end if            
        } // end remove
    } // end KeyIterator
    
    public class ValueIterator<V> implements Iterator<V>
    {
        private int cursor; // Current location of the cursor; 0 means beginning
        private ArrayDictionary<K, V> arrayDictionary;
        private V nextEntry;
        
        public ValueIterator(ArrayDictionary<K, V> newDictionary)
        {
            arrayDictionary = newDictionary;
            cursor = 0;
            nextEntry = null;
        } // end constructor
        
        /** Detects whether this iterator has completed its traversal
            and gone beyond the last entry in the collection of data. 
            @return True if the iterator has another entry to return. */
        public boolean hasNext()
        {
            return (!isEmpty() && (cursor < numberOfEntries));
        } // end hasNext

        /** Retrieves the next entry in the collection and
            advances this iterator by one position.
            @return  A reference to the next entry in the iteration,
                     if one exists.
            @throws  NoSuchElementException if the iterator had reached the
                     end already, that is, if hasNext() is false. */
        public V next()
        {
            if (hasNext())
            {
                V currentEntry= arrayDictionary.dictionary[cursor].getValue();
                cursor++;
                nextEntry = currentEntry;
                return currentEntry;
            } 
            else
                throw new NoSuchElementException();
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
            throw new UnsupportedOperationException("remove() method is not " +
                                                "supported for ValueIterator().");
        } // end remove
    } // end ValueIterator
    
    private class Entry<S, T> 
    {
        private S key; 
        private T value;
        
        private Entry(S searchKey, T dataValue) 
        {
            key = searchKey;
            value = dataValue;
        } // end constructor
        
        private S getKey() 
        {
            return key;
        } // end getKey
        
        private T getValue() 
        {
            return value; 
        } // end getValue
        
        private void setValue(T newValue) 
        {
            value = newValue;
        } // end setValue
    } // end Entry
} // end Dictionary
