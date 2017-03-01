//package Graph;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//public class Graph {
//
//	Vertex[] adjLists;
//
//	boolean undirected = true;
//
//	private Scanner sc;
//
//	public Graph(String file) throws FileNotFoundException{
//		sc = new Scanner(new File(file));
//		String graphType = sc.next();
//		if(graphType.equals("directed"))
//			undirected = false;
//
//		adjLists = new Vertex[sc.nextInt()];
//
//		//read vertices
//		for(int i = 0; i < adjLists.length; i++){
//			adjLists[i] = new Vertex(sc.next(),null);
//		}
//
//		//read edges
//		while(sc.hasNext()){
//			int v1 = indexForName(sc.next());
//			int v2 = indexForName(sc.next());
//
//			adjLists[v1].next = new Edge(v2, adjLists[v1].next);
//			if(undirected){
//				adjLists[v2].next = new Edge(v1,adjLists[v2].next);
//			}
//		}
//	}
//
//	public int countEdges(){
//		int count = 0;
//		for(Vertex v : adjLists){
//			for(Edge e = v.next; e != null; e = e.next){
//				count++;
//			}
//		}
//		return undirected? count/2 : count;
//	}
//
//	int indexForName(String name) {
//		for (int v=0; v < adjLists.length; v++) {
//			if (adjLists[v].name.equals(name)) {
//				return v;
//			}
//		}
//		return -1;
//	}
//
//	public void print() {
//		System.out.println();
//		for (int v=0; v < adjLists.length; v++) {
//			System.out.print(adjLists[v].name);
//			for (Edge nbr=adjLists[v].next; nbr != null;nbr=nbr.next) {
//				System.out.print(" --> " + adjLists[nbr.vertexNum].name);
//			}
//			System.out.println("\n");
//		}
//	}
//
//	public void dfs(){
//		boolean[] visited = new boolean[adjLists.length];
//		for(int v = 0; v<visited.length; v++){
//			visited[v] = false;
//		}
//
//		for(int v = 0; v < visited.length; v++){
//			if(!visited[v]){
//				System.out.println("\nSTARTING AT " + adjLists[v].name + "\n");
//				dfs(v,visited);
//			}
//		}
//	}
//
//	private void dfs(int v, boolean[] visited){
//		visited[v] = true;
//		System.out.println("\tvisiting " + adjLists[v].name);
//		for(Edge e = adjLists[v].next; e != null; e=e.next){
//			if(!visited[e.vertexNum]){
//				System.out.println("\t" + adjLists[v].name + "--" + adjLists[e.vertexNum].name);
//				dfs(e.vertexNum, visited);
//			}
//		}
//	}
//
//	public int[] dfsTopsort() {
//		 boolean[] visited = new boolean[adjLists.length];
//		 for (int v=0; v < visited.length; v++) {
//			 visited[v] = false;
//		 }
//
//		 int[] topsort = new int[adjLists.length];  // topsort[i] will be the vertex that has topological num i
//		 int topNum = adjLists.length-1;    // start with the largest possible top num
//
//		 for (int v=0; v < visited.length; v++) {
//			 if (!visited[v]) {
//				 topNum = dfsTopsort(v, visited, topsort, topNum);
//			 }
//		 }
//
//		 return topsort;
//	 }
//
//	private int dfsTopsort(int v, boolean[] visited, int[] topsort, int topNum) {
//		visited[v] = true;
//		for (Edge e=adjLists[v].next; e != null; e=e.next) {
//			if (!visited[e.vertexNum]) {
//				topNum = dfsTopsort(e.vertexNum, visited, topsort, topNum);
//			}
//		}
//		// assign topological number just before backtracking
//		// slot this vertex in the spot of its top num
//		topsort[topNum] = v;
//		// update top num and return
//		return topNum-1;
//	}
//
//	/**
//	 * @param args
//	 * @throws FileNotFoundException
//	 */
//	public static void main(String[] args) throws FileNotFoundException {
//		// TODO Auto-generated method stub
//		Scanner sc = new Scanner(System.in);
//		System.out.print("Enter graph input file name: ");
//		//String file = sc.nextLine();
//		Graph graph = new Graph("friendship.txt");
//		graph.print();
//
//		System.out.println("Doing DFS...");
//		graph.dfs();
//
//		if (!graph.undirected) {
//			System.out.println("Doing Topsort...");
//			int[] topsort = graph.dfsTopsort();
//			System.out.print("Topological sequence: ");
//			System.out.print(graph.adjLists[topsort[0]].name);
//			for (int i=1;i < topsort.length;i++) {
//				System.out.print(","+graph.adjLists[topsort[i]].name);
//			}
//			System.out.println();
//		}
//	}
//
//}
