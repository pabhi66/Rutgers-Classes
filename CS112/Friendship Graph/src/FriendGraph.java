////
////package structures;
////
////import java.util.ArrayList;
////import java.util.HashMap;
////import java.util.Iterator;
////import java.util.Map.Entry;
////import java.util.Set;
////import java.util.TreeMap;
////
////
/////**
////* The FriendGraph class encapsulates a graph of "facebook" friends and supports a number of methods on them.
////* The graph is implemented using an adjacency linked list representation, with a hashMap for rapid translation
////* from the name of a friend to its position in the arrayList of the LL representation.
////* @author jeremypriestner
////*
////*/
////public class FriendGraph {
////
////    ArrayList<User> adjList;
////    HashMap<String,Integer> nameToIndex;
////
////    public FriendGraph(ArrayList<User> adjList, HashMap<String,Integer> nameToIndex) {
////        this.adjList = adjList;
////        this.nameToIndex = nameToIndex;
////    }
////
////
////    /**
////     * Input: Name of school (case insensitive), e.g. "penn state"
////     * Output: Subgraph of original graph, vertices are all students at the given school,
////     * edges are a subset of the edges of the original graph such that both endpoints are
////     * students at the school. The subgraph must be in stored in the adjacency linked lists
////     * form, just as for the original graph.
////     *
////     * @param school
////     * @return FriendGraph
////     */
////    public FriendGraph subgraph(String school) {
////        if(school == null)
////            return null; //this code never gets used because Friends currently ensures non-null input
////
////        school = school.toLowerCase();
////
////        FriendGraph newGraph = new FriendGraph(null, null);
////        newGraph.adjList = new ArrayList<User>();
////        newGraph.nameToIndex = new HashMap<String,Integer>(1000, 2.0f);
////
////        User v, nv, tmpUser;
////        Neighbor n, tmp, tmp2;
////        int newNIndex, newVIndex;
////
////        //loop through each vertex in the arrayList for vertices
////        //set up the return graph's arrayList of vertices and hashMap for translating from name to arrayList index
////        for (int i = 0; i < adjList.size(); i++) {
////            v = adjList.get(i);
////
////            if (school.equalsIgnoreCase(v.school)) {
////                //make a duplicate of the vertex and put it into the return arrayList
////                //duplicate necessary?
////                tmpUser = new User(v.name);
////                tmpUser.school = v.school;
////                newGraph.adjList.add(tmpUser);
////                newGraph.nameToIndex.put(tmpUser.name, newGraph.adjList.size()-1);
////            }
////        }
////
////        //loop through each vertex in the arrayList for edges
////        for(int i=0; i<adjList.size(); i++) {
////
////            v = adjList.get(i);
////            n = v.firstNeighbor;//first neighbor
////
////            //check if the user is a student at the relevant school
////            if(school.equals(v.school)) {
////
////                //go through neighbors and check if they are students at the relevant school
////                //if so, then create a new edge for them and add them to the linked list in the new arrayList
////                while(n != null) {
////                    nv = adjList.get(n.neighborIndex);
////
////                    if(school.equals(nv.school)) {
////                        newNIndex = newGraph.nameToIndex.get(nv.name);
////                        newVIndex = newGraph.nameToIndex.get(v.name);
////
////                        tmp2 = newGraph.adjList.get(newVIndex).firstNeighbor; //first neighbor of new LL
////                        tmp = new Neighbor(newNIndex, tmp2);
////                        newGraph.adjList.get(newVIndex).firstNeighbor = tmp;
////                    }
////
////                    n = n.next;
////                }
////
////            }
////
////        }
////
////        if(newGraph.adjList.isEmpty())
////            return null;
////
////        return newGraph;
////    }
////
////
////    /**
////     * Input: Name of school for which cliques are to be found, e.g. "rutgers"
////     * Output: The subgraphs for each of the cliques. Output should be in the same format as the input described in the
////     * Graph build section. Note that if there is even one student at named school in the graph, there must be at least one
////     * clique in the output. If the graph has no students at all at that school, then the output will be empty.
////     *
////     * @param school
////     */
////    public void islands(String school) {
////
////        if(school == null)
////            return;
////
////        Boolean[] visited = new Boolean[adjList.size()];
////        ArrayList<String> vertices = new ArrayList<String>();
////        ArrayList<String> edges = new ArrayList<String>();
////
////        //initialize visited array to false
////        for(int i=0; i<visited.length; i++) {
////            visited[i] = false;
////        }
////
////        cliqueNum = 0;
////        for(int i=0; i<adjList.size(); i++) {
////            if(!visited[i]) {
////                islandsBFS(i, school, visited, vertices, edges);
////            }
////        }
////
////        //return if the subgraph is empty
////        if(vertices.isEmpty())
////            return;
////
////    }
////
////    public static int cliqueNum;
////
////    /**
////     * Helper method to accompany islands().  Uses BFS to traverse the graph and extract the edges and vertices of the subgraph.
////     * Uses a queue class to implement BFS.
////     * @param startIndex
////     * @param school
////     * @param visited
////     * @param arr1
////     * @param arr2
////     */
////    private void islandsBFS(int startIndex, String school, Boolean[] visited, ArrayList<String> arr1, ArrayList<String> arr2){
////
////        Queue queue = new Queue();
////        User v, w, pv;
////        Neighbor p;
////        IslandsEdge tmp, front = null;
////
////        //visit the first vertex and mark it as visited
////        v = adjList.get(startIndex);
////        if(!school.equals(v.school)) {
////            visited[startIndex] = true;
////            return;
////        }
////
////        //first vertex belongs to the school
////        //this means we have found a new clique, that was previously unvisited
////        cliqueNum++;
////        System.out.println("Clique " + cliqueNum);
////        System.out.println(v.name + "|y|" + school);//arr1.add(v.name + "|y|" + school);
////
////        //mark v as visited
////        visited[startIndex] = true;
////
////        //add vertex to the queue
////        queue.enqueue(v);
////
////        //keep checking the neighbors of whatever is in the queue while it isn't empty
////        while(!queue.isEmpty()) {
////            w = queue.dequeue();
////            p = w.firstNeighbor;
////
////            while(p != null) {
////
////                if(!visited[p.neighborIndex]) {
////                    //pv is vertex form of neighbor p
////                    pv = adjList.get(p.neighborIndex);
////
////                    //visit p
////                    if(!school.equals(pv.school)) {
////                        visited[p.neighborIndex] = true;
////                        continue;
////                    }
////                    //p goes to the same school as the argument to the method
////                    System.out.println(pv.name + "|y|" + school);
////
////                    //mark p as visited
////                    visited[p.neighborIndex] = true;
////
////                    //add p to the queue
////                    queue.enqueue(pv);
////
////                    //adjust linked list of edges to reflect the fact that pv and w are neighbors
////                    if(front == null){//no edges have been encountered yet
////                        front = new IslandsEdge();
////                        front.edge = pv.name + "|" + w.name;
////                    }else{
////                        tmp = front;
////                        front = new IslandsEdge();
////                        front.edge= pv.name + "|" + w.name;
////                        front.next = tmp;
////
////                    }
////
////                }
////
////                p = p.next;
////            }
////
////        }
////
////        //print out the edges from the linked list
////        tmp = front;
////        while(tmp != null){
////            System.out.println(tmp.edge);
////            tmp = tmp.next;
////        }
////
////    }
////
////
////    /**
////     * Given a start and end vertex, prints the shortest path between them. Implemented using BFS
////     * which itself uses the Queue class.
////     * @param source
////     * @param end
////     */
////    public void shortestPath(String source, String end)
////            throws Exception{
////
////        //check for null input
////        if(source == null || end == null){
////            throw new Exception("Invalid input!");
////        }
////
////        //check that input users actually exist in graph
////        if(nameToIndex.get(end) == null || nameToIndex.get(source) == null){
////            throw new Exception("Input user(s) is not in the graph!");
////        }
////
////        source = source.toLowerCase();
////        end = end.toLowerCase();
////
////        int endIndex,frontPtr; User v; Neighbor w;
////        Queue q = new Queue();
////
////        /**
////         * For our choice of storage for distances and paths, we chose to use arrays
////         * over a TreeMap because the algorithm assigns distances and paths to every
////         * part of the connected subgraph that the end user is in. In practice, most
////         * networks are completely connected, so this means that a distance and path
////         * will be assigned to almost every vertex anyway. Thus, it is both faster
////         * and more space efficient to use arrays.
////         */
////        int [] distances = new int[adjList.size()];
////        User [] path = new User[adjList.size()];
////
////        for(int i=0; i<adjList.size(); i++) {
////            distances[i] = Integer.MAX_VALUE;
////        }
////
////        endIndex = nameToIndex.get(end);
////        distances[endIndex] = 0;
////        q.enqueue(adjList.get(endIndex));
////
////        while(!q.isEmpty()) {
////            v = q.dequeue();
////            w = v.firstNeighbor;//first edge in the linked list
////
////            while(w != null) {
////                if(distances[w.neighborIndex] == Integer.MAX_VALUE){
////                    distances[w.neighborIndex] = distances[nameToIndex.get(v.name)] + 1;
////                    path[w.neighborIndex] = v;
////
////                    q.enqueue(adjList.get(w.neighborIndex));
////
////                }
////                w = w.next;
////            }
////        }
////
////        //throws exception if there is no path between start and end user
////        if(distances[nameToIndex.get(source)] == Integer.MAX_VALUE || source.equals(end)){
////            throw new Exception("No path exists between start and end user!");
////        }
////
////        frontPtr = nameToIndex.get(source);
////        while(!end.equals(adjList.get(frontPtr).name)) {
////
////            System.out.print(adjList.get(frontPtr).name + "---");
////
////            frontPtr = nameToIndex.get(path[frontPtr].name);
////        }
////        System.out.println(end);
////
////    }
////
////    /**
////     * Finds and prints all the connectors of a graph.
////     *
////     */
////    public void connectors() {
////
////        boolean[] visited = new boolean[adjList.size()];
////        int[] dfsNum = new int[adjList.size()];
////        int[] backNum = new int[adjList.size()];
////        int dfsNumCounter = 0, backNumCounter = 0;
////
////        //slower access than array, but much more space efficient
////        //key is userIndex, value is status as a connector:
////        //3 = connector, 2 = starting point that can be a connector, 1 = starting point
////        TreeMap<Integer, Integer> connectors = new TreeMap<Integer, Integer>();
////
////        //DFS driver
////        for (int i = 0; i < adjList.size(); i++) {
////            //if is part of a new island, start DFS on it
////            if (!visited[i]) {
////                connectors.put(i, 1);
////
////                //debug
////                //System.out.println(adjList.get(i));
////
////                connectorsDFS(adjList.get(i),
////                        visited,
////                        dfsNum,
////                        backNum,
////                        dfsNumCounter,
////                        backNumCounter,
////                        connectors);
////            }
////        }
////
////        if(connectors.containsValue(3) == false) {
////            System.out.println("No connectors.");
////            return;
////        }
////
////        System.out.print("Connectors are: ");
////        Set<Entry<Integer, Integer>> connectorEntries = connectors.entrySet();
////        Iterator<Entry<Integer, Integer>> it = connectorEntries.iterator();
////
////        while (it.hasNext()) {
////            Entry<Integer, Integer> entry = it.next();
////
////            if (entry.getValue() == 3) {
////                System.out.print(adjList.get(entry.getKey()).name + " ");
////            }
////        }
////        System.out.println();
////    }
////
////    private void connectorsDFS(
////            User user,
////            boolean[] visited,
////            int[] dfsNum,
////            int[] backNum,
////            int dfsNumCounter,
////            int backNumCounter,
////            TreeMap<Integer, Integer> connectors) {
////
////        //debug
////        //System.out.println(user.name);
////        //System.out.println(nameToIndex.get(user.name));
////
////        //mark user as visited + assign dfsNum in increasing order + assign backNum
////        int userIndex = nameToIndex.get(user.name);
////        visited[userIndex] = true;
////        dfsNum[userIndex] = dfsNumCounter;  dfsNumCounter++;
////        backNum[userIndex] = backNumCounter;  backNumCounter++;
////
////        //do DFS on each neighbor of user using recursion
////        Neighbor neighbor = user.firstNeighbor;
////        while (neighbor != null) {
////
////            //if the neighbor isn't visited, DFS on neighbor
////            if (!visited[neighbor.neighborIndex]) {
////                User next = adjList.get(neighbor.neighborIndex);
////                connectorsDFS(next, visited, dfsNum, backNum, dfsNumCounter, backNumCounter, connectors);
////
////                //after backing out:
////                if (dfsNum[userIndex] > backNum[neighbor.neighborIndex]) {
////                    backNum[userIndex] = Math.min(backNum[userIndex], backNum[neighbor.neighborIndex]);
////                }
////                else {
////                    //User is connector for sure if it isn't a starting point
////                    if (connectors.get(userIndex) == null) {
////                        //not a starting point
////                        //connectors.remove(userIndex);
////                        connectors.put(userIndex, 3);
////                    }
////                    else if (connectors.get(userIndex) == 1) {
////                        //starting point, but not connector
////                        connectors.remove(userIndex);
////                        connectors.put(userIndex, 2);
////                    }
////                    else if (connectors.get(userIndex) == 2) {
////                        //starting point, connector
////                        connectors.remove(userIndex);
////                        connectors.put(userIndex, 3);
////                    }
////                }
////            }
////            //if neighbor already visited, update current user's backNum
////            else {
////                backNum[userIndex] = Math.min(backNum[userIndex], dfsNum[neighbor.neighborIndex]);
////            }
////            neighbor = neighbor.next;
////        }
////    }
////
////    /**
////     * Simple print method for printing out contents of the FriendGraph graph representation.
////     */
////    public void printGraph() {
////
////        if(adjList == null || adjList.isEmpty()){
////            System.out.println("The graph is empty");
////            System.out.println("");
////        }
////
////        for(int i = 0; i < adjList.size(); i++) {
////            System.out.print(i + ". " + adjList.get(i) + " ");
////            Neighbor ptr = adjList.get(i).firstNeighbor;
////
////            while(ptr != null) {
////                System.out.print(ptr.neighborIndex + " ");
////                ptr = ptr.next;
////            }
////
////            System.out.println("");
////        }
////    }
////
////    /**
////     * Simple print method for printing out contents of the hash map.
////     */
////    public void printHash() {
////        System.out.println(nameToIndex.entrySet());
////    }
////}
//
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//
//
//public class GraphMethods {
//
//    //These are the global variables that are made in Graph Create.
//    //These variables are accessed in the other Graph Methods.
//    //Each key in the adjList is unique for this assignment is indexed in the order it is received.
//    ArrayList<Vertex> adjList = new ArrayList<Vertex>();
//
//    //The Hash map holds the key which is the personName string and the value which is the adjList
//    //index.  This allows for quick access to the adjList.
//    HashMap<String, Integer> lookUp = new HashMap<String,Integer>(500,2.0f);
//    //Holds the adjList and lookUp HashMap
//    Graph friendGraph;
//
//    /********************************************************************
//     Graph build
//
//     Input: file that lists names of all people and edges between them.
//     Here is the format of the input file for the sample friendship graph
//     given in the Background section.
//
//     Result: The adjacency linked list representation, along with a data structure
//     to be able to quickly translate from a person's name to vertex number.
//     There should also be a quick way to translate from vertex number to the person's name.
//
//     Output: You don't have to print the representation, we will check your code
//     to see if you are storing the graph in this representation.
//     ********************************************************************/
//    public void graphCreate(String graphFile) throws FileNotFoundException{
//
//        //These are local variables used in Graph Create.
//        Scanner sc = new Scanner(new File(graphFile));
//        String size = sc.nextLine();
//        int sizeInt = Integer.parseInt(size);
//
//        //This loop creates the adjList and lookUp table.
//        for (int v = 0; v < sizeInt; v++ )
//        {
//            //Variables that cut up the input string into useful parts.
//            String data = sc.nextLine();
//            String[] dataSplit = data.split("\\|");
//            String name = dataSplit[0];
//            String enrolled = dataSplit[1];
//            String school = null;
//
//            //Logic for handling school attendance data.
//            if(enrolled.equals("y"))
//            {
//                school = dataSplit[2];
//                school = school.toLowerCase();
//                Vertex yesSchool = new Vertex(name, school, null);
//                adjList.add(yesSchool);
//            }else{
//                Vertex noSchool = new Vertex(name, null, null);
//                adjList.add(noSchool);
//            }
//
//            //Hash the name with an index value equal to the loop counter.
//            lookUp.put(name, v);
//        }
//
//        //This loop adds friendships with the Neighbor class to the adjList personName keys.
//        while (sc.hasNext())
//        {
//            //Variables that cut up the input string into useful parts.
//            String friendship = sc.next();
//            String[] friendshipSplit = friendship.split("\\|");
//
//            String nameOne = friendshipSplit[0];
//            String nameTwo = friendshipSplit[1];
//
//            nameOne = nameOne.toLowerCase();
//            nameTwo = nameTwo.toLowerCase();
//
//            int v1 = lookUp.get(nameOne);
//            int v2 = lookUp.get(nameTwo);
//
//            //Adds the Neighbor nodes to the correct keys.
//            adjList.get(v1).neighborNext = new Neighbor(v2, adjList.get(v1).neighborNext);
//            adjList.get(v2).neighborNext = new Neighbor(v1, adjList.get(v2).neighborNext);
//        }
//
//        //This is the final Graph with the adjList and lookUp table.
//        friendGraph = new Graph(adjList,lookUp);
//    }
//
//    /********************************************************************
//     Subgraph (students at a school)
//
//     Input: Name of school (case insensitive), e.g. "penn state"
//
//     Result: Subgraph of original graph, vertices are all students at the given school,
//     edges are a subset of the edges of the original graph such that both end points are
//     students at the school. The subgraph must be in stored in the adjacency linked lists form,
//     just as for the original graph.
//
//     Output: Print the subgraph in the same format as the input in the Graph build section above.
//     NOTE: This also means if there is an edge x--y in the graph, then your output must have
//     either x--y or y--x, but NOT both.
//     ********************************************************************/
//
//    public void subGraph(String school)
//    {
//        //Checks for bad input
//        if(school==null){
//            return;
//        }
//
//        //Makes the input string case insensitive
//        school = school.toLowerCase();
//
//        //Creates the variables for the Sub Graph
//        ArrayList<Vertex> sAdjList = new ArrayList<Vertex>();
//        HashMap<String, Integer> sLookUp = new HashMap<String,Integer>(1000,2.0f);
//        Graph subGraph = new Graph(sAdjList,sLookUp);
//
//
//        //This loop creates the adjList and lookUp table for the sub graph.
//        for(int i = 0; i < adjList.size(); i ++){
//            Vertex vA = adjList.get(i);
//            String currentSchool = null;
//            if(vA.schoolName!=null)
//            {
//                currentSchool = vA.schoolName.toLowerCase();
//                //If the school matches the sub graph is updated.
//                if(currentSchool.equals(school))
//                {
//                    Vertex vB = new Vertex(vA.personName, currentSchool, null);
//                    subGraph.adjList.add(vB);
//                    subGraph.lookUp.put(vB.personName, subGraph.adjList.size()-1);
//                }
//            }
//        }
//
//        //This loop adds friendships with the Neighbor class to the adjList personName keys.
//        for(int j = 0; j < adjList.size(); j++){
//            Vertex vC = adjList.get(j);
//            Neighbor x = vC.neighborNext;
//
//            if(school.equals(vC.schoolName)){
//                //The inner loop scans all of the Neighbor nodes in a given key.
//                while(x!=null){
//                    Vertex vD = adjList.get(x.vertexNumber);
//
//                    //If the school matches, the sub graph is updated
//                    if(school.equals(vD.schoolName)){
//                        int index1 = subGraph.lookUp.get(vD.personName);
//                        int index2 = subGraph.lookUp.get(vC.personName);
//
//                        Neighbor tmp2 = subGraph.adjList.get(index2).neighborNext;
//                        Neighbor tmp = new Neighbor(index1, tmp2);
//
//                        subGraph.adjList.get(index2).neighborNext = tmp;
//                    }
//                    x = x.next;
//                }
//            }
//        }
//
//
//        //Prints the size of the sub graph
//        System.out.println(subGraph.adjList.size());
//
//        //Prints the names in the sub graph with their school information.
//        for(int k = 0; k<subGraph.adjList.size();k++){
//            System.out.println(subGraph.adjList.get(k).personName+"|y|"+school);
//        }
//
//        //A visit list to avoid duplicates in the print output.
//        boolean[] visit = new boolean[adjList.size()];
//
//        //Prints unique friendships in the sub graph
//        for(int h = 0; h < subGraph.adjList.size(); h++){
//            for(Neighbor neighbors = subGraph.adjList.get(h).neighborNext; neighbors!=null; neighbors=neighbors.next){
//                if(visit[h]==false || visit[neighbors.vertexNumber] == false){
//                    visit[h] = true;
//                    visit[neighbors.vertexNumber] = true;
//                    System.out.println(subGraph.adjList.get(h).personName + "|" + subGraph.adjList.get(neighbors.vertexNumber).personName);
//                }
//            }
//        }
//    }
//
//    /********************************************************************
//     Shortest path (Intro chain)
//
//     Input: Name of person who wants the intro, and the name of the other person,
//     e.g. "sam" and "aparna" for the graph in the Background section.
//     (Neither of these, nor any of the intermediate people are required to be students,
//     in the same school or otherwise.)
//
//     Result: The shortest chain of people in the graph starting at
//     the first and ending at the second.
//
//     Output: Print the chain of people in the shortest path, for example:
//
//     sam--jane-bob--samir--aparna
//
//     If there is no way to get from the first person to the second person,
//     then the output should be a message to this effect.
//     ********************************************************************/
//
//    public void shortestPath(String firstPerson, String secondPerson)
//            throws NoSuchElementException{
//        if(firstPerson == null || secondPerson == null)
//        {
//            throw new NoSuchElementException("Please Enter Input:");
//        }
//
//        firstPerson = firstPerson.toLowerCase();
//        secondPerson = secondPerson.toLowerCase();
//
//        if(!lookUp.containsKey(firstPerson) || !lookUp.containsKey(secondPerson))
//        {
//            throw new NoSuchElementException();
//        }
//
//        int index;
//        int front;
//        Vertex source;
//        Neighbor tracker;
//
//        Queue storage = new Queue();
//
//        int size = friendGraph.adjList.size();
//
//        Vertex[] path = new Vertex[size];
//        int[] distance = new int[size];
//
//        for(int i = 0; i < size; i++){
//            distance[i] = -1;
//        }
//
//        index = lookUp.get(secondPerson);
//        distance[index] = 0;
//        storage.enqueue(friendGraph.adjList.get(index));
//
//        while(storage.isEmpty()==false){
//            source = storage.dequeue();
//            tracker = source.neighborNext;
//
//            while(tracker != null){
//                if(distance[tracker.vertexNumber] == -1){
//                    distance[tracker.vertexNumber] = distance[lookUp.get(source.personName)] + 1;
//                    path[tracker.vertexNumber] = source;
//
//                    storage.enqueue(friendGraph.adjList.get(tracker.vertexNumber));
//                }
//                tracker = tracker.next;
//            }
//        }
//
//        if(distance[lookUp.get(firstPerson)] == -1 || firstPerson.equals(secondPerson)){
//            throw new NoSuchElementException("No Path Exists!");
//        }
//
//        front = lookUp.get(firstPerson);
//
//        while(secondPerson.equals(friendGraph.adjList.get(front).personName)==false){
//
//            System.out.print(friendGraph.adjList.get(front).personName + "--");
//
//            front = lookUp.get(path[front].personName);
//        }
//        System.out.println(secondPerson);
//    }
//
//    /********************************************************************
//     Connected Islands (cliques)
//
//     Input: Name of school for which cliques are to be found, e.g. "rutgers"
//
//     Result: The subgraphs for each of the cliques.
//
//     Output: Print the subgraph for each clique,
//     in the same format as the input described in the Graph build section.
//     For example:
//
//     Clique 1:
//
//     <subgraph output>
//
//     Clique 2:
//
//     <subgraph output>
//
//     etc...
//
//     Note: If there is even one student at the named school in the graph,
//     then there must be at least one clique in the output.
//     If the graph has no students at all at that school, then the output will be empty.
//     ********************************************************************/
//
//    public void connectedIslands(String school)
//    {
//        school = school.toLowerCase();
//        boolean[] visit = new boolean[friendGraph.adjList.size()];
//        Queue Q = new Queue();
//        int index = 0;
//
//
//        for(int t = 0; t<friendGraph.adjList.size(); t++)
//        {
//            ArrayList<Vertex> cAdjList = new ArrayList<Vertex>();
//            HashMap<String, Integer> cLookUp = new HashMap<String,Integer>(1000,2.0f);
//            int count = 0;
//            if(visit[t]==false)
//            {
//                if(school.equals(friendGraph.adjList.get(t).schoolName))
//                {
//                    Q.enqueue(friendGraph.adjList.get(t));
//                    visit[t] = true;
//                    cAdjList.add(0, friendGraph.adjList.get(t));
//                    cLookUp.put(friendGraph.adjList.get(t).personName, 0);
//                    t++;
//                }
//                while(Q.isEmpty()!=true)
//                {
//
//                    index = friendGraph.lookUp.get(Q.dequeue().personName);
//                    for(Neighbor neighbors = friendGraph.adjList.get(index).neighborNext; neighbors!=null; neighbors=neighbors.next)
//                    {	Vertex vA = friendGraph.adjList.get(neighbors.vertexNumber);
//                        if(visit[neighbors.vertexNumber]!=true){
//                            visit[neighbors.vertexNumber] = true;
//                            String currentSchool = null;
//                            if(vA.schoolName!=null){
//                                currentSchool = vA.schoolName;
//                                if(currentSchool.equals(school)){
//                                    count++;
//                                    Q.enqueue(friendGraph.adjList.get(neighbors.vertexNumber));
//                                    Vertex vB = new Vertex(vA.personName, currentSchool, null);
//                                    cAdjList.add(vB);
//
//                                    cLookUp.put(vA.personName, count);
//
//                                }
//                            }
//                        }
//                    }
//                }
//                Graph cliqueGraph = new Graph(cAdjList,cLookUp);
//
//                for(int k = 0; k<cliqueGraph.adjList.size();k++)
//                {
//                    cliqueGraph.adjList.get(k).neighborNext = null;
//                }
//                if(cliqueGraph.adjList.size()!=0)
//                {
//                    for(int j = 0; j < adjList.size(); j++)
//                    {
//                        Vertex vC = adjList.get(j);
//                        Neighbor x = vC.neighborNext;
//
//                        if(school.equals(vC.schoolName))
//                        {
//                            //The inner loop scans all of the Neighbor nodes in a given key.
//                            while(x!=null)
//                            {
//                                Vertex vD = adjList.get(x.vertexNumber);
//
//                                //If the school matches, the sub graph is updated
//
//                                if(school.equals(vD.schoolName))
//                                {
//                                    if((cliqueGraph.lookUp.get(vD.personName)!=null)
//                                            &&(cliqueGraph.lookUp.get(vC.personName)!=null)){
//                                        int index1 = cliqueGraph.lookUp.get(vD.personName);
//                                        int index2 = cliqueGraph.lookUp.get(vC.personName);
//
//                                        Neighbor tmp2 = cliqueGraph.adjList.get(index2).neighborNext;
//                                        Neighbor tmp = new Neighbor(index1, tmp2);
//
//                                        cliqueGraph.adjList.get(index2).neighborNext = tmp;
//                                    }
//                                }
//                                x = x.next;
//                            }
//                        }
//                    }
//
//
//                    System.out.println(cliqueGraph.adjList.size());
//                    for(int k = 0; k<cliqueGraph.adjList.size();k++){
//                        System.out.println(cliqueGraph.adjList.get(k).personName+"|y|"+school);
//                    }
//                    boolean[] printVisit = new boolean[cliqueGraph.adjList.size()];
//
//                    for(int h = 0; h < cliqueGraph.adjList.size(); h++)
//                    {
//                        for(Neighbor neighborsC = cliqueGraph.adjList.get(h).neighborNext; neighborsC!=null; neighborsC=neighborsC.next){
//                            if(printVisit[h]==false || printVisit[neighborsC.vertexNumber] == false){
//                                printVisit[h] = true;
//                                printVisit[neighborsC.vertexNumber] = true;
//                                System.out.println(cliqueGraph.adjList.get(h).personName + "|" + cliqueGraph.adjList.get(neighborsC.vertexNumber).personName);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    /********************************************************************
//     Connectors (Friends who keep friends together)
//
//     Input: Nothing
//
//     Result: Names of all people who are connectors in the graph
//
//     Output: Print names of all people who are connectors in the graph,
//     comma separated, in any order.
//     ********************************************************************/
//
//    public void connectors() {
//        boolean[] visited = new boolean[adjList.size()];
//        int size = adjList.size();
//        int[] dfsNum = new int[size];
//        int[] back = new int[size];
//        int dfsNumCounter = 0, backNumCounter = 0;
//        int[] kimchi = new int[size];
//
//        for (int i = 0; i < size; i++) {
//
//            if (!visited[i]) {
//                kimchi[i] = 1;
//
//
//                DFS(adjList.get(i), visited, dfsNum, back, dfsNumCounter, backNumCounter, kimchi);
//
//            }
//        }
//
//        System.out.print("Friends who keep friends together: ");
//        for (int i = 0; i < adjList.size(); i++)
//        {
//            if (kimchi[i] == 3)
//            {
//                System.out.print(adjList.get(i).personName + " ");
//            }
//        }
//        System.out.println();
//    }
//
//    private void DFS(Vertex v, boolean[] visited, int[] dfsNum, int[] back, int dfsNumCount, int backCount, int[] connectors) {
//
//        int index = lookUp.get(v.personName);
//        visited[index] = true;
//        dfsNum[index] = dfsNumCount;
//        dfsNumCount++;
//        back[index] = backCount;
//        backCount++;
//
//
//        Neighbor neighbor = v.neighborNext;
//        while (neighbor != null)
//        {
//            if (!visited[neighbor.vertexNumber])
//            {
//                Vertex next = adjList.get(neighbor.vertexNumber);
//                DFS(next, visited, dfsNum, back, dfsNumCount, backCount, connectors);
//                if (dfsNum[index] > back[neighbor.vertexNumber])
//                {
//                    back[index] = Math.min(back[index], back[neighbor.vertexNumber]);
//                }else{
//
//                    switch (connectors[index])
//                    {
//                        case 0: connectors[index] = 3;
//                            break;
//                        case 1:  connectors[index] = 2;
//                            break;
//                        case 2:  connectors[index] = 3;
//                            break;
//                    }
//                }
//            }
//
//            if(dfsNum[index] > back[neighbor.vertexNumber]){
//                back[index] = Math.min(back[index], dfsNum[neighbor.vertexNumber]);
//            }
//            neighbor = neighbor.next;
//        }
//    }
//    /********************************************************************
//     A Print Utility
//     ********************************************************************/
//
//    public void print(){
//        System.out.println();
//        for(int j = 0; j < adjList.size(); j++){
//            System.out.print(adjList.get(j).personName);
//            for(Neighbor neighbors = adjList.get(j).neighborNext; neighbors!=null; neighbors=neighbors.next){
//                System.out.print(" ---> " + adjList.get(neighbors.vertexNumber).personName);
//            }
//            System.out.println("\n");
//        }
//    }
//}



