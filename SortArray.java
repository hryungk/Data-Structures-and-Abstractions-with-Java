/** 
    Class for sorting an array of Comparable objects from smallest to largest.
*/
public class SortArray
{
    /** Sorts the first n objects in an array into ascending order.
        @param a An array of Comparable objects.
        @param first An integer > 0 containing the beginning index. 
        @param last An integer > first containing the last index. */
    // Iterative Selection Sort 
    public static <T extends Comparable<? super T>> void selectionSortI(T[] a, int first, int last)
    {
        for (int index = first; index < last; index++)
        {
            int indexOfNextSmallest = getIndexOfSmallest(a, index, last);
            swap(a, index, indexOfNextSmallest);
            // Assertion: a[0] <= a[1] <= ... <= a[index] <= all other a[i].
        } // end for
    } // end selectionSort

    /** Sorts the array entries a[first]m through a[last] recursively.
        @param a An array of Comparable objects.
        @param first An integer > 0 containing the beginning index. 
        @param last An integer > first containing the last index. */
    public static <T extends Comparable<? super T>> void selectionSortR(T[] a, int first, int last)
    {
        if (first < last)
        {
            int indexOfNextSmallest = getIndexOfSmallest(a, first, last);
            swap(a, first, indexOfNextSmallest);
            // Assertion: a[0] <= a[1] <= ... <= a[index] <= all other a[i].   
            selectionSortR(a, first + 1, last);
        } // end if
    } // end selectionSort
    
    // Finds the index of the smallest value in a portion of an array a.
    // Precondition: a.length > last >= first >= 0.
    // Returns the index of the smallest value among
    // a[first], a[first + 1], . . . , a[last].
    private static <T extends Comparable<? super T>> int getIndexOfSmallest(T[] a, int first, int last)
    {
        T min = a[first];
        int indexOfMin = first;
        for (int index = first; index <= last; index++)
        {
            if (a[index].compareTo(min) < 0)
            {
                min = a[index];
                indexOfMin = index;
            } // end if
            // Assertion: min is the smallest of a[first] through a[index].
        } // end for
        
        return indexOfMin;
    } // end getIndexOfSmallest
    
    // Swaps the array entries a[i] and a[j].
    private static void swap(Object[] a, int i, int j)
    {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    } // end swap
    
    /** Sorts the array entries a[first] through a[last] iteratively.
        @param a An array of Comparable objects.
        @param first An integer > 0 containing the beginning index. 
        @param last An integer > first containing the last index. */
    public static <T extends Comparable<? super T>> void insertionSortI(T[] a, int first, int last)
    {
        T nextToInsert;
        for (int unsorted = first + 1; unsorted <= last; unsorted++)
        {
            nextToInsert = a[unsorted];
            insertInOrderI(nextToInsert, a, first, unsorted - 1);
        } // end for
    } // end insertionSortI

    // Inserts anEntry into the sorted entries a[begin] through a[end].
    private static <T extends Comparable<? super T>> void insertInOrderI(T anEntry, T[] a, int begin, int end)
    {
        int index = end;    // Index of last entry in teh sorted portion
        while ((index >= begin) && (anEntry.compareTo(a[index])<0))
        {
            a[index + 1] = a[index];    // Make room
            index--;
        } // end while
        assert a[index + 1] != null;
        a[index + 1] = anEntry;  // Insert
    } // end insertInOrderI
    
    /** Sorts the array entries a[first] through a[last] recursively.
        @param a An array of Comparable objects.
        @param first An integer > 0 containing the beginning index. 
        @param last An integer > first containing the last index. */
    public static <T extends Comparable<? super T>> void insertionSortR(T[] a, int first, int last)
    {
        if (first < last)
        {
            // Sort all but the last entry
            insertionSortR(a, first, last - 1);
            
            // Insert the last entry in sorted order
            insertInOrderR(a[last], a, first, last - 1);
        } // end if
    } // end insertionSortR
    
    // Inserts anEntry into the sorted array entries a[begin] through a[end].
    private static <T extends Comparable<? super T>> void insertInOrderR(T anEntry, T[] a, int begin, int end)
    {
        if (!(anEntry.compareTo(a[end]) < 0))
            a[end + 1] = anEntry;
        else
        {
            if (begin < end)
            {
                a[end + 1] = a[end];
                insertInOrderR(anEntry, a, begin, end - 1);
            } // end else if
            else // begin == end and anEntry < a[end]
            {
                a[end + 1] = a[end];
                a[begin] = anEntry;
            } // end else else
        } // end else        
    } // end insertInOrderR 

    /** Sorts the entries of an array a[first...last] into ascending order.
        @param a An array of Comparable objects.
        @param first An integer containing the beginning index. 0 <= first < a.length 
        @param last An integer containing the last index. first <= last < a.length
    */
    public static <T extends Comparable<? super T>> void shellSort(T[] a, int first, int last)
    {
        int n = a.length;   // Number of array entries
        int space = n / 2;  // The difference betweeen the indices of the entries to sort.
        while (space > 0)
        {
            for (int begin = first; begin < first + space; begin++)
            {
                incrementalInsertionSort(a, begin, last, space);
            } // end for
            space = space / 2;
        } // end while
    } // end shellSort
    
    // Sorts equally spaced entries of an array a[first...last] into ascending order
    //  0 <= first < a.length , first <= last < a.length
    private static <T extends Comparable<? super T>> void incrementalInsertionSort(T[] a, int first, int last, int space)
    {
        T nextToInsert;
        int index;
        for (int unsorted = first + space; unsorted <= last; unsorted += space)
        {
            nextToInsert = a[unsorted];
            index = unsorted - space;
            while (index >= first && nextToInsert.compareTo(a[index]) < 0)
            {
                a[index + space] = a[index];
                index = index - space;            
            } // end while
            a[index + space] = nextToInsert;
        } // end for
    } // end incrementalInsertionSort
    
} // end SortArray