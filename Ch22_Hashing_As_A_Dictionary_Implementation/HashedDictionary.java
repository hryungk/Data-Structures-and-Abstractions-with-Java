import java.util.Iterator;
import java.util.NoSuchElementException;
/**
    A class that implements the ADT dictionary by using hashing and
    linear probing to resolve collisions.
    The dictionary is unsorted and has distinct search keys.
    @author Frank M. Carrano
*/
public class HashedDictionary<K, V> implements DictionaryInterface<K, V>
{    
    // The dictionary:
    private int numberOfEntries;    
    private final static int DEFAULT_CAPACITY = 5;  // Must be prime
    private static final int MAX_CAPACITY = 10000;
    
    // The hash table:
    private TableEntry<K, V>[] hashTable;          // Array of unsorted entries 
    private int tableSize;                          // Must be prime
    private static final int MAX_SIZE = 2*MAX_CAPACITY;
    private boolean initialized = false;
    private static final double MAX_LOAD_FACTOR = 0.5;  // Fraction of hash table
                                                        // that can be filled
    
    public HashedDictionary()
    {
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end constructor
    
    public HashedDictionary(int initialCapacity) 
    {
        checkCapacity(initialCapacity);
        numberOfEntries = 0;

        // Set up hash table:
        // Initial size of hash table is same as initilaCapacity if it is prime:
        // otherwise increase it until it is prime size
        tableSize = getNextPrime(initialCapacity);
        checkSize(tableSize);   // Check for max array size
        
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        TableEntry<K, V>[] temp = (TableEntry<K, V>[])new TableEntry[tableSize]; 
        hashTable = temp;        
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
            V oldValue; // Value to return
            int index = getHashIndex(key);
            index = probe(index, key);  // Chekc for and resolve collision
            // Assertion: index is within legal range for hashTable
            assert (index >= 0) && (index < hashTable.length);
            if ((hashTable[index] == null) || hashTable[index].isRemoved())
            {   // Key not found; add new entry to dictionary
                hashTable[index] =  new TableEntry<>(key, value);
                numberOfEntries++;
                oldValue = null;
            }
            else    
            {   // Key found; get old value for return and then replace it               
                oldValue = hashTable[index].getValue();
                hashTable[index].setValue(value);             
            } // end if                    
            // Ensure that hash table is large enough for another add
            if (isHashTableTooFull())
                enlargeHashTable();
            return oldValue;
        } // end if
    } // end add
    
    /** Removes a specific entry from this dictionary.
        @param key An object search key of the entry to be removed.
        @return Either the value that was associated with the search key
                or null if no such object exists. */
    public V remove(K key)
    {
        checkInitialization();
        V removedValue = null;
        int index = getHashIndex(key);
        index = probe(index, key);        
        if (hashTable[index] != null && hashTable[index].isIn())
        {   // Key found; remove entry and return its value
            removedValue = hashTable[index].getValue();
            hashTable[index].setToRemoved();
            numberOfEntries--;
        } // end if
        // Else key not found; return null
        return removedValue;        
    } // end remove
    
    /** Retrieves from this dictionary the value associated with a given
        search key.
        @param key An object search key of the entry to be retrieved.
        @return Either the value that is associated with the search key
         or null if no such object exists. */
    public V getValue(K key)
    {
        checkInitialization();
        V result = null;
        
        int index = getHashIndex(key);
        index = probe(index, key);        
        if (hashTable[index] != null && hashTable[index].isIn())
            result = hashTable[index].getValue();   // key found; get value
        // Else key not found; return null
        return result;
    } // end getValue
    
    /** Sees whether a specific entry is in this dictionary.
        @param key  An object search key of the desired entry.
        @return  True if key is associated with an entry in the dictionary. */
    public boolean contains(K key)    
    {
        boolean result = false;
        int index = getHashIndex(key);
        index = probe(index, key);        
        if (hashTable[index] != null && hashTable[index].isIn())
            result = true;
        return result;
    } // end contains
    
    /** Creates an iterator that traverses all search keys in this dictionary.
        @return  An iterator that provides sequential access to the search
                 keys in the dictionary. */
    public Iterator<K> getKeyIterator()    
    {            
        Iterator<K> myIterator = new KeyIterator(this);
        return myIterator;  
    } // end getKeyIterator   
    
