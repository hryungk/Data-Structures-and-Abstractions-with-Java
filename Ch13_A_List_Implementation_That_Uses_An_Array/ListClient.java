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
} // end ListClient
