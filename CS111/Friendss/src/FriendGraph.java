//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map.Entry;
//import java.util.Set;
//import java.util.TreeMap;
//
//
///**
// * The FriendGraph class encapsulates a graph of "facebook" friends and supports a number of methods on them.  
// * The graph is implemented using an adjacency linked list representation, with a hashMap for rapid translation
// * from the name of a friend to its position in the arrayList of the LL representation.
// * @author jeremypriestner
// *
// */
//public class FriendGraph {
//	
//	ArrayList<User> adjList;
//	HashMap<String,Integer> nameToIndex;
//	
//	public FriendGraph(ArrayList<User> adjList, HashMap<String,Integer> nameToIndex) {
//		this.adjList = adjList;
//		this.nameToIndex = nameToIndex;
//	}
//	
//	
//	/**
//	 * Input: Name of school (case insensitive), e.g. "penn state"
//     * Output: Subgraph of original graph, vertices are all students at the given school, 
//     * edges are a subset of the edges of the original graph such that both endpoints are 
//     * students at the school. The subgraph must be in stored in the adjacency linked lists
//     * form, just as for the original graph. 
//     * 
//	 * @param school
//	 * @return FriendGraph
//	 */
//	public FriendGraph subgraph(String school) {
//		if(school == null)
//			return null; //this code never gets used because Friends currently ensures non-null input
//		
//		school = school.toLowerCase();
//		
//		FriendGraph newGraph = new FriendGraph(null, null);
//		newGraph.adjList = new ArrayList<User>();
//		newGraph.nameToIndex = new HashMap<String,Integer>(1000, 2.0f);
//		
//		User v, nv, tmpUser;
//		Neighbor n, tmp, tmp2;
//		int newNIndex, newVIndex;
//		
//		//loop through each vertex in the arrayList for vertices
//		//set up the return graph's arrayList of vertices and hashMap for translating from name to arrayList index
//		for (int i = 0; i < adjList.size(); i++) { 
//			v = adjList.get(i);
//			
//			if (school.equalsIgnoreCase(v.school)) {
//				//make a duplicate of the vertex and put it into the return arrayList
//					//duplicate necessary?
//				tmpUser = new User(v.name);
//				tmpUser.school = v.school;
//				newGraph.adjList.add(tmpUser);
//				newGraph.nameToIndex.put(tmpUser.name, newGraph.adjList.size()-1);
//			}
//		}
//		
//		//loop through each vertex in the arrayList for edges
//		for(int i=0; i<adjList.size(); i++) {
//			
//			v = adjList.get(i);
//			n = v.firstNeighbor;//first neighbor
//			
//			//check if the user is a student at the relevant school
//			if(school.equals(v.school)) {
//	
//				//go through neighbors and check if they are students at the relevant school
//				//if so, then create a new edge for them and add them to the linked list in the new arrayList
//				while(n != null) {
//					nv = adjList.get(n.neighborIndex);
//					
//					if(school.equals(nv.school)) {
//						newNIndex = newGraph.nameToIndex.get(nv.name);
//						newVIndex = newGraph.nameToIndex.get(v.name);
//						
//						tmp2 = newGraph.adjList.get(newVIndex).firstNeighbor; //first neighbor of new LL
//						tmp = new Neighbor(newNIndex, tmp2);
//						newGraph.adjList.get(newVIndex).firstNeighbor = tmp;
//					}
//					
//					n = n.next;
//				}
//				
//			}
//			
//		}
//		
//		if(newGraph.adjList.isEmpty())
//			return null;
//		
//		return newGraph;
//	}
//	
//	
//	/**
//	 * Input: Name of school for which cliques are to be found, e.g. "rutgers"
//     * Output: The subgraphs for each of the cliques. Output should be in the same format as the input described in the 
//     * Graph build section. Note that if there is even one student at named school in the graph, there must be at least one 
//     * clique in the output. If the graph has no students at all at that school, then the output will be empty.
//     * 
//	 * @param school
//	 */
//	public void islands(String school) {
//	
//		if(school == null)
//			return;
//		
//		Boolean[] visited = new Boolean[adjList.size()];
//		ArrayList<String> vertices = new ArrayList<String>();
//		ArrayList<String> edges = new ArrayList<String>();
//		
//		//initialize visited array to false
//		for(int i=0; i<visited.length; i++) {
//			visited[i] = false;
//		}
//
//		cliqueNum = 0;
//		for(int i=0; i<adjList.size(); i++) {
//			if(!visited[i]) {
//				islandsBFS(i, school, visited, vertices, edges);
//			}
//		}
//		
//		//return if the subgraph is empty
//		if(vertices.isEmpty())
//			return;
//			
//	}
//	
//	public static int cliqueNum;
//	
//	/**
//	 * Helper method to accompany islands().  Uses BFS to traverse the graph and extract the edges and vertices of the subgraph.
//	 * Uses a queue class to implement BFS.
//	 * @param startIndex
//	 * @param school
//	 * @param visited
//	 * @param arr1
//	 * @param arr2
//	 */
//	private void islandsBFS(int startIndex, String school, Boolean[] visited, ArrayList<String> arr1, ArrayList<String> arr2){
//		
//		Queue queue = new Queue();
//		User v, w, pv;
//		Neighbor p;
//		IslandsEdge tmp, front = null;
//		
//		//visit the first vertex and mark it as visited
//		v = adjList.get(startIndex);
//		if(!school.equals(v.school)) {
//			visited[startIndex] = true;
//			return;
//		}
//		
//		//first vertex belongs to the school
//		//this means we have found a new clique, that was previously unvisited
//		cliqueNum++;
//		System.out.println("Clique " + cliqueNum);
//		System.out.println(v.name + "|y|" + school);//arr1.add(v.name + "|y|" + school);
//		
//		//mark v as visited
//		visited[startIndex] = true;
//				
//		//add vertex to the queue
//		queue.enqueue(v);
//		
//		//keep checking the neighbors of whatever is in the queue while it isn't empty
//		while(!queue.isEmpty()) {
//			w = queue.dequeue();
//			p = w.firstNeighbor;
//			
//			while(p != null) {
//				
//				if(!visited[p.neighborIndex]) {
//					//pv is vertex form of neighbor p
//					pv = adjList.get(p.neighborIndex);
//					
//					//visit p
//					if(!school.equals(pv.school)) {
//						visited[p.neighborIndex] = true;
//						continue;
//					}
//					//p goes to the same school as the argument to the method
//					System.out.println(pv.name + "|y|" + school);
//					
//					//mark p as visited
//					visited[p.neighborIndex] = true;
//					
//					//add p to the queue
//					queue.enqueue(pv);
//					
//					//adjust linked list of edges to reflect the fact that pv and w are neighbors
//					if(front == null){//no edges have been encountered yet
//						front = new IslandsEdge();
//						front.edge = pv.name + "|" + w.name;
//					}else{
//						tmp = front;
//						front = new IslandsEdge();
//						front.edge= pv.name + "|" + w.name;
//						front.next = tmp;
//				
//					}
//					
//				}
//				
//				p = p.next;
//			}
//		
//		}
//		
//		//print out the edges from the linked list
//		tmp = front;
//		while(tmp != null){
//			System.out.println(tmp.edge);
//			tmp = tmp.next;
//		}
//		
//	}
//	
//	
//	/**
//	 * Given a start and end vertex, prints the shortest path between them. Implemented using BFS
//	 * which itself uses the Queue class.
//	 * @param source
//	 * @param end
//	 */
//	public void shortestPath(String source, String end)
//	throws Exception{
//		
//		//check for null input
//		if(source == null || end == null){
//			throw new Exception("Invalid input!");
//		}
//		
//		//check that input users actually exist in graph
//		if(nameToIndex.get(end) == null || nameToIndex.get(source) == null){
//			throw new Exception("Input user(s) is not in the graph!");
//		}
//		
//		source = source.toLowerCase();
//		end = end.toLowerCase();
//		
//		int endIndex,frontPtr; User v; Neighbor w;
//		Queue q = new Queue();
//		
//		/**
//		 * For our choice of storage for distances and paths, we chose to use arrays
//		 * over a TreeMap because the algorithm assigns distances and paths to every
//		 * part of the connected subgraph that the end user is in. In practice, most 
//		 * networks are completely connected, so this means that a distance and path
//		 * will be assigned to almost every vertex anyway. Thus, it is both faster 
//		 * and more space efficient to use arrays.
//		 */
//		int [] distances = new int[adjList.size()];
//		User [] path = new User[adjList.size()];
//		
//		for(int i=0; i<adjList.size(); i++) {
//			distances[i] = Integer.MAX_VALUE;
//		}
//		
//		endIndex = nameToIndex.get(end);
//		distances[endIndex] = 0;
//		q.enqueue(adjList.get(endIndex));
//		
//		while(!q.isEmpty()) {
//			v = q.dequeue();
//			w = v.firstNeighbor;//first edge in the linked list
//			
//			while(w != null) {
//				if(distances[w.neighborIndex] == Integer.MAX_VALUE){
//				distances[w.neighborIndex] = distances[nameToIndex.get(v.name)] + 1;
//				path[w.neighborIndex] = v;
//				
//				q.enqueue(adjList.get(w.neighborIndex));
//				
//				}
//				w = w.next;
//			}
//		}
//		
//		//throws exception if there is no path between start and end user
//		if(distances[nameToIndex.get(source)] == Integer.MAX_VALUE || source.equals(end)){
//			throw new Exception("No path exists between start and end user!");
//		}
//		
//		frontPtr = nameToIndex.get(source);
//		while(!end.equals(adjList.get(frontPtr).name)) {
//		
//			System.out.print(adjList.get(frontPtr).name + "---");
//			
//			frontPtr = nameToIndex.get(path[frontPtr].name);
//		}
//		System.out.println(end);
//		
//	}
//	
//	/**
//	 * Finds and prints all the connectors of a graph.
//	 *  
//	 */
//	public void connectors() {
//		
//		boolean[] visited = new boolean[adjList.size()];
//		int[] dfsNum = new int[adjList.size()];
//		int[] backNum = new int[adjList.size()];
//		int dfsNumCounter = 0, backNumCounter = 0; 
//		
//		//slower access than array, but much more space efficient
//		//key is userIndex, value is status as a connector:
//		//3 = connector, 2 = starting point that can be a connector, 1 = starting point
//		TreeMap<Integer, Integer> connectors = new TreeMap<Integer, Integer>();
//		
//		//DFS driver
//		for (int i = 0; i < adjList.size(); i++) {
//			//if is part of a new island, start DFS on it
//			if (!visited[i]) {
//				connectors.put(i, 1);
//				
//				//debug
//				//System.out.println(adjList.get(i));
//				
//				connectorsDFS(adjList.get(i),
//							visited, 
//							dfsNum, 
//							backNum, 
//							dfsNumCounter, 
//							backNumCounter,
//							connectors);
//			}
//		}
//		
//		if(connectors.containsValue(3) == false) {
//			System.out.println("No connectors.");
//			return;
//		}
//		
//		System.out.print("Connectors are: ");
//		Set<Entry<Integer, Integer>> connectorEntries = connectors.entrySet();
//		Iterator<Entry<Integer, Integer>> it = connectorEntries.iterator();
//		
//		while (it.hasNext()) {
//			Entry<Integer, Integer> entry = it.next();
//			
//			if (entry.getValue() == 3) {
//				System.out.print(adjList.get(entry.getKey()).name + " ");
//			}
//		}
//		System.out.println();
//	}
//	
//	private void connectorsDFS(
//			User user, 
//			boolean[] visited, 
//			int[] dfsNum, 
//			int[] backNum,
//			int dfsNumCounter,
//			int backNumCounter,
//			TreeMap<Integer, Integer> connectors) {
//		
//		//debug
//		//System.out.println(user.name);
//		//System.out.println(nameToIndex.get(user.name));
//		
//		//mark user as visited + assign dfsNum in increasing order + assign backNum
//		int userIndex = nameToIndex.get(user.name);
//		visited[userIndex] = true;
//		dfsNum[userIndex] = dfsNumCounter;  dfsNumCounter++;	
//		backNum[userIndex] = backNumCounter;  backNumCounter++;
//		
//		//do DFS on each neighbor of user using recursion
//		Neighbor neighbor = user.firstNeighbor;
//		while (neighbor != null) {
//			
//			//if the neighbor isn't visited, DFS on neighbor
//			if (!visited[neighbor.neighborIndex]) {
//				User next = adjList.get(neighbor.neighborIndex);
//				connectorsDFS(next, visited, dfsNum, backNum, dfsNumCounter, backNumCounter, connectors);
//				
//				//after backing out:
//				if (dfsNum[userIndex] > backNum[neighbor.neighborIndex]) {
//					backNum[userIndex] = Math.min(backNum[userIndex], backNum[neighbor.neighborIndex]);
//				}
//				else {
//					//User is connector for sure if it isn't a starting point
//					if (connectors.get(userIndex) == null) {
//						//not a starting point
//						//connectors.remove(userIndex);
//						connectors.put(userIndex, 3);
//					}
//					else if (connectors.get(userIndex) == 1) {
//						//starting point, but not connector
//						connectors.remove(userIndex);
//						connectors.put(userIndex, 2);
//					}
//					else if (connectors.get(userIndex) == 2) {
//						//starting point, connector
//						connectors.remove(userIndex);
//						connectors.put(userIndex, 3);
//					}
//				}
//			}
//			//if neighbor already visited, update current user's backNum
//			else {
//				backNum[userIndex] = Math.min(backNum[userIndex], dfsNum[neighbor.neighborIndex]);
//			}
//			neighbor = neighbor.next;
//		}
//	}
//	
//	/**
//	 * Simple print method for printing out contents of the FriendGraph graph representation.
//	 */
//	public void printGraph() {
//		
//		if(adjList == null || adjList.isEmpty()){
//			System.out.println("The graph is empty");
//			System.out.println("");
//		}
//		
//		for(int i = 0; i < adjList.size(); i++) {
//			System.out.print(i + ". " + adjList.get(i) + " ");
//			Neighbor ptr = adjList.get(i).firstNeighbor;
//			
//			while(ptr != null) {
//				System.out.print(ptr.neighborIndex + " ");
//				ptr = ptr.next;
//			}
//			
//			System.out.println("");
//		}
//	}
//	
//	/**
//	 * Simple print method for printing out contents of the hash map.
//	 */
//	public void printHash() {
//		System.out.println(nameToIndex.entrySet());
//	}
//}