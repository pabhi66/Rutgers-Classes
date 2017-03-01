/**
 * Created by Abhi on 4/4/15.
 */
import java.io.*;
import java.util.*;
public class Driver {
    //static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {

        LittleSearchEngine test = new LittleSearchEngine();
        test.makeIndex("docs.txt", "noisewords.txt");
        for (String key : test.keywordsIndex.keySet()) {
            System.out.println(key + " " + test.keywordsIndex.get(key).toString());
        }
        System.out.println();
        System.out.println();
        ArrayList<String> results = test.top5search("captain", "america");

        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }
    }

//    static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
//
//    public static void main(String args[]) throws IOException
//    {
//        String docsFile = "docs.txt";
//        String noiseWords = "noisewords.txt";
//
//        LittleSearchEngine google = new LittleSearchEngine();
//
//        google.makeIndex(docsFile, noiseWords);
//
//        String kw1 = "Deep";
//        String kw2 = "World";
//
//        int size = google.keywordsIndex.size();
//        System.out.println(size);
//        System.out.println(google.keywordsIndex.toString());
//
//        google.top5search(kw1, kw2);
//    }


    }


