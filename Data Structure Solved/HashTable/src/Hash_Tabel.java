/**
 * Created by Abhi on 9/27/16.
 */
public class Hash_Tabel {
    public class HashEntry{
        private int key;
        private int value;

        HashEntry(int key, int value){
            this.key = key;
            this.value = value;
        }

        public int getKey(){
            return key;
        }

        public int getValue(){
            return value;
        }
    }

    public class HashMap{
        private final static int table_size = 500;

        HashEntry[] table;

        HashMap(){
            table = new HashEntry[table_size];
            for(int i = 0; i < table.length; i++){
                table[i] = null;
            }
        }

        public int get(int key){
            int hash = (key % table_size);
            while(table[hash] != null && table[hash].getKey() != key){
                hash = (hash + 1) % table_size;
            }
            if(table[hash] == null)
                return -1;
            else return table[hash].getValue();
        }

        public void put(int key, int value){
            int hash = key % table_size;
            while(table[hash] != null && table[hash].getKey() != key){
                hash = (hash + 1) % table_size;
            }
            table[hash] = new HashEntry(key, value);
        }
    }
}
