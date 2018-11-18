package geometry;

public enum Direction {
	north,
	east,
	south,
	west;
	
	public double toRadians() {
		switch(this) {
		case north:
			return Math.PI/2;
		case east:
			return 0;
		case south:
			return -Math.PI/2;
		case west:
			return Math.PI;
		default:
			return 0;
		}
	}
}
