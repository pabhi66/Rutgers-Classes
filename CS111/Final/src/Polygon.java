public class Polygon
{
	// define fields here
	private int vertexnumber;
	private int numbertices;
	Point p;

	public Polygon(int numvertices)
	{
		this.numbertices = numvertices;
		// complete this method
	}

	public boolean setVertex(int vertexnumber, Point p)
	{
		this.vertexnumber = vertexnumber;
		this.p = p;
		return false; // replace this line with your code
	}

	public double getPerimeter()
	{

		//return this.vertexnumber  + this.p; // replace this line with your code
		return 0.0;
	}

	public double getArea()
	{
		return 3+3; // replace this line with your code
	}
}
