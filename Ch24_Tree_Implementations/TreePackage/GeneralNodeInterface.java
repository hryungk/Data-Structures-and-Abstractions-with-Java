// @author Frank M. Carrano, Timothy M. Henry
// @version 5.0
package TreePackage;
import java.util.Iterator;
/**
    An interface for a node in a general tree.

    @author Frank M. Carrano
    @author Timothy M. Henry
    @version 5.0
*/
interface GeneralNodeInterface<T>
{
    public T getData();
    public void setData(T newData);
    public boolean isLeaf();
    public Iterator<GeneralNodeInterface<T>> getChildrenIterator();
    public void addChild(GeneralNodeInterface<T> newChild);
} // end GeneralNodeInterface
