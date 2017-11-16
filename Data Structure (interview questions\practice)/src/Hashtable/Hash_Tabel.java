package Hashtable;

/**
 * Created by Abhi on 9/27/16.
 */
public class Hash_Tabel {
//    public class HashEntry{
//        private int key;
//        private int value;
//
//        HashEntry(int key, int value){
//            this.key = key;
//            this.value = value;
//        }
//
//        public int getKey(){
//            return key;
//        }
//
//        public int getValue(){
//            return value;
//        }
//    }
//
//    public class HashMap{
//        private final static int table_size = 500;
//
//        HashEntry[] table;
//
//        HashMap(){
//            table = new HashEntry[table_size];
//            for(int i = 0; i < table.length; i++){
//                table[i] = null;
//            }
//        }
//
//        public int get(int key){
//            int hash = (key % table_size);
//            while(table[hash] != null && table[hash].getKey() != key){
//                hash = (hash + 1) % table_size;
//            }
//            if(table[hash] == null)
//                return -1;
//            else return table[hash].getValue();
//        }
//
//        public void put(int key, int value){
//            int hash = key % table_size;
//            while(table[hash] != null && table[hash].getKey() != key){
//                hash = (hash + 1) % table_size;
//            }
//            table[hash] = new HashEntry(key, value);
//        }
//    }




    class Node{
        Node next;
        int data;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    private Node[] table;
    private int size;

    public Hash_Tabel(int table_size){
        table = new Node[table_size];
        size = 0;
    }

    public void insert(int data){
        int hash = data % table.length;
        Node temp = new Node(data);
        if(table[hash] == null){
            table[hash] = temp;
        }else{
            temp.next = table[hash];
            table[hash] = temp;
        }
    }

    public void remove(int data){
        int hash = data % table.length;
        Node curr = table[hash];
        Node prev = curr;
        if(curr.data == data){
            table[hash] = curr.next;
            return;
        }

        while(prev.next.data != data){
            prev = prev.next;
        }

        if(prev.next == null){
            return;
        }

        if(prev.next.next == null)
            prev.next = null;
    }
}
