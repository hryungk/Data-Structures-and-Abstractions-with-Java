/**
    A class of null pointer exceptions thrown when an attempt is made to find milk.
*/
public class NoMilkException extends NullPointerException
{
    public NoMilkException()
    {
        this("OUT OF MILK!");
    } // end default constructor
    
    public NoMilkException(String message)
    {
        super(message);
    } // end constructor
} // end SquareRootException