///********************************************************************
// Sub Graph Class
// ********************************************************************/
//public void subGraph(String school){
//        school = school.toLowerCase();
//        if(school == null)
//        return;
//
//        HashMap<String, Integer> subSearch = new HashMap<String, Integer>(2000,2.0f);
//        ArrayList<user> subList = new ArrayList<user>();
//        graph subGraph = new graph(subSearch,subList);
//
//        int i=0;
//        int size = list.size();
//        String college = null;
//        while(i<size){
//        user u = list.get(i);
//        if(u.school != null){
//        college = u.school;
//        college = college.toLowerCase();
//        if(u.school.equalsIgnoreCase(school)){
//        user u2 = new user(u.name,college,null);
//        subGraph.list.add(u2);
//        subGraph.search.put(u2.name,subGraph.list.size()-1);
//        }
//        }
//        i++;
//        }
//
//        int j = 0;
//        int length = list.size();
//        while(j < length){
//        user sis = list.get(j);
//        if(school.equalsIgnoreCase(sis.school)){
//        for(neighbor bro = sis.next; bro != null; bro = bro.next){
//        user sis2 = list.get(bro.VNum);
//        if(school.equalsIgnoreCase(sis2.school)){
//        int friend1 = subGraph.search.get(sis2.name);
//        int friend2 = subGraph.search.get(sis.name);
//        neighbor cuz1 = subGraph.list.get(friend2).next;
//        neighbor cuz = new neighbor(cuz1, friend1);
//        subGraph.list.get(friend2).next = cuz;
//        }
//        }
//        }
//        j++;
//        }
//
//        System.out.print(subGraph.list.size());
//        System.out.print("\n");
//        int x = 0;
//        int siz = subGraph.list.size();
//        while(x < siz){
//        System.out.print("");
//        System.out.print(subGraph.list.get(x).name);
//        System.out.print("|y|");
//        System.out.print(school);
//        System.out.print("\n");
//        x++;
//        }
//
//        boolean[] checked = new boolean[list.size()];
//        int a = 0;
//        int len = subGraph.list.size();
//        while(a < len){
//        neighbor friend = subGraph.list.get(a).next;
//        while(friend != null){
//        if(!checked[a] || !checked[friend.VNum]){
//        System.out.print("");
//        System.out.print(subGraph.list.get(friend.VNum).name);
//        System.out.print("|");
//        System.out.print(subGraph.list.get(a).name);
//        System.out.print("\n");
//        checked[friend.VNum] = true;
//        checked[a] = true;
//        }
//        friend = friend.next;
//        }
//        a++;
//        }
//        }