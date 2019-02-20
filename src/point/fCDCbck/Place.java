package point.fCDCbck;

import java.util.ArrayList;

import point.geometrybck.Polygon;

public class Place {
	
	private double x;
	private double y;
	private String id;
	private Polygon p;
	
	public Place(int i) {
		x = Math.random();
		y = Math.random();
		id = "higeomes:Place"+i;
		p = null;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public String getID() {
		return id;
	}
	
	public void setPolygon(Polygon p) {
		this.p = p;
	}
	
	public String toSVG(int size) {
		if (p == null)
			return "<circle cx=\""+(int)(x*size)+"\" cy=\""+(int)(y*size)+"\" r=\"3\" fill=\"red\" />";
		return p.toSVG(size,"red");
	}
	
	public ArrayList<Assertion> calcAssertions(Place other, double z) {
		ArrayList<Assertion> assertions = new ArrayList<Assertion>();
		if (this.y > other.y) {
			double p = 1 - 2/Math.PI*Math.atan(Math.abs(this.x-other.x)/(this.y-other.y)) - z;
			if (p > 0)
				assertions.add(new Assertion(this,Property.northOf,other,p));
		}
		if (this.x > other.x) {
			double p = 1 - 2/Math.PI*Math.atan(Math.abs(this.y-other.y)/(this.x-other.x)) - z;
			if (p > 0)
				assertions.add(new Assertion(this,Property.eastOf,other,p));
		}
		if (this.y < other.y) {
			double p = 1 - 2/Math.PI*Math.atan(Math.abs(this.x-other.x)/(other.y-this.y)) - z;
			if (p > 0)
				assertions.add(new Assertion(this,Property.southOf,other,p));
		}
		if (this.x < other.x) {
			double p = 1 - 2/Math.PI*Math.atan(Math.abs(this.y-other.y)/(other.x-this.x)) - z;
			if (p > 0)
				assertions.add(new Assertion(this,Property.westOf,other,p));
		}
		double p = Math.exp(-Math.sqrt((this.x-other.x)*(this.x-other.x) + (this.y-other.y)*(this.y-other.y))) - z;
		if (p > 0)
			assertions.add(new Assertion(this,Property.closeTo,other,p));
		return assertions;
	}

	public double calcDistance() {
		if (p == null)
			return 0;
		return p.distance(x,y);
	}

}
