package point.model;

import point.fCDC.Polygon;

public class LinearModel implements Model {
	
	double beta = 1;
	
	public LinearModel(double beta) {
		this.beta = beta;
	}
	
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
		double p = 1 - Math.sqrt(x*x+y*y)/beta;
		return p>0 ? p : 0;
	}

	@Override
	public Polygon polygonCloseness(double p) {
		if (p == 0)
			return Polygon.all();
		return Polygon.circle((1-p)*beta,32);
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
		return Math.max(p+q-1,0);
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
