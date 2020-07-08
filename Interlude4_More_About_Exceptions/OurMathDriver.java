/**
    A demonstration of a runtime exception using the class OurMath.
*/
public class OurMathDriver
{
    public static void main(String[] args)
    {
        System.out.print("The square root of 9 is ");
        System.out.println(OurMath.squareRoot(9.0));
        
        System.out.print("The square root of −9 is ");
        System.out.println(OurMath.squareRoot(-9.0));
        
        System.out.print("The square root of 16 is ");
        System.out.println(OurMath.squareRoot(16.0));        
    } // end main
} // end OurMathDriver
