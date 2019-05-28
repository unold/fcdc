package region.geometrybck;

public class Point extends Geometry {
	
	double x;
	double y;
	
	public Point() {
		this.x = Math.random();
		this.y = Math.random();
	}
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	double dist2(Point other) {
		double dx2 = (other.x-this.x)*(other.x-this.x);
		double dy2 = (other.y-this.y)*(other.y-this.y);
		return dx2 + dy2;
	}
	
	double angleTo(Point other) {
		return Math.atan2(other.y-this.y,other.x-this.x);
	}
	
	public String toString() {
		String s = "<circle cx=\""+(int)(x*scale)+"\" cy=\""+(int)(y*scale)+"\" r=\"3\" fill=\""+color+"\" />";
		if (name != null)
			s += "<text x=\""+(int)(x*scale+4)+"\" y=\""+(int)(y*scale)+"\" fill=\""+color+"\">"+name+"</text>";
		return s;
	}
	
}
