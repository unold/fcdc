package point.geometrybck;

public enum Direction {
	east,
	north,
	west,
	south;
	
	public double toRadians() {
		switch(this) {
		case east:
			return 0;
		case north:
			return Math.PI/2;
		case west:
			return Math.PI;
		case south:
			return 3*Math.PI/2;
		default:
			return 0;
		}
	}
}
