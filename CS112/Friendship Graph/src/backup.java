//import java.io.*;
//import java.util.ArrayList;
//import java.util.NoSuchElementException;
//
///**
// * Created by Abhi on 4/28/15.
// */
//public class backup {
//    ///**
////* Abhishek Prajapati : section 15
////* Pranav Velanakanni: section 13
////* Friendship graph
////*/
////import java.util.*;
////import java.lang.*;
////import java.io.*;
////public class Friends {
////    public static void main(String[] args) throws IOException {
////        Scanner scan = new Scanner(System.in);
////        Friends friends = new Friends();
////        //read the file and build the graph
////        System.out.println("Please enter the graph file name: ");
////        String file = scan.next();
////        friends.Graph(file);
////        //print the options
////        System.out.println("1: Students at school: ");
////        System.out.println("2: Shortest intro chain: ");
////        System.out.println("3: Cliques at school: ");
////        System.out.println("4: connectors: ");
////        System.out.print("5: quit");
////        System.out.println("Enter your choice");
////        int choice = scan.nextInt();
////        //do something based on choice
////        while (choice != 5) {
////            if (choice < 1 || choice > 5) {
////                System.out.println("Wrong choice: " + choice);
////            } else {
////                if (choice == 1) {
////                    System.out.println("Enter the school name: ");
////                    String schoolSubGraph = scan.nextLine();
////                    Vertex[] graph = friends.subGraph(schoolSubGraph);
////                    if(graph != null){
////                        friends.printGraph(graph);
////                    }
////                    break;
////                }
////                if (choice == 2) {
////                    System.out.println("Enter first person's name: ");
////                    String first = scan.nextLine();
////                    System.out.println("Enter second person's name: ");
////                    String second = scan.nextLine();
////                    friends.ShortestPath(first, second);
////                    break;
////                }
////                if (choice == 3) {
////                    System.out.println("Enter the name of the school: ");
////                    String schoolIsland = scan.nextLine();
////                    friends.Cliques(schoolIsland);
////                    break;
////                }
////                if (choice == 4) {
////                    friends.connectors();
////                    break;
////                }
////            }
////            System.out.println("1: Students at school: ");
////            System.out.println("2: Shortest intro chain: ");
////            System.out.println("3: Cliques at school: ");
////            System.out.println("4: connectors: ");
////            System.out.print("5: quit");
////            System.out.println("Enter your choice");
////            choice = scan.nextInt();
////        }
////    }
////
////    public class graph {
////        Hashtable<String, Integer> search;
////        ArrayList<Vertex> adjacentLinkedList;
////
////        public graph(Hashtable<String, Integer> search, ArrayList<Vertex> adjacentLinkedList) {
////            this.search = search;
////            this.adjacentLinkedList = adjacentLinkedList;
////        }
////    }
////
////    public class neighbour {
////        neighbour next;
////        int Vertexnum;
////
////        public neighbour(neighbour next, int num) {
////            this.next = next;
////            this.Vertexnum = num;
////        }
////    }
////
////    public class Vertex {
////        String name;
////        String school;
////        neighbour ajNext;
////        boolean visited;
////        Vertex next;
////        int dfsnum;
////        int back;
////
////        public Vertex(String name, String school, neighbour neighbours) {
////            this.ajNext = neighbours;
////            this.name = name;
////            this.school = school;
////        }
////    }
////
////    public class Node {
////        Node next;
////        int data;
////        boolean isVisited;
////
////        public Node(Node next, int data, boolean isVisited) {
////            this.next = null;
////            this.data = data;
////            this.isVisited = false;
////        }
////    }
////
////    class Queue {
////
////        private Vertex rear;
////        private int size;
////
////        public Queue() {
////            rear = null;
////            size = 0;
////        }
////
////        public void enqueue(Vertex v) {
////            Vertex newItem = new Vertex(v.name, v.school, v.ajNext);
////
////            if (rear == null) {
////                newItem.next = newItem;
////            } else {
////                newItem.next = rear.next;
////                rear.next = newItem;
////            }
////            size++;
////            rear = newItem;
////        }
////
////        public Vertex dequeue() throws NoSuchElementException {
////            if (rear == null) {
////                throw new NoSuchElementException("queue is empty");
////            }
////            Vertex data = rear.next;
////            if (rear == rear.next) {
////                rear = null;
////            } else {
////                rear.next = rear.next.next;
////            }
////            size--;
////            return data;
////        }
////
////
////        public int size() {
////            return size;
////        }
////
////        public boolean isEmpty() {
////            return size == 0;
////        }
////
//////        public void clear() {
//////            size = 0;
//////            rear = null;
//////        }
//////
//////
//////        public Vertex peek() throws NoSuchElementException {
//////            if (rear == null) {
//////                throw new NoSuchElementException("queue is empty");
//////            }
//////            return rear.next;
//////        }
////
////    }
////
////    static Vertex[] list;
////    ArrayList<String> connectors = new ArrayList<String>();
////    int dfsn = 0;
////    int start = 0;
////
////    public void Graph(String file) throws FileNotFoundException {
////        BufferedReader reader = new BufferedReader(new FileReader(file));
////
////        try {
////
////            int i = Integer.parseInt(reader.readLine());
////            list = new Vertex[i];
////
////            for (int v = 0; v < i; v++) {
////                String line = reader.readLine();
////
////                String[] fields = line.split("\\|");
////
////                if (fields.length == 2) {
////
////                    list[v] = new Vertex(fields[0], null, null);
////
////                }
////                if (fields.length == 3) {
////
////                    list[v] = new Vertex(fields[0], fields[2], null);
////                }
////
////            }
////
////
////            String line3;
////            while ((line3 = reader.readLine()) != null) {
////
////                String[] fields3 = line3.split("\\|");
////
////                int v1 = NameIndex(fields3[0]);
////                int v2 = NameIndex(fields3[1]);
////
////                list[v1].ajNext = new neighbour(list[v1].ajNext,v2);
////                list[v2].ajNext = new neighbour(list[v2].ajNext,v1);
////
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////
////    }
////
////
////    public int NameIndex(String name) {
////        for (int v = 0; v < list.length; v++) {
////            if (list[v].name.equalsIgnoreCase(name)) {
////                return v;
////            } else {
////                continue;
////            }
////        }
////
////        return -1;
////    }
////
////    public Vertex[] subGraph(String college) {
////        college = college.toLowerCase();
////        int num = 0;
////        int i = 0;
////        int ll = list.length;
////        while (i < ll) {
////            if (list[i].school != null) {
////                if (list[i].school.equalsIgnoreCase(college))
////                    num++;
////            }
////            i++;
////        }
//////        for (int i = 0; i < list.length; i++) {
//////            if (list[i].school != null) {
//////                if (list[i].school.equalsIgnoreCase(school)) {
//////                    n++;
//////                } else {
//////                    continue;
//////                }
//////            } else {
//////                continue;
//////            }
//////        }
////        if (num == 0) {
////            System.out.println("No students goes to " + college);
////            System.out.println("");
////            return null;
////        }
////        Vertex[] subgraph = new Vertex[num];
////        // adds the vertices to the subgraph
////        int v = 0;
////        int j = 0;
////        int kk = list.length;
////        while (j < kk) {
////            if (list[i].school != null) {
////                if (list[i].school.equalsIgnoreCase(college)) {
////                    subgraph[v] = new Vertex(list[i].name, college, null);
////                    v++;
////                }
////            }
////            j++;
////        }
//////        for (int i = 0; i < list.length; i++) {
//////            if (list[i].school != null) {
//////                if (list[i].school.equalsIgnoreCase(school)) {
//////                    subgraph[v] = new Vertex(list[i].name,school,null);
//////                    v++;
//////                } else {
//////                    continue;
//////                }
//////            } else {
//////                continue;
//////            }
//////        }
////
////        int k = 0;
////        int jj = subgraph.length;
////        while (k < jj) {
////            int vert = NameIndex(subgraph[i].name);
////            neighbour bro = list[vert].ajNext;
////            neighbour newBro = new neighbour(null, -1);
////
////            while (bro != null) {
////
////                if (list[bro.Vertexnum].school != null) {
////                    if (list[bro.Vertexnum].school.equalsIgnoreCase(college)) {
////
////                        if (subgraph[i].ajNext != null) {
////                            newBro.next = new neighbour(null, bro.Vertexnum);
////                            newBro = newBro.next;
////                            bro = bro.next;
////                        } else {
////                            neighbour sis = new neighbour(null, bro.Vertexnum);
////                            newBro = sis;
////                            subgraph[i].ajNext = newBro;
////                            bro = bro.next;
////                        }
////                    } else {
////                        bro = bro.next;
////                    }
////                } else {
////                    bro = bro.next;
////                }
////
////            }
////            k++;
////        }
////
//////        for (int i = 0; i < subgraph.length; i++) {
//////
//////            int vert = NameIndex(subgraph[i].name);
//////            neighbour person = list[vert].ajNext;
//////            neighbour current = new neighbour(null,-1);
//////
//////            while (person != null) {
//////
//////                if (list[person.Vertexnum].school != null) {
//////                    if (list[person.Vertexnum].school
//////                            .equalsIgnoreCase(school)) {
//////
//////                        if (subgraph[i].ajNext == null) {
//////                            neighbour neig = new neighbour(null,person.Vertexnum);
//////                            current = neig;
//////                            subgraph[i].ajNext = current;
//////                            person = person.next;
//////                        } else {
//////                            current.next = new neighbour(null,person.Vertexnum);
//////                            current = current.next;
//////                            person = person.next;
//////                        }
//////                    } else {
//////                        person = person.next;
//////                    }
//////                } else {
//////                    person = person.next;
//////                }
//////
//////            }
//////        }
////
////        return subgraph;
////    }
////
////    public void ShortestPath(String first, String second) throws NoSuchElementException {
////
////        if (first == null || second == null) {
////            throw new NoSuchElementException("Invalid input");
////        }
////        Vertex friendOne = null;
////
////        int i = 0;
////        while (i < list.length) {
////            if (!list[i].name.equalsIgnoreCase(first)) {
////                continue;
////            } else {
////                friendOne = list[i];
////            }
////            i++;
////        }
////
//////        for (int i = 0; i < list.length; i++) {
//////            if (list[i].name.equalsIgnoreCase(start)) {
//////                friendOne = list[i];
//////            } else {
//////                continue;
//////            }
//////        }
////
////        if (friendOne == null) {
////            System.out.println("Invalid input: " + first + " was not found in list.");
////            return;
////        } else {
////            boolean boo = false;
////            int j = 0;
////            while (j < list.length) {
////                if (!list[j].name.equalsIgnoreCase(first)) {
////                    boo = true;
////                }
////                j++;
////            }
//////            boolean bool = false;
//////            for(int i = 0; i<list.length; i++){
//////                if(list[i].name.equalsIgnoreCase(second)){
//////                    bool = true;
//////                }
//////                continue;   // continue doing the thing
//////            }
////            if (boo == false) {
////                System.out.println(second + " not found in list."); //cant be found
////                return;
////            }
////
////            for (neighbour bro = friendOne.ajNext; bro != null; bro = bro.next) {
////                if (list[bro.Vertexnum].name.equalsIgnoreCase(second)) {
////                    System.out.println(first + " is already friends with "
////                            + second);
////                    return;
////                }
////            }
//////            neighbour bro = friendOne.ajNext;
//////            while (bro != null) {
//////                if (list[bro.Vertexnum].name.equalsIgnoreCase(second)) {
//////                    System.out.println(first + " is already friends with "
//////                            + second);
//////                    return;
//////                } else {
//////                    bro = bro.next;
//////                }
//////            }
//////            bro = friendOne.ajNext;
//////
//////        }
////
////            first = first.toLowerCase();
////            second = second.toLowerCase();
////
////            int endIndex;
////            Vertex ex;
////            neighbour bro2;
////            Queue que = new Queue();
////
////
////            Node[] checked = new Node[list.length];
////            Vertex[] edge = new Vertex[list.length];
////
//////        for (int i = 0; i < visited.length; i++) {
//////            visited[i] = new Node(null, -1, false);
//////        }
////            int ll = checked.length;
////            int k = 0;
////            while (k < ll) {
////                checked[i] = new Node(null, -1, false);
////            }
////            endIndex = NameIndex(second);
////            checked[endIndex].isVisited = true;
////            que.enqueue(list[endIndex]);
////
////            while (!que.isEmpty()) {
////                ex = que.dequeue();
////                //n = vert.ajNext;
////
////                for (bro2 = ex.ajNext; bro2 != null; bro2 = bro2.next) {
////                    if (!checked[bro2.Vertexnum].isVisited) {
////                        checked[bro2.Vertexnum].data = 1;
////                        edge[bro2.Vertexnum] = ex;
////                        checked[bro2.Vertexnum].isVisited = true;
////                        que.enqueue(list[bro2.Vertexnum]);
////                    }
////                }
////
//////            while (n != null) {
//////                if (checked[n.Vertexnum].isVisited == false) {
//////                    checked[n.Vertexnum].data = 1;
//////                    edge[n.Vertexnum] = vert;
//////                    checked[n.Vertexnum].isVisited = true;
//////
//////                    q.enqueue(list[n.Vertexnum]);
//////
//////                }
//////                n = n.next;
//////            }
////            }
////
////            if (checked[NameIndex(first)].data == -1 || first.equals(second)) {
////                System.out.println("No path exists between " + first + " and "
////                        + second + "!");
////            } else {
////                int begin = NameIndex(first);
////                while (!second.equals(list[begin].name)) {
////
////                    System.out.print(list[begin].name + "--");
////
////                    begin = NameIndex(edge[begin].name);
////                }
////                System.out.println(second);
////            }
////        }
////    }
////
////    public void Cliques(String school) {
////        school = school.toLowerCase();
////
////        Vertex[] schooll = subGraph(school);
////        if (schooll == null) {
////            return;
////        }
////        Vertex[] island = new Vertex[schooll.length];
////        ArrayList<Vertex[]> allIsland = new ArrayList<Vertex[]>();
////
////        int xxx = 0;
////        boolean again = true;
////        while (again == true) {
////            for (int i = 0; i < schooll.length; i++) {
////
////                if (xxx == 0) {
////                    if (schooll[i] != null) {
////
////                        island[xxx] = schooll[i];
////
////                        xxx = 1;
//////                        neighbour person = schooll[i].ajNext;
//////                        while (person != null) {
//////                            island[xxx] = list[person.Vertexnum];
//////
//////                            xxx++;
//////                            person = person.next;
//////                        }
////                        for (neighbour bro = schooll[i].ajNext; bro != null; bro = bro.next) {
////                            island[xxx] = list[bro.Vertexnum];
////                            xxx++;
////                        }
////                        continue;
//////                    } else {
//////                        continue;
//////                    }
////                    } else {
////                        int length = island.length;
////                        for (int k = 0; k < length; k++) {
////                            if (island[k] != null) {
////                                if (island[k] != null && schooll[i] != null) {
////                                    if (island[k].name.equals(schooll[i].name)) {
////                                        neighbour bro = schooll[i].ajNext;
////
////                                        boolean isthere = false;
////                                        while (bro != null) {
////
////                                            for (int d = 0; d < island.length; d++) {
////                                                if (island[d] != null) {
////                                                    if (list[bro.Vertexnum].name
////                                                            .equalsIgnoreCase(island[d].name)) {
////
////                                                        isthere = true;
////                                                        continue;
////                                                    } else {
////                                                        continue;
////                                                    }
////                                                } else {
////                                                    continue;
////                                                }
////                                            }
////                                            if (isthere != false) {
////                                                island[xxx] = list[bro.Vertexnum];
////                                                xxx++;
////                                                bro = bro.next;
////                                            } else {
////                                                bro = bro.next;
////                                            }
////                                        }
////                                    } else {
////
////                                        continue;
////                                    }
////                                } else {
////                                    continue;
////                                }
////                            } else {
////                                continue;
////                            }
////                        }
////
////                    }
////                }
////                int size = 0;
////                int ll = island.length;
////                int j = 0;
////                while (j < ll) {
////                    if (island[j] != null) {
////                        size++;
////                    }
////                    j++;
////                }
//////            for (int j = 0; j < ll; j++) {
//////                if (island[j] != null) {
//////                    size++;
//////                } else {
//////                    continue;
//////                }
//////            }
////
////                boolean[] islandd = new boolean[schooll.length];
////                int jj = schooll.length;
////                int kk = island.length;
////
////                for (int a = 0; a < jj; a++) {
////                    for (int b = 0; b < kk; b++) {
////                        if (island[b] != null && schooll[a] != null) {
////                            if (island[b].name
////                                    .equalsIgnoreCase(schooll[a].name)) {
////
////                                islandd[a] = true;
////                            } else {
////                                continue;
////                            }
////                        } else {
////                            continue;
////                        }
////                    }
////                }
////
//////            int x = 0;
//////            for (int a = 0; a < island.length; a++) {
//////                if (island[a] != null) {
//////                    x++;
//////                } else {
//////                    continue;
//////                }
//////            }
////
////                int xx = 0;
////                int aa = 0;
////                int bb = island.length;
////                while (aa < bb) {
////                    if (island[aa] != null) {
////                        xx++;
////                    }
////                    aa++;
////                }
////                allIsland.add(island);
////                island = new Vertex[schooll.length];
////                Vertex[] newSchool = new Vertex[schooll.length - xx];
////
////                int vert = 0;
////                for (int w = 0; w < schooll.length; w++) {
////
////                    if (islandd[w] != true && schooll[w] != null) {
////
////                        newSchool[vert] = schooll[w];
////                        vert++;
////                    } else {
////                        continue;
////                    }
////
////                }
////
////                schooll = newSchool;
////                xxx = 0;
////
////                if (schooll.length != 0) {
////                    again = true;
////                } else {
////                    again = false;
////                }
////
////            }
////            int x = 1;
////            for (int i = 0; i < allIsland.size(); i++) {
////
////                System.out.println("Clique " + x + ":");
////                x++;
////                int size = 0;
//////            for (int j = 0; j < allIsland.get(i).length; j++) {
//////                if (allIsland.get(i)[j] != null) {
//////                    size++;
//////                } else {
//////                    continue;
//////                }
//////            }
////                int abc = 0;
////                int xyz = allIsland.get(i).length;
////                while (abc < xyz) {
////                    if (allIsland.get(i)[abc] != null) {
////                        size++;
////                    }
////                    abc++;
////                }
////
////                System.out.println(size);
//////                for (int k = 0; k < allIsland.get(i).length; k++) {
//////                    if (allIsland.get(i)[k] != null) {
//////                        System.out.println(allIsland.get(i)[k].name + "|y|"
//////                                + allIsland.get(i)[k].school);
//////                    } else {
//////                        continue;
//////                    }
//////                }
////
////                int vv = 0;
////                int jk = allIsland.get(i).length;
////                while(vv < jk){
////                    if (allIsland.get(i)[vv] != null) {
////                        System.out.println(allIsland.get(i)[vv].name + "|y|"
////                                + allIsland.get(i)[vv].school);
////                    }
////                    vv++;
////                }
////                for (int p = 0; p < allIsland.get(i).length; p++) {
////                    if (allIsland.get(i)[p] != null) {
////                        neighbour person = allIsland.get(i)[p].ajNext;
////                        while (person != null) {
////                            if (person.Vertexnum < NameIndex(allIsland.get(i)[p].name)) {
////                                person = person.next;
////                            } else {
////                                if (allIsland.get(i)[p] != null) {
////                                    boolean there = false;
//////                                    for (int y = 0; y < allIsland.get(i).length; y++) {
//////                                        if (allIsland.get(i)[y] != null) {
//////                                            if (list[person.Vertexnum].name
//////                                                    .equals(allIsland.get(i)[y].name)) {
//////                                                there = true;
//////                                            } else {
//////                                                continue;
//////                                            }
//////                                        }
//////                                    }
////                                    int zz = 0;
////                                    int yy = allIsland.get(i).length;
////                                    while(zz < yy){
////                                        if (allIsland.get(i)[zz] != null) {
////                                            if (list[person.Vertexnum].name
////                                                    .equals(allIsland.get(i)[zz].name)) {
////                                                there = true;
////                                            }
////                                        }
////                                        zz++;
////                                    }
////                                    if (!there) {
////                                        continue;
////                                    }
////                                } else {
////                                    System.out.println(allIsland.get(i)[p].name
////                                            + "|"
////                                            + list[person.Vertexnum].name);
////                                }
////                                person = person.next;
////                            }
////                        }
////                    } else {
////                        continue;
////                    }
////                }
////            }
////        }
////    }
////    public void connectors() {
//////        for(int i = 0; i < list.length; i++) {
//////            Vertex v = list[i];
//////            if(!v.visited) {
//////                dfsn = 0;
//////                start = i;
//////                df(v);
//////            }else{
//////                continue;
//////            }
//////        }
////        int i=0;
////        int l = list.length;
////        while(i < l){
////            Vertex xyz = list[i];
////            if(!xyz.visited){
////                dfsn = 0;
////                start = i;
////                dfs(xyz);
////            }
////            i++;
////        }
////        System.out.println("Connectors: ");
//////        for (int i = 0; i<connectors.size(); i++){
//////            if (i==connectors.size()-1){
//////                System.out.print(connectors.get(i));
//////            }else{
//////                System.out.print(connectors.get(i)+",");}
//////        }
////        int j = 0;
////        int k = connectors.size();
////        while( j < k){
////            if(i != connectors.size()-1){
////                System.out.print(connectors.get(i) + ", ");
////            }
////            else{
////                System.out.print(connectors.get(i));
////            }
////        }
////    }
////    private void dfs(Vertex vertex) {
////        System.out.println("Starting Vertex: " + vertex.name);
////       // neighbour neig = vertex.ajNext;
////
////        dfsn++;
////        vertex.dfsnum = dfsn;
////        vertex.back = dfsn;
////        vertex.visited = true;
////
////        neighbour bro = vertex.ajNext;
////        while(bro != null){
////            Vertex w = list[bro.Vertexnum];
////            System.out.println(vertex.name+ "'s friend "+w.name+" came next");
////            if (w.visited){
////                System.out.println(w.name+" was visited already.");
////                vertex.back = Math.min(vertex.back, w.dfsnum);
////            }
////            else{
////                System.out.println("Recursive DFS from "+w.name);
////                dfs(w);
////                if (NameIndex(vertex.name)!= start && vertex.dfsnum <= w.back){
////                    if(!connectors.contains(vertex.name)){
////                        System.out.println("Adding "+vertex.name+" to the connectors list.");
////                        connectors.add(vertex.name);
////
////                    }
////                }
////                if(vertex.dfsnum > w.back){//checky checky
////                    vertex.back = Math.min(vertex.back, w.back);
////                }
////            }
////            bro = bro.next;
////        }
//////        for(neig = vertex.ajNext; neig!=null; neig = neig.next){
//////
//////            Vertex w = list[neig.Vertexnum];
//////            System.out.println(vertex.name+ "'s friend "+w.name+" came next");
//////            if (w.visited){
//////                System.out.println(w.name+" was visited already.");
//////                vertex.back = Math.min(vertex.back, w.dfsnum);
//////            }
//////            else{
//////                System.out.println("Recursive DFS from "+w.name);
//////                dfs(w);
//////                if (NameIndex(vertex.name)!= start && vertex.dfsnum <= w.back){
//////                    if(!connectors.contains(vertex.name)){
//////                        System.out.println("Adding "+vertex.name+" to the connectors list.");
//////                        connectors.add(vertex.name);
//////
//////                    }
//////                }
//////                if(vertex.dfsnum > w.back){//checky checky
//////                    vertex.back = Math.min(vertex.back, w.back);
//////                }
//////            }
//////        }
////    }
////    private void printGraph(Vertex[] graph){
////
////        int n = graph.length;
////        System.out.println(n);
////        for (int p = 0; p < n; p++) {
////            System.out.println(graph[p].name + "|y|" + graph[p].school);
////        }
////
////        for (int i = 0; i < graph.length; i++) {
////
//////            neighbour person = graph[i].ajNext;
//////            while (person != null) {
//////                if (person.Vertexnum < NameIndex(graph[i].name)) {
//////                    person = person.next;
//////                } else {
//////                    System.out.println(graph[i].name + "|" + list[person.Vertexnum].name);
//////                    person = person.next;
//////                }
//////            }
////
////            for(neighbour bro = graph[i].ajNext; bro != null; bro = bro.next){
////                if (bro.Vertexnum < NameIndex(graph[i].name)) {
////                } else {
////                    System.out.println(graph[i].name + "|" + list[bro.Vertexnum].name);
////                }
////            }
////        }
////    }
////}
//
//
//
////-----------------------------------------------------------------------
//
//    //import java.io.*;
//    //import java.util.*;
//
//    class Friend {
//        int VNum;
//        Friend next;
//        int sum;
//
//        public Friend(Friend b, int k) {
//            next = b;
//            VNum = k;
//        }
//    }
//
//    class Vertex {
//        Vertex next;
//        int tot;
//        int sumx;
//        boolean was;
//        boolean wasSeen;
//        String location;
//        Friend ax;
//        Friend xList;
//        boolean check;
//        int rev;
//        String title;
//
//        public Vertex(String lx, String s, Friend f) {
//            location = lx;
//            xList = f;
//            title = s;
//        }
//    }
//
//    class Node {
//        int length;
//        boolean Seen;
//        boolean visit;
//        Node next;
//        int total;
//
//        public Node(Node x1, int i, boolean b) {
//            Seen = false;
//            next = null;
//            length = -1;
//        }
//    }
//
//    class FriendQ {
//
//        private Vertex ex;
//        private boolean occ;
//        private int status;
//        private int size;
//
//        public FriendQ() {
//            ex = null;
//            size = 0;
//            occ = false;
//            status = 1;
//        }
//
//        public Vertex UnFriendQ()
//                throws NoSuchElementException {
//
//            if(status == 0){
//                occ = false;
//            }
//            if (ex == null) {
//                throw new NoSuchElementException("There are no Friends");
//            }
//            Vertex data = ex.next;
//            if (ex == ex.next) {
//                ex = null;
//                occ = false;
//            } else {
//                ex.next = ex.next.next;
//                status = status-1;
//            }
//            size--;
//            status++;
//            return data;
//        }
//
//        public int StatCheck(){
//            occ = true;
//            return status;
//        }
//
//        public boolean isOCC(){
//            status--;
//            return occ;
//        }
//
//        public boolean isEmpty() {
//            return size == 0;
//        }
//
//        public void xFriendQ(Vertex v) {
//
//            if(status != 0){
//                occ = false;
//            }
//            Vertex newItem = new Vertex(v.location, v.title, v.xList);
//            if (ex == null) {
//                newItem.next = newItem;
//                occ = true;
//            } else {
//                newItem.next = ex.next;
//                ex.next = newItem;
//                status = status+1;
//            }
//            size++;
//            status--;
//            ex = newItem;
//        }
//    }
//
//    public class Friends {
//
//        static Vertex[] xLists;
//
//        private void DisplayGraph(Vertex[] graph){
//
//            int n = graph.length;
//            FriendQ ll = new FriendQ();
//            boolean kx;
//
//            if(graph[0] != null){
//                ll.xFriendQ(graph[0]);
//                kx = ll.isOCC();
//            }
//
//            if(!ll.isEmpty()){
//                kx = false;
//            }
//
//            while(!ll.isEmpty()){
//                ll.UnFriendQ();
//            }
//
//            System.out.println(n);
//            for (int p = 0; p < n; p++) {
//                if(!ll.isEmpty()){
//                    kx = false;
//                }
//                System.out.println(graph[p].title + "|y|" + graph[p].location);
//            }
//
//            for (int i = 0; i < graph.length; i++) {
//                Friend person = graph[i].xList;
//                while (person != null) {
//                    if(!ll.isEmpty()){
//                        kx = false;
//                    }
//                    if (person.VNum < titleIndex(graph[i].title)) {
//                        person = person.next;
//                        kx = true;
//                    } else {
//                        if(!ll.isEmpty()){
//                            kx = false;
//                        }
//                        System.out.println(graph[i].title + "|"
//                                + xLists[person.VNum].title);
//                        person = person.next;
//                    }
//                }
//            }
//        }
//
//        public void Graph(String file)
//                throws FileNotFoundException {
//
//            BufferedReader text = new BufferedReader(new FileReader(file));
//            try {
//
//                int i = Integer.parseInt(text.readLine());
//                xLists = new Vertex[i];
//
//                for (int v = 0; v < i; v++) {
//                    String line = text.readLine();
//
//                    String[] fields = line.split("\\|");
//
//                    if (fields.length == 2) {
//
//                        xLists[v] = new Vertex(fields[0], null, null);
//
//                    }
//                    if (fields.length == 3) {
//
//                        xLists[v] = new Vertex(fields[2], fields[0], null);
//                    }
//
//                }
//
//
//                String line3;
//                while ((line3 = text.readLine()) != null) {
//
//                    String[] fields3 = line3.split("\\|");
//
//                    int v1 = titleIndex(fields3[0]);
//                    int v2 = titleIndex(fields3[1]);
//
//                    xLists[v1].xList = new Friend(xLists[v1].xList, v2);
//                    xLists[v2].xList = new Friend(xLists[v2].xList, v1);
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//        }
//
//
//        public int titleIndex(String title) {
//            for (int v = 0; v < xLists.length; v++) {
//                if (xLists[v].title.equalsIgnoreCase(title)) {
//                    return v;
//                } else {
//                    continue;
//                }
//            }
//
//            return -1;
//        }
//
//
//        public Vertex[] subGraph(String location) {
//            location = location.toLowerCase();
//            int n = 0;
//            for (int i = 0; i < xLists.length; i++) {
//                if (xLists[i].location != null) {
//                    if (xLists[i].location.equalsIgnoreCase(location)) {
//                        n++;
//                    } else {
//                        continue;
//                    }
//                } else {
//                    continue;
//                }
//            }
//            if (n==0){
//                System.out.println("");
//                System.out.println("No one in graph goes to " + location);
//                return null;
//            }
//            Vertex[] subgraph = new Vertex[n];
//
//            int v = 0;
//            for (int i = 0; i < xLists.length; i++) {
//                if (xLists[i].location != null) {
//                    if (xLists[i].location.equalsIgnoreCase(location)) {
//                        subgraph[v] = new Vertex(location, xLists[i].title, null);
//                        v++;
//                    } else {
//                        continue;
//                    }
//                } else {
//                    continue;
//                }
//            }
//
//            for (int i = 0; i < subgraph.length; i++) {
//
//                int vert = titleIndex(subgraph[i].title);
//                Friend person = xLists[vert].xList;
//                Friend current = new Friend(null, -1);
//
//                while (person != null) {
//
//                    if (xLists[person.VNum].location != null) {
//                        if (xLists[person.VNum].location
//                                .equalsIgnoreCase(location)) {
//
//                            if (subgraph[i].xList == null) {
//                                Friend neig = new Friend(null, person.VNum);
//                                current = neig;
//                                subgraph[i].xList = current;
//                                person = person.next;
//                            } else {
//                                current.next = new Friend(null, person.VNum);
//                                current = current.next;
//                                person = person.next;
//                            }
//                        } else {
//                            person = person.next;
//                        }
//                    } else {
//                        person = person.next;
//                    }
//
//                }
//            }
//
//            return subgraph;
//        }
//
//        public void islands(String location) {
//            location = location.toLowerCase();
//
//            Vertex[] entirelocation = subGraph(location);
//            if (entirelocation == null){
//                return;
//            }
//            Vertex[] clique = new Vertex[entirelocation.length];
//            ArrayList<Vertex[]> allCliques = new ArrayList<Vertex[]>();
//            System.out.println("");
//
//            int v1 = 0;
//            boolean again = true;
//            while (again == true) {
//                for (int i = 0; i < entirelocation.length; i++) {
//
//                    if (v1 == 0) {
//                        if (entirelocation[i] != null) {
//
//                            clique[v1] = entirelocation[i];
//
//                            v1 = 1;
//                            Friend person = entirelocation[i].xList;
//                            while (person != null) {
//                                clique[v1] = xLists[person.VNum];
//
//                                v1++;
//                                person = person.next;
//                            }
//                            continue;
//                        } else {
//                            continue;
//                        }
//                    } else {
//
//                        for (int k = 0; k < clique.length; k++) {
//                            if (clique[k] == null) {
//                                continue;
//                            } else {
//                                if (clique[k] != null && entirelocation[i] != null) {
//                                    if (clique[k].title.equals(entirelocation[i].title)) {
//                                        Friend person = entirelocation[i].xList;
//
//                                        boolean there = false;
//                                        while (person != null) {
//
//                                            for (int d = 0; d < clique.length; d++) {
//                                                if (clique[d] != null) {
//                                                    if (xLists[person.VNum].title
//                                                            .equalsIgnoreCase(clique[d].title)) {
//
//                                                        there = true;
//                                                        continue;
//                                                    } else {
//                                                        continue;
//                                                    }
//                                                } else {
//                                                    continue;
//                                                }
//                                            }
//                                            if (there == false) {
//
//                                                clique[v1] = xLists[person.VNum];
//
//                                                v1++;
//                                                person = person.next;
//                                            }
//                                            if (there == true) {
//
//                                                person = person.next;
//                                            }
//
//                                        }
//                                    } else {
//
//                                        continue;
//                                    }
//                                } else {
//                                    continue;
//                                }
//                            }
//                        }
//
//                    }
//                }
//                int size = 0;
//                for (int j = 0; j < clique.length; j++) {
//                    if (clique[j] != null) {
//                        size++;
//                    } else {
//                        continue;
//                    }
//                }
//
//                boolean[] cliqued = new boolean[entirelocation.length];
//
//                for (int y = 0; y < entirelocation.length; y++) {
//                    for (int u = 0; u < clique.length; u++) {
//                        if (clique[u] != null && entirelocation[y] != null) {
//                            if (clique[u].title
//                                    .equalsIgnoreCase(entirelocation[y].title)) {
//
//                                cliqued[y] = true;
//                            } else {
//                                continue;
//                            }
//                        } else {
//                            continue;
//                        }
//                    }
//                }
//
//                int x = 0;
//                for (int a = 0; a < clique.length; a++) {
//                    if (clique[a] != null) {
//                        x++;
//                    } else {
//                        continue;
//                    }
//                }
//                allCliques.add(clique);
//                clique = new Vertex[entirelocation.length];
//                Vertex[] newlocation = new Vertex[entirelocation.length - x];
//
//                int vert = 0;
//                for (int w = 0; w < entirelocation.length; w++) {
//
//                    if (cliqued[w] != true && entirelocation[w] != null) {
//
//                        newlocation[vert] = entirelocation[w];
//                        vert++;
//                    } else {
//                        continue;
//                    }
//
//                }
//
//                entirelocation = newlocation;
//                v1 = 0;
//
//                if (entirelocation.length == 0) {
//
//                    again = false;
//
//                } else {
//
//                    again = true;
//                }
//
//            }
//            System.out.println("");
//            int x = 1;
//            for (int i = 0; i < allCliques.size(); i++) {
//
//                System.out.println("");
//                System.out.println("Clique " + x + ":");
//                System.out.println("");
//                x++;
//                int size = 0;
//                for (int j = 0; j < allCliques.get(i).length; j++) {
//                    if (allCliques.get(i)[j] != null) {
//                        size++;
//                    } else {
//                        continue;
//                    }
//                }
//
//                System.out.println(size);
//                for (int k = 0; k < allCliques.get(i).length; k++) {
//                    if (allCliques.get(i)[k] != null) {
//                        System.out.println(allCliques.get(i)[k].title + "|y|"
//                                + allCliques.get(i)[k].location);
//                    } else {
//                        continue;
//                    }
//                }
//                for (int p = 0; p < allCliques.get(i).length; p++) {
//                    if (allCliques.get(i)[p] != null) {
//                        Friend person = allCliques.get(i)[p].xList;
//                        while (person != null) {
//                            if (person.VNum < titleIndex(allCliques.get(i)[p].title)) {
//                                person = person.next;
//                            } else {
//                                if (allCliques.get(i)[p] != null) {
//                                    boolean there = false;
//                                    for (int y = 0; y < allCliques.get(i).length; y++) {
//                                        if (allCliques.get(i)[y] != null) {
//                                            if (xLists[person.VNum].title
//                                                    .equals(allCliques.get(i)[y].title)) {
//                                                there = true;
//                                            } else {
//                                                continue;
//                                            }
//                                        }
//                                    }
//                                    if (there == true) {
//                                        System.out
//                                                .println(allCliques.get(i)[p].title
//                                                        + "|"
//                                                        + xLists[person.VNum].title);
//                                    }
//                                } else {
//                                    continue;
//                                }
//                                person = person.next;
//                            }
//                        }
//                    } else {
//                        continue;
//                    }
//                }
//            }
//        }
//
//        public void PathThatIsTheShortest(String start, String end) {
//
//            System.out.println("");
//            if (start == null || end == null) {
//                System.out.println("One or more input was invalid.");
//            }
//            Vertex friend_A = null;
//
//
//            for (int i = 0; i < xLists.length; i++) {
//                if (xLists[i].title.equalsIgnoreCase(start)) {
//                    friend_A = xLists[i];
//                } else {
//                    continue;
//                }
//            }
//
//            if (friend_A == null) {
//                System.out.println("Invalid input: " + start
//                        + " was not found in list.");
//                return;
//            } else {
//                boolean bool = false;
//                for(int i = 0; i<xLists.length; i++){
//                    if(xLists[i].title.equalsIgnoreCase(end)){
//                        bool = true;
//                    }
//                    continue;
//                }
//
//                if (bool == false){
//                    System.out.println(end + " not found in list.");
//                    return;
//                }
//
//                Friend person = friend_A.xList;
//
//                while (person != null) {
//                    if (xLists[person.VNum].title.equalsIgnoreCase(end)) {
//                        System.out.println(start + " is already friendz with "
//                                + end);
//                        return;
//                    } else {
//                        person = person.next;
//                    }
//                }
//                person = friend_A.xList;
//
//            }
//
//            start = start.toLowerCase();
//            end = end.toLowerCase();
//
//            int endIndex;
//            Vertex vert;
//            Friend n;
//            FriendQ q = new FriendQ();
//
//
//            Node[] wasSeen = new Node[xLists.length];
//            Vertex[] path = new Vertex[xLists.length];
//
//            for (int i = 0; i < wasSeen.length; i++) {
//                wasSeen[i] = new Node(null, -1, false);
//            }
//            endIndex = titleIndex(end);
//            wasSeen[endIndex].Seen = true;
//            q.xFriendQ(xLists[endIndex]);
//
//            while (!q.isEmpty()) {
//                vert = q.UnFriendQ();
//                n = vert.xList;
//
//                while (n != null) {
//                    if (wasSeen[n.VNum].Seen == false) {
//                        wasSeen[n.VNum].length = 1;
//                        path[n.VNum] = vert;
//                        wasSeen[n.VNum].Seen = true;
//
//                        q.xFriendQ(xLists[n.VNum]);
//
//                    }
//                    n = n.next;
//                }
//            }
//
//            if (wasSeen[titleIndex(start)].length == -1 || start.equals(end)) {
//                System.out.println("No path exists between " + start + " and "
//                        + end + "!");
//            } else {
//                int begin = titleIndex(start);
//                while (!end.equals(xLists[begin].title)) {
//
//                    System.out.print(xLists[begin].title + "--");
//
//                    begin = titleIndex(path[begin].title);
//                }
//                System.out.println(end);
//            }
//        }
//
//        ArrayList<String> connectors = new ArrayList<String>();
//        int dfsn = 0;
//        int start = 0;
//
//        private void df(Vertex v) {
//
//            System.out.println("");
//            System.out.println("Starting Vertex: " + v.title);
//            Friend neig = v.xList;
//
//            dfsn++;
//            v.sumx = dfsn;
//            v.rev = dfsn;
//            v.wasSeen = true;
//
//            for(neig = v.xList; neig!=null; neig = neig.next){
//
//                Vertex w = xLists[neig.VNum];
//                System.out.println(v.title+ "'s friend "+w.title+" came next");
//                if (!w.wasSeen){
//                    System.out.println("Recursive DFS from "+w.title);
//                    df(w);
//                    if (titleIndex(v.title)!= start && v.sumx <= w.rev){
//                        if(!connectors.contains(v.title)){
//                            System.out.println("Adding "+v.title+" to the connectors list.");
//                            connectors.add(v.title);
//                        }
//                    }
//                    if(v.sumx > w.rev){
//                        v.rev = Math.min(v.rev, w.rev);
//                    }
//                }
//                else{
//                    System.out.println(w.title+" was wasSeen already.");
//                    v.rev = Math.min(v.rev, w.sumx);
//                }
//            }
//        }
//
//        public void connectors() {
//            for(int i = 0; i < xLists.length; i++) {
//                Vertex v = xLists[i];
//                if(!v.wasSeen) {
//                    dfsn = 0;
//                    start = i;
//                    df(v);
//                }else{
//                    continue;
//                }
//            }
//            System.out.println("");
//            System.out.println("Connectors: ");
//            System.out.println("");
//            for (int i = 0; i<connectors.size(); i++){
//                if (i==connectors.size()-1){
//                    System.out.print(connectors.get(i));
//                }else{
//                    System.out.print(connectors.get(i)+",");}
//            }
//            System.out.println("");
//        }
//
//        static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
//        public static void main(String[] args) throws IOException {
//            Friends friendz = new Friends();
//            System.out.println("Enter file title: ");
//            String file = keyboard.readLine();
//            BufferedReader text = new BufferedReader(new FileReader(file));
//            friendz.Graph(file);
//
//            while(true){
//                int choice = getMenuChoice();
//
//                if(choice == 1){
//                    System.out.println("Enter location title: ");
//                    String location = keyboard.readLine();
//
//                    Vertex[] graphed = friendz.subGraph(location);
//                    if (graphed == null){
//                        continue;
//                    }
//                    System.out.println("");
//                    friendz.DisplayGraph(graphed);
//
//                }
//                else if(choice == 2){
//                    System.out.println("Enter first friendz title: ");
//                    String firsttitle = keyboard.readLine();
//                    System.out.println("Enter second friendz title: ");
//                    String secondtitle = keyboard.readLine();
//                    friendz.PathThatIsTheShortest(firsttitle, secondtitle);
//
//                }
//                else if(choice == 3){
//                    System.out.println("Enter title of location: ");
//                    String schoo = keyboard.readLine();
//                    friendz.islands(schoo);
//
//                }
//                else if(choice == 4){
//                    friendz.connectors();
//                }
//                else  if(choice == 5){
//                    return;
//                }
//            }
//        }
//
//        public static int getMenuChoice() throws IOException{
//            System.out.println();
//            System.out.println("Menu:");
//            System.out.println("1. Find Subgraph");
//            System.out.println("2. Shortest Path");
//            System.out.println("3. Cliques");
//            System.out.println("4. Connectors");
//            System.out.println("5. Quit");
//
//            System.out.print("Choice (1-5)? ");
//            String choice = keyboard.readLine();
//            int choices = Integer.parseInt(choice);
//
//            while(choices < 1 || choices > 5){
//                System.out.println(choice);
//                System.out.print("Please choose valid option.");
//                String x = keyboard.readLine();
//                choices = Integer.parseInt(x);
//            }
//            return choices;
//
//        }
//
//    }
//
//}
