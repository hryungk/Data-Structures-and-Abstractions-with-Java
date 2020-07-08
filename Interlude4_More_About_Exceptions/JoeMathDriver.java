/**
    A demonstration of a runtime exception using the class JoeMath.
*/
public class JoeMathDriver
{
    public static void main(String[] args)
    {
        System.out.print("The square root of 9 is ");
        System.out.println(JoeMath.squareRoot(9.0));
        
        System.out.print("The square root of −9 is ");
        System.out.println(JoeMath.squareRoot(-9.0));
        
        System.out.print("The square root of 16 is ");
        System.out.println(JoeMath.squareRoot(16.0));        

        System.out.print("The square root of -16 is ");
        System.out.println(JoeMath.squareRoot(-16.0));  
    } // end main
} // end JoeMathDriver
