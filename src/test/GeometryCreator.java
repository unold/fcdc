package test;

import java.io.FileWriter;
import java.io.IOException;

import geometry.*;

public class GeometryCreator {
	
	private static int scale = 500;
	
	private static String generate() {

		String s = "";
		
		Point pa = new Point(0.5,0.2);
		Cone ca = new Cone(pa,Direction.north,Math.tan(Math.PI/2*(1-0.8)));
		s += pa.toSVG(scale,"green","A");
		s += ca.toSVG(scale,"black");
		
		Point pb = new Point(0.2,0.5);
		Cone cb = new Cone(pb,Direction.east,Math.tan(Math.PI/2*(1-0.7)));
		s += pb.toSVG(scale,"green","B");
		s += cb.toSVG(scale,"black");
		
		Point q1 = new Point(0.5,0.4);
		Point q2 = new Point(0.6,0.7);
		Polygon g = new Polygon();
		g.clip(ca);
		g.clip(cb);
		s += g.toSVG(scale,"red");
		s += q1.toSVG(scale,"red","P?");
		s += q2.toSVG(scale,"red","P?");
		
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
