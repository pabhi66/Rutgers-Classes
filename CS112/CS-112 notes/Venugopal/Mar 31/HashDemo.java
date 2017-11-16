package search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class HashDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
			throws IOException {
		// TODO Auto-generated method stub
		// set up hash table
		HashMap<String,String> machines = new HashMap<String,String>(300,1.5f);

		System.out.print("Enter input file name: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String file = br.readLine().trim();

		BufferedReader filebr = new BufferedReader(new FileReader(file));
		// read lines
		String line=null;
		while ((line = filebr.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			// put is used for insert as well as update (replace old value with new value for a key)
			machines.put(st.nextToken(), st.nextToken());
		}
		filebr.close();
		
		System.out.print("Enter machine you want to look up, or \"quit\": ");
		String machine = br.readLine().toLowerCase();
		while (!"quit".equals(machine)) {
			String room = machines.get(machine);
			if (room != null) {
				System.out.println(machine + " is in " + room);
			} else {
				System.out.println(machine + " is not in the hash table");
			}
			System.out.print("Enter machine you want to look up, or \"quit\": ");
			machine = br.readLine().toLowerCase();
		}

		Set<String> keys = machines.keySet();  // Set is of type String since keys are Strings
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key + " " + machines.get(key));
		}
	}
}
