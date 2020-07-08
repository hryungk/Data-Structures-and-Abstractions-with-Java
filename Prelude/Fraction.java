/** A class that represents a fraction. */
public class Fraction implements FractionInterface
{
    private int numerator, denominator;
    
    /** Constructor: Initializes the numerator and denominator as 1. */
    public Fraction()
    {
        numerator = 1;
        denominator = 1;
    } // end default constructor
    
    public Fraction(int newNum, int newDen)
    {
        numerator = newNum;
        denominator = newDen;
        reduce();
    } // end constructor    
        
    /** Sets the numerator of the fraction.
        @param newNum   An integer that is the desired numerator number.
    */
    public void setNum(int newNum)
    {
        numerator = newNum;
    } // end setNum
    /** Sets the denominator of the fraction.
        @param newDen   An integer that is the desired denominator number.
        @throws NullPointerException if newDen = 0.
    */
    public void setDenom(int newDen)
    {
        denominator = newDen;
        divideByZero(newDen);
    } // end setDenom
    /** Gets numerator of this fraction
     * @return An integer containing numerator
     */
    public int getNum()
    {
        return numerator;
    } // end getNum
    /** Gets denominator of this fraction
     * @return An integer containing denominator
     */
    public int getDenom()
    {
        return denominator;
    } // end getDenom
    
    /** Adds two fractions.
     * @param frac1 A Fraction to be added to this fraction.
     * @return A Fraction containing the summation of two fractions. 
     */
    public FractionInterface add(FractionInterface frac1)
    {
        int den = denominator * frac1.getDenom();
        int num = numerator * frac1.getDenom() + frac1.getNum()*denominator;
        return reduce(num,den);
    } // end add
    
    /** Subtracts two fractions.
     * @param frac1 A Fraction to be subtracted from this fraction.
     * @return A Fraction containing the subtraction of two fractions. 
     */
    public FractionInterface subtract(FractionInterface frac1)
    {
        int den = denominator * frac1.getDenom();
        int num = numerator * frac1.getDenom() - frac1.getNum()*denominator;
        return reduce(num,den);
    } // end subtract
    
    /** Multiply two fractions.
     * @param frac1 A Fraction to be multiplied to this fraction.
     * @return A Fraction containing the multiplication of two fractions. 
     */
    public FractionInterface multiply(FractionInterface frac1)
    {
        int den = denominator * frac1.getDenom();
        int num = numerator * frac1.getNum();
        return reduce(num,den);        
    } // end multiply
    
    /** Divides two fractions.
     * @param frac1 A Fraction by which this fraction is to be divided.
     * @return A Fraction containing the division of two fractions. 
     */
    public FractionInterface divide(FractionInterface frac1)
    {
        int den = denominator * frac1.getNum();
        int num = numerator * frac1.getDenom();
        return reduce(num,den);       
    } // end divide
    
    /** Returns reciprocal of this fraction.
     * @return A Fraction containing a reciprocal of this fraction. 
     */
    public FractionInterface reciprocal()
    {
        divideByZero(numerator);
        return reduce(denominator,numerator);
    } // end reciprocal
    
    /** Returns an integer.
     * 1: this > frac1, 
     * 0: this = frac1, 
     * -1: this < frac1
     * @param frac1 A Fraction to be compared
     * @return an ingeter depending on the condition
     */
    public int compare(FractionInterface frac1)
    {
        int result;
        double fracNum0 = (double)numerator/denominator;
        double fracNum1 = (double)frac1.getNum()/frac1.getDenom();        
        
        if (Math.abs(fracNum0 - fracNum1) < Math.pow(10, -6))
            result = 0;
        else
            if (fracNum0 - fracNum1 > 0)
                result = 1;
            else
                result = -1;
            
        return result;
    } // end compare
    
    /** Returns true if two fractions are equal.
     * @param frac1 A Fraction to be compared
     * @return true if this = frac1, false if this != equal
     */
    public boolean isEqual(FractionInterface frac1)
    {
        boolean result = false;
        if (compare(frac1) == 0)
            result = true;
        
        return result;
    } // end isEqual
    
    /** Returns a string that contains this fraction in lowest terms.
     * @return A string containing this fraction. 
     */
    public String toString()
    {
        String result;
        if (denominator == 1)
            result = Integer.toString(numerator);
        else
            result = numerator + "/" + denominator;
        return result;
    } // end toString

    /** Reduces this fraction to its lowest terms.
     *  Update numerator and denominator accordingly.
     */
    private void reduce()
    {
        int GCD = gcd(numerator, denominator);
        setNum(numerator/GCD);
        setDenom(denominator/GCD);
    }    
    /** Returns a reduced fraction to its lowest terms.
        @return A Fraction at its lowest terms.
     */
    private FractionInterface reduce(int num, int den)
    {
        int GCD = gcd(num, den);
        return  new Fraction(num/GCD,den/GCD);
        
    } // end reduce

    /** Find the greatest common denominator
     * @param num1 An integer to compare to num2
     * @param num2 An integer to compare to num1
     * @return An integer containing gcd
     */
    private int gcd(int num1, int num2)
    {
        int result; 
        
        num1 = Math.abs(num1);
        num2 = Math.abs(num2);
        
        if (num1 % num2 == 0)
            result = num2;
        else
            result = gcd(num2, num1 % num2);
        
        return result;                    
    } // end gcd

    /** Catches divide by zero exception
        @param newDen   An integer that is the desired denominator number.
        @throws NullPointerException if newDen = 0.
    */
    public void divideByZero(int newDen)
    {
        try
        {
            double num1 = 1/newDen;
        }
        catch (NullPointerException exception)
        {
            System.out.println("Denominator cannot be 0.");
            System.out.println("The exception message: "+ exception.getMessage());
            System.out.println("The call stack trace: ");
            exception.printStackTrace();
        }
    } // end divideByZero    
} // end Fraction