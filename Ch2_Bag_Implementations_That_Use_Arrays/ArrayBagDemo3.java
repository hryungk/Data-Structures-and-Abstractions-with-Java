/** A test of the methods remove and clear, as defined in the class ArrayBag.
    Assumes the remaining methods are correct.
    @author Frank M. Carrano, Timothy M. Henry
    @version 5.0
*/
public class ArrayBagDemo3
{
    public static void main(String[] args) 
    {
        // A bag that is not full
        BagInterface<String> aBag = new ArrayBag<>();
        System.out.println("Testing an initially empty bag:");
      
        // Removing a string from an empty bag:
        String[] testStrings1 = {"", "B"};
        testRemove(aBag, testStrings1);

        // Adding strings
        String[] contentsOfBag1 = {"A", "A", "B", "A", "C", "A"};
        testAdd(aBag, contentsOfBag1);
		
        // Removing strings
        String[] testStrings3 = {"", "B", "A", "C", "Z"};
        testRemove(aBag, testStrings3);

        System.out.println("\nClearing the bag:");
        aBag.clear();
        testIsEmpty(aBag, true);
        displayBag(aBag);

	//----------------------------------------------------------------------

        // A bag that will be full
        aBag = new ArrayBag<String>(7);
        System.out.println("\nA new empty bag:");

        // Removing a string from an empty bag:
        testRemove(aBag, testStrings1);

        // Adding strings
        String[] contentsOfBag2 = {"A", "B", "A", "C", "B", "C", "D"};
        testAdd(aBag, contentsOfBag2);
		
        // Removing strings
        System.out.println("\nRemoving strings:");
        testRemove(aBag, testStrings3);

        System.out.println("\nClearing the bag:");
        aBag.clear();

        testIsEmpty(aBag, true);
        displayBag(aBag);
    } // end main
	
    // Tests the method add.
    private static void testAdd(BagInterface<String> aBag, String[] content)
    {
        System.out.print("Adding the following strings to the bag: ");
        for (int index = 0; index < content.length; index++)
        {
            if (aBag.add(content[index]))
                System.out.print(content[index] + " ");
            else
                System.out.print("\nUnable to add " + content[index] +
                             " to the bag.");
        } // end for
        System.out.println();
      
        displayBag(aBag);
    } // end testAdd

    // Tests the two remove methods.
    private static void testRemove(BagInterface<String> aBag, String[] tests)
    {
        System.out.println("\nTesting the two remove methods:");
        for (int index = 0; index < tests.length; index++)
        {
            String aString = tests[index];
            if (aString.equals("") || (aString == null))
            {
                // test remove()
                System.out.println("\nRemoving a string from the bag:");
                String removedString = aBag.remove();
                System.out.println("remove() returns " + removedString);
            }
            else
            {
                // Test remove(aString)
                System.out.println("\nRemoving \"" + aString + "\" from the bag:");
                boolean result = aBag.remove(aString);
                System.out.println("remove(\"" + aString + "\") returns " + result);
            } // end if
         
            displayBag(aBag);
        } // end for
    } // end testRemove

    // Tests the method isEmpty.
    // correctResult indicates what isEmpty should return.   
    private static void testIsEmpty(BagInterface<String> aBag, boolean correctResult)
    {
        System.out.print("\nTesting the method isEmpty with ");
        if (correctResult)
            System.out.println("an empty bag:");
        else
            System.out.println("a bag that is not empty:");
      
        System.out.print("isEmpty finds the bag ");
        if (correctResult && aBag.isEmpty())
            System.out.println("empty: OK.");
        else if (correctResult)
            System.out.println("not empty, but it is empty: ERROR.");
            else if (!correctResult && aBag.isEmpty())
                    System.out.println("empty, but it is not empty: ERROR.");
                else
                    System.out.println("not empty: OK.");      
    } // end testIsEmpty

    // Tests the method toArray while displaying the bag.
    private static void displayBag(BagInterface<String> aBag)
    {
        System.out.println("The bag contains " + aBag.getCurrentSize() +
                            " string(s), as follows:");		
        Object[] bagArray = aBag.toArray();
        for (int index = 0; index < bagArray.length; index++)
        {
            System.out.print(bagArray[index] + " ");
        } // end for
		
        System.out.println();
    } // end displayBag
} // end ArrayBagDemo

/*
 Testing an initially empty bag:
 
 Testing the two remove methods:
 
 Removing a string from the bag:
 remove() returns null
 The bag contains 0 string(s), as follows:
 
 
 Removing "B" from the bag:
 remove("B") returns false
 The bag contains 0 string(s), as follows:
 
 Adding to the bag: A A B A C A
 The bag contains 6 string(s), as follows:
 A A B A C A
 
 Testing the two remove methods:
 
 Removing a string from the bag:
 remove() returns A
 The bag contains 5 string(s), as follows:
 A A B A C
 
 Removing "B" from the bag:
 remove("B") returns true
 The bag contains 4 string(s), as follows:
 A A C A
 
 Removing "A" from the bag:
 remove("A") returns true
 The bag contains 3 string(s), as follows:
 A A C
 
 Removing "C" from the bag:
 remove("C") returns true
 The bag contains 2 string(s), as follows:
 A A
 
 Removing "Z" from the bag:
 remove("Z") returns false
 The bag contains 2 string(s), as follows:
 A A
 
 Clearing the bag:
 
 Testing the method isEmpty with an empty bag:
 isEmpty finds the bag empty: OK.
 The bag contains 0 string(s), as follows:
 
 
 A new empty bag:
 
 Testing the two remove methods:
 
 Removing a string from the bag:
 remove() returns null
 The bag contains 0 string(s), as follows:
 
 
 Removing "B" from the bag:
 remove("B") returns false
 The bag contains 0 string(s), as follows:
 
 Adding to the bag: A B A C B C D
 The bag contains 7 string(s), as follows:
 A B A C B C D
 
 Removing strings:
 
 Testing the two remove methods:
 
 Removing a string from the bag:
 remove() returns D
 The bag contains 6 string(s), as follows:
 A B A C B C
 
 Removing "B" from the bag:
 remove("B") returns true
 The bag contains 5 string(s), as follows:
 A C A C B
 
 Removing "A" from the bag:
 remove("A") returns true
 The bag contains 4 string(s), as follows:
 B C A C
 
 Removing "C" from the bag:
 remove("C") returns true
 The bag contains 3 string(s), as follows:
 B C A
 
 Removing "Z" from the bag:
 remove("Z") returns false
 The bag contains 3 string(s), as follows:
 B C A
 
 Clearing the bag:
 
 Testing the method isEmpty with an empty bag:
 isEmpty finds the bag empty: OK.
 The bag contains 0 string(s), as follows:
*/
