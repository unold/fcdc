package region.geometry;

public class Line extends Geometry {
	
	Point p;
	double dir;
	double length;
	
	Line(Point p, double dir, double length) {
		this.p = p;
		this.dir = dir;
		this.length = length;
	}

	@Override
	public String toString() {
		double x = length*Math.cos(dir);
		double y = length*Math.sin(dir);
		return "<line x1=\""+(int)(p.x*scale)+"\" y1=\""+(int)(p.y*scale)+"\" x2=\""+(int)(x*scale)+"\" y2=\""+(int)(y*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
	}
	
}
