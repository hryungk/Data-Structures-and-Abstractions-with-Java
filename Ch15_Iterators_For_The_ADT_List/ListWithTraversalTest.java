import java.util.Iterator;

public class ListWithTraversalTest 
{
    public static void main(String[] args) 
    {
        testList();
    } // end main
    
    public static void testList()
    {
        ListWithTraversal<String> nameList = new ListWithTraversal<>();
        // nameList has only methods in ListInterface
        
        nameList.add("Jamie");
        nameList.add("Joey");
        nameList.add("Rachel"); 
        displayList(nameList);
        System.out.println();
        
        // Test hasNext and Next 
        System.out.println("Test hasNext and Next.");
        nameList.resetTraversal();
        while(nameList.hasNext())
            System.out.println(nameList.next());
        System.out.println();
        
        // Display the third entry.
        System.out.println("Display the thrid entry");
        nameList.resetTraversal();
        nameList.next();
        nameList.next();
        System.out.println(nameList.next());
        System.out.println();
        
        // Display even-numbered entries.
        System.out.println("Display even-numberd entries");
        nameList.resetTraversal();
        nameList.next();
        while(nameList.hasNext())
        {
            System.out.println(nameList.next());
            if (nameList.hasNext())
                nameList.next();
        }
        System.out.println();

        // Remove all entries
        //System.out.println("Remove all entries");
        //nameIterator = nameList.getIterator();
        //while(nameIterator.hasNext())
        //{
//            nameIterator.next();
//            nameIterator.remove();                    
//        }
        //System.out.println("Is the list empty?: " + nameList.isEmpty());
        //System.out.println();
        
        // Test multiple iterators
        System.out.println("Test multiple iterators");
        ListWithTraversal<String> nameList2 = new ListWithTraversal<>();
        // nameList has only methods in ListInterface
        
        nameList2.add("Brad");  nameList2.add("Jane");  nameList2.add("Bob"); 
        nameList2.add("Jane");  nameList2.add("Bette");  nameList2.add("Brad"); 
        nameList2.add("Jane");  nameList2.add("Brenda");  
        
        nameList2.resetTraversal();
        while (nameList2.hasNext())
        {
            String currentName = nameList2.next(); 
            int nameCount = 0;
            //nameList2.resetTraversal();
            while (nameList2.hasNext())
            {
                String nextName = nameList2.next(); 
                if (currentName.equals(nextName))
                {
                    nameCount++;
                } // end if
            } // end while
            System.out.println(currentName + " occurs " + nameCount + " times.");
        } // end while        
    } // end testList
    
    public static <T> void displayList(ListWithTraversal<T> list)
    {
        int numberOfEntries = list.getLength();
        System.out.println("The list contains " + numberOfEntries + " entries, "
                            + "as follows:");
        
        list.resetTraversal();
        int position = 0;
        while(list.hasNext())
        {
            position++;
            System.out.println(list.next() + " is entry " + position);
        }
        System.out.println();
    } // end displayList
} // end ListClient
