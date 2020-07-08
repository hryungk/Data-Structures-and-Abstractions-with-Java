/**
    A class of runtime exceptions thrown when an attempt is made to access 
    or remove the front of a queue
    @author Hyunryung Kim
*/
public class EmptyQueueException extends RuntimeException
{
    public EmptyQueueException()
    {
        this(null);
    } // end default constructor
    
    public EmptyQueueException(String message)
    {
        super(message);
    } // end constructor
} // end EmptyQueueException
