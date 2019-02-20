package point.geometrybck;

public class Circle {
	
	Point c;
	double r;
	
	public Circle(double x, double y, double r) {
		this.c = new Point(x,y);
		this.r = r;
	}
	
	public Circle(Point p, double r) {
		this.c = p;
		this.r = r;
	}
	
	public String toString() {
		String s = "";
		s += "center: "+c+"\n";
		s += "radius: "+r+"\n";
		return s;
	}
	
	public String toSVG(int scale, String color) {
		return "<circle cx=\""+(int)(c.x*scale)+"\" cy=\""+(int)(c.y*scale)+"\" r=\""+(int)(r*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" fill=\"none\" />";
		
	}

}
