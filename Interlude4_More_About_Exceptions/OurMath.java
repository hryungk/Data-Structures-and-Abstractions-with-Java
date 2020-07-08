/**
    A class of static methods to perform various mathematical computations,
    including the square root.
    @author Frank M. Carrano
*/
public class OurMath
{
    /** Computes the square root of a nonnegative real number. 
        @param value A real value whose square root is desired.
        @return The square root of the given value.
        @throws SquareRootException if value < 0. */
    public static double squareRoot(double value) throws SquareRootException
    {
        if (value < 0)
            throw new SquareRootException();
        else
            return Math.sqrt(value);
    } // end squareRoot
} // end OurMath
