import java.util.ArrayList;
import java.util.List;


public class Vertex<T> implements Comparable<Vertex<T>>{
	T name;
	List<Edge> edges;
	private boolean isVisited;
	private Vertex<T> parent;
	public int minDistance = Integer.MAX_VALUE;
	Vertex(T name){
		this.name = name;
		this.edges = new ArrayList<>();
	}
	
	public Vertex<T> parent() {
        return parent;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void setParent(Vertex<T> parent) {
        this.parent = parent;
    }
    public int compareTo(Vertex<T> other){
        return Double.compare(minDistance, other.minDistance);
    }

}
