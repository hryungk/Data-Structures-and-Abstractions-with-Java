public class ListClient 
{
    public static void main(String[] args) 
    {
        testList();
    } // end main
    
    public static void testList()
    {
        ListInterface<String> runnerList = new AList<>();
        // runnerList has only methods in ListInterface
        
        runnerList.add("16");   // Winner
        runnerList.add(" 4");   // Second place
        runnerList.add("33");   // Third place
        runnerList.add("27");   // Fourth place
        displayList(runnerList);
    } // end testList
    
    public static <T> void displayList(ListInterface<T> list)
    {
        int numberOfEntries = list.getLength();
        System.out.println("The list contains " + numberOfEntries + " entries, "
                            + "as follows:");
        Object[] listArray = list.toArray();
        for (int index = 0; index < numberOfEntries; index++)
            System.out.print(listArray[index] + " ");
        // end for
        System.out.println();
    } // end displayList
    
    
    /**  Sequential search on an unsorted array for anEntry, iteratively. */
    public static <T> boolean inArray(T[] anArray, T anEntry)
    {        
        boolean found = false;
        int index = 0;
        while (!found && (index < anArray.length)) 
        {
            if (anEntry.equals(anArray[index])) 
                found = true;
            index++;
        } // end while
        return found;
    } // end inArray
    
    /** Sequential search on an unsorted array for anEntry, iteratively. 
        @return An integer indicating the location of the entry if exist, 
              otherwise -1. */
    public static <T> int inArrayQ1(T[] anArray, T anEntry)
    {        
        boolean found = false;
        int result = -1;
        int index = 0;
        while (!found && (index < anArray.length)) 
        {
            if (anEntry.equals(anArray[index]))
            {
                found = true;
                result = index;
            }
            index++;
        } // end while
        return result;
    } // end inArray    
    
    /** Sequential search on an unsorted array for anEntry, iteratively. 
        Implement with the ADT list array. */
    public static <T> boolean inList(AList<T> theList, T anEntry)
    {        
        boolean found = false;
        int position = 1;
        while (!found && (position <= theList.getLength())) 
        {
            if (anEntry.equals(theList.getEntry(position))) 
                found = true;
            position++;
        } // end while
        return found;
    } // end inList 
    
    /** Sequential search on an unsorted array for anEntry, recursively. */
    public static <T> boolean inArrayR(T[] anArray, T anEntry)
    {        
        return search(anArray, 0, anArray.length - 1, anEntry);
    } // end inArray
    // Searches anArray[first] through anArray[last] for desiredItem.
    // first >= 0 and < anArray.length.
    // last >= 0 and < anArray.length.
    private static <T> boolean search(T[] anArray, int first, int last, T desiredItem)
    {
        boolean found;
        if (first > last)
            found = false; // No elements to search
        else if (desiredItem.equals(anArray[first]))
            found = true;
        else
            found = search(anArray, first + 1, last, desiredItem);
        return found;
    } // end search
    
     /** Sequential search on an unsorted array for anEntry, recursively.
         Implement with the ADT list array. */
    public static <T> boolean inArrayRQ4(AList<T> theList, T anEntry)
    {        
        return searchQ4(theList, 1, theList.getLength(), anEntry);
    } // end inArray
    // Searches anArray[first] through anArray[last] for desiredItem.
    // first >= 0 and < anArray.length.
    // last >= 0 and < anArray.length.
    private static <T> boolean searchQ4(AList<T> theList, int first, int last, T desiredItem)
    {
        boolean found;
        if (first > last)
            found = false; // No elements to search
        else if (desiredItem.equals(theList.getEntry(first)))
            found = true;
        else
            found = searchQ4(theList, first + 1, last, desiredItem);
        return found;
    } // end search
    
    
    /** Sequential search on a sorted array for anEntry, iteratively.
        Implement with the ADT sorted list array. */
    public static <T extends Comparable<? super T>> boolean inListE2(SortedListInterface<T> theList, T anEntry)
    {   
        //return searchE2(theList, anEntry);
        return binarySearch(theList, 1, theList.getLength(), anEntry);
    } // end inArray
    // Searches the list for desiredItem.
    // first >= 0 and < anArray.length.
    // last >= 0 and < anArray.length.
    private static <T extends Comparable<? super T>> boolean searchE2(SortedListInterface<T> theList, T desiredItem)
    {
        int position = 1;
        while (desiredItem.compareTo(theList.getEntry(position)) > 0)
            position++;
        // end while
        return desiredItem.equals(theList.getEntry(position));      
    } // end search
    
    /** Binary search on a sorted array for desiredItem, recursively.
        Implement with the ADT sorted list array. */
    private static <T extends Comparable<? super T>> boolean binarySearch(SortedListInterface<T> theList, int first, int last, T desiredItem)
    {
        boolean found;
        int mid = first + (last - first) / 2;
        if (first > last)
            found = false;
        else if (desiredItem.equals(theList.getEntry(mid)))
            found = true;
        else if (desiredItem.compareTo(theList.getEntry(mid)) < 0)
            found = binarySearch(theList, first, mid - 1, desiredItem);
        else
            found = binarySearch(theList, mid + 1, last, desiredItem);
        return found;
    } // end binarySearch

    /** Binary search on a sorted array for desiredItem, recursively.
        Implement with the ADt sorted list array. 
        @return An integer indicating the location of the entry if exist, 
              otherwise -1. */
    private static <T extends Comparable<? super T>> int binarySearchQ7(SortedListInterface<T> theList, int first, int last, T desiredItem)
    {
        int result;
        int mid = first + (last - first) / 2;
        if (first > last)
            result = -1;
        else if (desiredItem.equals(theList.getEntry(mid)))
            result = mid;        
        else if (desiredItem.compareTo(theList.getEntry(mid)) < 0)
            result = binarySearchQ7(theList, first, mid - 1, desiredItem);
        else
            result = binarySearchQ7(theList, mid + 1, last, desiredItem);
        return result;
    } // end binarySearchQ7    
    
    
    /** Sequential search on an unsorted chain for desiredItem, iteratively. */
    public boolean contains(T anEntry) 
    {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) 
        {
            if (anEntry.equals(currentNode.getData())) 
                found = true;
            else
                currentNode = currentNode.getNextNode();
        } // end while
        return found; 
    } // end contains
    
    /** Sequential search on an unsorted chain for desiredItem, recursively.
        Recursively searches a chain of nodes for desiredItem, 
        beginning with the node that currentNode references. */
    private boolean searchUnsortedChain(Node currentNode, T desiredItem)
    {
        boolean found;
        if (currentNode == null) 
            found = false;
        else if (desiredItem.equals(currentNode.getData())) 
            found = true;
        else
            found = searchUnsortedChain(currentNode.getNextNode(), desiredItem); 
        return found;
    } // end search
        
    public boolean contains(T anEntry) 
    {
        return searchUnsortedChain(firstNode, anEntry); 
    } // end contains
    
    
    /** Sequential search on a sorted chain for anEntry, iteratively. */
    public boolean contains(T anEntry) 
    {        
        Node currentNode = firstNode;
        while ((currentNode != null) && (anEntry.compareTo(currentNode.getData()) > 0)) 
        {
            currentNode = currentNode.getNextNode();
        } // end while        
        return (currentNode != null) && (anEntry.equals(currentNode.getData())); 
    } // end contains
    
} // end ListClient
