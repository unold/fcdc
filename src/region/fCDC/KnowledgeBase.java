package region.fCDC;

import region.model.Model;

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
	private Circle[] regions;
	private double[][] dirNW;
	private double[][] dirN;
	private double[][] dirNE;
	private double[][] dirW;
	private double[][] near;
	private double[][] dirE;
	private double[][] dirSW;
	private double[][] dirS;
	private double[][] dirSE;
	
	public KnowledgeBase(int n, Model m) {
		regions = new Circle[n];
		for (int a=0; a<n; ++a) {
			regions[a] = new Circle();
		}
		restore(m);
	}
	
	public void restore(Model m) {
		model = m;
		restore();
	}
	
	public void restore() {
		int n = regions.length;
		dirNW = new double[n][n];
		dirN = new double[n][n];
		dirNE = new double[n][n];
		dirW = new double[n][n];
		near = new double[n][n];
		dirE = new double[n][n];
		dirSW = new double[n][n];
		dirS = new double[n][n];
		dirSE = new double[n][n];
		for (int a=0; a<n; ++a) {
			for (int b=0; b<n; ++b) {
				dirNW[a][b] = model.dirNW(regions[a],regions[b]);
				dirN[a][b] = model.dirN(regions[a],regions[b]);
				dirNE[a][b] = model.dirNE(regions[a],regions[b]);
				dirW[a][b] = model.dirW(regions[a],regions[b]);
				near[a][b] = model.near(regions[a],regions[b]);
				dirE[a][b] = model.dirE(regions[a],regions[b]);
				dirSW[a][b] = model.dirSW(regions[a],regions[b]);
				dirS[a][b] = model.dirS(regions[a],regions[b]);
				dirSE[a][b] = model.dirSE(regions[a],regions[b]);
			}
		}
	}
	
}
