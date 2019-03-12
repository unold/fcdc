package point.model;

import point.fCDC.Polygon;

public class LogModel implements Model {
	
	@Override
	public double direction(double d, double x, double y) {
		return 0;
	}

	@Override
	public Polygon polygonDirection(double d, double p) {
		return Polygon.all();
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
		return 0;
	}

	@Override
	public double intersectionDirection(double p, double q) {
		return 0;
	}

	@Override
	public double transitiveCloseness(double p, double q) {
		return p*q;
	}

	@Override
	public double inverseDirection(double p) {
		return 0;
	}

	@Override
	public double inverseCloseness(double p) {
		return p;
	}

}
