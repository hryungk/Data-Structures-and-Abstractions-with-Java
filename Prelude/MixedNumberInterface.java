/** An interface for a class of mixed numbers */
public interface MixedNumberInterface extends FractionInterface
{
    /** Sets the integer portion of the mixed number.
        @param newInt   An integer that is the desired numerator number.
    */
    public void setInt(int newInt); 
    
    /** Gets the denominator of the fraction.
        @return An integer portion of the mixed number.
    */
    public int getInt(); 

    /** Returns the sign number of this mixed number
        @return 1 if positive, -1 if negative
     */    
    public int getSign();
    
} // end MixedNumberInterface
