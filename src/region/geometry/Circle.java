package region.geometry;

public class Circle extends Geometry {
	
	public static double lambda = 100;
	
	Point center;
	double radius;
	
	public Circle() {
		this.center = new Point();
		this.radius = Math.log(1-Math.random())/(-lambda);
	}
	
	public Polygon toPolygon() {
		return null; // TODO
	}
	
	public String toString() {
		String s = "<circle cx=\""+(int)(center.x*scale)+"\" cy=\""+(int)(center.y*scale)+"\" r=\""+(int)(radius*scale)+"\" stroke=\""+color+"\" fill=\"none\" />";
		if (name != null)
			s += "<text x=\""+(int)(center.x*scale)+"\" y=\""+(int)(center.y*scale)+"\" fill=\""+color+"\">"+name+"</text>";
		return s;
	}

}
