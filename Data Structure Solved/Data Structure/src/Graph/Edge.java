package Graph;

public class Edge {
	public int weight;
	public Vertex v1,v2;

	public Edge(Vertex v1, Vertex v2, int weight){
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
}
