//********************************************************************************
//  FractionDriver.java      Author: Hyunryung Kim
//
//  Prelude Projects 5
//  Write a Java class Fraction that implements the interface you designed in the 
//  previous project. Begin with reasonable constructors. Design and implement 
//  useful private methods, and include comments that specify them. 
//  Write a program that adequately demonstrates your class.
//********************************************************************************

public class Prelude_P5_FractionDriver 
{
    //----------------------------------------------------------------------------
    //  Creates a CDCollection object and adds some CDs to it. Prints
    //  reports on the status of the collection.
    //----------------------------------------------------------------------------
    public static void main(String[] args) 
    {
        Fraction frac1 = new Fraction (12,36);
        Fraction frac2 = new Fraction (4,8);
        Fraction frac3 = new Fraction (5,15);
        
        System.out.println("frac1 = " + frac1);
        System.out.println("frac2 = " + frac2);
        System.out.println("1/frac1 = " + frac1.reciprocal());
        System.out.println("1/frac2 = " + frac2.reciprocal());
        System.out.println("frac1 + frac2 = " + frac1.add(frac2));
        System.out.println("frac1 - frac2 = " + frac1.subtract(frac2));
        System.out.println("frac1 * frac2 = " + frac1.multiply(frac2));
        System.out.println("frac1 / frac2 = " + frac1.divide(frac2));
        
        if (frac1.compare(frac2) > 0)
            System.out.println("frac1 ("+frac1+") is bigger than frac2 ("+frac2+") ");
        else
            if (frac1.compare(frac2) < 0)
                System.out.println("frac1  ("+frac1+") is smaller than frac2 ("+frac2+") ");
            else
                System.out.println("frac1 ("+frac1+")  is equal to frac2 ("+frac2+") ");
        
        if (frac1.isEqual(frac3))
            System.out.println("frac1 ("+frac1+") is equal to frac3 ("+frac3+") ");            
                
        
        
    }
}
