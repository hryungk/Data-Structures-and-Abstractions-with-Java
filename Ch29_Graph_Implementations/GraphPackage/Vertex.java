package GraphPackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import ADTPackage.*; // Classes that implement various ADTs
/**
 A class of vertices for a graph.
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
 */
class Vertex<T> implements VertexInterface<T>
{
    private T label;
    private ListWithIteratorInterface<Edge> edgeList; // Edges to neighbors
    private boolean visited;                          // True if visited
    private VertexInterface<T> previousVertex;        // On path to this vertex
    private double cost;                              // Of path to this vertex

    public Vertex(T vertexLabel)
    {
        label = vertexLabel;
        edgeList = new LinkedListWithIterator<>();
        visited = false;
        previousVertex = null;
        cost = 0;
    } // end constructor

    /** Gets this vertex's label.
        @return  The object that labels the vertex. */
    public T getLabel()
    {
        return label;
    } // end getLabel

    /** Marks this vertex as visited. */
    public void visit()
    {
        visited = true;
    } // end visit

    /** Removes this vertex's visited mark. */
    public void unvisit()
    {
        visited = false;
    } // end unvisit

    /** Sees whether the vertex is marked as visited.
        @return  True if the vertex is visited. */
    public boolean isVisited()
    {
        return visited == true;
    } // end isVisited

    /** Connects this vertex and a given vertex with a weighted edge.
        The two vertices cannot be the same, and must not already
        have this edge between them. In a directed graph, the edge 
        points toward the given vertex.
        @param endVertex   A vertex in the graph that ends the edge.
        @param edgeWeight  A real-valued edge weight, if any.
        @return  True if the edge is added, or false if not. */
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
    {
        boolean result = false;
        if (!this.equals(endVertex))
        {   // Vertices are distinct
            Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
            boolean duplicateEdge = false;
            while (!duplicateEdge && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    duplicateEdge = true;
            } // end while
            if (!duplicateEdge)
            {
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            } // end if
        } // end if            
        return result;
    } // end connect with weight

    /** Connects this vertex and a given vertex with an unweighted 
        edge. The two vertices cannot be the same, and must not 
        already have this edge between them. In a directed graph, 
        the edge points toward the given vertex.
        @param endVertex   A vertex in the graph that ends the edge.
        @return  True if the edge is added, or false if not. */
    public boolean connect(VertexInterface<T> endVertex)
    {
        return connect(endVertex, 0);
    } // end connect without weight

    /** Creates an iterator of this vertex's neighbors by following 
        all edges that begin at this vertex.
        @return  An iterator of the neighboring vertices of this vertex. */
    public Iterator<VertexInterface<T>> getNeighborIterator()
    {
        return new NeighborIterator();
    } // end getNeighborIterator

    /** Creates an iterator of the weights of the edges to this 
        vertex's neighbors.
     @return  An iterator of edge weights for edges to neighbors of this
              vertex. */
    public Iterator<Double> getWeightIterator()
    {
        return new WeightIterator();
    } // end getWeightIterator

    /** Sees whether this vertex has at least one neighbor.
        @return  True if the vertex has a neighbor. */
    public boolean hasNeighbor()
    {
        return edgeList.isEmpty();
    } // end hasNeighbor

