package point.fCDC;

import java.util.ArrayList;

public class Polygon {
	
	private ArrayList<Line> lines;
	
	private Polygon() {
		lines = new ArrayList<Line>();
	}
	
	public static Polygon all() {
		Point[] points = new Point[4];
		points[0] = new Point(-2,-2);
		points[1] = new Point(2,-2);
		points[2] = new Point(2,2);
		points[3] = new Point(-2,2);
		return new Polygon(points);
	}
	
	public static Polygon empty() {
		return new Polygon();
	}
	
	public static Polygon square() {
		Polygon p = new Polygon();
		p.lines.add(new Line(new Point(0,0),0*Math.PI/2,1));
		p.lines.add(new Line(new Point(1,0),1*Math.PI/2,1));
		p.lines.add(new Line(new Point(1,1),2*Math.PI/2,1));
		p.lines.add(new Line(new Point(0,1),3*Math.PI/2,1));
		return p;
	}
	
	public static Polygon circle(double radius, int parts) {
		Point[] points = new Point[parts];
		for (int i=0; i<parts; ++i) {
			double x = radius*Math.cos(2*Math.PI*i/parts);
			double y = radius*Math.sin(2*Math.PI*i/parts);
			points[i] = new Point(x,y);
		}
		return new Polygon(points);
	}
	
	public Polygon(Point[] points) {
		lines = new ArrayList<Line>();
		lines.add(new Line(points[points.length-1],points[0]));
		for (int i=1; i<points.length; ++i) {
			lines.add(new Line(points[i-1],points[i]));
		}
	}
	
	public void clip(Polygon other) {
		for (Line l : other.lines) {
			clip(l);
		}
	}
	
	public void clip(Line clipLine) {
		int n = lines.size();
		if (n == 0)
			return;
		ArrayList<Line> newLines = new ArrayList<Line>();
		Point newStart = null, newEnd = null;
		Line line = lines.get(n-1);
		double d0 = clipLine.distance(line.p);
		for (int i=0; i<n; ++i) {
			double d = clipLine.distance(lines.get(i).p);
			if (d0>0 && d>0)
				newLines.add(line);
			if (d0>0 && d<=0) {
				line.shortenEnd(d0/(d0-d));
				newStart = line.end();
				newLines.add(line);
				if (newEnd != null)
					newLines.add(new Line(newStart,newEnd));
			}
			if (d0<=0 && d>0) {
				Line newLine = line.shortenedStart(d/(d-d0));
				newEnd = newLine.start();
				if (newStart != null)
					newLines.add(new Line(newStart,newEnd));
				newLines.add(newLine);
			}
			d0 = d;
			line = lines.get(i);
		}
		lines = newLines;
	}
	
	public void translate(double x, double y) {
		for (Line l : lines) {
			l.p.x += x;
			l.p.y += y;
		}
	}
	
	double distance(Point p) {
		double d = 0;
		for (Line l : lines) {
			d = Math.max(d,l.p.distance(p));
		}
		return d;
	}
	
	String toSVG(int scale, int size, String color) {
		String s = "";
		for (Line l : lines) {
			s += l.toSVG(scale, size, color);
		}
		return s;
	}
	
	public String toString() {
		String s = "";
		for (Line l : lines) {
			s += l + "\n";
		}
		return s;
	}

}
