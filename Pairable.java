/**
    An interface for pairs of objects.
    @author Frank M. Carrano, Timothy M. Henry
    @version 5.0
*/
public interface Pairable<T>
{
   public T getFirst();
   public T getSecond();
   public void changeOrder();
} // end Pairable
