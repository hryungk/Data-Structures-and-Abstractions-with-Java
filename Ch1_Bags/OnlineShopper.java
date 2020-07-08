/** A class that maintains a shopping cart for an online store.
    @author Frank M. Carrano, Timothy M. Henry
    @version 5.0
*/
public class OnlineShopper
{
    public static void main(String[] args) 
    {
        Item[] items = {new Item("Bird feeder", 2050),
                        new Item("Squirrel guard", 1547),
                        new Item("Bird bath", 4499),
                        new Item("Sunflower seeds", 1295)};

        BagInterface<Item> shoppingCart = new ArrayBag<>();
        int totalCost = 0;

        // Statements that add selected items to the shopping cart:
        for (int index = 0; index < items.length; index++)
        {
            Item nextItem = items[index]; // Simulate getting item from shopper
            shoppingCart.add(nextItem);
            totalCost = totalCost + nextItem.getPrice();  
        } // end for

        // Simulate checkout
        while (!shoppingCart.isEmpty())
            System.out.println(shoppingCart.remove());

        System.out.println("Total cost: " + "\t$" + totalCost / 100 + "." +
                 totalCost % 100);
    } // end main
} // end OnlineShopper

/*
Sunflower seeds $12.95
Bird bath	    $44.99
Squirrel guard	 $15.47
Bird feeder	    $20.50
Total cost: 	 $93.91
*/
