package region.geometry;

public class Point implements Geometry {
	
	double x;
	double y;
	
	double dist2(Point other) {
		double dx2 = (this.x-other.x)*(this.x-other.x);
		double dy2 = (this.y-other.y)*(this.y-other.y);
		return dx2 + dy2;
	}
	
}
