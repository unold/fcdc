package prob;

public class Test {
	
	static KnowledgeBase kb;
	
	public static void main(String[] args) {
		
		int n = 10;
		int[][] data = new int[n][n];
		
		kb = new KnowledgeBase(n);
		
		for (int a=0; a<n; ++a) {
			for (int b=0; b<a; ++b) {
				optimize(data,a,b);
			}
		}
		
		for (int a=0; a<n; ++a) {
			for (int b=0; b<n; ++b) {
				for (int c=0; c<n; ++c) {
					if (a != b && b != c && a != c) {
						if (checkTransitivityContradiction(data[a][b], data[b][c], data[a][c])) {
							resolveTransitivityContradiction()
						}
					}
				}
			}
		}
		
	}
	
	private static void optimize(int[][] data, int a, int b) {
		double maxConf = Double.MIN_VALUE;
		int maxI = data[a][b];
		for (int i=0; i<8; ++i) {
			data[a][b] = i;
			data[b][a] = (i+4)%8;
			double conf = kb.confidence(data);
			if (maxConf < conf) {
				maxConf = conf;
				maxI = i;
			}
		}
		data[a][b] = maxI;
		data[b][a] = (maxI+4)%8;
	}
	
	private static boolean checkTransitivityContradiction(int ab, int bc, int ac) {
		
	}
	
	private static int resolveTransitivityContradiction(int[][] data, int a, int b, int c) {
		double maxConf = Double.MIN_VALUE;
		int maxI = data[a][b];
		for (int i=0; i<8; ++i) {
			data[a][b] = i;
			double conf = kb.confidence(data);
			if (maxConf < conf) {
				maxConf = conf;
				maxI = i;
			}
		}
		return maxI;
	}

}
