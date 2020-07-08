package TreePackage;
/**
    A class of null pointer exceptions thrown when an attempt is made to find data.
*/
public class EmptyTreeException extends NullPointerException
{
    public EmptyTreeException()
    {
        this("The root node is empty.");
    } // end default constructor
    
    public EmptyTreeException(String message)
    {
        super(message);
    } // end constructor
} // end SquareRootException
