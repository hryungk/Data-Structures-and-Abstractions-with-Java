public class ListStudent 
{
    public static void main(String[] args) 
    {
        testList();
    } // end main
    
    public static void testList()
    {
        ListInterface<String> alphaList = new AList<>();
        // alphaList has only methods in ListInterface
        
        alphaList.add(1, "Amy");   // Amy
        alphaList.add(2, "Ellen");   // Amy Ellen
        alphaList.add(2, "Bob");   // Amy Bob Ellen
        alphaList.add(3, "Drew");   // Amy Bob Drew Ellen
        alphaList.add(1, "Aaron");   // Aaron Amy Bob Drew Ellen
        alphaList.add(4, "Carol");   // Aaron Amy Bob Carol Drew Ellen
        displayList(alphaList);
        System.out.println();
        
        int loc = 4;
        System.out.println("After removing " + alphaList.remove(loc) + " from the"
                + " list, "+ alphaList.getEntry(loc) + " is in position " + loc + ".");
        displayList(alphaList);
        System.out.println();
        
        loc = 3;
        System.out.println("After replacing " + alphaList.getEntry(loc) + " with"
                + " Ben, Ben is in position " + loc + ".");
        alphaList.replace(3, "Ben");
        displayList(alphaList);
        System.out.println();
        
        
        // Question 4
        System.out.println("Question 4.");
        alphaList.clear();
        System.out.println("The array is cleared.\n");
        
        alphaList.add("Amy");
        alphaList.add("Ellen");
        alphaList.add("Bob");
        alphaList.add("Drew");
        displayList(alphaList);
        
        String bob = alphaList.remove(3);
        alphaList.add(2, bob);
        displayList(alphaList);
        
        String drew = alphaList.remove(4);
        alphaList.add(3, drew);
        displayList(alphaList);
        System.out.println();
        
        // Further example
        System.out.println("Further example");
        // Make a list of names as you think of them
        ListInterface<Name> nameList = new AList<>();
        Name amy = new Name("Amy", "Smith");
        nameList.add(amy);
        nameList.add(new Name("Tina", "Drexel"));
        nameList.add(new Name("Robert", "Jones"));
        displayList(nameList);
        
        Name secondName = nameList.getEntry(2);
        System.out.println("The second name in the name list: " + secondName);
        
        
    } // end testList
    
    public static <T> void displayList(ListInterface<T> list)
    {
        int numberOfEntries = list.getLength();
        System.out.println("The attendance list:");
        for (int position = 1; position <= numberOfEntries; position++)
            System.out.print(list.getEntry(position) + "\t");
        
        System.out.println();
    } // end displayList
} // end Listclient
