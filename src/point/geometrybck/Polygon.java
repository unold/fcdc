package point.geometrybck;

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
		double step = Math.PI/32;
		double r = -Math.log(0.75)/Math.cos(step);
		double x = 0.2;
		double y = 0.3;
		for (double angle = 0; angle < 2*Math.PI; angle += 4*step) {
			Cone c = new Cone(new Point(x-Math.cos(angle)*r,y-Math.sin(angle)*r),angle,Math.PI/2-step);
			clip(c);
		}
	}
	
}
