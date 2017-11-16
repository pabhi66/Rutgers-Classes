package tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Country implements Comparable<Country> {
	
	String name;
	int pop;
	
	public Country(String name, int pop) {
		this.name = name;
		this.pop = pop;
	}
	
	public int compareTo(Country other) {
		// name field used as key
		return name.compareTo(other.name);
	}
}

public class BSTApp {
	
	static BST<Country> countries;
	static BufferedReader stdbr = 
			new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	throws IOException {
		// load countries from file into BST
		loadCountries();
		// do searches
		doSearches();
	}

	
	
	public static void loadCountries() 
	throws IOException {
		countries = new BST<Country>();
		BufferedReader br = new BufferedReader(
				new FileReader("world_pop.txt"));
		
		// first line is number of countries
		int n = Integer.parseInt(br.readLine());
		// cycle through all
		for (int i=0; i < n; i++) {
			String line = br.readLine();
			/*
			System.out.println(line);
			System.out.flush();
			*/
			// extract country name and pop
			int pos = line.indexOf('|');			
			String name = line.substring(0,pos);
			String popstr = line.substring(pos+1);
			// pop may have comma, remove and convert to int
			int pop = Integer.parseInt(popstr.replace(",",""));
			countries.insert(new Country(name,pop));
		}
		br.close();
	}
	
	public static void doSearches() 
	throws IOException {
		while (true) {
			System.out.print("(s)earch, or (q)uit?: ");
			String line = stdbr.readLine();
			if (line.length() == 0) {
				continue;
			}
			char choice = line.toLowerCase().charAt(0);
			if (choice == 'q') {
				break;
			}
			if (choice == 's') {
				search();
			}
		} 
	}
	
	public static void search() 
	throws IOException {
		System.out.print("Enter country name: ");
		String name = stdbr.readLine();
		
		// search with name as key, population set to -1 (or anything at
		// all, doesn't matter because it won't be used for comparisons
		// between countries - see Country's compareTo method
		// When the full matching Country object is returned, we will 
		// extract the population from it
		Country country = countries.search(new Country(name,-1));
		if (country == null) {
			System.out.println(country + " is not in tree");
		} else {
				System.out.println("Population of " + name +
									" = " + country.pop);
		}
	}
}
