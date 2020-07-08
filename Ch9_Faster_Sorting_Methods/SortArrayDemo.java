/** A test of the methods add, toArray, isEmpty, and getCurrentSize, 
    as defined in the first draft of the class LinkedBag.
    @author Frank M. Carrano, Timothy M. Henry
    @version 5.0
*/
public class SortArrayDemo
{
    public static void main(String[] args) 
    {
        // Array to test
        //String[] anArray = {"I", "H", "B", "G", "E", "D", "F", "C", "A"};
        Integer[] anArray = {7, 5, 9, 3, 6, 0, 2, 4};
        System.out.println("Array contents:");
        displayArray(anArray);
        
        //System.out.println("\nRecursive merge sort:");
        //SortArray.mergeSort(anArray, 0, anArray.length-1);
        //displayArray(anArray);

        System.out.println("\nQuick sort:");
        SortArray.quickSort(anArray, 0, anArray.length-1);
        displayArray(anArray);        
    } // end main
   

    // Tests the method toArray while displaying the bag.
    private static void displayArray(Object[] a)
    {
        for (int index = 0; index < a.length; index++)
        {
            System.out.print(a[index] + " ");
        } // end for
        System.out.println();
    } // end displayArray
}
