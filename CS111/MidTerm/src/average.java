/**
 * Created by Abhi on 10/10/14.
 */
public class average {
    public static void main(String[] args){

        int count = 0;
        double average = 0;
        System.out.println("How many students do you have");
        double num = IO.readInt();

        System.out.println("Enter the GPA");
        double gpa = 0;//IO.readDouble();
        while(num!= count){
            gpa = IO.readDouble();
            average += gpa;
            count++;

        }
        average = average/num;
        IO.outputDoubleAnswer(average);
    }
}
