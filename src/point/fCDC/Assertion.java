package fCDC;

public class Assertion {
	
	Place a;
	Place b;
	Property d;
	double w;
	
	public Assertion(Place a, Property d, Place b, double w) {
		this.a = a;
		this.b = b;
		this.d = d;
		this.w = w;
	}
	
	public String toTTL() {
		return a.getID()+" fcdc:"+d+" "+b.getID()+" \""+w+"\"^^xsd:double . \n";
	}
	
	public String toSVG(int size) {
		return "<line x1=\""+(int)(a.getX()*size)+"\" y1=\""+(int)(a.getY()*size)+"\" x2=\""+(int)(b.getX()*size)+"\" y2=\""+(int)(b.getY()*size)+"\" stroke=\"black\" stroke-width=\"1\" />";
	}

}
