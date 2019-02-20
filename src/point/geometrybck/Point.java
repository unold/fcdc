package point.geometrybck;

public class Point {
	
	double x;
	double y;
	
	public Point () {
		this.x = 0;
		this.y = 0;
	}
	
	public Point (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double distance(Point other) {
		double dx = this.x - other.x;
		double dy = this.y - other.y;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public String toSVG(int scale, String color) {
		return "<circle cx=\""+(int)(x*scale)+"\" cy=\""+(int)(y*scale)+"\" r=\"3\" fill=\""+color+"\" />";
	}
	
	public String toSVG(int scale, String color, String label) {
		return toSVG(scale,color) + "<text x=\""+(int)(x*scale+4)+"\" y=\""+(int)(y*scale)+"\" fill=\""+color+"\">"+label+"</text>";
	}
	
	public String toString() {
		return "("+x+"/"+y+")";
	}

}
