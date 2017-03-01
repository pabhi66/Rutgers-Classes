public class EmployeeTester {

	public static void main(String[] args) {

		// Create objects/instances
		System.out.println("creating employees");

		Employee em1 = new Employee("Bob", 3000, 50);
		Employee em2 = new Employee("Rob", 1000, 20);
		Employee em3 = new Employee("Tom", 10);

		em1.printinfo();
		em2.printinfo();
		em3.printinfo();

		System.out.println("working employees a few hours");

		em1.work(10);
		em2.work(20);
		em3.work(30);

		System.out.println("After working: ");

		em1.printinfo();
		em2.printinfo();
		em3.printinfo();

	}
}