    /** Creates an iterator that traverses all values in this dictionary.
        @return  An iterator that provides sequential access to the values
                 in this dictionary. */
    public Iterator<V> getValueIterator()
    {
        Iterator<V> myIterator = new ValueIterator(this);
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
            hashTable[count] = null;
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

    private int getNextPrime(int anInteger)
    {
        int result;
        if (anInteger % 2 == 0) // even number
            anInteger++;    // Make it odd 
        
        while(!isPrime(anInteger))
            anInteger = anInteger + 2;
        
        result = anInteger;
        
        return result;
    } // end getNextPrime
    
    private boolean isPrime(int anInteger)
    {
        boolean result = false; // 1 and even numbers other than 2
        if ((anInteger == 2) || (anInteger == 3))   // 2 and 3 are prime numbers.
            result = true;
        else
            if ((anInteger % 2 == 1) && (anInteger >= 5))   // An odd integer >= 5
            {
                result = true;
                // prime if not divisible by every odd integer up to its square root.
                for (int i = 3; i <= Math.sqrt(anInteger); i = i + 2)
                    result = result && (anInteger % i != 0);
            } // end if            
        return result;
    } // end isPrime
    
    // Doubles the size of the array of entries if it is full.
    private void checkSize(int number)
    {        
        checkCapacity(number);   // Is capacity too big?  
    } // end checkSize
    
    private int getHashIndex(K key)
    {
        int hashIndex = key.hashCode() % hashTable.length;
        if (hashIndex < 0)
            hashIndex = hashIndex + hashTable.length;
        return hashIndex;
    } // end getHashIndex
    
//    // Searches the probe sequence that begins at index (key’s hash index) and 
//    // returns either the index of the entry containing key or
//    //                -1, if no such entry exists.    
//    private int locate(int index, K key)
//    {
//        boolean found = false;
//        while (!found && (hashTable[index] != null))
//        {
//            if (hashTable[index].isIn() &&  key.equals(hashTable[index].getKey()))
//                found = true;   // Key found                                
//            else                // Follow probe sequence
//                index = (index + 1) % hashTable.length; // Linear probing (circular)
//        } // end while
//        // Assertion: Either key or null is found at hashTable[index]
//        if (found)
//            return index;
//        else
//            return -1;       
//    } // end locate
    
           
    // Searches the probe sequence that begins at index. 
    // Returns either the index of the entry containing key or 
    //                the index of an available location in the hash table.
    // This index is always legal, since the probe sequence stays within the hash table.
    private int probe(int index, K key)
    {
        boolean found = false;
        int removedStateIndex = -1; // Index of first location in removed state
        while (!found && (hashTable[index] != null))
        {
            if (hashTable[index].isIn())
            {
                if (key.equals(hashTable[index].getKey()))
                   found = true;   // Key found      
                else               // Follow probe sequence
                    index = (index + 1) % hashTable.length; // Linear probing (circular)
            }
            else    // hashTable[index] references a removed entry
            {       // Skip entries that were removed
                // Save index of first location in removed state
                if (removedStateIndex == -1)
                    removedStateIndex = index;                
                index = (index + 1) % hashTable.length; // Linear probing (circular)
            } // end if
        } // end while
        // Assertion: either key or null is found at hashTable[index]
        if (found || removedStateIndex == -1)
            return index;        // Index of either key or null
        else
            return removedStateIndex;   // Index of first entry removed (available location)
    } // end probe
        
    // 􏰶Returns true if the hash table’s load factor is greater than or equal to 
    // MAX_LOAD_FACTOR. Here we define the load factor as the ratio of 
    // locationsUsed to hashTable.length.
    private boolean isHashTableTooFull()
    {
        double locationUsed = numberOfEntries;
        boolean result = false;
        double loadFactor = locationUsed / hashTable.length;
        if (loadFactor >= MAX_LOAD_FACTOR)
            result = true;
        
        return result;
    } // end isHashTableTooFull
    
    // Expands the hash table to a size that is both prime and at least double its
    // current size, and then adds the current entries in the dictionary to the
    // new hash table. In doing so, this method must rehash the table entries.
    private void enlargeHashTable()
    {
        TableEntry<K, V>[] oldTable = hashTable;  
        int oldSize = hashTable.length;
        int newSize = getNextPrime(oldSize + oldSize);
        checkSize(newSize);        
              
        // The cas is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        TableEntry<K, V>[] temp = (TableEntry<K, V>[]) new TableEntry[tableSize];
        hashTable = temp;
        numberOfEntries = 0;    // Reset number of dictionary entries, since
                                // it will be incremented by add during rehash
                                
        // Rehash dictionary entries from old array to the new and bigger array;
        // skip both null locations and removed entries.
        for (int index = 0; index < oldSize; index++)
        {
            if (oldTable[index] != null || oldTable[index].isIn())
                add(oldTable[index].getKey(),oldTable[index].getValue());        
        } // end for
    } // end enlargeHashTable
    
    
    public class KeyIterator implements Iterator<K>
    {
        private int currentIndex; // Current position in hash table
        private int numberLeft; // Number of entries left in iteration        
        
        public KeyIterator(HashedDictionary<K, V> newDictionary)
        {            
            currentIndex = 0;
            numberLeft = numberOfEntries;            
        } // end constructor
        
        /** Detects whether this iterator has completed its traversal
            and gone beyond the last entry in the collection of data. 
            @return True if the iterator has another entry to return. */
        public boolean hasNext()
        {
            return numberLeft > 0;
        } // end hasNext

        /** Retrieves the next entry in the collection and
            advances this iterator by one position.
            @return  A reference to the next entry in the iteration,
                     if one exists.
            @throws  NoSuchElementException if the iterator had reached the
                     end already, that is, if hasNext() is false. */
        public K next()
        {
            K result = null;
            if (hasNext())
            {
                while (hashTable[currentIndex] == null || 
                       hashTable[currentIndex].isRemoved())
                    currentIndex++;
                // end while
                
               result = hashTable[currentIndex].getKey();
               numberLeft--;
               currentIndex++;
            } 
            else
                throw new NoSuchElementException();
            // end if
            return result;
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
                                                    "supported.");           
        } // end remove
    } // end KeyIterator
    
