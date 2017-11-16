/********************************************************************
Name: Abhishek Prajapati - Section 15
Name: Pranav Velanakanni - Section 13
Friendship Graph Assignment
********************************************************************/

import java.io.*;
import java.util.*;

public class Friends{
    /********************************************************************
     Global variables
     ********************************************************************/
    HashMap<String, Integer> search = new HashMap<String, Integer>(1000, 2.0f);
    ArrayList<user> list = new ArrayList<user>();
    graph Graphh;

    /********************************************************************
     Main class
     ********************************************************************/
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the file name: ");
        String file = scan.nextLine();

        Friends friend = new Friends();
        friend.buildGraph(file);
        System.out.print("");
        System.out.print("\n");
        int i=0;
        int k = friend.list.size();
        while(i < k){
            System.out.print("");
            System.out.print(friend.list.get(i).name);
            System.out.print("");
            neighbor bro = friend.list.get(i).next;
            while(bro != null){
                System.out.print("--->");
                System.out.print(friend.list.get(bro.VNum).name);
                bro = bro.next;
            }
            System.out.print("");
            System.out.print("\n");
            i++;
        }
        System.out.print("\n");
        System.out.println("1: Shortest intro chain: ");
        System.out.println("2: Cliques at school: ");
        System.out.println("3: connectors: ");
        System.out.println("4: quit");
        System.out.print("Enter your choice: ");
        int choice = scan.nextInt();
        while (choice != 4) {
            System.out.println();
            if (choice < 1 || choice > 5) {
                System.out.println("Wrong choice: " + choice);
            } else {
                if (choice == 1) {
                    System.out.println("Enter first person's name: ");
                    scan.nextLine();
                    String first = scan.nextLine();
                    System.out.println("Enter second person's name: ");
                    String second = scan.nextLine();
                    friend.ShortestPath(first,second);
                }
                if (choice == 2) {
                    System.out.println("Enter the name of the school: ");
                    scan.nextLine();
                    String school = scan.nextLine();
                    friend.cliques(school);
                }
                if (choice == 3) {
                    friend.connectors();
                }
                if (choice == 4){

                }
            }
            System.out.print("\n");
            System.out.println("1: Shortest intro chain: ");
            System.out.println("2: Cliques at school: ");
            System.out.println("3: connectors: ");
            System.out.println("4: quit");
            System.out.println("Enter your choice");
            choice = scan.nextInt();
        }
    }

    /********************************************************************
     Graph Class
     ********************************************************************/
    public class graph{
        int count;
        HashMap<String, Integer> search;
        ArrayList<user> list;

        public graph(HashMap<String, Integer> search, ArrayList<user> list)
        {
            this.count = 0;
            this.search = search;
            this.list = list;
        }
    }

    /********************************************************************
     Neighbor class
     ********************************************************************/
    public class neighbor{
        int count;
        public neighbor next;
        public int VNum;

        public neighbor(neighbor next, int VNum){
            this.count = 0;
            this.next = next;
            this.VNum = VNum;
        }
    }

    /********************************************************************
     Node
     ********************************************************************/
    public class node{
        int count;
        node next;
        user data;

        public node(user info){
            this.count = 0;
            this.next = null;
            this.data = info;
        }
    }

    /********************************************************************
     isedge Class
     ********************************************************************/
    public class isEdge{
        public int count;
        public isEdge next;
        public String path;
    }

    /********************************************************************
     Vertex Class
     ********************************************************************/
    public class user{
        String name;
        String school;
        neighbor next;
        int count;

        public user(String name, String school, neighbor next){
            this.count = 0;
            this.next = next;
            this.name = name;
            this.school = school;
        }
    }
    /********************************************************************
     Queue Class
     ********************************************************************/
    public class queue{
        int size;
        node front;
        node rear;

        public queue(){
            size = 0;
            front = null;
            rear = null;
        }

        public boolean isEmpty(){
            if(front == null){
                return true;
            }
            return false;
        }

        public user dequeue() throws NoSuchElementException{
            if (front == null){
                throw new NoSuchElementException("Queue underflow");
            }
            user item = front.data;
            front = front.next;
            size--;
            return item;
        }

        public void dequeuee(){
            user item = front.data;
            front = front.next;
            size--;
            return;
        }

        public void enqueue(user info){
            node abc = new node(info);
            node temp = rear;
            rear = abc;
            rear.data = info;
            rear.next = null;
            if(isEmpty()){
                front = rear;
            }else{
                temp.next = rear;
            }
            size++;
        }

        public int size(){
            return size();
        }
    }

    /********************************************************************
     Build Graph Class
     ********************************************************************/
    public void buildGraph(String file) throws FileNotFoundException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            int length = Integer.parseInt(reader.readLine());
            for(int i = 0; i < length; i++){
                String read = reader.readLine();
                String[] split = read.split("\\|");
                String name = split[0];
                String yesNo = split[1];
                String school = null;
                boolean boo = false;
                user yesNoSchool = null;
                if(yesNo.equalsIgnoreCase("y")){
                    boo = true;
                }
                if(boo){
                    school = split[2];
                    school = school.toLowerCase();
                    yesNoSchool = new user(name,school,null);
                }
                else if(boo == false){
                    yesNoSchool = new user(name,null,null);
                }
                list.add(yesNoSchool);
                search.put(name,i);
            }

            String nextLine;
            while((nextLine = reader.readLine()) != null){
                String[] broSplit = nextLine.split("\\|");

                int bro1 = search.get(broSplit[0].toLowerCase());
                int bro2 = search.get(broSplit[1].toLowerCase());

                list.get(bro1).next = new neighbor(list.get(bro1).next, bro2);
                list.get(bro2).next = new neighbor(list.get(bro2).next, bro1);
            }

            Graphh = new graph(search,list);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /********************************************************************
     Shortest Path Class
     ********************************************************************/
    public void ShortestPath(String bro1, String bro2) throws NoSuchElementException{
        int count = 0;
        boolean boo = false;
        bro1 = bro1.toLowerCase();
        bro2 = bro2.toLowerCase();
        if(search.get(bro1) == null || search.get(bro2) == null)
            throw new NoSuchElementException("User not in graph");
        if(bro1 == null || bro2 == null){
            throw new NoSuchElementException("Wrong input: ");
        }
        int i;
        int first;
        user data;
        neighbor where;

        queue put = new queue();
        int length = Graphh.list.size();
        int[] mile = new int[length];
        user[] direction = new user[length];

        int k=0;
        while(k < length){
            mile[k] = -1;
            k++;
            count++;
        }

        i=search.get(bro2);
        mile[i] = 0;
        put.enqueue(Graphh.list.get(i));

        while(!put.isEmpty()){
            data = put.dequeue();
            count++;
            for(where = data.next; where != null; where = where.next){
                if(-1 == mile[where.VNum]){
                    mile[where.VNum] = mile[search.get(data.name)] + 1;
                    direction[where.VNum] = data;
                    if(count > 1){
                        boo = true;
                    }
                    put.enqueue(Graphh.list.get(where.VNum));
                }
            }
        }
        if(bro1.equals(bro2) || mile[search.get(bro1)] == -1)
            throw new NoSuchElementException("No path");
        for(first = search.get(bro1); bro2.equals(Graphh.list.get(first).name) == false; first = search.get(direction[first].name)){
            if(count  < 0){
                boo = false;
            }
            System.out.print("");
            System.out.print(Graphh.list.get(first).name);
            System.out.print("---");
            System.out.print("");
        }
        System.out.print(bro2);
        System.out.print("\n");
    }
    /********************************************************************
     Cliques Class
     ********************************************************************/
    public void cliquess(String school){
        int count = 0;
        boolean boo = false;
        int counter = 0;
        if(school == null)
            return;
        school = school.toLowerCase();queue que = new queue();boolean[] checked = new boolean[Graphh.list.size()];int i=0;

        int k = Graphh.list.size();
        for(int j=0; j < k; j++){
            count++;
            counter++;
            HashMap<String, Integer> islandSearch = new HashMap<String, Integer>(2000,2.0f);
            count++;
            counter++;
            ArrayList<user> islandList = new ArrayList<user>();
            int counterr = 0;
            if(!checked[j]) {
                if (school.equals(Graphh.list.get(j).name)) {
                    que.enqueue(Graphh.list.get(j));
                    islandList.add(0, Graphh.list.get(j));
                    islandSearch.put(Graphh.list.get(j).name, 0);
                    checked[j] = true;
                    j++;
                }
                graph island = new graph(islandSearch, islandList);
                int abc = 0;
                int size = island.list.size();
                while (abc < size) {
                    island.list.get(abc).next = null;
                    abc++;
                }
                if (island.list.size() != 0) {
                    count++;
                    int kk = list.size();
                    for (int ii = 0; ii < kk; ii++) {
                        user bro1 = list.get(ii);
                        if (school.equals(bro1.school)) {
                            count++;
                            boo = true;
                        }
                    }
                    boo = false;
                    count++;
                    System.out.print("");
                    int bbb = island.list.size();
                    System.out.print(bbb);
                    le(island.list.size());
                    System.out.print("\n");
                    int h = 0;
                    int e = island.list.size();
                    while (h < e) {
                        System.out.print("");
                        System.out.print(island.list.get(h).name);
                        System.out.print("|y|");
                        System.out.print(school);
                        System.out.print("\n");
                        h++;
                    }
                    int sizz = island.list.size();
                    boolean[] checkedd = new boolean[sizz];
                    int q = 0;
                    while (q < sizz) {
                        neighbor sis = island.list.get(q).next;
                        while (sis != null) {
                            if (!checkedd[sis.VNum] || !checkedd[q]) {
                                System.out.print("");
                                System.out.print(island.list.get(sis.VNum).name);
                                System.out.print("|");
                                System.out.print(island.list.get(q).name);
                                System.out.print("\n");
                                count++;
                                boo = true;
                                checkedd[sis.VNum] = true;
                                checkedd[q] = true;
                            }
                            sis = sis.next;
                        }
                        q++;
                    }

                }
            }
        }
    }
    public int le(int len){

        return len;
    }

    public int leng(int len){

        return len;
    }
    public static int cNum;

    public void cliques(String school){
        int count = 0;
        boolean boo = true;
        queue x = new queue();
        school = school.toLowerCase();
        if(school == null)
            return;
        int size = list.size();
        ArrayList<String> location = new ArrayList<String>();
        count++;
        ArrayList<String> path = new ArrayList<String>();
        count++;
        boolean[] checked = new boolean[size];

        if(x.isEmpty()){
            boo = false;
        }else{
            boo = true;
            count++;
        }
        int a = 0;
        int b = checked.length;
        while(a < b){
            checked[a] = false;
            a++;
        }
        cNum = 0;
        int c = 0;
        int d = list.size();
        while(c < d){
            if(checked[c] == false){
                BreathFirstSearch(path, location, checked, c, school);
            }
            c++;
        }
        if(location.isEmpty() == true)
            return;
    }

    /********************************************************************
     Breath First Search Class
     ********************************************************************/
    public void BreathFirstSearch(ArrayList<String> path,
                                  ArrayList<String> location, boolean[] checked,
                                  int c, String school){
        queue que = new queue();
        queue q = new queue();
        int xxx = 0;
        boolean boo = true;
        user u1 = null;
        user u2 = null;
        user u3 = null;
        neighbor bro;
        int count = 0;
        isEdge rear = null;
        isEdge front = null;

        u1 = list.get(c);
        if(school.equals(u1.school) == false){
            checked[c] = true;
            return;
        }
        HashMap<String, Integer> islandSearch = new HashMap<String, Integer>(2000,2.0f);
        count++;
        boo = true;
        ArrayList<user> islandList = new ArrayList<user>();
        count++;
        boo = false;
        graph island = new graph(islandSearch,islandList);
        count++;
        int abc = 0;
        int size = island.list.size();
        while(abc < size){
            island.list.get(abc).next = null;
            abc++;
        }

        String nn = u1.name;
        int hhh = nn.length();
        String ss = school;
        int sss = ss.length();
        cNum++;
        count++;
        System.out.print("\n");
        System.out.print("");
        System.out.print("clique: ");
        System.out.print(cNum);
        System.out.print("\n");
        System.out.print("");
        System.out.print(u1.name);
        System.out.print("|y|");
        System.out.print(school);
        System.out.print("\n");

        checked[c] = true;
        if(hhh < 0 && sss < 0){
            boo = false;
        }
        que.enqueue(u1);
        q.enqueue(u1);

        while(!que.isEmpty()){
            u2 = que.dequeue();

            for(bro = u2.next; bro != null; bro = bro.next){
                if(checked[bro.VNum] == false){
                    u3 = list.get(bro.VNum);
                    if(school.equals(u3.school) == false){
                        checked[bro.VNum] = true;
                        continue;
                    }
                    count++;
                    System.out.print("");
                    System.out.print(u3.name);
                    System.out.print("|y|");
                    System.out.print(school);
                    System.out.print("\n");

                    checked[bro.VNum] = true;
                    que.enqueue(u3);
                    q.enqueue(u3);

                    if(front != null){
                        rear = front;
                        front = new isEdge();
                        if(hhh < 0 && sss < 0){
                            boo = false;
                        }else{
                            boo = true;
                            count++;
                        }
                        front.path = u3.name;
                        front.path +=  "|";
                        front.path += u2.name;
                        front.next = rear;
                    }
                    else{
                        front = new isEdge();
                        if(hhh < 0 && sss < 0){
                            boo = false;
                        }else{
                            boo = true;
                            count++;
                        }
                        front.path = u3.name;
                        front.path +=  "|";
                        front.path += u2.name;
                    }
                }
            }
        }
        for(rear = front; rear != null; rear = rear.next){
            System.out.print("");
            System.out.print(rear.path);
            System.out.print("\n");
        }
    }

    /********************************************************************
     Connectors Class
     ********************************************************************/
    public void connectors(){
        int length = list.size();
        boolean[] checked = new boolean[length];
        int count = 0;
        boolean boo = true;
        int dcount = 0;
        int rcount = 0;
        int[] rev = new int[length];
        int[] dnum = new int[length];
        int[] k = new int[length];
        int index = 0;
        if(count >0){
            boo = false;
        }else{
            boo = true;
            count++;
        }
        while(index < length){
            if(count >0){
                boo = false;
            }else{
                boo = true;
                count++;
            }
            if(checked[index] == false) {
                k[index] = 1;
                DepthFirstSearch(k, rcount, dcount, rev, list.get(index), checked, dnum);
            }
            index++;
        }
        System.out.print("");
        System.out.print("Connectors: ");
        System.out.print("\n");
        int i = 0;
        int j = list.size();
        while(i < j){
            if(count >0){
                boo = false;
            }else{
                boo = true;
                count++;
            }
            if(k[i] == 3){
                System.out.print("");
                System.out.print(list.get(i).name);
                System.out.print(" - ");
            }
            i++;
        }
        System.out.print("\n");
    }

    /********************************************************************
     Depth First Search Class
     ********************************************************************/
    public void DepthFirstSearch(int[] mutual, int revCount, int dcount, int[] rev, user u, boolean[] checked, int[] dnum){
        int i = search.get(u.name);
        int count = 0;
        boolean boo = true;
        rev[i] = revCount;
        dnum[i] = dcount;
        count++;
        dcount++;
        revCount++;
        checked[i] = true;
        if(count >0){
            boo = false;
        }else{
            boo = true;
            count++;
        }

        for(neighbor bro = u.next; bro != null; bro = bro.next){
            if(count >0){
                boo = false;
            }else{
                boo = true;
                count++;
            }
            if(checked[bro.VNum] == false){
                user friend = list.get(bro.VNum);
                DepthFirstSearch(mutual,revCount, dcount,rev,friend,checked,dnum);
                if(dnum[i] > rev[bro.VNum]){
                    rev[i] = Math.min(rev[bro.VNum],rev[i]);
                }
                else{
                    if(count >0){
                        boo = false;
                    }else{
                        boo = true;
                        count++;
                    }
                    switch (mutual[i]){
                        case 0:mutual[i] = 3;
                            break;
                        case 1: mutual[i] = 2;
                            break;
                        case 2: mutual[i] = 3;
                            break;
                    }
                }
            }
            else{
                rev[i] = Math.min(dnum[bro.VNum],rev[i]);
            }
        }
    }
}

