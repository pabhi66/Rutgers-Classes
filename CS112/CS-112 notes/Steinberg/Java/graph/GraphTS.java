package graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class Neighbor {
	public int vertexNum;
	public Neighbor next;
	public Neighbor(int vnum, Neighbor nbr) {
		this.vertexNum = vnum;
		next = nbr;
	}
}

class Vertex {
	String name;
	Neighbor adjList;
	Vertex(String name, Neighbor neighbors) {
		this.name = name;
		this.adjList = neighbors;
	}
}

/**
 * @author Sesh Venugopal. May 31, 2013.
 */
 public class GraphTS {

	 Vertex[] adjLists;

	 boolean undirected=true;

	 public GraphTS(String file) throws FileNotFoundException {

		 Scanner sc = new Scanner(new File(file));

		 String graphType = sc.next();
		 if (graphType.equals("directed")) {
			 undirected=false;
		 }

		 adjLists = new Vertex[sc.nextInt()];

		 // read vertices
		 for (int v=0; v < adjLists.length; v++) {
			 adjLists[v] = new Vertex(sc.next(), null);
		 }

		 // read edges
		 while (sc.hasNext()) {

			 // read vertex names and translate to vertex numbers
			 int v1 = indexForName(sc.next());
			 int v2 = indexForName(sc.next());

			 // add v2 to front of v1's adjacency list and
			 // add v1 to front of v2's adjacency list
			 adjLists[v1].adjList = new Neighbor(v2, adjLists[v1].adjList);
			 if (undirected) {
				 adjLists[v2].adjList = new Neighbor(v1, adjLists[v2].adjList);
			 }
		 }
	 }

	 public int countEdges() {
		 int edges=0;
		 for (int v=0; v < adjLists.length; v++) {
			 for (Neighbor n=adjLists[v].adjList;
					 n != null; n=n.next) {
				 edges++;
			 }
		 }
		 if (undirected) {
			 edges /= 2;
		 }
		 return edges;
	 }

	 int indexForName(String name) {
		 for (int v=0; v < adjLists.length; v++) {
			 if (adjLists[v].name.equals(name)) {
				 return v;
			 }
		 }
		 return -1;
	 }	

	 public void print() {
		 System.out.println();
		 for (int v=0; v < adjLists.length; v++) {
			 System.out.print(adjLists[v].name);
			 for (Neighbor nbr=adjLists[v].adjList; nbr != null;nbr=nbr.next) {
				 System.out.print(" --> " + adjLists[nbr.vertexNum].name);
			 }
			 System.out.println("\n");
		 }
	 }

	 public void dfs() {
		 boolean[] visited = new boolean[adjLists.length];
		 for (int v=0; v < visited.length; v++) {
			 visited[v] = false;
		 }
		 for (int v=0; v < visited.length; v++) {
			 if (!visited[v]) {
				 System.out.println("\nSTARTING AT " + adjLists[v].name + "\n");
				 dfs(v, visited);
			 }
		 }
	 }
	 
	 // recursive dfs
	 private void dfs(int v, boolean[] visited) {
		 visited[v] = true;
		 System.out.println("\tvisiting " + adjLists[v].name);
		 for (Neighbor e=adjLists[v].adjList; e != null; e=e.next) {
			 if (!visited[e.vertexNum]) {
				 System.out.println("\t" + adjLists[v].name + "--" + adjLists[e.vertexNum].name);
				 dfs(e.vertexNum, visited);
			 }
		 }
	 }
     
	 public int[] dfsTopsort() {
		 boolean[] visited = new boolean[adjLists.length];
		 for (int v=0; v < visited.length; v++) {
			 visited[v] = false;
		 }

		 // topsort[i] will be the vertex that has topological num i
		 int[] topsort = new int[adjLists.length];  
		 int topNum = adjLists.length-1;    // start with the largest possible top num 

		 for (int v=0; v < visited.length; v++) {
			 if (!visited[v]) {
				 topNum = dfsTopsort(v, visited, topsort, topNum);
			 }
		 }

		 return topsort;
	 }

	 // recursive dfs topsort
	 private int dfsTopsort(int v, boolean[] visited, int[] topsort, int topNum) {
		 visited[v] = true;
		 for (Neighbor e=adjLists[v].adjList; e != null; e=e.next) {
			 if (!visited[e.vertexNum]) {
				 topNum = dfsTopsort(e.vertexNum, visited, topsort, topNum);
			 }
		 }
		 // assign topological number just before backtracking
		 // slot this vertex in the spot of its top num
		 topsort[topNum] = v;
		 // update top num and return
		 return topNum-1;
	 }

	 /**
	  * @param args
	  */
	 public static void main(String[] args) 
			 throws IOException {
		 // TODO Auto-generated method stub
		 Scanner sc = new Scanner(System.in);
		 System.out.print("Enter graph input file name: ");
		 String file = sc.nextLine();
		 GraphTS graph = new GraphTS(file);
		 graph.print();
		 
		 System.out.println("Doing DFS...");
	     graph.dfs();
	     
	     if (!graph.undirected) {
	    	 	System.out.println("Doing Topsort...");
	    	 	int[] topsort = graph.dfsTopsort();
	    	 	System.out.print("Topological sequence: ");
	    	 	System.out.print(graph.adjLists[topsort[0]].name);
	    	 	for (int i=1;i < topsort.length;i++) {
	    	 		System.out.print(","+graph.adjLists[topsort[i]].name);
	    	 	}
	    	 	System.out.println();
	     }
	 }
 }