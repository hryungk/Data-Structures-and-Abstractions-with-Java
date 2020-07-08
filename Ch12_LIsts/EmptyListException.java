/**
    A class of runtime exceptions thrown when an attempt is made to access 
    or remove the front of an empty list.
    @author Hyunryung Kim
*/
public class EmptyListException extends RuntimeException
{
    public EmptyListException()
    {
        this(null);
    } // end default constructor
    
    public EmptyListException(String message)
    {
        super(message);
    } // end constructor
} // end EmptyQueueException