    /** Gets an unvisited neighbor, if any, of this vertex.
        @return  Either a vertex that is an unvisited neighbor or null
                 if no such neighbor exists. */
    public VertexInterface<T> getUnvisitedNeighbor()
    {
        VertexInterface<T> result = null;
        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while((neighbors.hasNext()) && (result == null))
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
                result = nextNeighbor;
        } // end while        
        return result;
    } // getUnvisitedNeighbor

    /** Records the previous vertex on a path to this vertex.
        @param predecessor  The vertex previous to this one along a path.  */
    public void setPredecessor(VertexInterface<T> predecessor)
    {
        previousVertex = predecessor;
    } // end setPredecessor

    /** Gets the recorded predecessor of this vertex.
        @return  Either this vertex's predecessor or null if no predecessor
                 was recorded. */
    public VertexInterface<T> getPredecessor()
    {
        return previousVertex;
    } // end getPredecessor

    /** Sees whether a predecessor was recorded for this vertex.
        @return  True if a predecessor was recorded. */
    public boolean hasPredecessor()
    {
        return previousVertex != null;
    } // end hasPredecessor

    /** Records the cost of a path to this vertex.
        @param newCost  The cost of the path. */
    public void setCost(double newCost)
    {
        cost = newCost;
    } // end setCost

    /** Gets the recorded cost of the path to this vertex.
        @return  The cost of the path. */
    public double getCost()
    {
        return cost;
    } // end getCost
    
    public boolean equals(Object other)
    {
        boolean result;
        if ((other == null) || (getClass() != other.getClass()))
            result = false;
        else
        {   // This cast is safe within this else clause
            @SuppressWarnings("unchecked")
            Vertex<T> otherVertex = (Vertex<T>) other;
            result = label.equals(otherVertex.label);
        } // end if
        return result;
    } // end equals
    
    private class NeighborIterator implements Iterator<VertexInterface<T>>
    {
        private Iterator<Edge> edges;
        
        private NeighborIterator()
        {            
            edges = edgeList.getIterator();
        } // end default constructor
        
        /** Detects whether this iterator has completed its traversal
            and gone beyond the last entry in the collection of data. 
            @return True if the iterator has another entry to return. */
        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        /** Retrieves the next entry in the collection and
            advances this iterator by one position.
            @return  A reference to the next entry in the iteration,
                     if one exists.
            @throws  NoSuchElementException if the iterator had reached the
                     end already, that is, if hasNext() is false. */
        public VertexInterface<T> next()
        {
            VertexInterface<T> nextNeighbor = null;
            if (edges.hasNext())
            {                
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();                
            } 
            else
                throw new NoSuchElementException("Illegal call to next(); " + 
                                            "iterator is after end of list.");
            return nextNeighbor;
            // end if
        } // end next
        
        /** Removes from the collection of data the last entry that
            next() returned. A subsequent call to next() will behave
            as it would have before the removal.
            Precondition: next() has been called, and remove() has not
            been called since then. The collection has not been altered
            during the iteration except by calls to this method.
            @throws IllegalStateException if next() has not been called, or
                    if remove() was called already after the last call to next().
            @throws UnsupportedOperationException if the iterator does
                    not permit a remove operation. */
        public void remove()
        {
            throw new UnsupportedOperationException("remove() is not supported "
                                                    + "by this iterator");     
        } // end remove
    } // end NeighborIterator

    
    private class WeightIterator implements Iterator<Double>
    {
        private Iterator<Edge> edges;
        
        private WeightIterator()
        {            
            edges = edgeList.getIterator();
        } // end default constructor
        
        /** Detects whether this iterator has completed its traversal
            and gone beyond the last entry in the collection of data. 
            @return True if the iterator has another entry to return. */
        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        /** Retrieves the next entry in the collection and
            advances this iterator by one position.
            @return  A reference to the next entry in the iteration,
                     if one exists.
            @throws  NoSuchElementException if the iterator had reached the
                     end already, that is, if hasNext() is false. */
        public Double next()
        {
            Double nextNeighbor;
            if (edges.hasNext())
            {                
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getWeight();
            } 
            else
                throw new NoSuchElementException("Illegal call to next(); " + 
                                            "iterator is after end of list.");
            return nextNeighbor;
            // end if
        } // end next
        
        /** Removes from the collection of data the last entry that
            next() returned. A subsequent call to next() will behave
            as it would have before the removal.
            Precondition: next() has been called, and remove() has not
            been called since then. The collection has not been altered
            during the iteration except by calls to this method.
            @throws IllegalStateException if next() has not been called, or
                    if remove() was called already after the last call to next().
            @throws UnsupportedOperationException if the iterator does
                    not permit a remove operation. */
        public void remove()
        {
            throw new UnsupportedOperationException("remove() is not supported "
                                                    + "by this iterator");     
        } // end remove
    } // end WeightIterator
    
    protected class Edge
    {
        private VertexInterface<T> vertex; // Vertex at end of edge
        private double weight;

        protected Edge(VertexInterface<T> endVertex, double edgeWeight)
        {
           vertex = endVertex;
           weight = edgeWeight;
        } // end constructor

        protected Edge(VertexInterface<T> endVertex)
        {
           vertex = endVertex;
           weight = 0;
        } // end constructor

        protected VertexInterface<T> getEndVertex()
        {
           return vertex;
        } // end getEndVertex

        protected double getWeight()
        {
           return weight; 
        } // end getWeight
    } // end Edge
} // end Vertex
