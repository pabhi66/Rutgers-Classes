//Buid a calculator
public class Calculator {
    public static void main(String[] args){


       int choice;
        do{
            System.out.println("Please enter the number: ");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Exit");
            
            choice = IO.readInt();

            if(choice == 1){
                //add numbers
                System.out.println("Enter first num: ");
                int a = IO.readInt();
                System.out.println("Enter second num: ");
                int b = IO.readInt();

                System.out.println("Sum: " + (a+b));
            }
            if(choice == 2){
                //subtract numbers
                System.out.println("Enter first num: ");
                int a = IO.readInt();
                System.out.println("Enter second num: ");
                int b = IO.readInt();

                System.out.println("Difference: " + (a-b));
            }
            if(choice == 3) {
                //Multiply numbers
                System.out.println("Enter first num: ");
                int a = IO.readInt();
                System.out.println("Enter second num: ");
                int b = IO.readInt();

                int product = 0;
                for (int i = 0; i < b; i++) {
                    if (a > 0 && b > 0) {
                        product += a;
                    }
                    else if((a < 0 && b > 0))
                    {
                        product -= a;
                    }
                }
                for (int i = 0; i > b; i--) {
                    if (a < 0 && b < 0) {
                        product += Math.abs(a);
                    }
                    else if((a > 0 || b < 0))
                    {
                        product -= a;
                    }
                }

                System.out.println("product: " + product);

            }
            if(choice == 4){
                //divide numbers
                System.out.println("Enter first num: ");
                int a = IO.readInt();
                System.out.println("Enter second num: ");
                int b = IO.readInt();

                int divide = 0;
                for (int i = 1; i < a; i++) {
                    if(a > 0 && b > 0)
                    {
                        divide = i;
                        a -= b;
                    }
                    else if(a > 0 && b < 0)
                    {
                        divide = -i;
                        a -= Math.abs(b);
                    }
                }
                for(int i = 1; i > a; i++)
                {
                    if(a < 0 && b < 0)
                    {
                        divide = i;
                        a -=b;
                    }
                    else if(a < 0 && b > 0)
                    {
                        divide = -i;
                        a = a - (-b);
                    }
                }
                System.out.println("divide: " + divide);
            }
        }while(choice != 5);
    }
    
}
