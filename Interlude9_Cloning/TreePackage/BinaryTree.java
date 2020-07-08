package TreePackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import StackAndQueuePackage.*; // Needed by tree iterators
/**
   A class that implements the ADT binary tree.   
   @author Frank M. Carrano
*/
public class BinaryTree<T extends Copyable> implements BinaryTreeInterface<T>
{
    private BinaryNode<T> root;

    public BinaryTree()
    {
        root = null;
    } // end default constructor

    public BinaryTree(T rootData)
    {
        root = new BinaryNode<>(rootData);
    } // end constructor

    public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
    {
        privateSetTree(rootData, leftTree, rightTree);
    } // end constructor 

    public void setTree(T rootData) 
    {
        root = new BinaryNode<>(rootData); 
    } // end setTree
    
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                                   BinaryTreeInterface<T> rightTree)
    {
        privateSetTree(rootData, (BinaryTree<T>)leftTree,
                                (BinaryTree<T>)rightTree);
    } // end setTree
    
    public T getRootData()
    {
        if (isEmpty())
            throw new EmptyTreeException();
        else
            return root.getData();
    } // end getRootData
    
    public int getHeight()
    {
        return root.getHeight();
    } // end getHeight
    
    public int getNumberOfNodes()
    {
        return root.getNumberOfNodes();
    } // end getNumberOfNodes
    
    public boolean isEmpty()
    {
        return root == null;
    } // end isEmpty
    
    public void clear()
    {
        root = null;
    } // end clear
    
    protected void setRootData(T rootData) 
    {
        root.setData(rootData);
    } // end setRootData
    
    protected void setRootNode(BinaryNode<T> rootNode) 
    {
        root = rootNode;
    } // end setRootNode
    protected BinaryNode<T> getRootNode() 
    {
        return root;
    } // end getRootNode
    
    
    public Iterator<T> getPreorderIterator()
    {
        return new PreorderIterator();
    } // end getPreorderIterator
    
    public Iterator<T> getPostorderIterator()
    {
        return new PostorderIterator();
    } // end getPostorderIterator
    
    public Iterator<T> getInorderIterator()
    {
        return new InorderIterator();
    } // end getInorderIterator
    
    public Iterator<T> getLevelOrderIterator()
    {
        return new LevelOrderIterator();
    } // end getLevelOrderIterator

    private void privateSetTree(T rootData, BinaryTree<T> leftTree,
                                            BinaryTree<T> rightTree)
    {
        // < FIRST DRAFT - See Segments 25.4 - 25.7 for improvements. >
        root = new BinaryNode<T>(rootData);

        if (leftTree != null && !leftTree.isEmpty()) 
            root.setLeftChild(leftTree.root);
        if (rightTree != null && !rightTree.isEmpty())
            if (rightTree != leftTree)
                root.setRightChild(rightTree.root);
            else
                root.setRightChild(rightTree.root.copy());
        if (leftTree != null && leftTree != this)
            leftTree.clear();
        if (rightTree != null && rightTree != this)
            rightTree.clear();
    } // end privateSetTree

    
    //////////////////////////////////////////////////////////////////////////////
    //                          Iterator inner classes                          //
    //////////////////////////////////////////////////////////////////////////////    
    public void iterativePreorderTraverse() 
    {
        StackInterface<BinaryNode<T>> nodeStack = new LinkedStack<>(); 
        BinaryNode<T> currentNode = root;
        while (!nodeStack.isEmpty() || (currentNode != null)) 
        {   
            if (currentNode != null)
                nodeStack.push(currentNode);
            // Visit leftmost node, then traverse its right subtree
            if (!nodeStack.isEmpty()) 
            {
                BinaryNode<T> nextNode = nodeStack.pop();
                assert nextNode != null; // Since nodeStack was not empty
                                       // before the pop
                System.out.println(nextNode.getData());
                currentNode = nextNode.getRightChild();
                if (currentNode != null)
                    nodeStack.push(currentNode);                
                currentNode = nextNode.getLeftChild();
            } // end if
        } // end while
     } // end iterativePreorderTraverse
    
    
    private class PreorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        public PreorderIterator()
        {
           nodeStack = new LinkedStack<>();
           currentNode = root;
        } // end default constructor

        public boolean hasNext() 
        {
           return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public T next()
        {
            BinaryNode<T> nextNode = null;

            if (currentNode != null)
                nodeStack.push(currentNode);
            // Visit leftmost node, then traverse its right subtree
            if (!nodeStack.isEmpty()) 
            {
                nextNode = nodeStack.pop();                
                currentNode = nextNode.getRightChild();
                if (currentNode != null)
                    nodeStack.push(currentNode);                
                currentNode = nextNode.getLeftChild();
            } // end if
            else
               throw new NoSuchElementException();

            return nextNode.getData(); 
        } // end next

        public void remove()
        {
           throw new UnsupportedOperationException();
        } // end remove
    } // end PreorderIterator
    
    
    public void iterativePostorderTraverse() 
    {
        StackInterface<BinaryNode<T>> nodeStack = new LinkedStack<>(); 
        BinaryNode<T> currentNode = root;
        BinaryNode<T> nextNode = null;
        BinaryNode<T> prev = null;
        
        nodeStack.push(currentNode);
        while (!nodeStack.isEmpty() || (currentNode != null)) 
        {
            currentNode = nodeStack.peek();
            
            // Go down the tree in search of a leaf an if so process it  
            // and pop stack otherwise move down 
            if (prev == null || prev.getLeftChild() == currentNode ||  
                                        prev.getRightChild() == currentNode)  
            { 
                if (currentNode.getLeftChild() != null)
                    nodeStack.push(currentNode.getLeftChild());
                else if (currentNode.getRightChild() != null)
                    nodeStack.push(currentNode.getRightChild());
                else
                    nextNode = nodeStack.pop();
            
            // Go up the tree from left node, if the child is right push it onto 
            // stack otherwise process parent and pop stack 
            }  
            else if (currentNode.getLeftChild() == prev)  
            { 
                if (currentNode.getRightChild() != null) 
                    nodeStack.push(currentNode.getRightChild()); 
                else 
                    nextNode = nodeStack.pop(); 
                    
                // go up the tree from right node and after coming back 
                // from right node process parent and pop stack
            }  
            else if (currentNode.getRightChild() == prev)  
                nextNode = nodeStack.pop(); 
   
            prev = currentNode; 
        } // end while
    } // end iterativePostorderTraverse
    private class PostorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;
        private BinaryNode<T> prev; 
        public PostorderIterator()
        {
            nodeStack = new LinkedStack<>();
            currentNode = root;
            prev = null;
            nodeStack.push(currentNode);
        } // end default constructor

        public boolean hasNext() 
        {
            return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public T next()
        {
            BinaryNode<T> nextNode = null;                   
                        
            currentNode = nodeStack.peek();
            
            // Go down the tree in search of a leaf an if so process it  
            // and pop stack otherwise move down 
            if (prev == null || prev.getLeftChild() == currentNode ||  
                                        prev.getRightChild() == currentNode)  
            { 
                if (currentNode.getLeftChild() != null)
                    nodeStack.push(currentNode.getLeftChild());
                else if (currentNode.getRightChild() != null)
                    nodeStack.push(currentNode.getRightChild());
                else
                    nextNode = nodeStack.pop();
            
            // Go up the tree from left node, if the child is right push it onto 
            // stack otherwise process parent and pop stack 
            }  
            else if (currentNode.getLeftChild() == prev)  
            { 
                if (currentNode.getRightChild() != null) 
                    nodeStack.push(currentNode.getRightChild()); 
                else 
                    nextNode = nodeStack.pop(); 
                    
                // go up the tree from right node and after coming back 
                // from right node process parent and pop stack
            }  
            else if (currentNode.getRightChild() == prev)  
                nextNode = nodeStack.pop();    
            prev = currentNode; 
            return nextNode.getData(); 
        } // end next

        public void remove()
        {
           throw new UnsupportedOperationException();
        } // end remove
    } // end PostorderIterator
    
    public void iterativeInorderTraverse() 
    {
        StackInterface<BinaryNode<T>> nodeStack = new LinkedStack<>(); 
        BinaryNode<T> currentNode = root;
        while (!nodeStack.isEmpty() || (currentNode != null)) 
        {
            // Find getLeftChild()most node with no getLeftChild() child
            while (currentNode != null) 
            {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            } // end while
            // Visit getLeftChild()most node, then traverse its getRightChild() subtree
            if (!nodeStack.isEmpty()) 
            {
                BinaryNode<T> nextNode = nodeStack.pop();
                assert nextNode != null; // Since nodeStack was not empty
                                       // before the pop
                System.out.println(nextNode.getData());
                currentNode = nextNode.getRightChild();
            } // end if
        } // end while
     } // end iterativeInorderTraverse
    
    // @author Frank M. Carrano, Timothy M. Henry
    // @version 5.0
    private class InorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        public InorderIterator()
        {
           nodeStack = new LinkedStack<>();
           currentNode = root;
        } // end default constructor

        public boolean hasNext() 
        {
           return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public T next()
        {
           BinaryNode<T> nextNode = null;

           // Find getLeftChild()most node with no getLeftChild() child
           while (currentNode != null)
           {
              nodeStack.push(currentNode);
              currentNode = currentNode.getLeftChild();
           } // end while

           // Get getLeftChild()most node, then move to its getRightChild() subtree
           if (!nodeStack.isEmpty())
           {
              nextNode = nodeStack.pop();
              // Assertion: nextNode != null, since nodeStack was not empty
              // before the pop
              currentNode = nextNode.getRightChild();
           }
           else
              throw new NoSuchElementException();

           return nextNode.getData(); 
        } // end next

        public void remove()
        {
           throw new UnsupportedOperationException();
        } // end remove
    } // end InorderIterator
    
    
    
    public void iterativeLevelOrderTraverse() 
    {
        QueueInterface<BinaryNode<T>> nodeQueue = new LinkedQueue<>(); 
        BinaryNode<T> currentNode = root;
        BinaryNode<T> prevNode = null;
        while (!nodeQueue.isEmpty() || (currentNode != null)) 
        {
            if (currentNode != null)
                nodeQueue.enqueue(currentNode);            
            if (prevNode != null && prevNode.getRightChild() != null)
                nodeQueue.enqueue(prevNode.getRightChild());  
            // Visit getLeftChild()most node, then traverse its getRightChild() subtree
            if (!nodeQueue.isEmpty()) 
            {
                BinaryNode<T> nextNode = nodeQueue.dequeue();
                assert nextNode != null; // Since nodeQueue was not empty
                                       // before the pop
                System.out.println(nextNode.getData());
                prevNode = nextNode;
                currentNode = nextNode.getLeftChild();
            } // end if
        } // end while
     } // end iterativeLevelOrderTraverse
    
    private class LevelOrderIterator implements Iterator<T>
    {
        private QueueInterface<BinaryNode<T>> nodeQueue;
        private BinaryNode<T> currentNode;
        private BinaryNode<T> prevNode;
        public LevelOrderIterator()
        {
           nodeQueue = new LinkedQueue<>();
           currentNode = root;
           prevNode = null;
        } // end default constructor

        public boolean hasNext() 
        {
           return !nodeQueue.isEmpty() || (currentNode != null);
        } // end hasNext

        public T next()
        {
           BinaryNode<T> nextNode = null;          

           if (currentNode != null)
                nodeQueue.enqueue(currentNode);            
            if (prevNode != null && prevNode.getRightChild() != null)
                nodeQueue.enqueue(prevNode.getRightChild());  
            // Visit getLeftChild()most node, then traverse its getRightChild() subtree
            if (!nodeQueue.isEmpty()) 
            {
                nextNode = nodeQueue.dequeue();
                assert nextNode != null; // Since nodeQueue was not empty
                                       // before the pop
                System.out.println(nextNode.getData());
                prevNode = nextNode;
                currentNode = nextNode.getLeftChild();
            } 
            else
                throw new NoSuchElementException();

           return nextNode.getData(); 
        } // end next

        public void remove()
        {
           throw new UnsupportedOperationException();
        } // end remove
    } // end LevelOrderIterator
} // end BinaryTree
