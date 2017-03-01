class Employee {

	// fields
	public int acbalance;
	public int rate;
	public String empname;

	// first constructor
	public Employee(String name, int balance, int hourly) {
		empname = name;
		rate = hourly;
		acbalance = balance;

	}

	// second constructor
	public Employee(String name, int hourly) {
		empname = name;
		rate = hourly;
		acbalance = 0;
	}

	public void work(int hours) {

		acbalance += (rate * hours);

	}

	public void spend(int mon) {
		acbalance -= mon;

	}

	public void printinfo() {
		System.out.println("Employee name is: " + empname);
		System.out.println("Employee rate is: " + rate);
		System.out.println("Employee balance is: " + acbalance);
		System.out.println();

	}

}