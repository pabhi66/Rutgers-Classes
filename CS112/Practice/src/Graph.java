

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
public class Graph {

    Vertex[] adjLists;

    boolean undirected = true;

    public Graph(String file) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(file));

        String graphType = sc.next();
        if (graphType.equals("directed")) {
            undirected = false;
        }

        adjLists = new Vertex[sc.nextInt()];

        // read vertices
        for (int v = 0; v < adjLists.length; v++) {
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
        // COMPLETE THIS METHOD
        // FOLLOWING LINE IS A PLACEHOLDER SO THE PROGRAM COMPILES
        int count = 0;
        for (Vertex vertex : adjLists) {
            for (Neighbor nbr = vertex.adjList; nbr != null; nbr = nbr.next) {
                count++;
            }
        }
        return undirected ? count / 2 : count;

    }

    int indexForName(String name) {
        for (int v = 0; v < adjLists.length; v++) {
            if (adjLists[v].name.equals(name)) {
                return v;
            }
        }
        return -1;
    }

    public void print() {
        System.out.println();
        for (int v = 0; v < adjLists.length; v++) {
            System.out.print(adjLists[v].name);
            for (Neighbor nbr = adjLists[v].adjList; nbr != null; nbr = nbr.next) {
                System.out.print(" --> " + adjLists[nbr.vertexNum].name);
            }
            System.out.println("\n");
        }
    }

    public void dfs() {
        boolean[] visited = new boolean[adjLists.length];
        for (int v = 0; v < visited.length; v++) {
            visited[v] = false;
        }
        for (int v = 0; v < visited.length; v++) {
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
        for (Neighbor e = adjLists[v].adjList; e != null; e = e.next) {
            if (!visited[e.vertexNum]) {
                System.out.println("\t" + adjLists[v].name + "--" + adjLists[e.vertexNum].name);
                dfs(e.vertexNum, visited);
            }
        }
    }

    public int[] dfsTopsort() {
        boolean[] visited = new boolean[adjLists.length];
        for (int v = 0; v < visited.length; v++) {
            visited[v] = false;
        }

        int[] topsort = new int[adjLists.length];  // topsort[i] will be the vertex that has topological num i
        int topNum = adjLists.length - 1;    // start with the largest possible top num

        for (int v = 0; v < visited.length; v++) {
            if (!visited[v]) {
                topNum = dfsTopsort(v, visited, topsort, topNum);
            }
        }

        return topsort;
    }

    // recursive dfs topsort
    private int dfsTopsort(int v, boolean[] visited, int[] topsort, int topNum) {
        visited[v] = true;
        for (Neighbor e = adjLists[v].adjList; e != null; e = e.next) {
            if (!visited[e.vertexNum]) {
                topNum = dfsTopsort(e.vertexNum, visited, topsort, topNum);
            }
        }
        // assign topological number just before backtracking
        // slot this vertex in the spot of its top num
        topsort[topNum] = v;
        // update top num and return
        return topNum - 1;
    }

    public boolean isStronglyConnected() {
        boolean[][] temp = new boolean[adjLists.length][adjLists.length];

        for (int v = 0; v < adjLists.length; v++) {
            for (Neighbor e = adjLists[v].adjList; e != null; e = e.next) {
                temp[v][e.vertexNum] = true;
            }
        }

        int n = adjLists.length;
        boolean[] visited = new boolean[n];
        for(int i=0; i<n;i++){
            visited[i] = false;
        }
        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                dfs();
            }
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                System.out.println(" visited: " + temp[i][j]);
            }
        }
        return false;
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
        Graph graph = new Graph(file);
        graph.print();

        System.out.println("Doing DFS...");
        graph.dfs();

        System.out.print(graph.isStronglyConnected() + " yees");

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


//import java.util.*;
//
//public class Graph {
//    public static Scanner in;
//    public static LinkedList[] graph;
//    public static boolean visited[];
//    public static int n,m;
//    public static int numVisited;
//
//    public static void main(String[] args) {
//        in = new Scanner(System.in);
//
//        // input the graph:
//        n = in.nextInt();
//        m = in.nextInt();
//        graph = new LinkedList[n];
//        for (int i = 0; i < n; i++)
//            graph[i] = new LinkedList();
//
//        for (int i = 0; i < m; i++) {
//            int u = in.nextInt();
//            int v = in.nextInt();
//            graph[u].add(v);
//        }
//
//        // Now do a  depth-first search from every vertex to see if
//        // the graph is strongly-connected. THIS IS NOT THE MOST
//        // EFFICIENT WAY TO DO IT!
//        boolean strong = true;
//        for (int i = 0; i < n; i++) {
//            if (!dfs(i)) {
//                strong = false;
//                break;
//            }
//        }
//
//        if (strong)
//            System.out.println("yes");
//        else
//            System.out.println("no");
//    }
//
//
//    // Returns "true" if and only if a depth first search starting
//    // at v visits every node in the graph:
//    public static boolean dfs(int v) {
//        visited = new boolean[n];
//        numVisited = 0;
//        // NEXT LOOP IS NOT NEEDED:
//        //    for(int i = 0; i < n; i++) visited[i] = false;
//        visit(v);
//        return (numVisited == n);
//    }
//
//    // Recursively visit all neighbors of v:
//    public static void visit(int v) {
//        if (visited[v]) return;
//
//        visited[v] = true;
//        numVisited++;
//
//        ListIterator iter = graph[v].listIterator();
//        while (iter.hasNext()) {
//            int w = (Integer)iter.next();
//            visit(w);
//        }
//    }

//    directed
//    5
//    PageA
//            PageB
//    PageC
//            PageD
//    PageE
//    PageA PageB
//    PageB PageD
//    PageB PageC
//    PageC PageD
//    PageD PageE
//    PageE PageA
//