
public class Test {
    public static void main(String[] args){
        
        System.out.println("enter a positive number: ");
        int n = IO.readInt();

        for(int j = 1; j <= n; j++) {
            System.out.println();
            for (int i = 1; i <= n; i++) {
                System.out.print(i * j + "\t");
            }
        }
    }
    
}
