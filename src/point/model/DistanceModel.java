package point.model;

import point.fCDC.Point;
import point.fCDC.Polygon;

public class DistanceModel implements Model {
	
	@Override
	public double direction(double d, double x, double y) {
		double m = x*Math.cos(d)+y*Math.sin(d);
		if (m<=0)
			return 0;
		return 1-Math.exp(-m);
	}

	@Override
	public Polygon polygonDirection(double d, double p) {
		if (p==0)
			return Polygon.all();
		Point[] points = new Point[4];
		double x,y;
		x = 2*Math.cos(d-Math.PI/2);
		y = 2*Math.sin(d-Math.PI/2);
		points[0] = new Point(x,y);
		x += 2*Math.cos(d);
		y += 2*Math.sin(d);
		points[1] = new Point(x,y);
		x = 2*Math.cos(d+Math.PI/2);
		y = 2*Math.sin(d+Math.PI/2);
		points[3] = new Point(x,y);
		x += 2*Math.cos(d);
		y += 2*Math.sin(d);
		points[2] = new Point(x,y);
		Polygon polygon = new Polygon(points);
		x = -Math.log(1-p)*Math.cos(d);
		y = -Math.log(1-p)*Math.sin(d);
		polygon.translate(x,y);
		return polygon;
	}

	@Override
	public double closeness(double x, double y) {
		return 0;
	}

	@Override
	public Polygon polygonCloseness(double p) {
		return Polygon.all();
	}
	

	@Override
	public double transitiveDirection(double p, double q) {
		return Math.min(p,q);
	}

	@Override
	public double intersectionDirection(double p, double q) {
		return Math.min(p,q);
	}

	@Override
	public double transitiveCloseness(double p, double q) {
		return 0;
	}
	

	@Override
	public double inverseDirection(double p) {
		return p;
	}

	@Override
	public double inverseCloseness(double p) {
		return 0;
	}

}
