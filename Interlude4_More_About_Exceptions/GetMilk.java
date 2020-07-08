/** 
    Demonstrates the behavior of a finally block.
 */
public class GetMilk 
{
    public static void main (String[] args)
    {
        try
        {
            openRefrigerator();
            takeOutMilk();
            pourMilk();
            putBackMilk();
        }
        catch (NoMilkException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            closeRefrigerator();
        }
    }// end main    
    
    public static void openRefrigerator()
    {
        System.out.println("Open the refrigerator door.");
    } // end openRefrigerator
    
    public static void takeOutMilk() throws NoMilkException
    {
        if (Math.random() < 0.5)
            System.out.println("Take out the milk.");
        else
            throw new NoMilkException("Out of Milk!");
    } // end taekOutMilk
    
    public static void pourMilk()
    {
        System.out.println("Pour the milk.");
    } // end pourMilk
  
    public static void putBackMilk()
    {
        System.out.println("Put the milk back.");
    } // end putBackMilk    
    
    public static void closeRefrigerator()
    {
        System.out.println("Close the refrigerator door.");
    } // end closeRefrigerator    
    
} // end GetMilk
