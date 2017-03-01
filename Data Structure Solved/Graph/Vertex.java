import java.util.ArrayList;

/**
 * This class models a vertex in a graph.
 * Graph Object only accepts one vertex per label, so its unique.
 * The vertex's neighbors are described by the edges incident to it.
 * @author abp119
 *
 */
public class Vertex {

	private ArrayList<Edge> neighbors;
	private String label;
	
	/**
	 * The unique label associated with this vertex
	 * @param label
	 */
	public Vertex(String label){
		this.label = label;
		this.neighbors = new ArrayList<Edge>();
	}
	
	/**
	 * Adds edge to the incidence neighbor of this graph iff the graph is not already present.
	 * @param edge
	 */
	public void addNeighbor(Edge edge){
		if(this.neighbors.contains(edge))
			return;
		this.neighbors.add(edge);
	}
	
	/**
	 * 
	 * @param Edge the edge to be search
	 * @return true iff edge is contained in this.neighbors
	 */
	public boolean containsNeighbor(Edge Edge){
		return this.neighbors.contains(Edge);
	}
	
	/**
	 * 
	 * @param index the index of the edge to retrieve
	 * @return the edge at the specified index in this neighbors
	 */
	public Edge getNeighbor(int index){
		return this.neighbors.get(index);
	}
	
	/**
	 * 
	 * @param index the index of the edge to be removed
	 * @return the removed edge
	 */
	public Edge removeNeighbor(int index){
		return this.neighbors.remove(index);
	}
	
	/**
     * 
     * @param e The Edge to remove from this.neighborhood
     */
    public void removeNeighbor(Edge e){
        this.neighbors.remove(e);
    }
	
	/**
	 * 
	 * @return the number of neighbors for the vertex
	 */
	public int getNeighborCount(){
		return this.neighbors.size();
	}
	
	/**
	 * 
	 * @return name of the vertex
	 */
	public String getLabel(){
		return this.label;
	}
	
	/**
	 * return String representation of this vertex
	 */
	public String toString(){
		return "Vertex  " + label;
	}
	
	/**
	 * return hashCode of this vertex's label
	 */
	public int hashCode(){
		return this.label.hashCode();
	}
	
	/**
	 * other = object to compare
	 * return = true iff other instance of vertex and the two vertex object have same label
	 */
	public boolean equals(Object other){
		if(!(other instanceof Vertex))
			return false;
		
		Vertex v = (Vertex) other;
		return this.label.equals(v.label);
	}
	
	/**
     * 
     * @return ArrayList<Edge> A copy of this.neighborhood. Modifying the returned
     * ArrayList will not affect the neighborhood of this Vertex
     */
	public ArrayList<Edge> getNeighbors(){
		return new ArrayList<Edge>(this.neighbors);
	}
	
	
	
	
}
