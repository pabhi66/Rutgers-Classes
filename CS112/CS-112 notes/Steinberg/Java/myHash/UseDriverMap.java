package myHash;

import java.util.*;

public class UseDriverMap {

	public static void main(String[] args) {
		// create new HashMap with capacity (array size) of 128 
		// and load factor limit (expand if lf reaches limit) of 2.5, ie
		// ie expand when # entries reaches 2.5*128
		HashMap<String, Driver> hm1 = new HashMap<String, Driver>(128, 2.5f);
		hm1.put("ST456", new Driver("Louis Seinberg","321 ain"));
		hm1.put("MO678", new Driver("Mickey Mouse", "1 Disney Drive"));
		System.out.println(hm1.get("ST455"));
		System.out.println(hm1.get("ST456"));
		hm1.put("ST456", new Driver("Louis Steinberg","321 Main"));
		System.out.println(hm1.get("ST456"));
	}

}
