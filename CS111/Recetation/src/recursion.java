public class recursion {

	public static void main(String[] args){
		
		String animal = "giraffe";
		
		String reversedAnimal = reverse(animal);
		
		System.out.println(reversedAnimal);
		
		System.out.println(isPalindrome("anna"));
		
		
	}
	
	public static String reverse(String str) {
		
		//base case
		if (str.length() == 0) {
			return str;
		}
		
		//recursive step, return back to the method itself!
		return reverse(str.substring(1, str.length())) + str.charAt(0);
	}

	public static boolean isPalindrome(String str) {
		
		//base case
		if (str.length() <= 1) {
			return true;
		}
		
		//recursive
		if (str.charAt(0) != str.charAt(str.length() - 1)) {
			return false;
		}
		
		return isPalindrome(str.substring(1, str.length() - 1));
	}

}