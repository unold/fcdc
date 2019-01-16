package region.geometry;

public class Cone extends Geometry {
	
	Point center;
	double dir;
	double angle;
	
	public static Cone generate(Circle from, Circle to, Direction dir) {
		Cone c = new Cone();
		c.center = new Point(from.center.x,from.center.y);
		double a = from.center.angleTo(to.center) - dir.toAngle();
		if (a<0)
			a += 2*Math.PI;
		a += Math.asin(to.radius/Math.sqrt(from.center.dist2(to.center)));
		if (a > Math.PI/2)
			return null;
		c.angle = a;
		c.color = "red";
		return c;
	}
	
	public Polygon toPolygon() {
		return null; // TODO
	}
	
	public double getW() {
		return 1-2*angle/Math.PI;
	}
	
	public String toString() {
		double x,y;
		String s = "";
		x = center.x + Math.cos(dir-angle);
		y = center.y + Math.sin(dir-angle);
		s += "<line x1=\""+(int)(center.x*scale)+"\" y1=\""+(int)(center.y*scale)+"\" x2=\""+(int)(x*scale)+"\" y2=\""+(int)(y*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		x = center.x + Math.cos(dir+angle);
		y = center.y + Math.sin(dir+angle);
		s += "<line x1=\""+(int)(center.x*scale)+"\" y1=\""+(int)(center.y*scale)+"\" x2=\""+(int)(x*scale)+"\" y2=\""+(int)(y*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		return s;
	}
	
}
