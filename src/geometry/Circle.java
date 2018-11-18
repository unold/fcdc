package geometry;

public class Circle {
	
	Point c;
	double r;
	
	public Circle(double x, double y, double r) {
		this.c = new Point(x,y);
		this.r = r;
	}
	
	public String toString() {
		String s = "";
		s += "center: "+c+"\n";
		s += "radius: "+r+"\n";
		return s;
	}

}
