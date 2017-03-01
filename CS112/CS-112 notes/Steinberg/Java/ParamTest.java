class Fie{
    int x;
    public Fie(int x){
	this.x = x;
    }
}
public class ParamTest{
    public static void foo(int arg1){
	arg1 = 10;
    }
    public static void bar(Fie fie1){
	fie1.x = 5;
    }
    public static void main(String [ ] args){
	int a = 20;
	foo(a);
	System.out.println(a);
	Fie f2 = new Fie(40);
	bar(f2);
	System.out.println(f2.x);
    }
}
