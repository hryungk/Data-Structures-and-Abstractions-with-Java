package TreePackage;
import java.util.Iterator;
/**
   A class that implements the ADT binary search tree by extending BinaryTree.
   Recursive version.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class BinarySearchTree<T extends Comparable<? super T>>
             extends BinaryTree<T> implements SearchTreeInterface<T>
{
    public BinarySearchTree()
    {
        super();
    } // end default constructor

    public BinarySearchTree(T rootEntry)
    {
        super();
        setRootNode(new BinaryNode<T>(rootEntry));
    } // end constructor

    // Disable setTree (see Segment 25.6)
    public void setTree(T rootData) 
    {
        throw new UnsupportedOperationException(); 
    } // end setTree    
    
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                                    BinaryTreeInterface<T> rightTree)
    {
        throw new UnsupportedOperationException();
    } // end setTree

/* Implementations of contains, getEntry, add, and remove are here.
   . . .
   Other methods in SearchTreeInterface are inherited from BinaryTree.  */
        
    /** Retrieves a specific entry in this tree.
        @param entry  An object to be found.
        @return  Either the object that was found in the tree or
                 null if no such object exists. */
    public T getEntry(T entry)
    {
        return findEntry(getRootNode(), entry);
    } // end getEntry
    
    private T findEntry(BinaryNode<T> rootNode, T entry)
    {
        T result = null;
        
        if (rootNode != null)
        {
            T rootEntry = rootNode.getData();
            if (entry.equals(rootEntry))
                result = rootEntry;
            else if (entry.compareTo(rootEntry) < 0)
                result = findEntry(rootNode.getLeftChild(), entry);
            else 
                result = findEntry(rootNode.getRightChild(), entry);
        } // end if
        return result;
    } // end findEntry
    
        /** Searches for a specific entry in this tree.
        @param entry  An object to be found.
        @return  True if the object was found in the tree. */
    public boolean contains(T entry)
    {
        return getEntry(entry) != null;
    } // end contains

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
            result = addEntry(getRootNode(), newEntry);
        // or 
        // result = addEntryI(newEntry);
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
                result = addEntry(rootNode.getLeftChild(), newEntry);
            else
                rootNode.setLeftChild(new BinaryNode<>(newEntry));
        }
        else 
        {
            assert comparison > 0;
            if (rootNode.hasRightChild())
                result = addEntry(rootNode.getRightChild(), newEntry);
            else
                rootNode.setRightChild(new BinaryNode<>(newEntry));
        }
        return result;        
    } // end addEntry
    private T addEntryI(T newEntry)
    {
        BinaryNode<T> currentNode = getRootNode();
        assert currentNode != null;
        T result = null;        
        boolean found = false;
        
        while (!found)
        {
            T currentEntry = currentNode.getData();
            int comparison = newEntry.compareTo(currentEntry);
            if (comparison == 0)
            {   // newEntry matches currentEntry;
                // return and replace currentEntry
                found = true;
                result = currentEntry;
                currentNode.setData(newEntry);
            }
            else if (comparison < 0)
            {
                if (currentNode.hasLeftChild())
                    currentNode = currentNode.getLeftChild();
                else
                {
                    found = true;
                    currentNode.setLeftChild(new BinaryNode<>(newEntry));
                } // end if
            }
            else 
            {
                assert comparison > 0;
                if (currentNode.hasRightChild())
                    currentNode = currentNode.getRightChild();
                else
                {
                    found = true;
                    currentNode.setRightChild(new BinaryNode<>(newEntry));
                } // end if
            } // end if
        } // end while
        return result;        
    } // end addEntryI

    
    /** Removes a specific entry from this tree, iteratively.
        @param entry  An object to be removed.
        @return  Either the object that was removed from the tree or
                 null if no such object exists. */
    public T remove(T entry)
    {        
        T result = null;
        // Locate node (and its parent) that contains a match for entry
        NodePair pair = findNode(entry);
        BinaryNode<T> currentNode = pair.getFirst();
        BinaryNode<T> parentNode = pair.getSecond();
        if (currentNode != null)    // Entry is found
        {
            result = currentNode.getData(); // Get entry to be removed
            // Case 1: currentNode has two children
            if (currentNode.hasLeftChild() && currentNode.hasRightChild())
            {
                // Replace entry in currentNode with the entry in another node
                // that has at most one child; that node can be deleted
                // Get node to remove (contains inorder predecessor; has at 
                // most one child) and its parent
                pair = getNodeToRemove(currentNode);
                BinaryNode<T> nodeToRemove = pair.getFirst();
                parentNode = pair.getSecond();
                // Copy entry from nodeToRemove to currentNode
                currentNode.setData(nodeToRemove.getData());
                currentNode = nodeToRemove;
                // Assertion: currentNode is the node to be removed; it has at 
                //            most one child
                // Assertion: Case 1 has been transformed to Case 2                
            } // end if
            // Case 2: currentNode has at most one child; delete it
            removeNode(currentNode, parentNode);
        } // end if
        return result;
    } // end remove
    
    private NodePair findNode(T entry)
    {        
        NodePair result = new NodePair();
        boolean found = false;
        BinaryNode<T> parentNode = null;
        BinaryNode<T> currentNode = getRootNode();                
        T currentData = currentNode.getData();
        int comparison = entry.compareTo(currentData);
        
        while (!found)
        {
            if (comparison == 0)
                found = true;
            else if (comparison < 0)
            {
                parentNode = currentNode;
                currentNode = currentNode.getLeftChild();
                currentData = currentNode.getData();
                comparison = entry.compareTo(currentData);
            }
            else // entry > current node's entry
            {
                parentNode = currentNode;
                currentNode = currentNode.getRightChild();
                currentData = currentNode.getData();
                comparison = entry.compareTo(currentData);
            }
        } // end while
                    
        if (found)
            result = new NodePair(currentNode, parentNode);
        return result;
    } // end findNode
    
    // Returns the node to remove (contains inorder predecessor; has at 
    // most one child) and its parent
    private NodePair getNodeToRemove(BinaryNode<T> currentNode)
    {         
        // Find node with largest entry in left subtree by
        // moving as far right in the subtree as possible        
        BinaryNode<T> leftSubtreeRoot = currentNode.getLeftChild();
        BinaryNode<T> rightChild = leftSubtreeRoot;
        BinaryNode<T> priorNode = currentNode;
        
        while (rightChild.hasRightChild())
        {
            priorNode = rightChild;
            rightChild = rightChild.getRightChild();
        } // end while
        // rightChild contains the inorder predecessor and is the node to
        // remove; priorNode is its parent        
        return new NodePair(rightChild, priorNode);        
    } // end getNodeToRemove
    
    private void removeNode(BinaryNode<T> nodeToRemove, BinaryNode<T> parentNode)
    {
        BinaryNode<T> childNode;
        
        if (nodeToRemove.hasLeftChild())
            childNode = nodeToRemove.getLeftChild();
        else
            childNode = nodeToRemove.getRightChild();
        
        // Assertion: If nodeToRemove is leaf, childNode is null
        assert (nodeToRemove.isLeaf() && childNode == null) ||
                !nodeToRemove.isLeaf();
        
        if (nodeToRemove == getRootNode())
            setRootNode(childNode);
        else if (parentNode.getLeftChild() == nodeToRemove)
            parentNode.setLeftChild(childNode);
        else
            parentNode.setRightChild(childNode);
    } // end removeNode    
    
    private class NodePair
    {
        BinaryNode<T> curNode;
        BinaryNode<T> parNode;
        
        private NodePair()
        {
            this(null, null);
            
        } // end default constructor
        
        private NodePair(BinaryNode<T> firstNode, BinaryNode<T> secondNode)
        {
            curNode = firstNode;
            parNode = secondNode;
        } // end constructor
        
        private BinaryNode<T> getFirst()
        {
            return curNode;
        } // end getFirst
        
        private BinaryNode<T> getSecond()
        {
            return parNode;
        } // end getSecond
        
    } // end NodePair
    
    
    
    
    
    
    /** Removes a specific entry from this tree, recursively.
        @param entry  An object to be removed.
        @return  Either the object that was removed from the tree or
                 null if no such object exists. */
    public T removeR(T entry)
    {        
        ReturnObject oldEntry = new ReturnObject(null);
        BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
        setRootNode(newRoot);
        
        return oldEntry.get();
    } // end removeR
    // Removes an entry from the tree rooted at a given node.
    // rootNode is a reference to the root of a tree.
    // entry is the object to be removed.
    // oldEntry is an object whose data field is null.
    // Returns the root node of the resulting tree; if entry matches
    //         an entry in the tree, oldEntry’s data field is the entry
    //         that was removed from the tree; otherwise it is null.
    private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry, 
                                      ReturnObject oldEntry)
    {        
        if (rootNode != null)            
        {
            T rootData = rootNode.getData();
            int comparison = entry.compareTo(rootData);
            if (comparison == 0)    // entry == root entry
            {
                oldEntry.set(rootNode.getData());
                rootNode = removeFromRoot(rootNode);
            }
            else if (comparison < 0) // entry < root entry
            {
                BinaryNode<T> leftChild = rootNode.getLeftChild();
                BinaryNode<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
                rootNode.setLeftChild(subtreeRoot);
            }
            else    // entry > entry in root
            {
                BinaryNode<T> rightChild = rootNode.getRightChild();
                BinaryNode<T> subtreeRoot = removeEntry(rightChild, entry, oldEntry);
                rootNode.setRightChild(subtreeRoot);
            } // end if
        } // end if
        return rootNode;                
    } // end removeEntry    
    
    // Removes the entry in a given root node of a subtree.
    // rootNode is the root node of the subtree.
    // Returns the root node of the revised subtree.
    private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode)
    {
        // Case 1: rootNode has two children
        if (rootNode.hasLeftChild() && rootNode.hasRightChild())
        {
            // Find node with largest entry in left subtree
            BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
            BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
            // Replace entry in root
            rootNode.setData(largestNode.getData());
            // Remove node with largest entry in left subtree
            rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
        }            
        // Case 2: rootNode has at most one child
        else if (rootNode.hasRightChild())
            rootNode = rootNode.getRightChild();
        else
            rootNode = rootNode.getLeftChild();
        // Assertion: If rootNode was a leaf, it is now null
        return rootNode;        
    } // end removeFromRoot
    
    // Finds the node containing the largest entry in a given tree.
    // rootNode is the root node of the tree.
    // Returns the node containing the largest entry in the tree.
    private BinaryNode<T> findLargest(BinaryNode<T> rootNode)
    {        
        while (rootNode.hasRightChild())
            rootNode = findLargest(rootNode.getRightChild());
        return rootNode;        
    } // end findLargest
    
    // Removes the node containing the largest entry in a given tree. 
    // rootNode is the root node of the tree.
    // Returns the root node of the revised tree.
    private BinaryNode<T> removeLargest(BinaryNode<T> rootNode)
    {
        if (rootNode.hasRightChild())
        {
            BinaryNode<T> rightChild = rootNode.getRightChild();
            rightChild = removeLargest(rightChild);
            rootNode.setRightChild(rightChild);            
        }
        else
            rootNode = rootNode.getLeftChild();
        return rootNode;        
    } // end removeLargest
    
    
    private class ReturnObject
    {
        private T dataField;
        
        private ReturnObject()
        {
            this(null);
        } // end default constructor
        
        private ReturnObject(T entry)
        {
            dataField = entry;
        } // end constructor
        
        private void set(T newData)
        {
            dataField = newData;
        } // end set
        
        private T get()
        {
            return dataField;
        } // end get        
    } // end ReturnObject
} // end BinarySearchTree
