package TreePackage;
/**
   A class that implements the ADT AVL tree by extending BinarySearchTree.
   The remove operation is not supported.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
 */
public class AVLTree<T extends Comparable<? super T>>
             extends BinarySearchTree<T> 
//           implements SearchTreeInterface<T>  // Optional since BinarySearchTree
                                                // implements this interface
{
    public AVLTree()
    {
        super();
    } // end default constructor

    public AVLTree(T rootEntry)
    {
        super(rootEntry);
    } // end constructor

    /** Adds a new entry to this tree, if it does not match an existing 
        object in the tree. Otherwise, replaces the existing object with
        the new entry.
        @param anEntry  An object to be added to the tree.
        @return  Either null if anEntry was not in the tree but has been added, or
                 the existing entry that matched the parameter anEntry
                 and has been replaced in the tree. */
    public T add(T newEntry)
    {
        T result = null;
        if (isEmpty())
            setRootNode(new BinaryNode<>(newEntry));                    
        else
        {
            BinaryNode<T> rootNode = getRootNode();
            result = addEntry(rootNode, newEntry);
            setRootNode(rebalance(rootNode));
        }
        return result;        
    } // end add
    // Adds a new entry to a binary search tree that is not empty.
    // Returns null if newEntry did not exist already in the tree. Otherwise, 
    // returns the tree entry that matched and was replaced by newEntry.
    private T addEntry(BinaryNode<T> rootNode, T newEntry)
    {
        assert rootNode != null;
        T result = null;
        int comparison = newEntry.compareTo(rootNode.getData());
        if (comparison == 0)
        {
            result = rootNode.getData();
            rootNode.setData(newEntry);
        }
        else if (comparison < 0)
        {
            if (rootNode.hasLeftChild())
            {
                BinaryNode<T> leftChild = rootNode.getLeftChild();
                result = addEntry(leftChild, newEntry);
                rootNode.setLeftChild(rebalance(leftChild));
            }
            else
                rootNode.setLeftChild(new BinaryNode<>(newEntry));
        }
        else 
        {
            assert comparison > 0;
            if (rootNode.hasRightChild())
            {
                BinaryNode<T> rightChild = rootNode.getRightChild();
                result = addEntry(rightChild, newEntry);
                rootNode.setRightChild(rebalance(rightChild));
            }
            else
                rootNode.setRightChild(new BinaryNode<>(newEntry));
        }
        return result;        
    } // end addEntry
    
    
    private BinaryNode<T> rebalance(BinaryNode<T> nodeN)
    {
        int heightDifference = getHeightDifference(nodeN);
        if (heightDifference > 1)
        {   // Left subtree is taller by more than 1,
            // so addition was in left subtree
            if (getHeightDifference(nodeN.getLeftChild()) > 0)
                rotateRight(nodeN); // Addition was in left's left
            else
                rotateLeftRight(nodeN); // Addition was in left's right
        }
        else if (heightDifference < -1)
        {   // Right subtree is taller by more than 1,
            // so addition was in right subtree
            if (getHeightDifference(nodeN.getRightChild()) < 0)
                rotateLeft(nodeN);  // Addition was in right's right
            else
                rotateRightLeft(nodeN); /// Addition was in right's left
        } // end if
        // Else nodeN is balanced
        return nodeN;      
    } // end rebalance
    
    private int getHeightDifference(BinaryNode<T> nodeN)
    {
        BinaryNode<T> nodeNLeft = nodeN.getLeftChild();
        BinaryNode<T> nodeNRight = nodeN.getRightChild();
        return nodeNLeft.getHeight() - nodeNRight.getHeight();
    } // end getHeightDifference
    
    // Corrects an imbalance at the node closest to a structural change in the
    // left subtree of the node's left child.
    // nodeN is a node, closest to the newly added leaf, at which an imbalance 
    // occurs and that has a left child.
    private BinaryNode<T> rotateRight(BinaryNode<T> nodeN)
    {
        BinaryNode<T> nodeC = nodeN.getLeftChild();
        nodeN.setLeftChild(nodeC.getRightChild());
        nodeC.setRightChild(nodeN);
        return nodeC;        
    } // end rotateRight
    // Corrects an imbalance at the node closest to a structural change in the
    // right subtree of the node's right child.
    // nodeN is a node, closest to the newly added leaf, at which an imbalance 
    // occurs and that has a right child.
    private BinaryNode<T> rotateLeft(BinaryNode<T> nodeN)
    {
        BinaryNode<T> nodeC = nodeN.getRightChild();
        nodeN.setRightChild(nodeC.getLeftChild());
        nodeC.setLeftChild(nodeN);
        return nodeC;        
    } // end rotateLeft
    
    // Corrects an imbalance at the node closest to a structural
    // change in the left subtree of the node's right child.
    // nodeN is a node, closest to the newly added leaf, at which
    // an imbalance occurs and that has a right child.
    private BinaryNode<T> rotateRightLeft(BinaryNode<T> nodeN)
    {
        BinaryNode<T> nodeC = nodeN.getRightChild();
        nodeN.setRightChild(rotateRight(nodeC));
        return rotateLeft(nodeN);
    } // end rotateRightLeft
    
    // Corrects an imbalance at the node closest to a structural
    // change in the right subtree of the node's left child.
    // nodeN is a node, closest to the newly added leaf, at which
    // an imbalance occurs and that has a left child.
    private BinaryNode<T> rotateLeftRight(BinaryNode<T> nodeN)
    {
        BinaryNode<T> nodeC = nodeN.getLeftChild();
        nodeN.setLeftChild(rotateLeft(nodeC));
        return rotateRight(nodeN);
    } // end rotateLeftRight
} // end AVLTree
