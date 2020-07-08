//********************************************************************************
//  MixedNumberDriver.java      Author: Hyunryung Kim
//
//  Prelude Projects 7
//  Design a class MixedNumber of mixed numbers that uses the class Fraction that 
//  you designed in the previous project. Provide operations for MixedNumber that 
//  are analogous to those of Fraction. 
//********************************************************************************

public class Prelude_P7_MixedNumberDriver 
{
    //----------------------------------------------------------------------------
    //  Creates a CDCollection object and adds some CDs to it. Prints
    //  reports on the status of the collection.
    //----------------------------------------------------------------------------
    public static void main(String[] args) 
    {
        MixedNumber mixedNum1 = new MixedNumber (-3,12,36);
        MixedNumber mixedNum2 = new MixedNumber (1,4,8);
        MixedNumber mixedNum3 = new MixedNumber (3,5,15);
        
        System.out.println("mixedNum1 = " + mixedNum1);
        System.out.println("mixedNum2 = " + mixedNum2);
        System.out.println("1/mixedNum1 = " + mixedNum1.reciprocal());
        System.out.println("1/mixedNum2 = " + mixedNum2.reciprocal());
        System.out.println("mixedNum1 + mixedNum2 = " + mixedNum1.add(mixedNum2));
        System.out.println("mixedNum1 - mixedNum2 = " + mixedNum1.subtract(mixedNum2));
        System.out.println("mixedNum1 * mixedNum2 = " + mixedNum1.multiply(mixedNum2));
        System.out.println("mixedNum1 / mixedNum2 = " + mixedNum1.divide(mixedNum2));
        
        if (mixedNum1.compare(mixedNum2) > 0)
            System.out.println("mixedNum1 ("+mixedNum1+") is bigger than mixedNum2 ("+mixedNum2+") ");
        else
            if (mixedNum1.compare(mixedNum2) < 0)
                System.out.println("mixedNum1  ("+mixedNum1+") is smaller than mixedNum2 ("+mixedNum2+") ");
            else
                System.out.println("mixedNum1 ("+mixedNum1+")  is equal to mixedNum2 ("+mixedNum2+") ");
        
        if (mixedNum1.isEqual(mixedNum3))
            System.out.println("mixedNum1 ("+mixedNum1+") is equal to mixedNum3 ("+mixedNum3+") ");            
                
        
        
    }
}
