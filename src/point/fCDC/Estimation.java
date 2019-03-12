package point.fCDC;

public class Estimation {
	
	Point point;
	Polygon polygon;
	
	Estimation(Point point, Polygon polygon) {
		this.point = point;
		this.polygon = polygon;
	}
	
	public double distance() {
		return polygon.distance(point);
	}
	
	public String toSVG() {
		return point.toSVG(500,10,"green") + polygon.toSVG(500,2,"red");
	}

}
