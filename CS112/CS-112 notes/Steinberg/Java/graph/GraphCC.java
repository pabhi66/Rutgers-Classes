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
 *  connected components by Lou Steinberg, April, 2015
 */
public class GraphCC {

    Vertex[] adjLists;

    boolean undirected=true;

    public GraphCC(String file) throws FileNotFoundException {

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

    public int [ ] connectedComponentsDriver() {
	if (! undirected){
	    throw new IllegalArgumentException ();
	}
	int[ ] component = new int[adjLists.length]; 
	// set all vertices to not visted
	for (int v=0; v < component.length; v++) {
	    component[v] = -1;
	}
	// dfs on each vertex
	for (int v=0; v < component.length; v++) {
	    if (component[v]<0) {
		System.out.println("\n"+v+":  STARTING AT " + adjLists[v].name + "\n");
		connectedComponents(v, v, component);
	    }
	}
	return component;
    }
	 
    // recursive dfs to find connected component compNum from vertex v
    private void connectedComponents(int v, int compNum, int[ ] component) {
	component[v] = compNum;
	System.out.println("\tvisiting " + adjLists[v].name);
	for (Neighbor e=adjLists[v].adjList; e != null; e=e.next) {
	    if (component[e.vertexNum]<0) {
		System.out.println("\t" + adjLists[v].name + "--" + adjLists[e.vertexNum].name);
		connectedComponents(e.vertexNum, compNum, component);
	    }
	}
    }
     
    /**
     * @param args
     */
    public static void main(String[ ] args) 
	throws IOException {
	// TODO Auto-generated method stub
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter graph input file name: ");
	String file = sc.nextLine();
	GraphCC graph = new GraphCC(file);
	graph.print();
		 
	System.out.println("Doing DFS...");
	graph.dfs();

	System.out.println("Doing connected components ...");
	int [ ] components = graph.connectedComponentsDriver( );
	for (int n=0; n<components.length; n++){
	    System.out.println(n+": "+components[n]);
	}
    }
}
