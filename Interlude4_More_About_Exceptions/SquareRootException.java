/**
    A class of runtime exceptions thrown when an attempt is made to find the 
    square root of a negative number. 
    @author Frank M. Carrano
*/
public class SquareRootException extends RuntimeException
{
    public SquareRootException()
    {
        super("Attempted square root of a negative number.");
        // or this("Attempted square root of a negative number.");
    } // end default constructor
    
    public SquareRootException(String message)
    {
        super(message);
    } // end constructor
} // end SquareRootException
