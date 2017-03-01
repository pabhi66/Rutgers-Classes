public class EqualsTest{
    public static void main(String [ ] args){
	String s1 = "Hello";
	String s2 = "xHello";
	String s2a = s2.substring(1);
	System.out.println(s2a);
	System.out.println(s1 == s2a);
	System.out.println(s1.equals(s2a));
	System.out.println(s2a == s3);
    }
}
