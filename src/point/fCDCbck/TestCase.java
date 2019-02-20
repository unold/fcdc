package point.fCDCbck;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TestCase {
	
	private Place[] places;
	private ArrayList<Assertion> assertions;
	
	public TestCase(int amount, double imprecision) {
		places = new Place[amount];
		for (int i=0; i<amount; ++i) {
			places[i] = new Place(i);
		}
		assertions = new ArrayList<Assertion>();
		for (int i=0; i<places.length; ++i) {
			for (int j=0; j<places.length; ++j) {
				if (i != j)
					assertions.addAll(places[i].calcAssertions(places[j],imprecision));
			}
		}
		Collections.shuffle(assertions);
	}
	
	public void calculation(double relLoss, double absLoss, boolean reasoning, boolean vagueness) {
		KnowledgeBase kb = new KnowledgeBase(assertions,relLoss,vagueness);
		if (reasoning)
			kb.reasoning();
		int k = (int)((1-absLoss)*places.length);
		ArrayList<Place> known = new ArrayList<Place>();
		for (int i=0; i<k; ++i) {
			places[i].setPolygon(null);
			known.add(places[i]);
		}
		for (int i=k; i<places.length; ++i) {
			places[i].setPolygon(kb.calcPolygon(places[i],known));
		}
	}
	
	public void printSVG(String filename, int size) {
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			writer.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">");
			for (Place p : places) {
				writer.write(p.toSVG(size));
			}
			writer.write("</svg>");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printTTL(String filename) {
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write("@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> . \n");
			writer.write("@prefix xsd: <http://www.w3.org/2001/XMLSchema#> . \n");
			writer.write("@prefix fcdc: <http://www.fuzzy-cardinal-direction-calculus.xyz#> . \n");
			writer.write("@prefix higeomes: <http://www.higeomes.org#> . \n");
			for (Place p : places) {
				writer.write(p.getID()+" rdf:type fcdc:Place . \n");
				writer.write(p.getID()+" fcdc:x \""+p.getX()+"\"^^xsd:double . \n");
				writer.write(p.getID()+" fcdc:y \""+p.getY()+"\"^^xsd:double . \n");
			}
			for (Assertion a : assertions) {
				writer.write(a.toTTL());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public double avgDistance() {
		double d = 0;
		for (Place p : places) {
			d += p.calcDistance();
		}
		return d/places.length;
	}
	
}
