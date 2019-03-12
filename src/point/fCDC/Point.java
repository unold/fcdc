package point.fCDC;

public class Point {
	
	double x;
	double y;
	
	Point() {
		x = Math.random();
		y = Math.random();
	}
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	double distance(Point other) {
		double dx = other.x - this.x;
		double dy = other.y - this.y;
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	public String toSVG(int scale, int size, String color) {
		return toSVG(scale,size,color,null);
	}
	
	public String toSVG(int scale, int size, String color, String name) {
		String s = "<circle cx=\""+(int)(x*scale)+"\" cy=\""+(int)(y*scale)+"\" r=\""+size+"\" fill=\""+color+"\" />";
		if (name != null)
			s += "<text x=\""+(int)(x*scale+size+1)+"\" y=\""+(int)(y*scale)+"\" fill=\""+color+"\">"+name+"</text>";
		return s;
	}
	
	public String toString() {
		return x + " / " + y;
	}

}
