package region.geometrybck;

public class Zone extends Geometry {
	
	Point ne;
	Point nw;
	Point sw;
	Point se;
	Direction d;
	
	public static Zone generate(Circle c, Direction d) {
		Zone s = new Zone();
		s.d = d;
		double x = c.center.x;
		double y = c.center.y;
		double r = c.radius;
		s.ne = new Point(x+r,y+r);
		s.nw = new Point(x-r,y+r);
		s.sw = new Point(x-r,y-r);
		s.se = new Point(x+r,y-r);
		s.color = "red";
		return s;
	}
	
	public String toString() {
		String s = "";
		if (d == Direction.E)
			s += "<line x1=\""+(int)(se.x*scale)+"\" y1=\""+(int)(se.y*scale)+"\" x2=\""+(int)(ne.x*scale)+"\" y2=\""+(int)(ne.y*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		if (d == Direction.N)
			s += "<line x1=\""+(int)(ne.x*scale)+"\" y1=\""+(int)(ne.y*scale)+"\" x2=\""+(int)(nw.x*scale)+"\" y2=\""+(int)(nw.y*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		if (d == Direction.W)
			s += "<line x1=\""+(int)(nw.x*scale)+"\" y1=\""+(int)(nw.y*scale)+"\" x2=\""+(int)(sw.x*scale)+"\" y2=\""+(int)(sw.y*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		if (d == Direction.S)
			s += "<line x1=\""+(int)(sw.x*scale)+"\" y1=\""+(int)(sw.y*scale)+"\" x2=\""+(int)(se.x*scale)+"\" y2=\""+(int)(se.y*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		if (d == Direction.NE || d == Direction.E)
			s += "<line x1=\""+(int)(ne.x*scale)+"\" y1=\""+(int)(ne.y*scale)+"\" x2=\""+(int)(1*scale)+"\" y2=\""+(int)(ne.y*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		if (d == Direction.NE || d == Direction.N)
			s += "<line x1=\""+(int)(ne.x*scale)+"\" y1=\""+(int)(ne.y*scale)+"\" x2=\""+(int)(ne.x*scale)+"\" y2=\""+(int)(1*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		if (d == Direction.NW || d == Direction.N)
			s += "<line x1=\""+(int)(nw.x*scale)+"\" y1=\""+(int)(nw.y*scale)+"\" x2=\""+(int)(nw.x*scale)+"\" y2=\""+(int)(1*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		if (d == Direction.NW || d == Direction.W)
			s += "<line x1=\""+(int)(nw.x*scale)+"\" y1=\""+(int)(nw.y*scale)+"\" x2=\""+(int)(0*scale)+"\" y2=\""+(int)(nw.y*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		if (d == Direction.SW || d == Direction.W)
			s += "<line x1=\""+(int)(sw.x*scale)+"\" y1=\""+(int)(sw.y*scale)+"\" x2=\""+(int)(0*scale)+"\" y2=\""+(int)(sw.y*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		if (d == Direction.SW || d == Direction.S)
			s += "<line x1=\""+(int)(sw.x*scale)+"\" y1=\""+(int)(sw.y*scale)+"\" x2=\""+(int)(sw.x*scale)+"\" y2=\""+(int)(0*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		if (d == Direction.SE || d == Direction.S)
			s += "<line x1=\""+(int)(se.x*scale)+"\" y1=\""+(int)(se.y*scale)+"\" x2=\""+(int)(se.x*scale)+"\" y2=\""+(int)(0*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		if (d == Direction.SE || d == Direction.E)
			s += "<line x1=\""+(int)(se.x*scale)+"\" y1=\""+(int)(se.y*scale)+"\" x2=\""+(int)(1*scale)+"\" y2=\""+(int)(se.y*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
		return s;
	}
	
	public Polygon toPolygon() {
		return null; // TODO
	}

	public double getW(Circle o) {
		return 0; // TODO
	}

}
