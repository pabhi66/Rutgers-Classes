import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph<T> {
	private Map<T, Vertex<T>> adjList;
	boolean undirected = true;
	
	
	/**
	 * Create a new hash map of vertex values and vertex name as key
	 */
	public Graph(){
		adjList = new HashMap<T, Vertex<T>>();
	}
	
	/**
	 * add an vertex to the graph
	 * @param vertexName vertex name
	 * @return true if vertex is added successfully
	 */
	public boolean addVertex(T vertexName){
		if(adjList.containsKey(vertexName))
			return false;
		adjList.put(vertexName, new Vertex<T>(vertexName) );
		return true;
	}
	
	/**
	 * adds a edges between two vertices in the graph
	 * @param vertex1 vertex 1 (start)
	 * @param vertex2 vertex 2 (end)
	 * @return true if edge successfully added
	 */
	public boolean addEdge(T vertex1, T vertex2){
		return addEdge(vertex1, vertex2,0);
	}
	
	/**
     * Adds a weighted directed edge between two vertices in the graph.
     * @param vertex1       vertex where the (directed) edge begins
     * @param vertex2       vertex where the (directed) edge ends
     * @param weight        weight of the edge
     * @return true if edge successfully added
     */
	public boolean addEdge(T vertex1, T vertex2, int weight){
		if(!adjList.containsKey(vertex1) || !adjList.containsKey(vertex2))
			throw new IllegalArgumentException("No such verticies found to add edge");
		
		Vertex<T> v1 = adjList.get(vertex1);
		Vertex<T> v2 = adjList.get(vertex2);
		
		//no loops
		if(v1.name.equals(v2.name))
			return false;
		
		Edge e = new Edge(v1,v2,weight);
		v1.edges.add(e);
		if(undirected){
			e = new Edge(v2,v1,weight);
			v2.edges.add(e);
		}
		return true;
	}
	
	/**
	 * Counts number of edges in graph
	 * @return number of edges in the graph
	 */
	public int countAllEdge(){
		int count = 0;
		Set<Map.Entry<T, Vertex<T>>> set = adjList.entrySet();
		for(Map.Entry<T, Vertex<T>> entry : set){
			List<Edge> e = entry.getValue().edges;
			count += e.size();
		}
		return undirected? count/2 : count;
	}
	
	/**
	 * count number of vertices in the graph
	 * @return number of vertices in the graph
	 */
	public int countAllVertices(){
		return adjList.keySet().size();
	}
	
	/**
	 * print the graph
	 */
	public void print(){
		Set<Map.Entry<T, Vertex<T>>> set = adjList.entrySet();
		for(Map.Entry<T, Vertex<T>> entry : set){
			System.out.print(entry.getKey());
			Vertex<T> v = entry.getValue();
			
			List<Edge> e = v.edges;
			for(int i = 0; i < e.size(); i++){
				System.out.print("--" +  e.get(i).weight  + "-->" + e.get(i).v2.name);
			}
			System.out.println();
		}
	}
	
	/**
	 * remove the vertex from the graph
	 * @param vertex vertex to be removed
	 * @return true if successs
	 */
	public boolean removeVertex(T vertex){
		if(!adjList.containsKey(vertex))
			return false;
		final Vertex<T> toRemove = adjList.get(vertex);
		List<Edge> e = toRemove.edges;
		
		
		for(int i = 0; i < e.size(); i++){
			if(undirected){
				Vertex<T> v = e.get(i).v2;
				List<Edge > e1 = v.edges;
				int index = 0;
				for(int j = 0; j < e1.size(); j++){
					if(e1.get(j).v2.equals(toRemove.name)){
						index = j;
						break;
					}
				}
				e1.remove(index);
			}
			e.remove(i);
		}
		adjList.remove(toRemove.name);
	
		return true;
	}
	
	/**
	 * remove edge between two vertex 1
	 * @param v1 vertex 1
	 * @param v2 vertex 2
	 * @return true if successfull
	 */
	public boolean removeEdge(T v1, T v2){
		if(!adjList.containsKey(v1) || !adjList.containsKey(v2))
			return false;
		final Vertex<T> removeFrom = adjList.get(v1);
		final Vertex<T> removeTo = adjList.get(v2);
		
		List<Edge> e1 = removeFrom.edges;
		List<Edge> e2 = removeTo.edges;
		
		if(undirected){
			int index = 0;
			for(int i = 0; i < e2.size(); i++){
				if(e2.get(i).v2.name.equals(v1)){
					index = i;
					break;
				}
			}
			e2.remove(index);
		}
		int index = 0;
		for(int i = 0; i < e1.size(); i++){
			if(e1.get(i).v1.name.equals(v2)){
				index = i;
				break;
			}
		}
		e1.remove(index);
		return true;
	}
	
	/**
	 * do bfs on graph
	 * @param startVertex
	 */
	public void bfs(T startVertex){
		
		if(!adjList.containsKey(startVertex))
			throw new IllegalArgumentException("No such Vertex found");
		
		for(T key : adjList.keySet()){
			Vertex<T> v = adjList.get(key);
			v.setParent(null);
			v.setVisited(false);
		}
		
		Queue<Vertex<T>> queue = new LinkedList<>();
		Vertex<T> start = adjList.get(startVertex);
		queue.add(start);
		
		while(!queue.isEmpty()){
			Vertex<T> first = queue.remove();
			//System.out.println(first.name);
			first.setVisited(true);
			List<Edge> edges = first.edges;
			for(Edge e : edges){
				Vertex<T> nbr = e.v2;
				if(!nbr.isVisited()){
					nbr.setParent(first);
					queue.add(nbr);
				}
			}
		}
	}
	
	/**
	 * dfs traverse
	 * @param startVertex starting vertex
	 */
	public void dfs(T startVertex){
		if(!adjList.containsKey(startVertex))
			return;
		
		for(T key : adjList.keySet()){
			Vertex<T> v = adjList.get(key);
			v.setParent(null);
			v.setVisited(false);
		}
		
		int i = 0;
		for(T key : adjList.keySet()){
			if(i == 0){
				System.out.println("Starting at " +adjList.get(startVertex).name);
				dfs(adjList.get(startVertex));
				i++;
			}else{
				Vertex v = adjList.get(key);
				if(!v.isVisited()){
					System.out.println("Starting at " +adjList.get(key).name);
					dfs(adjList.get(key));
				}
			}
		}
	}
	/**
	 * dfs helper
	 * @param v vertex
	 */
	private void dfs(Vertex v){
		v.setVisited(true);
		System.out.println("visiting " +v.name);
		List<Edge> edges = v.edges;
		for(Edge e : edges){
			Vertex<T> v2 = e.v2;
			if(!v2.isVisited()){
				//System.out.println("visiting " +v.name);
				dfs(v2);
			}
		}
	}
	
	/**
	 * find shortest path between two vertices.  This does not take weight into consideration
	 * @param start start vertex
	 * @param end end vertex
	 * @return shortest path
	 */
	public List<T> shortestPath(T start, T end){
		if(!adjList.containsKey(start) || !adjList.containsKey(end))
			return null;
		
		bfs(start);
		
		List<T> path = new ArrayList<>();
		Vertex<T> endVertex = adjList.get(end);
		while(endVertex != null && endVertex != adjList.get(start)){
			path.add(endVertex.name);
			endVertex = endVertex.parent();
		}
		
		if(endVertex == null)
			return null;
		Collections.reverse(path);
		
		return path;
	}
	
	public List<T> shortestPathByWeight(T start, T end){
		
		if(!adjList.containsKey(start) || !adjList.containsKey(end))
			return null;
		
		for(T key : adjList.keySet()){
			Vertex<T> v = adjList.get(key);
			v.setParent(null);
			v.setVisited(false);
		}
		
		Vertex<T> source = adjList.get(start);
		source.minDistance = 0;
		PriorityQueue<Vertex<T>> pq = new PriorityQueue<Vertex<T>>();
		pq.add(source);
		
		while(!pq.isEmpty()){
			Vertex<T> u = pq.poll();
			List<Edge> edges = u.edges;
			if(edges.size() == 0 || u.edges == null)
				continue;
			
			for(Edge e : edges){
				Vertex<T> v = e.v2;
				int weight = e.weight;
				int distanceThroughU = u.minDistance + weight;
				if(distanceThroughU < v.minDistance){
					pq.remove(v);
					v.minDistance = distanceThroughU;
					v.setParent(u);
					pq.add(v);
				}
			}
		}
		
		List<T> path = new ArrayList<>();
		Vertex<T> endVertex = adjList.get(end);
		int sum = 0;
		while(endVertex != null && endVertex != adjList.get(start)){
			path.add(endVertex.name);
			List<Edge> edges = endVertex.edges;
			for(int i = 0; i < edges.size(); i++){
				if(edges.get(i).v2.name.equals(endVertex.parent().name))
					sum += edges.get(i).weight;
			}
			endVertex = endVertex.parent();
		}
		
		
		System.out.println("Distance: "+sum);
		Collections.reverse(path);
		return path;
	}
	
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Graph<String> graph = new Graph<String>();
		Scanner scan = new Scanner(new File("graph.txt"));
		String graphType = scan.next();
		if(graphType.equals("directed"))
			graph.undirected = false;
		
		int numVertex = scan.nextInt();
		for(int i = 0; i < numVertex; i++){
			graph.addVertex(scan.next());
		}
		
		while(scan.hasNext()){
			String v1 = scan.next();
			String v2 = scan.next();
			int weight = scan.nextInt();
			
			graph.addEdge(v1, v2, weight);
		}
//		graph.print();
//		graph.removeVertex("Rahul");
//		System.out.println("----------------------");
//		graph.print();
//		System.out.println("----------------------");
//		graph.removeEdge("Rohit", "Sapna");
		graph.print();
		System.out.println("----------------------");
		//graph.dfs("Maria");
		System.out.println("\n" + graph.shortestPathByWeight("Sam", "Sapna"));
		System.out.println("\n Total Edges: " + graph.countAllEdge()
				+ " Total Vertices " + graph.countAllVertices());
	}

}
