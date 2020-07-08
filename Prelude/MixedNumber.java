/** A class that represents a fraction. */
public class MixedNumber implements MixedNumberInterface
{
    OutOfRangeException problem =
         new OutOfRangeException("Input value is out of range.");
    private int numerator, totalNum, denominator,integer;
    private Fraction frac;
    private char sign;
    private int signNum = 1;
    
    /** Constructor: Initializes the number as 0. */
    public MixedNumber()
    {
        integer = 0;
        numerator = 0;
        denominator = 1;
        totalNum = integer * denominator + numerator;
        frac = new Fraction(totalNum, denominator);
        sign = ' ';
    } // end default constructor
    
    public MixedNumber(int newInt, int newNum, int newDen)
    {
        integer = newInt;
        numerator = newNum;
        denominator = newDen;
        sign = calcSign(newInt, newNum, newDen);
        // Make numbers absolute
        integer = Math.abs(integer);
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);
        reduce();
        // Numerator of fraction form
        totalNum = integer * denominator + numerator;
        frac = new Fraction(totalNum, denominator);
        
        
        numBiggerThanDen();
        
    } // end constructor    
        
    
    /** Sets the integer portion of the mixed number.
        @param newInt   An integer that is the desired numerator number.
    */
    public void setInt(int newInt)
    {
        integer = newInt;
    }
    
    /** Gets the denominator of the fraction.
        @return An integer portion of the mixed number.
    */
    public int getInt()
    {
        return integer;
    }
    
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
        int num = totalNum * frac1.getDenom() + frac1.getNum()*denominator;
        return reduce(num,den);
    } 
    /** Adds two mixed numbers.
     * @param mixedNum1 A MixedNumber to be added to this mixed number.
     * @return A Fraction containing the summation of two mixed numbers. 
     */
    public MixedNumberInterface add(MixedNumberInterface mixedNum1)
    {
        Fraction frac1 = toFraction(mixedNum1);
        return toMixedNumber(frac.add(frac1));
    } // end add    
    
    /** Subtracts two fractions.
     * @param frac1 A Fraction to be subtracted from this fraction.
     * @return A Fraction containing the subtraction of two fractions. 
     */
    public FractionInterface subtract(FractionInterface frac1)
    {
        int den = denominator * frac1.getDenom();
        int num = totalNum * frac1.getDenom() - frac1.getNum()*denominator;
        return reduce(num,den);
    }
    /** Subtracts two mixed numbers.
     * @param mixedNum1 A MixedNumber to be subtracted from this mixed number.
     * @return A MixedNumber containing the subtraction of two mixed number. 
     */
    public MixedNumberInterface subtract(MixedNumberInterface mixedNum1)
    {
        Fraction frac1 = toFraction(mixedNum1);
        return  toMixedNumber(subtract(frac1));  
    } // end subtract    
    
    /** Multiply two fractions.
     * @param frac1 A Fraction to be multiplied to this fraction.
     * @return A Fraction containing the multiplication of two fractions. 
     */
    public FractionInterface multiply(FractionInterface frac1)
    {
        int den = denominator * frac1.getDenom();
        int num = totalNum * frac1.getNum();
        return reduce(num,den);        
    }
    /** Multiply two mixed numbers.
     * @param mixedNum1 A MixedNumber to be multiplied to this mixed number.
     * @return A MixedNumber containing the multiplication of two mixed numbers. 
     */
    public MixedNumberInterface multiply(MixedNumberInterface mixedNum1)
    {
        Fraction frac1 = toFraction(mixedNum1);
        return  toMixedNumber(multiply(frac1));
    } // end multiply    
    
    /** Divides two fractions.
     * @param frac1 A Fraction by which this fraction is to be divided.
     * @return A Fraction containing the division of two fractions. 
     */
    public FractionInterface divide(FractionInterface frac1)
    {
        int den = denominator * frac1.getNum();
        int num = totalNum * frac1.getDenom();
        return reduce(num,den);       
    }
    /** Divides two mixed numbers.
     * @param mixedNum1 A MixedNumber by which this mixed number is to be divided.
     * @return A MixedNumber containing the division of two mixed numbers. 
     */
    public MixedNumberInterface divide(MixedNumberInterface mixedNum1)
    {
        Fraction frac1 = toFraction(mixedNum1);
        return toMixedNumber(divide(frac1)); 
    } // end divide    
    
    /** Returns reciprocal of this fraction.
     * @return A Fraction containing a reciprocal of this fraction. 
     */
    public FractionInterface reciprocal()
    {
        divideByZero(totalNum);
        return reduce(denominator,totalNum);
    } // end reciprocal
    
    /** Returns an integer based on comparing two fractions.
     * 1: this > frac1, 
     * 0: this = frac1, 
     * -1: this < frac1
     * @param frac1 A Fraction to be compared
     * @return an integer depending on the condition
     */
    public int compare(FractionInterface frac1)
    {
        int result;
        double fracNum0 = (double)totalNum/denominator*signNum;
        double fracNum1 = (double)frac1.getNum()/frac1.getDenom();        
        
        if (Math.abs(fracNum0 - fracNum1) < Math.pow(10, -6))
            result = 0;
        else
            if (fracNum0 - fracNum1 > 0)
                result = 1;
            else
                result = -1;
            
        return result;
    }
    /** Returns an integer based on comparing two mixed numbers.
     * @param mixedNum1 A MixedNumber to be compared
     * @return An integer depending on the condition
     */
    public int compare(MixedNumberInterface mixedNum1)
    {
        Fraction frac1 = toFraction(mixedNum1);
        return compare(frac1);    
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
    } 
    /** Returns true if two mixed numbers are equal.
     * @param mixedNum1 A MixedNumber to be compared
     * @return true if this = mixedNum1, false if this != equal
     */
    public boolean isEqual(MixedNumberInterface mixedNum1)
    {
        Fraction frac1 = toFraction(mixedNum1);
        return isEqual(frac1);        
    } // end isEqual
    
    /** Returns a string that contains this fraction in lowest terms.
     * @return A string containing this fraction. 
     */
    public String toString()
    {
        String result;
        if (denominator == 1)
            result = sign + Integer.toString(integer+numerator);
        else
            if (integer != 0)
                result = sign + (integer + " " + numerator + "/" + denominator);
            else
                result = sign + (numerator + "/" + denominator);
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
    private void divideByZero(int newDen)
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
    
    /** Catches when numerator > denominator
        @throws OutOfBoundaryException if numerator > denominator.
    */
    private void numBiggerThanDen()
    {
        try
        {
            boolean bigger = numerator > denominator;
            if (bigger)
                throw problem;
        }
        catch (OutOfRangeException exception)
        {
            System.out.println("Numerator cannot be greather than denominator.");
            System.out.println("The exception message: "+ exception.getMessage());
            System.out.println("The call stack trace: ");
            exception.printStackTrace();
        }
    } // end numBiggerThanDen
    
    /** Returns a Fraction that is converted from mixed number
        @param mixedNum1 A MixedNumber object to be calculated with this number.
        @return A Fraction containing mixedNum1's fraction form.
     */
    private Fraction toFraction(MixedNumberInterface mixedNum1)
    {
        // num: numerator of mixedNum1 after making into a fraction
        int num = (mixedNum1.getInt()*mixedNum1.getDenom() + mixedNum1.getNum())
                  * mixedNum1.getSign();
        
        return new Fraction(num,mixedNum1.getDenom());
    } // end toFraction
    
    /** Returns a MixedNumber that is converted from a Fraction
        @param frac1 A Fraction object to be converted to a MixedNumber.
        @return A MixedNumber containing frac1's mixed number form.
     */
    private MixedNumber toMixedNumber(FractionInterface frac1)
    {
        int intNum = frac1.getNum()/frac1.getDenom();
        int num = frac1.getNum()%frac1.getDenom();
        int den = frac1.getDenom();
        FractionInterface fracReduced = reduce(num,den);
        
        return new MixedNumber(intNum,fracReduced.getNum(),fracReduced.getDenom());
    } // end toMixedNumber
    
    /** Returns char of sign of the mixed number
        @param mixedNum1 A MixedNumber of which the sign to be determined
        @return A char containing the sign of mixedNum1
     */
    private char calcSign(MixedNumberInterface mixedNum1)
    {
        char result = ' ';
        
        int intNum = mixedNum1.getInt();
        int num = mixedNum1.getNum();
        int den = mixedNum1.getDenom();
        
        if (intNum*num*den < 0)
            result = '-';
        
        return result;
    }
    /** Returns char of sign of the mixed number
        @param intNum An integer that is the integer portion of a MixedNumber
        @param num An integer that is the numerator of a MixedNumber
        @param den An integer that is the denominator of a MixedNumber
        @return A char containing the sign of mixedNum1
     */    
    private char calcSign(int intNum, int num, int den)
    {
        char result = ' ';
        
        if (intNum*num*den < 0)
        {
            result = '-';
            signNum = -1;
        }
        
        return result;
    } // end calcSign
    
    /** Returns the sign number of this mixed number
        @return 1 if positive, -1 if negative
     */
    public int getSign()
    {
        return signNum;
    }
} // end Fraction