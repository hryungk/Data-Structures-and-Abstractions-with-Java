package ADTPackage;

/** An interface for the ADT list.
    Entries in a list have positions that begin with 1.
    @author Frank M. Carrano
 */
import java.util.Iterator;
public interface ListWithIteratorInterface<T> extends ListInterface<T>, 
                                                      Iterable<T>
{
    public Iterator<T> getIterator();           
} // end ListWithIteratorInterface
