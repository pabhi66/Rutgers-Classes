/**
 * Created by Abhi on 12/17/14.
 */
public class array {
    public static void main(String[] args){
        int[][] arr = new int[10][10];

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                arr[i][j] = i;
            }
        }

        System.out.println("original");
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

        //go halfway down the columns, swap numbers in the rows horizontally
        for (int i = 0; i < arr.length; i++) {
            for (int k = 0; k < arr[0].length / 2; k++) {
                int temp = arr[i][k];
                arr[i][k] = arr[i][arr[0].length - k - 1];
                arr[i][arr[0].length - k - 1] = temp;
            }
        }

        // go halfway down the rows, swap numbers vertically
        for (int i = 0; i < arr.length / 2; i++) {
            int[] temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }

        System.out.println("original");
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
