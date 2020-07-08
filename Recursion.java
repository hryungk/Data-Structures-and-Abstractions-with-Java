/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HRK
 */
public class Recursion 
{
    public static void main (String arg[])
    {
        countDown(3);   
        System.out.println();
        
        countUp(3);
        System.out.println();
        
        int[] intArray = {1, 2, 3, 4, 5, 6};
        displayArray(intArray, 2, 5);
        System.out.println();
        displayArrayF(intArray, 2, 5);
        System.out.println();
        displayArrayH(intArray, 2, 5);
        System.out.println();
    }    
    public static void countDown(int n)
    {
        System.out.println(n);
        if (n > 1)            
            countDown(n - 1);
    } // end countDown

    public static void skipLine(int n)
    {
        if (n >= 1)
        {
            System.out.println();
            skipLine(n-1);
        } // end if
    } // end skipLine
     
    public static void countUp(int integer)
    {
        if (integer > 1 )
            countUp(integer-1);
        System.out.println(integer);
        
    } // end countUp
    
    public static int productOf(int n)
    {
        int product = 1;
        if (n > 1)
            product = n * productOf(n-1);   // Recursive call
        return product;
    } // end productOf
    
    public static void displayArray(int array[], int first, int last) 
    {
        System.out.print(array[first] + " "); 
        if (first < last)
            displayArray(array, first + 1, last);
    } // end displayArray
    
    public static void displayArrayF(int array[], int first, int last) 
    {
        if (first <= last) 
        {
            displayArrayF(array, first, last - 1);
            System.out.print(array[last] + " ");
       } // end if
    } // end displayArray
    
    public static void displayArrayH(int array[], int first, int last) 
    {
        if (first == last) 
            System.out.print(array[first] + " ");
        else
        {
            int mid = (first + last) / 2; 
            displayArrayH(array, first, mid); 
            displayArrayH(array, mid + 1, last);
        } // end if
    } // end displayArray
} // end Recursion
