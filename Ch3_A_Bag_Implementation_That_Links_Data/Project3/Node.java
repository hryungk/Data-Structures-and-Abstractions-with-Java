// @author Frank M. Carrano, Timothy M. Henry
// @version 5.0
package BagPackage;
class Node<T>
{
    private T       data;
    private Node<T> next;

    Node(T dataPortion) // The constructor’s name is Node, not Node<T>
    {
       this(dataPortion, null);
    } // end constructor

    Node(T dataPortion, Node<T> nextNode)
    {
       data = dataPortion;
       next = nextNode;
    } // end constructor

    T getData()
    {
       return data;
    } // end getData

    void setData(T newData)
    {
       data = newData;
    } // end setData

    Node<T> getNextNode()
    {
       return next;
    } // end getNextNode

    void setNextNode(Node<T> nextNode)
    {
       next = nextNode;
    } // end setNextNode
} // end Node
