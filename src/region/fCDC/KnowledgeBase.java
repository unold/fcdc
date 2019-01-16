package region.fCDC;

import java.util.ArrayList;

import region.geometry.Circle;
import region.geometry.Cone;
import region.geometry.Direction;
import region.geometry.Zone;

public class KnowledgeBase {
	
	Circle[] regions;
	ArrayList<Axiom> axioms;
	
	KnowledgeBase(Circle[] regions, boolean angleMethod) {
		this.regions = regions;
		for (Circle s : regions) {
			for (Direction p : Direction.values()) {
				for (Circle o : regions) {
					if (angleMethod) {
						Cone c = Cone.generate(s,o,p);
						if (c != null) {
							axioms.add(new Axiom(s,p,o,c.getW()));
						}
					}
					else {
						Zone z = Zone.generate(s,p);
						double w = z.getW(o);
						if (w > 0) {
							axioms.add(new Axiom(s,p,o,w));
						}
					}
				}
			}
		}
	}
	
}
