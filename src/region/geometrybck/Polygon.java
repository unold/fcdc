package region.geometrybck;

import java.util.ArrayList;

/*
 * Points x within the polygon fulfill the equation
 * (x-p)*n > 0 for each line, where n = (cos(dir+PI/2),sin(dir+PI/2)).
 * The lines of the polygon are oriented counterclockwise.
 */

public class Polygon extends Geometry {
	
	ArrayList<Line> lines;
	
	public Polygon() {
		lines = new ArrayList<Line>();
		lines.add(new Line(new Point(0,0),0*Math.PI/2,1));
		lines.add(new Line(new Point(1,0),1*Math.PI/2,1));
		lines.add(new Line(new Point(1,1),2*Math.PI/2,1));
		lines.add(new Line(new Point(0,1),3*Math.PI/2,1));
	}
	
	public void clip(Line clipLine) {
		
	}

	public String toString() {
		String s = "";
		for (Line l : lines) {
			l.color = this.color;
			s += l;
		}
		return s;
	}
	
	
	
}
