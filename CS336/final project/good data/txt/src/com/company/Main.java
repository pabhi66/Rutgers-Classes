package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException {

        // The name of the file to open.
        String fileName = "s.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            StringBuilder sb = new StringBuilder();
            int i = 1;
            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                if(i == 6){
                    int k = sb.length();
                    String s = sb.toString();
                    System.out.println(s.substring(0,k-1));
                    i = 1;
                    sb = new StringBuilder();
                }
                if(line.contains("(")){
                    int index = line.indexOf("(");
                    line = line.substring(0,index);
                }

                if(line.contains(",")){
                    String s = line.replaceAll(",", "");
                    line = s;
                }

                if(line.contains("-")){
                    String s = line.replaceAll("-", "0");
                    line = s;
                }
                sb.append(line + ",");
                i++;
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName);
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}
