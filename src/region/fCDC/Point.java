package region.fCDC;

public class Point {
	
	double x;
	double y;
	
	Point(double r) {
		x = r + Math.random()*(1-2*r);
		y = r + Math.random()*(1-2*r);
	}

}
