/**
 * Created by Abhi on 10/28/14.
 */
public class SpreadSheet {
    public static void main(String[] args){
        double[][] spreadSheet = new double[50][50];


        setCell(spreadSheet, 2, 2, 67.9);
        readCell(spreadSheet,2,2);
    }
    public static void setCell(double[][] ss, int row, int col, double num){
        if(row<0 || col<0 || row >= ss.length || col >= ss[row].length)
            return;
        ss[row][col] = num;

    }
    public static double readCell(double[][] ss, int row, int col){
        if(row<0 || col<0 || row >= ss.length || col >= ss[row].length)
            return Double.NaN;
        return ss[row][col];
    }
    public static void initialize(double[][] ss){
        for(int row=0; row<ss.length; row++){
            for(int col=0; col<ss[row].length; col++){
                ss[row][col] = Double.NaN;
            }
        }
    }
    public static double calcColAverage(double[][] ss, int col){

        if(col < 0 || col >= ss[0].length)
            return Double.NaN;
        double average = 0;
        int count = 0;
        for(int i=0; i<ss.length; i++){
            if(ss[i][col] != Double.NaN) {
                average += ss[i][col];
                count++;
            }
        }
        if(count == 0)
            return  Double.NaN;
        else
            return average/count;
    }
}
