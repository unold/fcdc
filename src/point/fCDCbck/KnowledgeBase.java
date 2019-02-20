package point.fCDCbck;

import java.util.ArrayList;
import java.util.HashMap;

import point.geometrybck.Circle;
import point.geometrybck.Cone;
import point.geometrybck.Direction;
import point.geometrybck.Point;
import point.geometrybck.Polygon;

public class KnowledgeBase {
	
	private HashMap<Place,ArrayList<Assertion>> assertions;
	private boolean vagueness;
	
	KnowledgeBase(ArrayList<Assertion> assertions, double relLoss, boolean vagueness) {
		this.vagueness = vagueness;
		this.assertions = new HashMap<Place,ArrayList<Assertion>>();
		int amount = (int)((1-relLoss)*assertions.size());
		for (Assertion a : assertions) {
			if (!vagueness)
				a.w = 1;
			if (amount == 0)
				break;
			add(a);
			--amount;
		}
	}
	
	private boolean add(Assertion assertion) {
		if (assertions.containsKey(assertion.a)) {
			ArrayList<Assertion> list = assertions.get(assertion.a);
			for (Assertion a : list) {
				if (assertion.d == a.d && assertion.b == a.b) {
					if (assertion.w > a.w) {
						a.w = assertion.w;
						return true;
					}
					else {
						return false;
					}
				}
			}
			assertions.get(assertion.a).add(assertion);
			return true;
		}
		else {
			ArrayList<Assertion> list = new ArrayList<Assertion>();
			list.add(assertion);
			assertions.put(assertion.a,list);
			return true;
		}
	}
	
	public void reasoning() {
		boolean change = true;
		while(change) {
			change = false;
			ArrayList<Assertion> newAssertions = new ArrayList<Assertion>();
			for (ArrayList<Assertion> list : assertions.values()) {
				for (Assertion x : list) {
					if (assertions.containsKey(x.b))
					for (Assertion y : assertions.get(x.b)) {
						if (x.d == y.d) {
							double p = 0;
							switch (x.d) {
							case northOf: case eastOf: case southOf: case westOf: p = Math.min(x.w,y.w); break;
							case closeTo: p = x.w*y.w;
							}
							newAssertions.add(new Assertion(x.a,x.d,y.b,p));
						}
					}
				}
			}
			for (ArrayList<Assertion> list : assertions.values()) {
				for (Assertion x : list) {
					Property d = null;
					switch (x.d) {
					case northOf: d = Property.southOf; break;
					case eastOf: d = Property.westOf; break;
					case southOf: d = Property.northOf; break;
					case westOf: d = Property.eastOf; break;
					case closeTo: d = Property.closeTo; break;
					}
					newAssertions.add(new Assertion(x.b,d,x.a,x.w));
				}
			}
			for (Assertion a : newAssertions) {
				if(add(a))
					change = true;
			}
		}
	}
	
	public Polygon calcPolygon(Place pl, ArrayList<Place> known) {
		Polygon p = new Polygon();
		if (assertions.containsKey(pl))
		for (Assertion a : assertions.get(pl)) {
			if (known.contains(a.b)) {
				double x = a.b.getX();
				double y = a.b.getY();
				if (a.d != Property.closeTo) {
					double t = vagueness ? Math.PI*(1-a.w)/2 : Math.PI/2;
					p.clip(new Cone(new Point(x,y),a.d.toDirection(),t));
				}
				else {
					if (vagueness)
						p.clip(new Circle(x,y,-Math.log(a.w)));
				}
			}
		}
		return p;
	}
	
}
