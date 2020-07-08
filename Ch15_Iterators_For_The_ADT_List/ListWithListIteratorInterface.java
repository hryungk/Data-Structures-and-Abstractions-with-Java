/** An interface for the ADT list.
    Entries in a list have positions that begin with 1.
    @author Frank M. Carrano
 */
import java.util.ListIterator;
public interface ListWithListIteratorInterface<T> extends ListInterface<T>, 
                                                      Iterable<T>
{
    public ListIterator<T> getIterator();           
} // end ListWithListIteratorInterface
