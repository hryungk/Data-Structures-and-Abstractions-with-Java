import java.util.Iterator;

public class SeparateClassIteratorTest 
{
    public static void main(String[] args) 
    {
        testList();
    } // end main
    
    public static void testList()
    {
        ListInterface<String> nameList = new AList<>();
        // nameList has only methods in ListInterface
        
        nameList.add("Jamie");
        nameList.add("Joey");
        nameList.add("Rachel"); 
        displayList(nameList);
        System.out.println();
        
        // Test hasNext and Next 
        System.out.println("Test hasNext and Next.");
        Iterator<String> nameIterator = new SeparateIterator<>(nameList);
        while(nameIterator.hasNext())
            System.out.println(nameIterator.next());
        System.out.println();
        
        // Display the third entry.
        System.out.println("Display the thrid entry");
        nameIterator = new SeparateIterator<>(nameList);
        nameIterator.next();
        nameIterator.next();
        System.out.println(nameIterator.next());
        System.out.println();
        
        // Display even-numbered entries.
        System.out.println("Display even-numberd entries");
        nameIterator = new SeparateIterator<>(nameList);
        nameIterator.next();
        while(nameIterator.hasNext())
        {
            System.out.println(nameIterator.next());
            if (nameIterator.hasNext())
                nameIterator.next();
        }
        System.out.println();

        // Remove all entries
        System.out.println("Remove all entries");
        nameIterator = new SeparateIterator<>(nameList);
        while(nameIterator.hasNext())
        {
            nameIterator.next();
            nameIterator.remove();                    
        }
        System.out.println("Is the list empty?: " + nameList.isEmpty());
        System.out.println();
        
        // Test multiple iterators
        System.out.println("Test multiple iterators");
        ListInterface<String> nameList2 = new AList<>();
        // nameList has only methods in ListInterface
        
        nameList2.add("Brad");  nameList2.add("Jane");  nameList2.add("Bob"); 
        nameList2.add("Jane");  nameList2.add("Bette");  nameList2.add("Brad"); 
        nameList2.add("Jane");  nameList2.add("Brenda");  
        
        nameIterator = new SeparateIterator<>(nameList2);
        while (nameIterator.hasNext())
        {
            String currentName = nameIterator.next(); 
            int nameCount = 0;
            Iterator<String> countingIterator = new SeparateIterator<>(nameList2);
            while (countingIterator.hasNext())
            {
                String nextName = countingIterator.next(); 
                if (currentName.equals(nextName))
                {
                    nameCount++;
                    if (nameCount > 1)
                        countingIterator.remove();
                } // end if
            } // end while
            System.out.println(currentName + " occurs " + nameCount + " times.");
        } // end while        
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
