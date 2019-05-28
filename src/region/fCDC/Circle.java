package region.fCDC;

public class Circle {
	
	static double lambda = 100; // Parameter der Exponentialverteilung
	
	Point center;
	double radius;
	
	Circle() {
		do {
			radius = Math.log(1-Math.random())/(-lambda);
		} while (radius >= 0.5);
		center = new Point(radius);
	}
	
	public String toSVG(int scale, String color) {
		return toSVG(scale,color,null);
	}
	
	public String toSVG(int scale, String color, String name) {
		String s = "<circle cx=\""+(int)(center.x*scale)+"\" cy=\""+(int)(center.y*scale)+"\" r=\""+radius+"\" fill=\""+color+"\" stroke=\"black\" />";
		if (name != null)
			s += "<text x=\""+(int)(center.x*scale)+"\" y=\""+(int)(center.y*scale)+"\" fill=\""+color+"\">"+name+"</text>";
		return s;
	}

}
