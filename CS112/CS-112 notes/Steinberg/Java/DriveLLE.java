import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

// A driver that uses classes StringLLE (linked lists of strings with exceptions) 

public class DriveLLE{

    // reads and returns first line from file filename
    public static String nameFromFile(String fileName)
	throws FileNotFoundException, IOException{
	BufferedReader br = new BufferedReader(new FileReader(fileName)); 
	String line = br.readLine( );
	br.close();
	return line;
    }

    public static void main(String[] args) 
	throws IOException {
	StringLLE sll = new StringLLE();
	sll.addAtFront("Ann");
	sll.addAtFront("Bob");
	sll.delete("Bob");
	// try to delete Joe, catch error if he is not there
	try {sll.delete("Joe"); }
	catch (NoSuchElementException nse){
	    System.out.println(nse.getMessage( ));
	}

	// try to delete name in file, catch error if he is not there
	//  or problem with file
	try {sll.delete(nameFromFile("foo")); }
	catch (NoSuchElementException nse){
	    System.out.println("Not there.  \n"+nse.getMessage( ));
	} catch (FileNotFoundException fnfe){
	    System.out.println("No such file. \n" + fnfe.getMessage( ));
	} catch (IOException ioe){
	    System.out.println("IO Problem.  \n"+ioe.getMessage( ));
	}
	System.out.println("program continues...");
    }
    
}
