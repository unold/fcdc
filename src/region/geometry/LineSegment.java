package region.geometry;

public class LineSegment {
	
	Point start;
	Point end;
	
	double length() {
		return Math.sqrt(start.dist2(end));
	}
	
}
