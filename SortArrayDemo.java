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
        String[] anArray = {"I", "H", "B", "G", "E", "D", "F", "C", "A"};
        System.out.println("Array contents:");
        displayArray(anArray);
        
        System.out.println("\nIterative selection sort:");
        SortArray.selectionSortI(anArray, 0, anArray.length-1);
        displayArray(anArray);
        
        String[] anArray2 = {"I", "H", "B", "G", "E", "D", "F", "C", "A"};
        System.out.println("\nRecursive selection sort:");
        SortArray.selectionSortR(anArray2, 0, anArray2.length-1);
        displayArray(anArray2);
        
        String[] anArray3 = {"I", "H", "B", "G", "E", "D", "F", "C", "A"};
        System.out.println("\nIterative insertion sort:");
        SortArray.insertionSortI(anArray3, 0, anArray3.length-1);
        displayArray(anArray3);
        
        
        String[] anArray4 = {"I", "H", "B", "G", "E", "D", "F", "C", "A"};
        System.out.println("\nRecursive insertion sort:");
        SortArray.insertionSortR(anArray4, 0, anArray4.length-1);
        displayArray(anArray4);
        
        String[] anArray5 = {"I", "H", "B", "G", "E", "D", "F", "C", "A"};
        System.out.println("\nShellsort:");
        SortArray.shellSort(anArray5, 0, anArray5.length-1);
        displayArray(anArray5);
        
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
