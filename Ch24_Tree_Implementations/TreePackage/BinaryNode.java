package TreePackage;
/**
   A class that represents nodes in a binary tree.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
class BinaryNode<T>
{
    private T             data;
    private BinaryNode<T> leftChild;  // Reference to left child
    private BinaryNode<T> rightChild; // Reference to right child

    public BinaryNode()
    {
       this(null); // Call next constructor
    } // end default constructor

    public BinaryNode(T dataPortion)
    {
       this(dataPortion, null, null); // Call next constructor
    } // end constructor

    public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild,
                                     BinaryNode<T> newRightChild)
    {
       data = dataPortion;
       leftChild = newLeftChild;
       rightChild = newRightChild;
    } // end constructor

    /** Retrieves the data portion of this node.
        @return  The object in the data portion of the node. */
    public T getData()
    {
       return data;
    } // end getData

    /** Sets the data portion of this node.
        @param newData  The data object. */
    public void setData(T newData)
    {
       data = newData;
    } // end setData

    /** Retrieves the left child of this node.
        @return  A reference to this node's left child. */
    public BinaryNode<T> getLeftChild()
    {
       return leftChild;
    } // end getLeftChild

    /** Sets this node’s left child to a given node.
        @param newLeftChild  A node that will be the left child. */
    public void setLeftChild(BinaryNode<T> newLeftChild)
    {
       leftChild = newLeftChild;
    } // end setLeftChild

    /** Detects whether this node has a left child.
        @return  True if the node has a left child. */
    public boolean hasLeftChild()
    {
       return leftChild != null;
    } // end hasLeftChild

    /** Retrieves the right child of this node.
        @return  A reference to this node's right child. */
    public BinaryNode<T> getRightChild()
    {
       return rightChild;
    } // end getRightChild

    /** Sets this node’s right child to a given node.
        @param newRightChild  A node that will be the right child. */
    public void setRightChild(BinaryNode<T> newRightChild)
    {
       rightChild = newRightChild;
    } // end setRightChild

    /** Detects whether this node has a right child.
        @return  True if the node has a right child. */
    public boolean hasRightChild()
    {
       return rightChild != null;
    } // end hasRightChild
    
    /** Detects whether this node is a leaf.
     @return  True if the node is a leaf. */
    public boolean isLeaf()
    {
       return (leftChild == null) && (rightChild == null);
    } // end isLeaf

    /** Counts the nodes in the subtree rooted at this node.
        @return  The number of nodes in the subtree rooted at this node. */
    public int getNumberOfNodes()
    {
        int leftNumber = 0;
        int rightNumber = 0;
        if (leftChild != null)
            leftNumber = leftChild.getNumberOfNodes();
        if (rightChild != null)
            rightNumber = rightChild.getNumberOfNodes();
        return 1 + leftNumber + rightNumber;
    } // end getNumberOfNodes

    /** Computes the height of the subtree rooted at this node.
        @return  The height of the subtree rooted at this node. */
    public int getHeight()
    {
        return getHeight(this);
    } // end getHeight
    
    private int getHeight(BinaryNode<T> node)
    {
        int height = 0;
        
        if (node != null)
            height = 1 + Math.max(getHeight(node.getLeftChild()), 
                                  getHeight(node.getRightChild()));
        return height;
    } // end getHeight

    /** Copies the subtree rooted at this node.
        @return  The root of a copy of the subtree rooted at this node. */
    public BinaryNode<T> copy()
    {
        BinaryNode<T> newRoot = new BinaryNode<>(data);
        if (leftChild != null)
            newRoot.setLeftChild(leftChild.copy());
        if (rightChild != null)
            newRoot.setRightChild(rightChild.copy());
        return newRoot;
    } // end copy
} // end BinaryNode
