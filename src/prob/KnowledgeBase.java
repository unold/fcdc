package prob;

public class KnowledgeBase {
	
	private Point[] points;
	private boolean[][][] dir;
	private double trust = 1;
	
	KnowledgeBase(int n) {
		points = new Point[n];
		for (int i=0; i<n; ++i) {
			points[i] = new Point();
		}
		restore();
	}
	
	void restore() {
		int n = points.length;
		dir = new boolean[8][n][n];
		for (int a=0; a<n; ++a) {
			for (int b=0; b<n; ++b) {
				if (a != b) {
					double x = points[b].x - points[a].x;
					double y = points[b].y - points[a].y;
					for (int i=0; i<8; ++i) {
						dir[i][a][b] = direction(i*Math.PI/4,x,y);
					}
				}
			}
		}
		trust = 1;
	}
	
	private boolean direction(double d, double x, double y) {
		if (x == 0 && y == 0)
			return false;
		double a = Math.atan2(y,x);
		return ((d-Math.PI/2 < a && a < d+Math.PI/2) || (d-Math.PI/2 < a+2*Math.PI && a+2*Math.PI < d+Math.PI/2));
	}
	
	void damage(double trust) {
		int n = points.length;
		this.trust = trust;
		for (int a=0; a<n; ++a) {
			for (int b=0; b<n; ++b) {
				if (a != b) {
					for (int i=0; i<8; ++i) {
						if (Math.random() > trust) {
							dir[i][a][b] = !dir[i][a][b];
						}
					}
				}
			}
		}
	}
	
	double confidence() {
		int n = points.length;
		double c = 0;
		for (int a=0; a<n; ++a) {
			for (int b=0; b<n; ++b) {
				if (a != b) {
					double x = points[b].x - points[a].x;
					double y = points[b].y - points[a].y;
					for (int i=0; i<8; ++i) {
						if(dir[i][a][b] == direction(i*Math.PI/4,x,y)) {
							c += (1-trust);
						}
						else {
							c -= trust;
						}
					}
				}
			}
		}
		return c;
	}
	
	double confidence(int[][] test) {
		int n = points.length;
		double c = 0;
		for (int a=0; a<n; ++a) {
			for (int b=0; b<n; ++b) {
				if (a != b) {
					boolean reference = test[a][b] < 4;
					for (int i=0; i<8; ++i) {
						if (dir[a][b][i] == reference) {
							c += (1-trust);
						}
						else {
							c -= trust;
						}
						if (test[a][b]%4 == i%4)
							reference = !reference;
					}
				}
			}
		}
		return c;
	}
	

}
