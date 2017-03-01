/**
 * Created by Abhi on 10/14/14.
 */
public class nested {
    public static void main(String[] args){
        int num = 3;
        for(int i=1; i<=num; i++){
            for(int j=1; j<i; j++){
                if(num == j)
                    System.out.print("oooooooooo");
                else
                    System.out.print("##########");
            }
            System.out.println();
        }
    }
}
