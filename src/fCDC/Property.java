package fCDC;

import geometry.Direction;

public enum Property {
	northOf,
	eastOf,
	southOf,
	westOf,
	closeTo;
	
	public Direction toDirection() {
		switch(this) {
		case northOf:
			return Direction.north;
		case eastOf:
			return Direction.east;
		case southOf:
			return Direction.south;
		case westOf:
			return Direction.west;
		default:
			return null;
		}
	}
}
