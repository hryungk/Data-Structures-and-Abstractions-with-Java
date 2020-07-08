package GraphPackage;
import java.util.Iterator;
/**
   An interface for a vertex in a graph.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public interface VertexInterface<T>
{
    /** Gets this vertex's label.
        @return  The object that labels the vertex. */
    public T getLabel();

    /** Marks this vertex as visited. */
    public void visit();

    /** Removes this vertex's visited mark. */
    public void unvisit();

    /** Sees whether the vertex is marked as visited.
        @return  True if the vertex is visited. */
    public boolean isVisited();

    /** Connects this vertex and a given vertex with a weighted edge.
        The two vertices cannot be the same, and must not already
        have this edge between them. In a directed graph, the edge 
        points toward the given vertex.
        @param endVertex   A vertex in the graph that ends the edge.
        @param edgeWeight  A real-valued edge weight, if any.
        @return  True if the edge is added, or false if not. */
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight);

    /** Connects this vertex and a given vertex with an unweighted 
        edge. The two vertices cannot be the same, and must not 
        already have this edge between them. In a directed graph, 
        the edge points toward the given vertex.
        @param endVertex   A vertex in the graph that ends the edge.
        @return  True if the edge is added, or false if not. */
    public boolean connect(VertexInterface<T> endVertex);

    /** Creates an iterator of this vertex's neighbors by following 
        all edges that begin at this vertex.
        @return  An iterator of the neighboring vertices of this vertex. */
    public Iterator<VertexInterface<T>> getNeighborIterator();

    /** Creates an iterator of the weights of the edges to this 
        vertex's neighbors.
     @return  An iterator of edge weights for edges to neighbors of this
              vertex. */
    public Iterator<Double> getWeightIterator();

    /** Sees whether this vertex has at least one neighbor.
        @return  True if the vertex has a neighbor. */
    public boolean hasNeighbor();

    /** Gets an unvisited neighbor, if any, of this vertex.
        @return  Either a vertex that is an unvisited neighbor or null
                 if no such neighbor exists. */
    public VertexInterface<T> getUnvisitedNeighbor();

    /** Records the previous vertex on a path to this vertex.
        @param predecessor  The vertex previous to this one along a path.  */
    public void setPredecessor(VertexInterface<T> predecessor);

    /** Gets the recorded predecessor of this vertex.
        @return  Either this vertex's predecessor or null if no predecessor
                 was recorded. */
    public VertexInterface<T> getPredecessor();

    /** Sees whether a predecessor was recorded for this vertex.
        @return  True if a predecessor was recorded. */
    public boolean hasPredecessor();

    /** Records the cost of a path to this vertex.
        @param newCost  The cost of the path. */
    public void setCost(double newCost);

    /** Gets the recorded cost of the path to this vertex.
        @return  The cost of the path. */
    public double getCost();
} // end VertexInterface
