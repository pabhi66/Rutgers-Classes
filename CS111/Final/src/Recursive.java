/**
 * Created by Abhi on 12/11/14.
 */
public class Recursive {
    public static void main(String[] args){
        int[] arr = {11,2,3,5,11,11,11,11};
        System.out.println(round(3.145,2));
    }


    //factorial
    public static int factorial(int n){
        if(n ==0 || n==1)
            return 1;
        else
            return n * factorial(n-1);
    }

    //count bunny ears
    public static int bunnyEars(int n){
        if(n == 0)
            return  0;
        if(n == 1)
            return 2;
        else
            return 2 + bunnyEars(n-1);
    }

    //fibonacci
    public static int fib(int n){
        if(n==0)
            return 0;
        if(n==1 || n==2)
            return 1;
        else
            return fib(n-1) + fib(n-2);
    }

    //bunnyEars2
    public static int bunnyEars2(int n){

        if(n==0)
            return 0;
        if(n == 1)
            return 2;
        else{
            if(n % 2 == 0)
                return 3 + bunnyEars2(n-1);
            return 2 + bunnyEars2(n-1);
        }

    }

    //computer triangle blocks in row
    public static int triangle(int n){
        if(n ==0)
            return 0;
        if(n == 1)
            return 1;
        else
            return n + triangle(n-1);
    }

    //calculate sum of digits n
    public static int sum(int n){
        if (n==0)
            return 0;
        if(n==1)
            return 1;
        else{
            int x = n % 10;
            return x + sum(n / 10);
        }
        }

    //return count occurance digit 7
    public static int count7(int n){
        if(n==0)
            return 0;
        if(n % 10 == 7) {
            return 1 + count7(n / 10);
        }
        else
            return 0 + count7(n / 10);
    }

    //count occurance of 8
    public static int count8(int n){
        if(n==0)
            return 0;
        if(n % 10 == 8) {
            int x = n/10;
            if(x % 10 == 8){
                return 2 + count8(n/10);
            }
            return 1 + count8(n / 10);
        }
        else
            return 0 + count8(n / 10);
    }

    //compute power of a number
    public static int power(int n, int x){
        if(x == 1)
            return n;
        else
            return n * power(n, x-1);
    }

    //count number of chars x
    public static int countX(String str){
        if(str.length() == 0){
            return 0;
        }
        if(str.charAt(0) == 'x')
            return 1 + countX(str.substring(1));
        else
            return 0 + countX(str.substring(1));
    }

    //count occurrence of "hi"
    public static int countHi(String str){
        if(str.length() == 0 || str.length() == 1)
            return 0;
        if(str.charAt(0) == 'h' && str.charAt(1) == 'i')
            return 1 + countHi(str.substring(2));
        else
            return 0 + countHi(str.substring(1));
    }
    public static String changeX(String str){
        if(str.length() == 0)
            return "";
        char s = str.charAt(0);
        if(s == 'x')
            return 'y' + changeX(str.substring(1));
        return s + changeX(str.substring(1));
    }

    //change pi to 3.14 return the rest
    public static String changePi(String str){
        if(str.length() == 0 || str.length() == 1)
            return str;
        char c = str.charAt(0);
        if(c == 'p' && str.charAt(1) == 'i')
            return "3.14" + changePi(str.substring(2));
        return c + changePi(str.substring(1));
    }

    //remove all x from the string recursivly
    public static String removeX(String str){
        if(str.length() == 0)
            return str;
        char c = str.charAt(0);
        if(c == 'x')
            return "" + removeX(str.substring(1));
        return c  + removeX(str.substring(1));
    }

    //return true if array has 6
    public static boolean boo6(int[] arr, int index){
        if(arr.length == index)
            return false;
        if(arr[index] == 6) {
            return true;
        }
        else
            return boo6(arr, index +1);
    }

