/** An interface for a class of fractions */
public interface FractionInterface 
{
    /** Sets the numerator of the fraction.
        @param newNum   An integer that is the desired numerator number.
    */
    public void setNum(int newNum); 
    
    /** Sets the denominator of the fraction.
        @param newDen   An integer that is the desired denominator number.
        @throws NullPointerException if newDen = 0.
    */
    public void setDenom(int newDen); 

    /** Gets the numerator of the fraction.
        @return newNum   An integer containing numerator.
    */
    public int getNum(); 
    
    /** Gets the denominator of the fraction.
        @return An integer containing denominator.
    */
    public int getDenom(); 
        
    /** Adds two fractions.
     * @param frac1 A Fraction to be added to this fraction.
     * @return A Fraction containing the summation of two fractions. 
     */
    public FractionInterface add(FractionInterface frac1);
    
    /** Subtracts two fractions.
     * @param frac1 A Fraction to be subtracted from this fraction.
     * @return A Fraction containing the subtraction of two fractions. 
     */
    public FractionInterface subtract(FractionInterface frac1);
    
    /** Multiply two fractions.
     * @param frac1 A Fraction to be multiplied to this fraction.
     * @return A Fraction containing the multiplication of two fractions. 
     */
    public FractionInterface multiply(FractionInterface frac1);
    
    /** Divides two fractions.
     * @param frac1 A Fraction by which this fraction is to be divided.
     * @return A Fraction containing the division of two fractions. 
     */
    public FractionInterface divide(FractionInterface frac1);
    
    /** Returns reciprocal of this fraction.
     * @return A Fraction containing a reciprocal of this fraction. 
     */
    public FractionInterface reciprocal();
    
    /** Returns an integer.
     * 1: this > frac1, 
     * 0: this = frac1, 
     * -1: this < frac1
     * @param frac1 A Fraction to be compared
     * @return an ingeter depending on the condition
     */
    public int compare(FractionInterface frac1);
    
    /** Returns true if two fractions are equal.
     * @param frac1 A Fraction to be compared
     * @return true if this = frac1, false if this != equal
     */
    public boolean isEqual(FractionInterface frac1);
    
    /** Returns a string that contains this fraction in lowest terms.
     * @return A string containing this fraction. 
     */
    public String toString(); 
} // end FractionInterface
