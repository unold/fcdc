package point.geometry;

class Line {
	
	Point a;
	Point b;
	
	Line (Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	public String toSVG(int scale, String color) {
		return "<line x1=\""+(int)(a.x*scale)+"\" y1=\""+(int)(a.y*scale)+"\" x2=\""+(int)(b.x*scale)+"\" y2=\""+(int)(b.y*scale)+"\" stroke=\""+color+"\" stroke-width=\"1\" />";
	}

}
