package GraphPackage;
import ADTPackage.*;
/** Test ADT Graph implementation
 * @author HRK
 */
public class TestGraph 
{
    public static void main(String[] args) 
    {
        VertexInterface<String> vertexA = new Vertex<>("A");
        VertexInterface<String> vertexB = new Vertex<>("B");
        VertexInterface<String> vertexC = new Vertex<>("C");
        
        vertexA.connect(vertexB, 2.0);
        vertexB.connect(vertexC, 3.0);
        vertexC.connect(vertexA, 4.0);
        vertexA.connect(vertexA, 5.0);
        
        
        // Q4. Create an instance of the class DirectedGraph for the graph described in Q3.
        DirectedGraph<String> myGraph = new DirectedGraph<>();
        myGraph.addVertex("A");
        myGraph.addVertex("B");
        myGraph.addVertex("C");
        
        myGraph.addEdge("A", "B", 2.0);
        myGraph.addEdge("B", "C", 3.0);
        myGraph.addEdge("C", "A", 4.0);
        myGraph.addEdge("A", "C", 5.0);
        
        // Q5. Display the vertices in a breadht-first traversal of graph in Q4.
        System.out.print("Breadth-first traversal: ");
        QueueInterface<String> bft = myGraph.getBreadthFirstTraversal("A");
        while (!bft.isEmpty())
            System.out.print(bft.dequeue() + " ");
        System.out.println();
        
        // Q6. Display the vertices in teh shortest path from vertex A to C 
        //     and length of this path.
        StackInterface<String> pathStack = new LinkedStack<>();
        int pathLength = myGraph.getShortestPath("A", "C", pathStack);
        System.out.println("The shortest path from A to C has length " + 
                pathLength + " and passes through the following vertices:");
        while (!pathStack.isEmpty())
            System.out.print(pathStack.pop() + " ");        
        System.out.println();        
    }
    
}
