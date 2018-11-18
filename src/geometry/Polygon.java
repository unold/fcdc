package geometry;

import java.util.ArrayList;

public class Polygon {
	
	ArrayList<Line> lines = new ArrayList<Line>();
	
	public Polygon() {
		lines.add(new Line(new Point(0,0),new Point(0,1)));
		lines.add(new Line(new Point(0,1),new Point(1,1)));
		lines.add(new Line(new Point(1,1),new Point(1,0)));
		lines.add(new Line(new Point(1,0),new Point(0,0)));
	}
	
	public void clip(Cone cone) {
		clip(cone,true);
		clip(cone,false);
	}
	
	private void clip(Cone cone, boolean side) {
		ArrayList<Line> newLines = new ArrayList<Line>();
		Point p = null;
		Point q = null;
		for (Line line : lines) {
			boolean a = cone.clip(line.a,side);
			boolean b = cone.clip(line.b,side);
			if (a && b)
				newLines.add(line);
			if (a != b) {
				Point r = a ? line.a : line.b;
				if (p == null) {
					p = cone.clip(line,side);
					newLines.add(new Line(p,r));
				}
				else if (q == null) {
					q = cone.clip(line,side);
					newLines.add(new Line(q,r));
				}
			}
		}
		if (p != null && q != null)
			newLines.add(new Line(p,q));
		lines = newLines;
	}
	
	public double distance(double x, double y) {
		Point p = new Point(x,y);
		double d = 0;
		for (Line line : lines) {
			d = Math.max(d,line.a.distance(p));
			d = Math.max(d,line.b.distance(p));
		}
		return d;
	}
	
	public String toSVG(int scale, String color) {
		String s = "";
		for (Line line : lines) {
			s += line.toSVG(scale,color);
		}
		return s;
	}

	public void clip(Circle circle) {
		System.out.println(circle);
		double r = Math.sqrt(2)*circle.r;
		double x = circle.c.x;
		double y = circle.c.y;
		Cone c;
		c = new Cone(new Point(x-r,y),Direction.north,Math.PI/4);
		clip(c);
		c = new Cone(new Point(x,y-r),Direction.east,Math.PI/4);
		clip(c);
		c = new Cone(new Point(x+r,y),Direction.south,Math.PI/4);
		clip(c);
		c = new Cone(new Point(x,y+r),Direction.west,Math.PI/4);
		clip(c);
	}
	
}
