/** A class that maintains a shopping cart for an online store.
    @author Frank M. Carrano, Timothy M. Henry
    @version 5.0
*/

import java.util.ArrayList;
public class groceryShopper
{
    public static void main(String[] args) 
    {   
        String[] items = {"milk", "eggs", "broccoli", "cilantro", "oranges",
                    "rolled oats", "sugar", "flour", "soup", "beer"};
        BagInterface<String> groceryBag = new ArrayBag<>();

        // Statements that add selected items to the grocery bag:
        for (int index = 0; index < items.length; index++)
        {
            String nextItem = items[index]; // Simulate adding items for shopper
            groceryBag.add(nextItem);
        } // end for
        
        // Make a list of distinct strings
        int bagSize = groceryBag.getCurrentSize();  // number of groceries in bag
        String[] groceries = groceryBag.toArray();  // groceries in the bag
        String currentItem;
        ArrayList<String> distinctList = new ArrayList<>(); // distinct array list
        for (int i = 0; i < bagSize; i++)
        {
            currentItem = groceries[i];
            if (!distinctList.contains(currentItem))
                distinctList.add(currentItem);
        } // end for
        String[] distinctGroceries = new String[distinctList.size()]; // distinct array
        for (int i = 0; i < distinctList.size(); i++)
            distinctGroceries[i] = distinctList.get(i);

        // Remove all soups!
        int countSoup = 0;
        currentItem = "";
        for (int i = 0; i < bagSize; i++) // Look for soup
        {
            currentItem = groceries[i];
            if (currentItem.equalsIgnoreCase("soup"))
            {
                groceryBag.remove(currentItem);
                countSoup++;
            } // end if
        } // end for
        
        if (countSoup == 0)
            System.out.println("No soup is found in the grocery bag.");
        else
            if (countSoup == 1)
                System.out.println("A soup is removed.");
            else
                System.out.println(countSoup + " soups are removed.");

    } // end main
} // end groceryShopper

