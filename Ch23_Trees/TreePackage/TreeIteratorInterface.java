package TreePackage;
import java.util.Iterator;
public interface TreeIteratorInterface<T>
{
    public T getPreorderIterator();
    public int getPostorderIterator();
    public int getInorderIterator();
    public boolean getLevelOrderIterator();
} // end TreeInterface
