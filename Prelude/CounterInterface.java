/** An interface for a class of counters */
public interface CounterInterface 
{
    /** Sets the count.
         @param newCount  An integer that is the desired count number. 
         @throws ArithmeticException if newCount <= 0.
    */
    public void setCounter(int newCount);
    /** Gets the count.
        @return  An integer containing the current count. */
    public int getCounter();
    
    /** Increments the count variable by 1. */
    public void increase();
    /** Decrements the count variable by 1. 
        @throws ArithmeticException if the count variable <= 0.
     */
    public void decrease();
    
    /** Tests whether the current count is zero.
        @return A Boolean which is true if the count is zero. */
    public boolean isZero();
    
    /** Returns the current count as a string.
        @return A string containing count in a suitable form for display. */
    public String toString();
    
    
    
} // end CounterInterface