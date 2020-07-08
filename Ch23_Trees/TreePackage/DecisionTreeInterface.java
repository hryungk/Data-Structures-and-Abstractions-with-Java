package TreePackage;
/**
   An interface for a decision tree.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public interface DecisionTreeInterface<T> extends BinaryTreeInterface<T>
{
    /** Gets the data in the current node.
        @return  The data object in the current node, or
                 null if the current node is null. */
    public T getCurrentData();

    /** Sets the data in the current node.
        Precondition: The current node is not null. 
        @param newData  The new data object. */
    public void setCurrentData(T newData);

    /** Sets the data in the children of the current node,
        creating them if they do not exist.
        Precondition: The current node is not null. 
        @param responseForNo   The new data object for the left child.
        @param responseForYes  The new data object for the right child. */
    public void setResponses(T responseForNo, T responseForYes);

    /** Sees whether the current node contains an answer.
       @return  True if the current node is a leaf, or
                false if it is a nonleaf. */
    public boolean isAnswer();

    /** Sets the current node to its left child.
        If the child does not exist, sets the current node to null. */
    public void advanceToNo();

    /** Sets the current node to its right child.
        If the child does not exist, sets the current node to null. */
    public void advanceToYes();

    /** Sets the current node to the root of the tree. */
    public void resetCurrentNode();
} // end DecisionTreeInterface
