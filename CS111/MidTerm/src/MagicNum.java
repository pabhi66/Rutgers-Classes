/**
 * Created by Abhi on 10/10/14.
 */
public class MagicNum {
    public static void main(String[] args){
        System.out.println("ENter upper limit");
        int upper = IO.readInt();
        int lower;
        int sum =0;

        for(int i=1; i<upper; i++){
            sum = 0;
            for(int j=1; j<i;j++){
                if(i%j ==0)
                    sum +=j;
            }
            if(sum==i)
                System.out.println(i);
        }
    }
}
