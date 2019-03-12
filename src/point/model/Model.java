package point.model;

import point.fCDC.Polygon;

public interface Model {

	double direction(double d, double x, double y);
	Polygon polygonDirection(double d, double p);
	double closeness(double x, double y);
	Polygon polygonCloseness(double p);
	
	double transitiveDirection(double p, double q);
	double intersectionDirection(double p, double q);
	double transitiveCloseness(double p, double q);
	double inverseDirection(double p);
	double inverseCloseness(double p);

}
