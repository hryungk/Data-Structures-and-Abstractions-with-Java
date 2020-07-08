package GraphPackage;
import java.util.Iterator;
import ADTPackage.*; // Classes that implement various ADTs
/**
   A class that implements the ADT directed graph.
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class DirectedGraph<T> implements GraphInterface<T>
{
    private DictionaryInterface<T, VertexInterface<T>> vertices;
    private int edgeCount;

    public DirectedGraph()
    {
        vertices = new LinkedDictionary<>();
        edgeCount = 0;
    } // end default constructor

    
    /** Adds a given vertex to this graph.
        @param vertexLabel  An object that labels the new vertex and is
                            distinct from the labels of current vertices.
        @return  True if the vertex is added, or false if not. */
    public boolean addVertex(T vertexLabel)
    {
        VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex(vertexLabel));
        return  addOutcome == null;
    } // end addVertex

    /** Adds a weighted edge between two given distinct vertices that 
        are currently in this graph. The desired edge must not already 
        be in the graph. In a directed graph, the edge points toward
        the second vertex given.
        @param begin  An object that labels the origin vertex of the edge.
        @param end    An object, distinct from begin, that labels the end
                      vertex of the edge.
        @param edgeWeight  The real value of the edge's weight.
        @return  True if the edge is added, or false if not. */
    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        boolean result = false;
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        if ((originVertex != null) && (endVertex != null))
            result = originVertex.connect(endVertex, edgeWeight);        
        if (result)
            edgeCount++;
        
        return result;
    } // end addEdge with weight

    /** Adds an unweighted edge between two given distinct vertices 
        that are currently in this graph. The desired edge must not
        already be in the graph. In a directed graph, the edge points 
        toward the second vertex given.
        @param begin  An object that labels the origin vertex of the edge.
        @param end    An object, distinct from begin, that labels the end
                      vertex of the edge.
        @return  True if the edge is added, or false if not. */
    public boolean addEdge(T begin, T end)
    {
        return addEdge(begin, end, 0);
    } // end addEdge without weight

    /** Sees whether an edge exists between two given vertices.
        @param begin  An object that labels the origin vertex of the edge.
        @param end    An object that labels the end vertex of the edge.
        @return  True if an edge exists. */
    public boolean hasEdge(T begin, T end)
    {
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        
        if ((beginVertex != null) && (endVertex != null))
        {
            Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();             
            while (!found && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (nextNeighbor.equals(endVertex))
                    found = true;
            } // end while
        } // end if
        return found;
    } // end hasEdge

    /** Sees whether this graph is empty.
        @return  True if the graph is empty. */
    public boolean isEmpty()
    {
        return vertices.isEmpty();
    } // end empty

    /** Gets the number of vertices in this graph.
        @return  The number of vertices in the graph. */
    public int getNumberOfVertices()
    {
        return vertices.getSize();
    } // end getNumberOfVertices

    /** Gets the number of edges in this graph.
       @return  The number of edges in the graph. */
    public int getNumberOfEdges()
    {
        return edgeCount;
    } // end getNumberOfEdges

    /** Removes all vertices and edges from this graph resulting in an empty graph. */
    public void clear()
    {
        vertices.clear();
        edgeCount = 0;
    } // end clear
    
    /** Performs a breadth-first traversal of this graph.
        @param origin  An object that labels the origin vertex of the traversal.
        @return  A queue of labels of the vertices in the traversal, with
                 the label of the origin vertex at the queue's front. */
    public QueueInterface<T> getBreadthFirstTraversal(T origin)
    {
        resetVertices();        
        QueueInterface<T> traversalOrder = new LinkedQueue<>();            
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        
        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin);     // Enqueue vertex label
        vertexQueue.enqueue(originVertex);  // Enqueue vertex
        while(!vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors = 
                                        frontVertex.getNeighborIterator();
            while(neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited())
                {
                    nextNeighbor.visit();
                    traversalOrder.enqueue(nextNeighbor.getLabel());
                    vertexQueue.enqueue(nextNeighbor);
                } // end if                
            } // end while
        } // end while        
        return traversalOrder;
    } // end getBreadthFirstTraversal

    /** Performs a depth-first traversal of this graph.
        @param origin  An object that labels the origin vertex of the traversal.
        @return  A queue of labels of the vertices in the traversal, with
                 the label of the origin vertex at the queue's front. */
    public QueueInterface<T> getDepthFirstTraversal(T origin)
    {
        resetVertices();        
        QueueInterface<T> traversalOrder = new LinkedQueue<>();            
        StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();
        
        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin);     // Enqueue vertex label
        vertexStack.push(originVertex);  // Enqueue vertex
        while(!vertexStack.isEmpty())
        {
            VertexInterface<T> topVertex = vertexStack.peek();
            VertexInterface<T> unvisitedNeighbor = topVertex.getUnvisitedNeighbor();
            if (unvisitedNeighbor != null)
            {
                VertexInterface<T> nextNeighbor = unvisitedNeighbor;
                nextNeighbor.visit();
                traversalOrder.enqueue(nextNeighbor.getLabel());
                vertexStack.push(nextNeighbor);                
            }
            else
                vertexStack.pop();
        } // end while        
        return traversalOrder;
    } // end getDepthFirstTraversal

    /** Performs a topological sort of the vertices in this graph without cycles.
        @return  A stack of vertex labels in topological order, beginning
                 with the stack's top. */
    public StackInterface<T> getTopologicalOrder()
    {
        resetVertices();     
        StackInterface<T> vertexStack = new LinkedStack<>();
        int numberOfVertices = getNumberOfVertices();                
        
        for (int counter = 1; counter <= numberOfVertices; counter++)
        {
            VertexInterface<T> nextVertex = findNextUnvisited();
            nextVertex.visit();
            vertexStack.push(nextVertex.getLabel());
        } // end for
        return vertexStack;
    } // end getTopologicalOrder
    
    private VertexInterface<T> findNextUnvisited()
    {
        VertexInterface<T> result = null;
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        boolean found = false;
        while (vertexIterator.hasNext() && !found)
        {
            VertexInterface<T> nextVertex = vertexIterator.next();
            if (!nextVertex.isVisited())    // unvisited vertex
            {
                if (!nextVertex.hasNeighbor())  // vertex without successor
                {
                    found = true;
                    result = nextVertex;
                }
                else    // vertex with neighbors (successors)
                {
                    Iterator<VertexInterface<T>> neighbors = nextVertex.getNeighborIterator();
                    boolean allVisited = true;
                    while (neighbors.hasNext() && allVisited)
                    {                        
                        VertexInterface<T> nextNeighbor = neighbors.next();
                        if (!nextNeighbor.isVisited())
                            allVisited = false;
                    } // end while
                    found = allVisited;
                } // end if
            } // end if
        } // end while
        
        return result;
    } // end findNextUnvisited

    /** Finds the shortest-length path between two given vertices in this graph.
        @param begin  An object that labels the path's origin vertex.
        @param end    An object that labels the path's destination vertex.
        @param path   A stack of labels that is empty initially;
                      at the completion of the method, this stack contains
                      the labels of the vertices along the shortest path;
                      the label of the origin vertex is at the top, and
                      the label of the destination vertex is at the bottom       
        @return  The length of the shortest path. */
    public int getShortestPath(T begin, T end, StackInterface<T> path)
    {
        resetVertices();
        boolean done = false;
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        
        originVertex.visit();
        // Assertion: resetVertices() has executed setCost(0)
        // and setPredecessor(null) for originVertex
        vertexQueue.enqueue(originVertex);
        while(!done && !vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors = 
                                        frontVertex.getNeighborIterator();
            while(!done && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited())
                {
                    nextNeighbor.visit();
                    nextNeighbor.setCost(1 + frontVertex.getCost());
                    nextNeighbor.setPredecessor(frontVertex);
                    vertexQueue.enqueue(nextNeighbor);
                } // end if
                if (nextNeighbor.equals(endVertex))
                    done = true;
            } // end while            
        } // end while
        // Traversal ends; construct shortest path
        int pathLength = (int) endVertex.getCost();
        path.push(endVertex.getLabel());
        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while
        return pathLength;
    } // end getShortestPath

    /** Finds the least-cost path between two given vertices in this graph.
        @param begin  An object that labels the path's origin vertex.
        @param end    An object that labels the path's destination vertex.
        @param path   A stack of labels that is empty initially;
                      at the completion of the method, this stack contains
                      the labels of the vertices along the cheapest path;
                      the label of the origin vertex is at the top, and
                      the label of the destination vertex is at the bottom       
        @return  The cost of the cheapest path. */
    public double getCheapestPath(T begin, T end, StackInterface<T> path)
    {
        resetVertices();
        boolean done = false;
        PriorityQueueInterface<EntryPQ> priorityQueue = new PriorityQueue<>();
                
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        priorityQueue.add(new EntryPQ(originVertex, 0, null));
        
        while(!done && !priorityQueue.isEmpty())
        {
            EntryPQ frontEntry = priorityQueue.remove();
            VertexInterface<T> frontVertex = frontEntry.getVertex();
            if (!frontVertex.isVisited())
            {
                frontVertex.visit();
                frontVertex.setCost(frontEntry.getWeight());
                frontVertex.setPredecessor(frontEntry.getPrevious());
                if (frontVertex.equals(endVertex))
                    done = true;
                else
                {
                    Iterator<VertexInterface<T>> neighbors = 
                                                frontVertex.getNeighborIterator();
                    while (!done && neighbors.hasNext())
                    {
                        VertexInterface<T> nextNeighbor = neighbors.next();
                        double weightOfEdgeToNeighbor = nextNeighbor.getCost();
                        if (!nextNeighbor.isVisited())
                        {
                            double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
                            priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
                        } // end if
                    } // end while
                } // end if
            } // end if           
        } // end while
        // Traversal ends; construct shortest path
        double pathCost = endVertex.getCost();
        path.push(endVertex.getLabel());
        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while
        return pathCost;
    } // end getCheapestPath
    
    
    protected void resetVertices()
    {
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext())
        {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);            
        } // end while
    } // end resetVertices
    
    private class EntryPQ implements Comparable<EntryPQ>
    {
        VertexInterface<T> currentVertex;
        double costToHere; // cost of the path to this vertex from origin vertex
        VertexInterface<T> previousVertex;  // previous vertex on that path
        
        public EntryPQ(VertexInterface<T> newVertex, double totalCost, VertexInterface<T> vertexFrom)
        {
            currentVertex = newVertex;
            costToHere = totalCost;
            previousVertex = vertexFrom;    
        } // end  constructor
        
        public double getWeight()
        {
            return costToHere;
        } // end getCost
        
        public VertexInterface<T> getVertex()
        {
            return currentVertex;
        } // end getVertex
        
        public VertexInterface<T> getPrevious()
        {
            return previousVertex;
        } // end getPrevious
        
        public int compareTo(EntryPQ other)
        {
            int result = (int) (costToHere - other.getWeight());
            return result;
        } // end compareTo
    } // end EntryPQ
} // end DirectedGraph
