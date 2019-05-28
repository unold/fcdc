package region.geometrybck;

public enum Direction {
	
	E,NE,N,NW,W,SW,S,SE;
	
	public double toAngle() {
		switch(this) {
		case E:  return 0*Math.PI/4;
		case NE: return 1*Math.PI/4;
		case N:  return 2*Math.PI/4;
		case NW: return 3*Math.PI/4;
		case W:  return 4*Math.PI/4;
		case SW: return 5*Math.PI/4;
		case S:  return 6*Math.PI/4;
		case SE: return 7*Math.PI/4;
		default: return Double.NaN;
		}
	}

}
