////
////
////
////import java.io.FileNotFoundException;
////import java.util.Scanner;
////
////public class Friends{
////	
////
////	public static void main(String[] args) throws FileNotFoundException
////	{	
////		//Input variables
////		Scanner userInput = new Scanner(System.in);
////		GraphMethods graph = new GraphMethods();
////		System.out.print("Please enter the friendship graph file name: ");
////		
////		String fileName = userInput.nextLine();
////		
////		//Creates a new graph object.
////		graph.graphCreate(fileName);
////		
////		//This just calls the print utility to make sure that graph was built properly.
////		//graph.print();
////		
////		//Condition to break out of the Options Menu
////		boolean quit = false;
////		
////		//Prints the options menu and takes in user Input.
////		while(!quit)
////		{	
////		System.out.println("\nFriendship Graph Options:\n");
////		System.out.println("1. Subgraph: Students at a school");
////		System.out.println("2. Shortest path: Intro Chain");
////		System.out.println("3. Connected Islands: Cliques");
////		System.out.println("4. Connectors: Friends who keep friends together");
////		System.out.println("5. Quit");
////		
////		System.out.println("\nPlease enter your choice: ");
////
////		int userChoice = userInput.nextInt();
////
////			switch(userChoice)
////			{
////				case 1:
////					System.out.println("Enter the name of the school: ");
////					userInput.nextLine();
////					String schoolSubGraph = userInput.nextLine();
////					graph.subGraph(schoolSubGraph);
////					break;
////				case 2:
////					System.out.println("Enter the name of the first person: ");
////					userInput.nextLine();
////					String firstPerson = userInput.nextLine();
////					System.out.println("Enter the name of the second person: ");
////					String secondPerson = userInput.nextLine();
////					graph.shortestPath(firstPerson, secondPerson);
////					break;
////				case 3:
////					System.out.println("Enter the name of the school: ");
////					userInput.nextLine();
////					String schoolIsland = userInput.nextLine();
////					graph.connectedIslands(schoolIsland);
////					break;
////				case 4:
////					graph.graphCreate(fileName);
////					graph.connectors();
////					break;
////				case 5:
////					quit = true;
////			}
////		}
////		return;
////	}
////}
//
//
//
//
////Leo Yu and Jeremy Priestner
//
//
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Scanner;
//import java.util.StringTokenizer;
//
//
//
//public class Friends {
//	
//	static Scanner kb;
//	static FriendGraph friendGraph = null;
//	
//	static void build(String fileName) 
//	throws IOException {
//			
//		HashMap<String, Integer> nameTable = new HashMap<String,Integer>(1000,2.0f);
//		int index1, index2;
//		User user1, user2;
//		
//		Scanner sc = new Scanner(new File(fileName));
//					
//		//first line is number of vertices
//		int n = sc.nextInt();
//		ArrayList<User> userList = new ArrayList<User>(n);
//		sc.nextLine();//skip blank line
//		
//		//read student info from file and add to arraylist
//		//and to the indexing hashmap "nameTable" with key-value pair <Name,Index in ArrayList al>
//		for (int i = 0 ; i < n ; i++) {
//			
//			//go through each line and tokenize it using "|" as a delimiter
//			String str = sc.nextLine();
//			StringTokenizer st = new StringTokenizer(str,"|");
//			
//			String name = st.nextToken().toLowerCase();
//			String yesNo = st.nextToken().toLowerCase();
//			
//			User user = new User(name);
//			if (yesNo.equals("y")) {
//				String school = st.nextToken().toLowerCase();
//				user.school = school;
//			}
//
//			userList.add(user);
//			
//			//debug
//			System.out.println("\tUser: " + user.toString());
//			
//			nameTable.put(name,i);
//		}
//		
//		//build the neighbor linked lists from the edge information contained in the input file
//		while(sc.hasNext()) {
//			StringTokenizer st = new StringTokenizer(sc.next(),"|");
//			//hashmap access
//			index1 = nameTable.get(st.nextToken());
//			index2 = nameTable.get(st.nextToken());
//			//arraylist access
//			user1 = userList.get(index1);
//			user2 = userList.get(index2);
//			
//			if (user1.firstNeighbor == null)
//				user1.firstNeighbor = new Neighbor(index2, null);
//			else {
//				Neighbor tmp = user1.firstNeighbor;
//				user1.firstNeighbor = new Neighbor(index2, tmp);
//			}
//			
//			if (user2.firstNeighbor == null)
//				user2.firstNeighbor = new Neighbor(index1, null);
//			else {
//				Neighbor tmp = user2.firstNeighbor;
//				user2.firstNeighbor = new Neighbor(index1, tmp);
//			}
//			
//			//debug
//			System.out.println("\tedge: " + user1.name + "|" + userList.get(user1.firstNeighbor.neighborIndex).name);
//		}
//		
//		friendGraph = new FriendGraph(userList, nameTable);
//    }
//	
//	static void subgraph(){
//		System.out.println("Enter the name of the school you want a subgraph for: ");
//		kb.nextLine();
//		String str = kb.nextLine();
//		
//		//original code
//		//friendGraph.subgraph(str).printGraph();
//		
//		//modified code w/ option to keep/replace original graph
//		FriendGraph outputGraph = friendGraph.subgraph(str);
//		if(outputGraph == null){
//			System.out.println("Subgraph of " + str + " students is empty");
//		}else{
//			outputGraph.printGraph();
//		}
//		
//		System.out.println("Replace current graph with subgraph of " + str + " students? (y/n)");
//		while (true) {
//			str = kb.next();
//			if (str.equalsIgnoreCase("y")) {
//				friendGraph = outputGraph;
//				break;
//			}
//			else if (str.equalsIgnoreCase("n")) {
//				break;
//			}
//			else
//				System.out.println("Not valid input. Try again.");
//		}
//	}
//	
//	static void shortestPath(){
//		System.out.println("Enter the name of the start user: ");
//		String str1 = kb.next();
//		System.out.println("Enter the name of the end user: ");
//		String str2 = kb.next();
//		
//		//throws exception if there is know path between start and end user
//		try{
//			friendGraph.shortestPath(str1, str2);
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	static void islands(){
//		kb.nextLine();
//		System.out.println("Enter the name of the school you want the cliques for: ");
//		String str = kb.nextLine();
//		friendGraph.islands(str);
//		System.out.println("");
//	}
//	
//	static void connectors() {
//		friendGraph.connectors();
//	}
//	
//	static void printGraph() {
//		if(friendGraph == null){
//			System.out.println("Graph is empty");
//		}else{
//			friendGraph.printGraph();
//		}
//	}
//			    
//	static void printHash() {
//		friendGraph.printHash();
//	}
//	
//	public static void main(String[] args) throws IOException {
//
//	    	kb = new Scanner(System.in);
//	    	
//	    	while (true) {
//	    		System.out.println("Type the name of the input graph file: ");
//	    		String line = kb.next();
//	    		if (line.length() == 0) { continue; }
//	    		build(line);
//	    		break;
//	    		} 
//	    	
//	    	while (true) {
//	    		System.out.println("");
//	    		System.out.println("Do you want to run:" +
//	    				"\n(1) subgraph" +
//	    				"\n(2) shortest path" +
//	    				"\n(3) connected islands" +
//	    				"\n(4) connectors" +
//	    				"\n(5) print graph" +
//	    				"\n(6) print hash table" +
//	    				"\n(7) quit");
//	    		String line = kb.next();
//	    		if (line.equals("")){
//	    			continue;
//	    		}
//
//	    		switch(line.charAt(0)) {
//	    		case '1': subgraph(); break;
//	    		case '2': shortestPath(); break;
//	    		case '3': islands(); break;			
//	    		case '4': connectors(); break;
//	    		case '5': printGraph(); break;
//	    		case '6': printHash(); break;
//	    		case '7': System.exit(0);
//	    		}
//	    	}
//	    }
//}


import java.io.*;
import java.util.*;

class Friend {
	int VNum;
	Friend next;
	int sum;

	public Friend(Friend b, int k) {
		next = b;
		VNum = k;
	}
}

class Vertex {
	Vertex next;
	int tot;
	int sumx;
	boolean was;
	boolean wasSeen;
	String location;
	Friend ax;
	Friend xList; 
	boolean check;
	int rev;
	String title;
	
	public Vertex(String lx, String s, Friend f) {
		location = lx;
		xList = f;
		title = s;
	}
}

class Node {
	int length;
	boolean Seen;
	boolean visit;
	Node next;
	int total;
	
	public Node(Node x1, int i, boolean b) {
		Seen = false;
		next = null;
		length = -1;
	}
}

class FriendQ {

	private Vertex ex;
	private boolean occ;
	private int status;
	private int size;

	public FriendQ() {
		ex = null;
		size = 0;
		occ = false;
		status = 1;
	}
	
	public Vertex UnFriendQ() 
			throws NoSuchElementException {
		
		if(status == 0){
			occ = false;
		}
		if (ex == null) {
			throw new NoSuchElementException("There are no Friends");
		}
		Vertex data = ex.next;
		if (ex == ex.next) {
			ex = null;
			occ = false;
		} else {
			ex.next = ex.next.next;
			status = status-1;
		}
		size--;
		status++;
		return data;
	}
	
	public int StatCheck(){
		occ = true;
		return status;
	}
	
	public boolean isOCC(){
		status--;
		return occ;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public void xFriendQ(Vertex v) {
		
		if(status != 0){
			occ = false;
		}
		Vertex newItem = new Vertex(v.location, v.title, v.xList);
		if (ex == null) {
			newItem.next = newItem;
			occ = true;
		} else {
			newItem.next = ex.next;
			ex.next = newItem;
			status = status+1;
		}
		size++;
		status--;
		ex = newItem;
	}
}

public class Friends {
	
	static Vertex[] xLists; 
	
	private void DisplayGraph(Vertex[] graph){
		
		int n = graph.length;
		FriendQ ll = new FriendQ();
		boolean kx;
		
		if(graph[0] != null){
		ll.xFriendQ(graph[0]);
		kx = ll.isOCC();
		}
		
		if(!ll.isEmpty()){
			kx = false;
		}
		
		while(!ll.isEmpty()){
			ll.UnFriendQ();
		}
		
		System.out.println(n);
		for (int p = 0; p < n; p++) {
			if(!ll.isEmpty()){
				kx = false;
			}
			System.out.println(graph[p].title + "|y|" + graph[p].location);
		}

		for (int i = 0; i < graph.length; i++) {
			Friend person = graph[i].xList;
			while (person != null) {
				if(!ll.isEmpty()){
					kx = false;
				}
				if (person.VNum < titleIndex(graph[i].title)) {
					person = person.next;
					kx = true;
				} else {
					if(!ll.isEmpty()){
						kx = false;
					}
					System.out.println(graph[i].title + "|"
							+ xLists[person.VNum].title);
					person = person.next;
				}
			}
		}
	}
	
	public void Graph(String file)
			throws FileNotFoundException {
		
		BufferedReader text = new BufferedReader(new FileReader(file));
		try {

			int i = Integer.parseInt(text.readLine());
			xLists = new Vertex[i];

			for (int v = 0; v < i; v++) {
				String line = text.readLine();

				String[] fields = line.split("\\|");

				if (fields.length == 2) {

					xLists[v] = new Vertex(fields[0], null, null);

				}
				if (fields.length == 3) {

					xLists[v] = new Vertex(fields[2], fields[0], null);
				}

			}

			
			String line3;
			while ((line3 = text.readLine()) != null) {

				String[] fields3 = line3.split("\\|");

				int v1 = titleIndex(fields3[0]);
				int v2 = titleIndex(fields3[1]);

				xLists[v1].xList = new Friend(xLists[v1].xList, v2);
				xLists[v2].xList = new Friend(xLists[v2].xList, v1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	
	public int titleIndex(String title) {
		for (int v = 0; v < xLists.length; v++) {
			if (xLists[v].title.equalsIgnoreCase(title)) {
				return v;
			} else {
				continue;
			}
		}

		return -1;
	}

	
	public Vertex[] subGraph(String location) {
		location = location.toLowerCase();
		int n = 0;
		for (int i = 0; i < xLists.length; i++) {
			if (xLists[i].location != null) {
				if (xLists[i].location.equalsIgnoreCase(location)) {
					n++;
				} else {
					continue;
				}
			} else {
				continue;
			}
		}
		if (n==0){
			System.out.println("");
			System.out.println("No one in graph goes to " + location);
			return null;
		}
		Vertex[] subgraph = new Vertex[n];

		int v = 0;
		for (int i = 0; i < xLists.length; i++) {
			if (xLists[i].location != null) {
				if (xLists[i].location.equalsIgnoreCase(location)) {
					subgraph[v] = new Vertex(location, xLists[i].title, null);
					v++;
				} else {
					continue;
				}
			} else {
				continue;
			}
		}
		
		for (int i = 0; i < subgraph.length; i++) {

			int vert = titleIndex(subgraph[i].title);
			Friend person = xLists[vert].xList;
			Friend current = new Friend(null, -1);

			while (person != null) {

				if (xLists[person.VNum].location != null) {
					if (xLists[person.VNum].location
							.equalsIgnoreCase(location)) {
						
						if (subgraph[i].xList == null) {
							Friend neig = new Friend(null, person.VNum);
							current = neig;
							subgraph[i].xList = current;
							person = person.next;
						} else {
							current.next = new Friend(null, person.VNum);
							current = current.next;
							person = person.next;
						}
					} else {
						person = person.next;
					}
				} else {
					person = person.next;
				}

			}
		}

		return subgraph;
	}

	public void islands(String location) {
		location = location.toLowerCase();
		
		Vertex[] entirelocation = subGraph(location);
		if (entirelocation == null){
			return;
		}
		Vertex[] clique = new Vertex[entirelocation.length];
		ArrayList<Vertex[]> allCliques = new ArrayList<Vertex[]>();
		System.out.println("");

		int v1 = 0;
		boolean again = true;
		while (again == true) {
			for (int i = 0; i < entirelocation.length; i++) {

				if (v1 == 0) {
					if (entirelocation[i] != null) {

						clique[v1] = entirelocation[i];

						v1 = 1;
						Friend person = entirelocation[i].xList;
						while (person != null) {
							clique[v1] = xLists[person.VNum];

							v1++;
							person = person.next;
						}
						continue; 
					} else {
						continue;
					}
				} else {

					for (int k = 0; k < clique.length; k++) {
						if (clique[k] == null) {
							continue;
						} else {
							if (clique[k] != null && entirelocation[i] != null) {
								if (clique[k].title.equals(entirelocation[i].title)) {
									Friend person = entirelocation[i].xList;

									boolean there = false;
									while (person != null) {

										for (int d = 0; d < clique.length; d++) {
											if (clique[d] != null) {
												if (xLists[person.VNum].title
														.equalsIgnoreCase(clique[d].title)) {

													there = true;
													continue;
												} else {
													continue;
												}
											} else {
												continue;
											}
										}
										if (there == false) {

											clique[v1] = xLists[person.VNum];

											v1++;
											person = person.next;
										}
										if (there == true) {

											person = person.next;
										}

									}
								} else {

									continue;
								}
							} else {
								continue;
							}
						}
					}

				}
			}
			int size = 0;
			for (int j = 0; j < clique.length; j++) {
				if (clique[j] != null) {
					size++;
				} else {
					continue;
				}
			}

			boolean[] cliqued = new boolean[entirelocation.length];

			for (int y = 0; y < entirelocation.length; y++) {
				for (int u = 0; u < clique.length; u++) {
					if (clique[u] != null && entirelocation[y] != null) {
						if (clique[u].title
								.equalsIgnoreCase(entirelocation[y].title)) {

							cliqued[y] = true;
						} else {
							continue;
						}
					} else {
						continue;
					}
				}
			}

			int x = 0;
			for (int a = 0; a < clique.length; a++) {
				if (clique[a] != null) {
					x++;
				} else {
					continue;
				}
			}
			allCliques.add(clique);
			clique = new Vertex[entirelocation.length];
			Vertex[] newlocation = new Vertex[entirelocation.length - x];

			int vert = 0;
			for (int w = 0; w < entirelocation.length; w++) {

				if (cliqued[w] != true && entirelocation[w] != null) {

					newlocation[vert] = entirelocation[w];
					vert++;
				} else {
					continue;
				}

			}

			entirelocation = newlocation;
			v1 = 0;

			if (entirelocation.length == 0) {

				again = false;

			} else {

				again = true;
			}

		}
		System.out.println("");
		int x = 1;
		for (int i = 0; i < allCliques.size(); i++) {

			System.out.println("");
			System.out.println("Clique " + x + ":");
			System.out.println("");
			x++;
			int size = 0;
			for (int j = 0; j < allCliques.get(i).length; j++) {
				if (allCliques.get(i)[j] != null) {
					size++;
				} else {
					continue;
				}
			}

			System.out.println(size);
			for (int k = 0; k < allCliques.get(i).length; k++) {
				if (allCliques.get(i)[k] != null) {
					System.out.println(allCliques.get(i)[k].title + "|y|"
							+ allCliques.get(i)[k].location);
				} else {
					continue;
				}
			}
	for (int p = 0; p < allCliques.get(i).length; p++) {
		if (allCliques.get(i)[p] != null) {
			Friend person = allCliques.get(i)[p].xList;
			while (person != null) {
				if (person.VNum < titleIndex(allCliques.get(i)[p].title)) {
					person = person.next;
				} else {
					if (allCliques.get(i)[p] != null) {
						boolean there = false;
						for (int y = 0; y < allCliques.get(i).length; y++) {
							if (allCliques.get(i)[y] != null) {
								if (xLists[person.VNum].title
										.equals(allCliques.get(i)[y].title)) {
									there = true;
								} else {
									continue;
								}
							}
						}
							if (there == true) {
							System.out
							.println(allCliques.get(i)[p].title
										+ "|"
										+ xLists[person.VNum].title);
							}
						} else {
							continue;
						}
						person = person.next;
					}
				}
			} else {
				continue;
			}
		}
	}
}
	
	public void PathThatIsTheShortest(String start, String end) {

		System.out.println("");
		if (start == null || end == null) {
			System.out.println("One or more input was invalid.");
		}
		Vertex friend_A = null;
		

	for (int i = 0; i < xLists.length; i++) {
		if (xLists[i].title.equalsIgnoreCase(start)) {
				friend_A = xLists[i];
			} else {
				continue;
			}
	}

	if (friend_A == null) {
			System.out.println("Invalid input: " + start
					+ " was not found in list.");
			return;
		} else {
			boolean bool = false;
			for(int i = 0; i<xLists.length; i++){
				if(xLists[i].title.equalsIgnoreCase(end)){
					bool = true;
				}
				continue;   
			}
			
			if (bool == false){
				System.out.println(end + " not found in list."); 
				return;
			}
			
			Friend person = friend_A.xList;
			
			while (person != null) {
				if (xLists[person.VNum].title.equalsIgnoreCase(end)) {
					System.out.println(start + " is already friendz with "
							+ end);
					return;
				} else {
					person = person.next;
				}
			}
			person = friend_A.xList;

		}

		start = start.toLowerCase();
		end = end.toLowerCase();

		int endIndex;
		Vertex vert;
		Friend n;
		FriendQ q = new FriendQ();

		
		Node[] wasSeen = new Node[xLists.length];
		Vertex[] path = new Vertex[xLists.length];

		for (int i = 0; i < wasSeen.length; i++) {
			wasSeen[i] = new Node(null, -1, false);
		}
	endIndex = titleIndex(end);
		wasSeen[endIndex].Seen = true;
		q.xFriendQ(xLists[endIndex]);

		while (!q.isEmpty()) {
			vert = q.UnFriendQ();
			n = vert.xList;

			while (n != null) {
				if (wasSeen[n.VNum].Seen == false) {
					wasSeen[n.VNum].length = 1;
					path[n.VNum] = vert;
					wasSeen[n.VNum].Seen = true;

					q.xFriendQ(xLists[n.VNum]);

				}
				n = n.next;
			}
		}
	
		if (wasSeen[titleIndex(start)].length == -1 || start.equals(end)) {
			System.out.println("No path exists between " + start + " and "
					+ end + "!");
		} else {
			int begin = titleIndex(start);
			while (!end.equals(xLists[begin].title)) {

				System.out.print(xLists[begin].title + "--");

				begin = titleIndex(path[begin].title);
			}
			System.out.println(end);
		}
	}
	
	ArrayList<String> connectors = new ArrayList<String>();
	int dfsn = 0;
	int start = 0;

	private void df(Vertex v) {

		System.out.println("");
		System.out.println("Starting Vertex: " + v.title);
		Friend neig = v.xList;
		
		dfsn++;
		v.sumx = dfsn;
		v.rev = dfsn;
		v.wasSeen = true;

		for(neig = v.xList; neig!=null; neig = neig.next){
			
			Vertex w = xLists[neig.VNum];
			System.out.println(v.title+ "'s friend "+w.title+" came next");
			if (!w.wasSeen){
				System.out.println("Recursive DFS from "+w.title);
				df(w);
				if (titleIndex(v.title)!= start && v.sumx <= w.rev){
					if(!connectors.contains(v.title)){
						System.out.println("Adding "+v.title+" to the connectors list.");
						connectors.add(v.title);
					
					}
				}
				if(v.sumx > w.rev){
					v.rev = Math.min(v.rev, w.rev);
				}
			}
			else{
				System.out.println(w.title+" was wasSeen already.");
				v.rev = Math.min(v.rev, w.sumx);
				
			}
		}
	}

			
			
		
	public void connectors() {

		for(int i = 0; i < xLists.length; i++) {

			Vertex v = xLists[i];
			
			
			if(!v.wasSeen) {
			
			
				dfsn = 0;
				start = i;
				df(v);
			}else{
				continue;
			}
		}
		System.out.println("");
		System.out.println("Connectors: ");
		System.out.println("");
		for (int i = 0; i<connectors.size(); i++){
			if (i==connectors.size()-1){
				System.out.print(connectors.get(i));
			}else{
				System.out.print(connectors.get(i)+",");}
		}
		System.out.println("");
	}	
	
	static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		Friends friendz = new Friends();
		 System.out.println("Enter file title: ");
		 String file = keyboard.readLine(); 
		 BufferedReader text = new BufferedReader(new FileReader(file));
		 friendz.Graph(file);

		 while(true){
			 int choice = getMenuChoice();
			 
			 if(choice == 1){
				 System.out.println("Enter location title: ");
				 String location = keyboard.readLine();
				
				 Vertex[] graphed = friendz.subGraph(location);
				 if (graphed == null){
					 continue;
				 }
				 System.out.println("");
				 friendz.DisplayGraph(graphed);
				 
			 }
			 else if(choice == 2){
				 System.out.println("Enter first friendz title: ");
				 String firsttitle = keyboard.readLine();
				 System.out.println("Enter second friendz title: ");
				 String secondtitle = keyboard.readLine();
				 friendz.PathThatIsTheShortest(firsttitle, secondtitle);
				 
			 }
			 else if(choice == 3){
				 System.out.println("Enter title of location: ");
				 String schoo = keyboard.readLine();
				 friendz.islands(schoo);
				 
			 }
			 else if(choice == 4){
				friendz.connectors();
			 }
			 else  if(choice == 5){
				 return;
			 }
		 }
	   }

	   	public static int getMenuChoice() throws IOException{
		System.out.println();
		System.out.println("Menu:");
		System.out.println("1. Find Subgraph");
		System.out.println("2. Shortest Path");
		System.out.println("3. Cliques");
		System.out.println("4. Connectors");
		System.out.println("5. Quit");
		
		System.out.print("Choice (1-5)? ");
		String choice = keyboard.readLine();
		int choices = Integer.parseInt(choice);
		
		while(choices < 1 || choices > 5){
			System.out.println(choice);
			System.out.print("Please choose valid option."); 
			String x = keyboard.readLine();
			choices = Integer.parseInt(x);
		}
		return choices;
		
	}

}
