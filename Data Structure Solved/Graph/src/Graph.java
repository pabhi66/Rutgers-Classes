import java.io.FileNotFoundException;

/**
 * Created by Abhi on 9/27/16.
 */
public class Graph {
    public void dfs(int v, boolean[] visited){
        visited[v] = true;
        for(Neighbor e = adjList[v].adjList; e != null, e = e.next){
            System.out.print("value here");
            dfs(e.vertexNum, visited);
        }
    }

    public void dfs(){
        boolean[] visited = new boolean[adjList.length];
        for(int v = 0; v < visited.length; v++){
            visited[v] = false;
        }

        for(int v = 0; v < visited.length; v++){
            if(!visited[v]){
                System.out.print("print here");
                dfs(v,visited);
            }
        }
    }

}
