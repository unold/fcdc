package region.test;

import java.io.FileWriter;
import java.io.IOException;

import region.geometrybck.*;

public class GeometryTest {
	
	private static String generate(int n) {

		String s = "";
		
		for (int i=0; i<n; ++i) {
			Circle c = new Circle();
			s += c;
		}
		
		return s;
		
	}
	
	public static void test(int n, int l) {
		Circle.lambda = l;
		String filename = "test_"+n+"_"+l+".svg";
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			writer.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">");
			writer.write(generate(n));
			writer.write("</svg>");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		/*for (int n=10; n<=1000; n*=10) {
			for (int l=10; l<=1000; l*=10) {
				test(n,l);
			}
		}*/
		
		String s = "";
		
		Circle.lambda = 20;
		Circle a = new Circle();
		s += a;
		Circle b = new Circle();
		s += b;
		
		Cone c = Cone.generate(a,b,Direction.E);
		if (c == null)
			return;
		
		String filename = "test1.svg";
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			writer.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">");
			writer.write(s+c);
			writer.write("</svg>");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Zone x = Zone.generate(a,Direction.E);
		
		filename = "test2.svg";
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			writer.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">");
			writer.write(s+x);
			writer.write("</svg>");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Zone y = Zone.generate(a,Direction.W);
		
		filename = "test3.svg";
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			writer.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">");
			writer.write(s+y);
			writer.write("</svg>");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
