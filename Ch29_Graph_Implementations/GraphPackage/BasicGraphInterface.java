package GraphPackage;
/**
   An interface of methods providing basic operations for directed
   and undirected graphs that are either weighted or unweighted.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public interface BasicGraphInterface<T>
{
    /** Adds a given vertex to this graph.
        @param vertexLabel  An object that labels the new vertex and is
                            distinct from the labels of current vertices.
        @return  True if the vertex is added, or false if not. */
    public boolean addVertex(T vertexLabel);

    /** Adds a weighted edge between two given distinct vertices that 
        are currently in this graph. The desired edge must not already 
        be in the graph. In a directed graph, the edge points toward
        the second vertex given.
        @param begin  An object that labels the origin vertex of the edge.
        @param end    An object, distinct from begin, that labels the end
                      vertex of the edge.
        @param edgeWeight  The real value of the edge's weight.
        @return  True if the edge is added, or false if not. */
    public boolean addEdge(T begin, T end, double edgeWeight);

    /** Adds an unweighted edge between two given distinct vertices 
        that are currently in this graph. The desired edge must not
        already be in the graph. In a directed graph, the edge points 
        toward the second vertex given.
        @param begin  An object that labels the origin vertex of the edge.
        @param end    An object, distinct from begin, that labels the end
                      vertex of the edge.
        @return  True if the edge is added, or false if not. */
    public boolean addEdge(T begin, T end);

    /** Sees whether an edge exists between two given vertices.
        @param begin  An object that labels the origin vertex of the edge.
        @param end    An object that labels the end vertex of the edge.
        @return  True if an edge exists. */
    public boolean hasEdge(T begin, T end);

    /** Sees whether this graph is empty.
        @return  True if the graph is empty. */
    public boolean isEmpty();

    /** Gets the number of vertices in this graph.
        @return  The number of vertices in the graph. */
    public int getNumberOfVertices();

    /** Gets the number of edges in this graph.
       @return  The number of edges in the graph. */
    public int getNumberOfEdges();

    /** Removes all vertices and edges from this graph resulting in an empty graph. */
    public void clear();
} // end BasicGraphInterface
