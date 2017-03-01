// What happens when an argument is passed to a method which then
// changes its dummy parameter - is the change visible back in main?
// Three cases are tested: 1. parameter is an int, 2. parameter is a
// reference to an object and parameter itself is changed,
// 3. parameter is a reference to an object and a field of the object
// is changed.
public class ParamTest2{

    int x;

    public ParamTest2(int x){
	this.x = x;
    }

    public static void setParameter(int param1){
	param1 = 5;
    }
    public static void setObjectParameter(ParamTest2 objectParameter){
	ParamTest2 pt = new ParamTest2(10);
	objectParameter = pt;
    }
    public static void setField(ParamTest2 param1){
	param1.x = 15;
    }
    public static void main(String [ ] args){
	int a = 200;
	setParameter(a);
	System.out.println(a); // prints 200

	ParamTest2 pt1 = new ParamTest2(400);
	setObjectParameter(pt1);
	System.out.println(pt1.x); // prints 400

	ParamTest2 pt2 = new ParamTest2(600);
	setField(pt2);
	System.out.println(pt2.x); // prints 15
    }
}
