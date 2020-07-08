package TrackAssignments;

/** 
    Class for sorting an array of Comparable objects from smallest to largest.
*/
public class SortArray
{
    private static final int MIN_SIZE = 3;
    
    /** Sorts the first n objects in an array into ascending order recursively.
        @param a An array of Comparable objects.
        @param first An integer > 0 containing the beginning index. 
        @param last An integer > first containing the last index. */
    public static <T extends Comparable<? super T>> void mergeSort(T[] a, int first, int last)
    {
        // The cas is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempArray = (T[]) new Comparable<?>[a.length];  // Unchecked cast
        mergeSort(a, tempArray, first, last);
    } // end mergeSort 
    
    // Sorts the array entries a[first...last] recursively.
    private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tempArray, int first, int last)
    {       
        int mid;
        if (first < last)
        {
            mid = first + (last - first) / 2;
            mergeSort(a, tempArray, first, mid);
            mergeSort(a, tempArray, mid + 1, last);
            if (a[mid].compareTo(a[mid + 1]) > 0)
                merge(a, tempArray, first, mid, last);
        } // end if
    } // end mergeSort  
    
    // Merges the adjacent subarrays a[first...mid] and a[mid + 1...last].
    private static <T extends Comparable<? super T>> void merge(T[] a, T[] tempArray, int first, int mid, int last)
    {
        int beginHalf1 = first;
        int endHalf1 = mid;
        int beginHalf2 = mid + 1;
        int endHalf2 = last;
        
        // While both subarrays are not empty, compare an entry in one subarray with
        // an entry in teh other; then copy the smaller item into the temporary array
        int index = first; // Next available location in tempArray
        while ((beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2))
        {
            if (a[beginHalf1].compareTo(a[beginHalf2]) <= 0)
            {
                tempArray[index] = a[beginHalf1];
                beginHalf1++;
            }
            else
            {
                tempArray[index] = a[beginHalf2];
                beginHalf2++;
            } // end if
            index++;
        } // end while
        // Assertion: One subarray has been completely copied to tempArray.
        
        // Copy remaining entries from other subarray to tempArray
        if (beginHalf1 > endHalf1) // remaining array is latter half
        {
            for (int i = beginHalf2; i <= endHalf2; i++)
            {
                tempArray[index] = a[i];
                index++;
            } // end for
        }
        else
        {
            if (beginHalf2 > endHalf2) // remaining array is first half
            {
                for (int i = beginHalf1; i <= endHalf1; i++)
                {
                    tempArray[index] = a[i];
                    index++;
                } // end for
            } 
        } // end if
        
        // Copy entries from tempArray to array a
        for (int i = first; i <= last; i++)
        {
            a[i] = tempArray[i];
        }        
    } // end merge    
    
    /** Sorts an array into ascending order. Uses quick sort with median-of-three
        pivot selection for arrays of at least MIN_SIZE entries, and uses 
        insertion sort for smaller arrays.
        @param a An array of Comparable objects.
        @param first An integer > 0 containing the beginning index. 
        @param last An integer > first containing the last index. */
    public static <T extends Comparable<? super T>> void quickSort(T[] a, int first, int last)
    {
        if (last - first + 1 < MIN_SIZE)
            insertionSortI(a, first, last);
        else
        {
            // Create the partition: Smaller | Pivot | Larger
            int pivotIndex = partition(a, first, last);
            System.out.println("pivotIndex = " + pivotIndex);
            
            // Sort subarrays Smaller and Larger
            quickSort(a, first, pivotIndex-1);
            quickSort(a, pivotIndex, last);
        } // end if
    } // end quickSort
    
    // Partitions an array a[first..last] as part of quick sort into two subarrays 
    // named Smaller and Larger that are separated by a single entry — the pivot— 
    // named pivotValue. Entries in Smaller are <= pivotValue and appear before 
    // pivotValue in the array. Entries in Larger are >= pivotValue and appear 
    // after pivotValue in the array. first >= 0; first < a.length; 
    // last – first >= 3; last < a.length. Returns the index of the pivot.
    private static <T extends Comparable<? super T>> int partition(T[] a, int first, int last)
    {
        int mid = first + (last - first) / 2;
        System.out.println("first = " + first + ", mid = " + mid + ", last = " + last);
        sortFirstMiddleLast(a, first, mid, last);
        System.out.print("After FML sort: ");
        for (T item : a)
            System.out.print(item + " ");
        System.out.println();
        // Assertion: pivotValue = a[mid], a[first] <= a[mid] <= a[last] so do not
        // compare these two array entreis with pivotValue.
        
        // Move pivotValue to next-to-last position in array
        int pivotIndex = last - 1;
        T pivotValue = a[mid];
        a[mid] = a[pivotIndex];
        a[pivotIndex] = pivotValue;
        
        // Determine two subarrays:
        //  Smaller = a[first...endSmaller] and
        //  Larger = a[endSmaller+1...last-1]
        // such that entries in Smaller are <= pivotValue <= entries in Larger
        // Initially, these subarrays are empty.
        int indexFromLeft = first + 1;
        int indexFromRight = last - 2;
        
        boolean done = false;
        while (!done)
        {
            // Starting at the beginning of the array, leave entries < pivotValue
            // and locate the first entry >= pivotValue. You will find one, since
            // the last entry is >= pivotValue.
            while (a[indexFromLeft].compareTo(pivotValue) < 0)
                indexFromLeft++;
            
            // Starting at the end of the array, leave entries > pivotValue and
            //locate the first entry 􏰣􏰕􏰘􏰧􏰛􏰗􏰛􏰐􏰗􏰹􏰑􏰓􏰛􏰗􏰚􏰛􏰑􏰮􏰛􏰐􏰧􏰛􏰒􏰓<= pivotValue.􏱑􏰕􏰙􏰝􏰒􏰣􏰣􏰹􏰚􏰩􏰕􏰚􏰗􏱍􏰓􏰒􏰚􏰘􏰗􏰛􏰐􏰗􏰹􏰑􏰓􏰛 You will find one, since the
            // last entry is <= pivotValue.
            while (a[indexFromRight].compareTo(pivotValue) > 0)
                indexFromRight--;
            
            // Assertion: a[indexFromRight] <= pivotValue <= a[indexFromLeft]
            if (indexFromLeft < indexFromRight)
            {
                T temp = a[indexFromLeft];
                a[indexFromLeft] = a[indexFromRight];
                a[indexFromRight] = temp;
                indexFromLeft++;
                indexFromRight--;                
            }
            else
                done = true;
            // end if
        } // end while
        //Place pivotValue between the subarrays Smaller and Larger
        T temp = a[indexFromLeft];
        a[indexFromLeft] = a[pivotIndex];
        a[pivotIndex] = temp;
        pivotIndex = indexFromLeft;
        // Assertion: Smaller = a[first...pivotIndex-1]
        //            pivotValue = a[pivotIndex]
        //            Larger = a[pivotIndex+1...last]        
        return pivotIndex;
    } // end partition
    
    // Sorts the first, middle, and last entries of an array into ascending order.
    private static <T extends Comparable<? super T>> void sortFirstMiddleLast(T[] a, int first, int mid, int last)
    {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempArray = (T[]) new Comparable<?>[3]; // Unchecked cast
        Integer[] indices = {first, mid, last};
        // Create a temporary array of first, middle, last entry
        for (int i = 0; i < tempArray.length; i++)
            tempArray[i] = a[indices[i]];
        
        // Sort the temporary array
        insertionSortI(tempArray, 0, 2);
        System.out.print("tempArray: ");
        for (T item : tempArray)
            System.out.print(item + " ");
        System.out.println();
        // Place sorted temporary entries back to the original array.
        for (int i = 0; i < tempArray.length; i++)
             a[indices[i]] = tempArray[i];
    } // end sortFirstMiddleLast
    
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
} // end SortArray