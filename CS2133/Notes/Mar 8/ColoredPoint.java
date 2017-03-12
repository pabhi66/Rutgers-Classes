package geometry;

public class ColoredPoint extends Point {
	
	private String color;
	
	public ColoredPoint(int x, int y, String color) { 
		super(x,y);
		this.color = color;
	}
	
	public String toString() {
		return super.toString() + "," + color;
	}
	
	public String getColor() {
		return color;
	}
}
