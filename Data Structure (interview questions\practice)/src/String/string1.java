package String;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Abhi on 9/28/16.
 */
public class string1 {

    public static void main(String[] args){
        String a = "aabcccccaaab";
        //findAllPermutation(a);
        System.out.print(stringCompression(a));
    }

    public static String stringCompression(String str){
        String string = "";
        int counter = 1;
        String tempString = "";

        for(int i = 0; i < str.length()-1; i++){
            if(str.charAt(i) == str.charAt(i+1)){
                counter ++;
                tempString = "" + str.charAt(i);
            }else{
                string+= str.charAt(i) + "" + counter;
                counter = 1;
                tempString = "";
            }
        }
        return string + tempString + "" + str.charAt(str.length()-1) + counter;
    }

    //find duplicates characters in string
    public static void findDuplicateCharacters(String word){
        char[] characters = word.toCharArray();

        //create a hash map to store the characters
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();

        //put characters from char array into hash map
        for(Character ch : characters){
            if(charMap.containsKey(ch))
                charMap.put(ch,charMap.get(ch) + 1);
            else
                charMap.put(ch,1);
        }

        //iterate through the hash map to print all duplicates
        Set<Map.Entry<Character,Integer>> entrySet = charMap.entrySet();
        for(Map.Entry<Character,Integer> entry : entrySet){
            if(entry.getValue()>1)
                System.out.print(entry.getKey() + " : " + entry.getValue() + "\n");
        }
    }

    //check if two strings are anagram of each other
    public static boolean isAnagram(String word1, String word2){

//        char[] arr1 = word1.toCharArray();
//        char[] arr2 = word2.toCharArray();
//
//        Arrays.sort(arr1);
//        Arrays.sort(arr2);
//
//        return Arrays.equals(arr1,arr2);
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        char[] arr1 = word1.toCharArray();
        StringBuilder arr2 = new StringBuilder(word2);

        for(char ch : arr1){
            int index = arr2.indexOf("" + ch);
            if(index != -1){
                arr2.deleteCharAt(index);
            }
            else return false;
        }

        return arr2.length() == 0;
    }

    //program to print first non repeated character from String?
    public static Character printFirstNonRepeatedCharacter(String str){
        char[] arr = str.toCharArray();
        Map<Character, Integer> charMap = new HashMap<>();
        for(Character ch : arr){
            if(charMap.containsKey(ch))
                charMap.put(ch, charMap.get(ch) + 1);
            else charMap.put(ch, 1);
        }

        for(Character ch : arr){
            if(charMap.get(ch) == 1)
                return ch;
        }
        return null;
    }

    //reverse string using iteration and recursion
    public static String reverse(String str){
        //sol 1
            //return new StringBuilder(str).reverse().toString();

        //sol 2 iterative
//            StringBuilder string = new StringBuilder();
//            for(int i = str.length()-1; i >= 0; i--){
//                string.append(str.charAt(i));
//            }
//            return string.toString();

        //sol 3 recursive
        if(str.length() < 2)
            return str;
        return reverse(str.substring(1)) + str.charAt(0);
    }

    //how to check if the string contains only digits
    public static boolean isDigitOnly(String str){
        Pattern pattern = Pattern.compile(".*[^0-9].*");
        return !pattern.matcher(str).matches();
    }

    //check if the string is 6 digit number or not
    public static boolean is6digitNum(String str){
        Pattern pattern = Pattern.compile("\\d{6}");
        return pattern.matcher(str).matches();
    }

    //count number of occurrence of a character in string
    public static int countOccurence(String str, char a){
        //sol 1 for loop
//        int count = 0;
//        for(int i = 0; i < str.length(); i++){
//            if(str.charAt(i) == a)
//                count++;
//        }
//        return count;

        //sol 2 hash table
        char[] arr = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for(Character ch : arr){
            if(map.containsKey(ch))
                map.put(ch, map.get(ch) + 1);
            else map.put(ch,1);
        }
         return map.get(a);



    }

    //replace given character of string to something else
    public static String replaceCharacter(String str, String replacement){
        String s = str.replaceAll(replacement," ");
        return s;
    }

    //find all permutation of string
    public static void findAllPermutation(String str){
        permutation("", str);
    }
    private static void permutation(String perm, String str){
        int n = str.length();
        if(n == 0)
            System.out.println(perm);
        else{
            for(int i = 0; i < n; i++){
                permutation(perm + str.charAt(i), str.substring(0,i) + str.substring(i+1, n));
            }
        }
    }

