/**
 * Created by Abhi on 11/21/14.
 */
public class SelectionSort {
    public static void main(String[] args){
        String[] name = {"Zack", "Anna", "Mike", "Joe", "Dee"};
        Selection(name);

        for(int i=0; i<name.length; i++){
            System.out.print(name[i] + " ");
        }

    }

    public static void Selection(String[] name){

        for(int i=0; i<name.length; i++){
            int first = i;
            for(int j=i; j<name.length; j++){
                if(name[j].compareTo(name[first]) < 0){
                    first = j;
                }
            }

            String temp = name[i];
            name[i] = name[first];
            name[first] = temp;
        }
    }
}
