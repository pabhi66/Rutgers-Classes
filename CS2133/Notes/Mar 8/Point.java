package geometry;

import java.io.Serializable;

public class Point implements Serializable {
	
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return x + "," + y;
	}
		
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Point)) {
			return false;
		}
		Point p = (Point)o;
		return x == p.x && y == p.y;
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
