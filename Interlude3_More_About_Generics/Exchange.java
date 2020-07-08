/** A class that exchanges the objects at two given positions in a given array. */
public class Exchange 
{
    /** Interchanges two entries at given position within a given array
        @param anArray An array of objects.
        @param loc1 An integer >=0 and < anArray.length.
        @param loc2 An integer >=0 and < anArray.length.  */
    public static <T> void swap(T[] anArray, int loc1, int loc2)
    {
        T temp = anArray[loc1];
        anArray[loc1] = anArray[loc2];
        anArray[loc2] = temp;        
    } // end swap
    
} // end Exchange
