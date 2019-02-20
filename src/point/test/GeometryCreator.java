package point.test;

import java.io.FileWriter;
import java.io.IOException;

import point.geometrybck.*;

public class GeometryCreator {
	
	private static int scale = 500;
	
	private static String generate() {

		String s = "";
		
		Point b = new Point(0.5,0.5);
		s += b.toSVG(scale,"green","B");
		
		Point a1 = new Point(0.7,0.5);
		s += a1.toSVG(scale,"red","A?");
		
		Point a2 = new Point(0.3,0.6);
		s += a2.toSVG(scale,"red","A?");
		
		Point a3 = new Point(0.4,0.3);
		s += a3.toSVG(scale,"red","A?");
		
		Circle c = new Circle(b,0.3);
		s += c.toSVG(scale,"black");
		
		/*
		Point pa = new Point(0.5,0.8);
		Cone ca = new Cone(pa,Direction.south,(Math.PI/2*(1-0.7)));
		s += pa.toSVG(scale,"green","A");
		s += ca.toSVG(scale,"black");
		
		Point pb = new Point(0.8,0.5);
		Cone cb = new Cone(pb,Direction.west,(Math.PI/2*(1-0.7)));
		s += pb.toSVG(scale,"green","B");
		s += cb.toSVG(scale,"black");
		
		Point pc = new Point(0.2,0.3);
		Circle cc = new Circle(pc,-Math.log(0.75));
		s += pc.toSVG(scale,"green","C");
		s += cc.toSVG(scale,"black");
		
		Polygon g = new Polygon();
		g.clip(ca);
		g.clip(cb);
		g.clip(cc);
		s += g.toSVG(scale,"red");
		
		Point q1 = new Point(0.4,0.4);
		s += q1.toSVG(scale,"red","P?");
		*/
		
		return s;
		
	}
	
	private static String caption(int scale, String text) {
		return "<text x=\""+10+"\" y=\""+26+"\" fill=\"black\">"+text+"</text>";
	}

	public static void main(String[] args) {
		String filename = "test.svg";
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			writer.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">");
			writer.write(generate());
			writer.write("</svg>");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
