package point.fCDC;

public class Line {
	
	Point p;
	double dir;
	double length;
	
	Line(Point p, double dir, double length) {
		this.p = p;
		this.dir = dir;
		this.length = length;
	}
	
	Line(Point p1, Point p2) {
		p = p1;
		dir = Math.atan2(p2.y-p1.y,p2.x-p1.x);
		length = p1.distance(p2);
	}
	
	String toSVG(int scale, int size, String color) {
		Point e = end();
		return "<line x1=\""+(int)(p.x*scale)+"\" y1=\""+(int)(p.y*scale)+"\" x2=\""+(int)(e.x*scale)+"\" y2=\""+(int)(e.y*scale)+"\" stroke=\""+color+"\" stroke-width=\""+size+"\" />";
	}
	
	double distance(Point point) {
		return -(point.x - p.x)*Math.sin(dir) + (point.y - p.y)*Math.cos(dir);
	}
	
	void setEnd(Point p2) {
		dir = Math.atan2(p2.y-p.y,p2.x-p.x);
		length = p.distance(p2);
	}
	
	Point start() {
		return new Point(p.x,p.y);
	}
	
	Point end() {
		double x = p.x+length*Math.cos(dir);
		double y = p.y+length*Math.sin(dir);
		return new Point(x,y);
	}

	Line shortenedStart(double r) {
		double x = p.x + (1-r)*length*Math.cos(dir);
		double y = p.y + (1-r)*length*Math.sin(dir);
		return new Line(new Point(x,y),dir,length*r);
	}
	
	void shortenEnd(double r) {
		length *= r;
	}
	
	public String toString() {
		return "[ " + start() + " , " + end() + " ]";
	}
	

}
