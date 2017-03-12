package geometry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class GeomApp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3853302790680221386L;

	private ArrayList<Point> points;
	
	public static final String storeDir = "dat";
	public static final String storeFile = "points.dat";
	
	private int x;  // added this after serializing instance of an older version
	
	public GeomApp() {
		points = new ArrayList<Point>();
	}
	
	public void addPoint(Point p) {
		points.add(p);
	}
	
	public void writePoints() {
		for (Point p: points) {
			System.out.println(p);
		}
	}
	
	public static void write(GeomApp app) 
	throws IOException {
		ObjectOutputStream oos =
				new ObjectOutputStream(
						new FileOutputStream(
								storeDir + File.separator + storeFile));
		oos.writeObject(app);
	}
	
	public static GeomApp read() 
	throws IOException, ClassNotFoundException {
		ObjectInputStream ois =
				new ObjectInputStream(
						new FileInputStream(
								storeDir + File.separator + storeFile));
		return (GeomApp)ois.readObject();
		
	}
	
	public static void main(String[] args) 
	throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		/*
		GeomApp gapp = new GeomApp();
		gapp.addPoint(new Point(1,2));
		gapp.addPoint(new ColoredPoint(2,3,"blue"));
		gapp.addPoint(new ColoredPoint(3,4,"green"));
		gapp.addPoint(new Point(4,5));
		gapp.addPoint(new Point(5,6));
		
		gapp.writePoints();
		
		write(gapp);  // serialize
		*/
		
		// deserialize
		
		GeomApp gapp = read();
		gapp.writePoints();
		
	}

}
