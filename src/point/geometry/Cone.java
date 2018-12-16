package point.geometry;

public class Cone {
	
	Point center;
	double dir;
	double angle;
	
	public Cone(Point center, Direction dir, double angle) {
		this.center = center;
		this.dir = dir.toRadians();
		this.angle = angle;
	}
	
	public Cone(Point center, double dir, double angle) {
		this.center = center;
		this.dir = dir;
		this.angle = angle;
	}
	
	boolean clip(Point p, boolean side) {
		double s = side ? 1 : -1;
		double n = dir+s*(Math.PI/2-angle);
		double d = center.x*Math.cos(n) + center.y*Math.sin(n);
		return p.x*Math.cos(n) + p.y*Math.sin(n) > d;
	}
	
	Point clip(Line line, boolean side) {
		double s = side ? 1 : -1;
		double n = dir+s*(Math.PI/2-angle);
		double sn = Math.sin(n);
		double cn = Math.cos(n);
		double d = center.x*cn + center.y*sn;
		double h = (d-line.a.x*cn-line.a.y*sn)/((line.b.x-line.a.x)*cn+(line.b.y-line.a.y)*sn);
		double x = line.a.x + h*(line.b.x-line.a.x);
		double y = line.a.y + h*(line.b.y-line.a.y);
		return new Point(x,y);
	}
	
	public String toString() {
		String s = "\n";
		s += "center: "+center.x+" / "+center.y+" \n";
		s += "dir: "+dir+"\n";
		s += "angle: "+angle+"\n";
		return s;
	}
	
	public String toSVG(int scale, String color) {
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
