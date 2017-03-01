package myHash;

public class Driver {
	String name;
	String address;
	
	public Driver(String name, String address){
		this.name = name;
		this.address = address;
	}
	
	public String toString( ){
		return name + " " + address;   
	}
}
