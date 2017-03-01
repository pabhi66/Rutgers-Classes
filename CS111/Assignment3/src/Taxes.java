/**Name: Abhishek Prajapati
 * date: 10/7/14
 * Computer Science 111 Section 58
 * This Program will collect your taxes according to your income
 */
public class Taxes {
    public static void main(String[] args){

        double income = 0; //create a variable
        double tax = 0;

        //Ask the user for income
        System.out.println("Please enter your income in Elbonian dollars(integers)");
        income = IO.readDouble(); // store the income

        //set conditions
        if(income < 0)
            IO.reportBadInput();
        else if(income <= 8000)
            tax = income * .10;
        else if(income > 8000 && income <= 26000){
            tax = 800;
            income -= 8000;
            tax += income * .15;
        }
        else if(income > 26000 && income <= 48000){
            tax = 800;
            income -= 8000;
            if(income >= 26000) {
                tax += 3900;
                income -= 26000;
                tax += income * .25;
            }
            else{
                tax += income * .15;
            }
        }
        else if(income > 48000) {
            tax = 800;
            income -= 8000;
            if(income >= 26000){
                tax += 3900;
                income -= 26000;
                if(income > 48000){
                    tax += 12000;
                    income -= 48000;
                    tax += income * .35;
                }
                else{
                    tax += income * .25;
                }
            }
        }
        IO.outputDoubleAnswer(tax); //output the answer
    }
}
