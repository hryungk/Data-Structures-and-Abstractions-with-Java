/**
   A class of ordered pairs of objects having the same data type.
   
   @author Frank M. Carrano, Timothy M. Henry
   @version 5.0
*/
public class OrderedPair<T> implements Pairable<T>
{
   private T first, second;
  
   public OrderedPair(T firstItem, T secondItem) // NOTE: no <T> after constructor name
   { 
      first = firstItem;
      second = secondItem;
   } // end constructor
   
   /** Returns the first object in this pair. */
   public T getFirst()
   {
      return first;
   } // end getFirst
   
   /** Returns the second object in this pair. */
   public T getSecond()
   {
      return second;
   } // end getSecond
   
   /** Returns a string representation of this pair. */
   public String toString()
   {
      return "(" + first + ", " + second + ")";
   } // end toString
   
   /** Interchanges the objects in this pair. */
   public void changeOrder()
   {
      T temp = first;
      first = second;
      second = temp;
   } // changeOrder
} // end OrderedPair
