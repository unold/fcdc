package point.model;

import point.fCDC.Point;
import point.fCDC.Polygon;

public class LogConeModel implements Model {
	
	@Override
	public double direction(double d, double x, double y) {
		if (x == 0 && y == 0)
			return 0;
		double a = Math.atan2(y,x);
		double h = -1;
		if ((d-Math.PI/2 < a && a < d+Math.PI/2))
			h = Math.abs(a-d);
		if ((d-Math.PI/2 < a+2*Math.PI && a+2*Math.PI < d+Math.PI/2))
			h = Math.abs(a+2*Math.PI-d);
		if (h < 0)
			return 0;
		double p = 1 - 2*h/Math.PI;
		return p>0 ? p : 0;
	}

	@Override
	public Polygon polygonDirection(double d, double p) {
		if (p==0)
			return Polygon.all();
		Point[] points = new Point[5];
		double x,y;
		points[0] = new Point(0,0);
		x = 2*Math.cos(d-(1-p)*Math.PI/2);
		y = 2*Math.sin(d-(1-p)*Math.PI/2);
		points[1] = new Point(x,y);
		x += 2*Math.cos(d);
		y += 2*Math.sin(d);
		points[2] = new Point(x,y);
		x = 2*Math.cos(d+(1-p)*Math.PI/2);
		y = 2*Math.sin(d+(1-p)*Math.PI/2);
		points[4] = new Point(x,y);
		x += 2*Math.cos(d);
		y += 2*Math.sin(d);
		points[3] = new Point(x,y);
		return new Polygon(points);
	}

	@Override
	public double closeness(double x, double y) {
		return Math.exp(-Math.sqrt(x*x+y*y));
	}

	@Override
	public Polygon polygonCloseness(double p) {
		if (p == 0)
			return Polygon.all();
		return Polygon.circle(-Math.log(p),32);
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
		return p*q;
	}
	

	@Override
	public double inverseDirection(double p) {
		return p;
	}

	@Override
	public double inverseCloseness(double p) {
		return p;
	}

}
