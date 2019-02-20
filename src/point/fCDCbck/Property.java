package point.fCDCbck;

import point.geometrybck.Direction;

public enum Property {
	eastOf,
	northOf,
	westOf,
	southOf,
	closeTo;
	
	public Direction toDirection() {
		switch(this) {
		case eastOf:
			return Direction.east;
		case northOf:
			return Direction.north;
		case westOf:
			return Direction.west;
		case southOf:
			return Direction.south;
		default:
			return null;
		}
	}
}
