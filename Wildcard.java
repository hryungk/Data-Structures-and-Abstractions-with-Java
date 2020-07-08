/** A class that exchanges the objects at two given positions in a given array. */
public class Wildcard 
{
    public static void displayPair(OrderedPair<?> pair)
    {
        System.out.println(pair);
    } // end displayPair
    
    public static void main(String arg[])
    {
        OrderedPair<String> aPair = new OrderedPair<>("apple","banana");
        OrderedPair<Integer> anotherPair = new OrderedPair<>(1,2);
        
        displayPair(aPair);
        displayPair(anotherPair);

    } // end main
    
} // end Wildcard