    //return count 11
    public static int count11(int[] arr, int index){
        if(arr.length == index)
            return 0;
        if(arr[index] == 11)
            return 1 + count11(arr,index+1);
        return count11(arr,index+1);
    }

    //return true if multiple of 10 found
    public static boolean array220(int[] arr, int index){
        if(index == arr.length)
            return false;
        if(arr[index] % 10 == 0)
            return true;
        return array220(arr,index+1);
    }

    //all the adjecent starts are saperated by *
    public static String allStar(String str){
        if(str.length() == 0 || str.length() == 1)
            return str;
        char x = str.charAt(0);
        return x + "*" + allStar(str.substring(1));
    }
    //identical chars are separated by *
    public static String pairStar(String str){
        if(str.length() == 0 || str.length() == 1)
            return str;
        char x = str.charAt(0);
        if(x == str.charAt(1))
            return x + "*" + pairStar(str.substring(1));
        return x + pairStar(str.substring(1));
    }

    //move all x to end
    public static String endX(String str){
        if(str.length() == 0 || str.length() == 1)
            return str;
        char x = str.charAt(0);
        if(x == 'x')
            return "" + endX(str.substring(1)) + 'x';
        return x + endX(str.substring(1));
    }

    //count pairs separated  by char
    public static int countPairs(String str){
        if(str.length() == 0 || str.length() == 1 || str.length() == 2)
            return 0;
        char x = str.charAt(0);
        if(x == str.charAt(2))
            return 1 + countPairs(str.substring(1));
        return 0 + countPairs(str.substring(1));
    }

    //count abc or aba
    public static int countAbc(String str){
        if(str.length() == 0 || str.length() == 1 || str.length() == 2)
            return 0;
        char x = str.charAt(0);
        if(x == 'a' && str.charAt(1) == 'b' && (str.charAt(2) == 'c' || str.charAt(2) == 'a'))
            return 1 + countAbc(str.substring(1));
        return 0 + countAbc(str.substring(1));
    }

    //count 11 substring in a string and should not overlap
    public static int count11(String str){
        if(str.length() == 0 || str.length() == 1)
            return 0;
        char x = str.charAt(0);
        if(x == '1' && str.charAt(1) == '1')
            return 1 + count11(str.substring(2));
        return 0 + count11(str.substring(1));
    }

    //clean the string e.g. yyzzz = yz
    public static String stringClean(String str){
        if(str.length() == 0 || str.length() == 1)
            return str;
        char x = str.charAt(0);
        if(x == str.charAt(1)) {
            return stringClean(str.substring(1));
        }
        return x + stringClean(str.substring(1));
    }

    //count hi in a string and do no count if has x in front
    public static int countHi2(String str){
        if(str.length() < 2)
            return 0;
        char x = str.charAt(0);
        if(x == 'x' && str.charAt(1) == 'h' && str.charAt(2) == 'i')
            return 0 + countHi2(str.substring(3));
        if(x == 'h' && str.charAt(1) == 'i')
            return 1 + countHi2(str.substring(2));
        return 0  + countHi2(str.substring(1));
    }

    //print everything inside ()
    public static String parenBit(String str){

        char x = str.charAt(0);
        if(x == '('){
            if(str.charAt(str.length() -1 ) != ')')
                return parenBit(str.substring(1,str.length() -1));
            return parenBit(str.substring(1));
        }
        if(str.charAt(str.length() -1 ) != ')'){
            return parenBit(str.substring(0,str.length() -1));
        }
        return str;
    }

    //round to nth places
    public static double round(double x, int n){

        if(n == 0)
            return x;
        return round(x * 10, n-1);
    }

    //backword string
    public static String reverse(String str){
        if(str.length() == 0)
            return str;
        int length = str.length();
        return str.charAt(length-1) + reverse(str.substring(0, length - 1));
    }

    //add numbers
    public static int add(int[] a){
        return add(a,0);
    }
    public static int add(int[] a, int i){
        if(a.length == i)
            return 0;
        return a[i] + add(a,i+1);
    }
}