    public class ValueIterator implements Iterator<V>
    {
        private int currentIndex; // Current position in hash table
        private int numberLeft; // Number of entries left in iteration        
        
        public ValueIterator(HashedDictionary<K, V> newDictionary)
        {            
            currentIndex = 0;
            numberLeft = numberOfEntries;            
        } // end constructor
        
        /** Detects whether this iterator has completed its traversal
            and gone beyond the last entry in the collection of data. 
            @return True if the iterator has another entry to return. */
        public boolean hasNext()
        {
            return numberLeft > 0;
        } // end hasNext

        /** Retrieves the next entry in the collection and
            advances this iterator by one position.
            @return  A reference to the next entry in the iteration,
                     if one exists.
            @throws  NoSuchElementException if the iterator had reached the
                     end already, that is, if hasNext() is false. */
        public V next()
        {
            V result = null;
            if (hasNext())
            {
                while (hashTable[currentIndex] == null || 
                       hashTable[currentIndex].isRemoved())
                    currentIndex++;
                // end while
                
               result = hashTable[currentIndex].getValue();
               numberLeft--;
               currentIndex++;
            } 
            else
                throw new NoSuchElementException();
            // end if
            return result;
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
                                                    "supported.");           
        } // end remove
    } // end ValueIterator
    
    private static class TableEntry<S, T> 
    {
        private S key; 
        private T value;
        private States state;   // Flags whether this entry is in the hash table
        private enum States {CURRENT, REMOVED}  // Possible values of state
        
        private TableEntry(S searchKey, T dataValue) 
        {
            key = searchKey;
            value = dataValue;
            state = States.CURRENT;
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
        
        private boolean isIn()
        {
            return state == States.CURRENT;
        } // end isIn
        
        private void setToIn()
        {
            state = States.CURRENT;
        } // end isIn
        
        private boolean isRemoved()
        {
            return state == States.REMOVED;
        } // end isIn
        
        private void setToRemoved()
        {
            state = States.REMOVED;
        } // end isIn
    } // end Entry
} // end Dictionary