    //reverse words in string
    private static String reverseWordsinString(String str){
        String[] arr = str.split(" ");
        String reversed = "";
        for(int i = 0; i < arr.length; i++){
            String word = arr[i];
            String reverseWord = "";
            for(int j = word.length()-1; j >=0; j--){
                reverseWord = reverseWord + word.charAt(j);
            }
            reversed = reversed + " "+ reverseWord;
        }
        //return reversed;
        return new StringBuilder(reversed).toString();
    }

    //check if string is palindrome
    private static boolean isPalindrome(String str){
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != str.charAt(str.length() - 1 - i))
                return false;
        }
        return true;
    }

    //find the largest palindrome in a string
//    public static String longestPalindrome(String s) {
//        int start = 0, end = 0;
//        for (int i = 0; i < s.length(); i++) {
//            int len1 = expandAroundCenter(s, i, i);
//            int len2 = expandAroundCenter(s, i, i + 1);
//            int len = Math.max(len1, len2);
//            if (len > end - start) {
//                start = i - (len - 1) / 2;
//                end = i + len / 2;
//            }
//        }
//        return s.substring(start, end + 1);
//    }
//
//    private static int expandAroundCenter(String s, int left, int right) {
//        int L = left, R = right;
//        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
//            L--;
//            R++;
//        }
//        return R - L - 1;
//    }

    public static String longestPalindrome(String s){
        int n = s.length();
        int palindromeBegainsAt = 0;
        int mex_length = 0;
        boolean[][] palindrome = new boolean[n][n];

        for(int i = 0; i < n; i++){
            palindrome[i][i] = true;
        }

        for(int i = 0; i < n-1; i++){
            if(s.charAt(i) == s.charAt(i+1)){
                palindrome[i][i+1] = true;
                palindromeBegainsAt = i;
                mex_length = 2;
            }
        }

        for(int currLength = 3; currLength < n; currLength++){
            for(int i = 0; i < n-currLength+1; i++){
                int j = i+currLength-1;
                if(s.charAt(i) == s.charAt(j) && palindrome[i+1][j-1]){
                    palindrome[i][j] = true;
                    palindromeBegainsAt = i;
                    mex_length = currLength;
                }
            }
        }
        //System.out.println(palindromeBegainsAt + " " + mex_length);
        return s.substring(palindromeBegainsAt, palindromeBegainsAt+  mex_length);
    }

    //delete repeated character from string
    public static String deleteRepeatedCharacters(String str){
        char[] arr = str.toCharArray();
        ArrayList<Character> arraylist = new ArrayList<>();
        for(Character ch :arr){
            if(arraylist.contains(ch))
                //arraylist.remove(ch);
                continue;
            else arraylist.add(ch);
        }
        String newstr = "";
        for(int i = 0; i < arraylist.size(); i++){
            newstr += arraylist.get(i);
        }
        return newstr;
    }

    //check if string contains another string
    public static boolean stringContainsAnotherString(String str1, String substr){
        return str1.toLowerCase().contains(substr.toLowerCase());
    }

    //return highest occurred character in string
    public static char highestOccurredChar(String str){
        char[] arr = str.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for(Character ch : arr){
            if(map.containsKey(ch))
                map.put(ch, map.get(ch) + 1);
            else map.put(ch, 1);
        }

        int max = 0;
        char c = 0;
        Set<Map.Entry<Character, Integer>> set = map.entrySet();
        for(Map.Entry<Character, Integer> entries : set){
            if(entries.getValue() > max){
                max = entries.getValue();
                c = entries.getKey();
            }

        }

        return c;
    }

    //remove given character from string
    public static String removeGivenChar(String str, char c){
        StringBuilder s = new StringBuilder(str);
        for(int i = 0; i <s.length(); i++){
            if(s.charAt(i) == c){
                s.deleteCharAt(i);
                i--;
            }
        }

        return s.toString();
    }

    //sort string based on their word length
    public static String SortString(String str){
        String[] arr = str.split(" ");

        class MyComparator implements Comparator<String>{
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                    return 1;
                } else if (o1.length() < o2.length()) {
                    return -1;
                }
                return o1.compareTo(o2);
            }
        }
        Arrays.sort(arr,new MyComparator());

        String sorted = "";
        for(int i = 0; i < arr.length; i++){
            sorted += arr[i] + " ";
        }
        return sorted;


    }
}
