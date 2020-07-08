/**
    A class of static methods to perform various mathematical computations,
    including the square root.
*/
public class JoeMath
{
    /** Computes the square root of a real number. 
        @param value A real value whose square root is desired.
        @return A string containing the square root. */
    public static String squareRoot(double value) 
    {
        String result = "";
        try
        {
            Double temp = OurMath.squareRoot(value);
            result = temp.toString();
        }
        catch (SquareRootException e) // Assume value is negative.
        {
            //assert value < 0;
            Double temp = OurMath.squareRoot(-value);
            result = temp.toString() + "i";
        } // end try-catch

        return result;
    } // end squareRoot    
} // end JoeMath
