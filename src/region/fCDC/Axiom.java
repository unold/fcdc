package region.fCDC;

import region.geometry.Circle;
import region.geometry.Direction;

public class Axiom {
	
	Circle s;
	Direction p;
	Circle o;
	double w;
	
	Axiom(Circle s, Direction p, Circle o, double w) {
		this.s = s;
		this.p = p;
		this.o = o;
		this.w = w;
	}

}
