/** A class that represents a circle. */
public class Circle implements Circular
{
    private double radius;
    
    /** Sets the radius of the circle.
     * @param newRadius A real number > 0
     * radius stores the value newRadius.
     * @throws ArithmeticException if newRadius <= 0.
     */
    public void setRadius(double newRadius) 
    {
        radius = newRadius;
        try
        {
            double num1 = 1/newRadius;
            double num2 = Math.sqrt(newRadius);
        }
        catch (ArithmeticException exception)
        {
            System.out.println("Improper radius: " + newRadius);
            System.out.println("The exception message is: "+ exception.getMessage());
            System.out.println("The call stack trace: ");
            exception.printStackTrace();
        }
        catch (NullPointerException exception)
        {
            System.out.println("Improper radius: " + newRadius);
            System.out.println("The exception message is: "+ exception.getMessage());
            System.out.println("The call stack trace: ");
            exception.printStackTrace();
        }
    } // end setRadius
    
    public double getRadius() 
    {
        return radius; 
    } // end getRadius
    
    public double getArea() 
    {
        return Math.PI * radius * radius; 
    } // end getArea     
} // end Circle