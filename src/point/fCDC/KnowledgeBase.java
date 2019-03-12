package point.fCDC;

import java.util.Arrays;

import point.model.Model;

public class KnowledgeBase {
	
	public static String test() {
		Polygon p1 = Polygon.circle(1,32);
		System.out.println(p1+"\n\n");
		Polygon p2 = Polygon.square();
		p2.translate(0.1,0.6);
		Line line = new Line(new Point(0,1),new Point(1,0));
		p1.clip(line);
		System.out.println(p1+"\n\n");
		p1.clip(p2);
		System.out.println(p1+"\n\n");
		p2 = Polygon.circle(0.4,16);
		p2.translate(0.2,0.7);
		System.out.println(p2+"\n\n");
		p1.clip(p2);
		System.out.println(p1+"\n\n");
		
		int scale = 500;
		String s = "";
		s += p1.toSVG(scale,2,"blue");
		s += p2.toSVG(scale,2,"red");
		//s += line.toSVG(scale,3,"black");
		return s;
	}
	
	private Model model;
	private Point[] points;
	private double[][][] direction;
	private double[][] closeness;
	
	public KnowledgeBase(int n, Model m) {
		points = new Point[n];
		for (int a=0; a<n; ++a) {
			points[a] = new Point();
		}
		restore(m);
	}
	
	public void restore(Model m) {
		model = m;
		restore();
	}
	
	public void restore() {
		int n = points.length;
		direction = new double[8][n][n];
		closeness = new double[n][n];
		for (int a=0; a<n; ++a) {
			for (int b=0; b<n; ++b) {
				double x = points[b].x - points[a].x;
				double y = points[b].y - points[a].y;
				for (int i=0; i<8; ++i) {
					direction[i][a][b] = model.direction(i*Math.PI/4,x,y);
				}
				closeness[a][b] = model.closeness(x,y);
			}
		}
	}
	
	public void reduce(double direction, double closeness, double imprecision) {
		int n = points.length;
		for (int a=0; a<n; ++a) {
			for (int b=0; b<n; ++b) {
				for (int i=0; i<8; ++i) {
					if (Math.random() < direction) {
						this.direction[i][a][b] = 0;
					}
					else {
						this.direction[i][a][b] *= Math.pow(Math.random(),imprecision/(1-imprecision));
					}
				}
				if (Math.random() < closeness) {
					this.closeness[a][b] = 0;
				}
				else {
					this.closeness[a][b] *= Math.pow(Math.random(),imprecision/(1-imprecision));
				}
			}
		}
	}
	
	public void reasoning() {
		int n = points.length;
		boolean change;
		double r = 0;
		do {
			change = false;
			for (int a=0; a<n; ++a) {
				for (int b=0; b<n; ++b) {
					for (int c=0; c<n; ++c) {
						for (int i=0; i<8; ++i) {
							r = model.transitiveDirection(direction[i][a][b],direction[i][b][c]);
							if (r > direction[i][a][c]) {
								direction[i][a][c] = r;
								change = true;
							}
						}
					}
					for (int i=0; i<8; ++i) {
						r = model.intersectionDirection(direction[i][a][b],direction[(i+2)%8][a][b]);
						if (r > direction[(i+1)%8][a][b]) {
							direction[(i+1)%8][a][b] = r;
							change = true;
						}
						r = model.inverseDirection(direction[i][a][b]);
						if (r > direction[(i+4)%8][b][a]) {
							direction[(i+4)%8][b][a] = r;
							change = true;
						}
					}
				}
			}
		} while(change);
		do {
			change = false;
			for (int a=0; a<n; ++a) {
				for (int b=0; b<n; ++b) {
					for (int c=0; c<n; ++c) {
						r = model.transitiveCloseness(closeness[a][b],closeness[b][c]);
						if (r > closeness[a][c]) {
							closeness[a][c] = r;
							change = true;
						}
					}
					r = model.inverseCloseness(closeness[a][b]);
					if (r > closeness[b][a]) {
						closeness[b][a] = r;
						change = true;
					}
				}
			}
		} while(change);
	}
	
	public Estimation[] estimate(double absLoss) {
		int n = points.length;
		int loss = (int)(absLoss*n);
		Estimation[] result = new Estimation[loss];
		Polygon p0;
		for (int e=0; e<loss; ++e) {
			Polygon p = Polygon.square();
			for (int a=loss; a<n; ++a) {
				p0 = model.polygonCloseness(closeness[a][e]);
				p0.translate(points[a].x,points[a].y);
				p.clip(p0);
				for (int i=0; i<8; ++i) {
					p0 = model.polygonDirection(i*Math.PI/4,direction[i][a][e]);
					p0.translate(points[a].x,points[a].y);
					p.clip(p0);
				}
			}
			result[e] = new Estimation(points[e],p);
		}
		return result;
	}
	
	public String toSVG(int scale, double absLoss) {
		int n = points.length;
		String s = "";
		for (int e=(int)(absLoss*n); e<n; ++e) {
			s += points[e].toSVG(scale,10,"green");
		}
		for (int a=0; a<n; ++a) {
			for (int b=0; b<n; ++b) {
				for (int i=0; i<8; ++i) {
					if (direction[i][a][b]>0) {
						Line line = new Line(points[a],points[b]);
						s += line.toSVG(scale,1,"black");
					}
				}
			}
		}
		return s;
	}

}
