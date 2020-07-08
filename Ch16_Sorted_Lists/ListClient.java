public class ListClient 
{
    public static void main(String[] args) 
    {
        testList();
    } // end main
    
    public static void testList()
    {
        System.out.println("Create an empty list.");
        SortedListInterface<String> myList = new LinkedSortedList<>();
        System.out.println("List should be empty; isEmpty returns " +
                            myList.isEmpty() + ".");
        System.out.println("\nTesting add to end:");
        myList.add("15");
        myList.add("25");
        myList.add("35");
        myList.add("45");
        System.out.println("List should contain 15 25 35 45.");
        displayList(myList);
        System.out.println("List should not be empty; isEmpty() returns " +
                           myList.isEmpty() + ".");
        System.out.println("\nTesting clear():");
        myList.clear();
        System.out.println("List should be empty; isEmpty returns " +
                            myList.isEmpty() + ".");
        
        SortedListInterface<String> sortedWordList = new LinkedSortedList<>();
        ListInterface<String> wordList = new LList<>();
        for (int i = 1; i <= wordList.getLength(); i++)
        {
            sortedWordList.add(wordList.getEntry(i));
        }
        System.out.println(sortedWordList.getEntry(sortedWordList.getLength()));
        
    } // end testList
    
    public static <T extends Comparable<? super T>> void displayList(SortedListInterface<T> list)
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
