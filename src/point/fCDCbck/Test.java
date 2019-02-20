package point.fCDC;

import java.io.FileWriter;
import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		
		TestCase test = new TestCase(100,0.01);
		test.printTTL("test.TTL");
		/*test.calculation(0.6,0.6,false,false);
		test.printSVG("test0.svg",500);
		test.calculation(0.6,0.6,false,true);
		test.printSVG("test2.svg",500);*/
		//test.calculation(0.6,0.6,true,true);
		//test.printSVG("test3.svg",500);
		double[][][] result = new double[4][101][101];
		for (int r = 100; r>=0; --r) {
			for (int a = 100; a>=0; --a) {
				if (r == 90 || r == 60 || r == 30 || a == 90 || a == 60 || a == 30) {
				test.calculation(r/100.0,a/100.0,false,false);
				result[0][r][a] = test.avgDistance();
				test.calculation(r/100.0,a/100.0,true,false);
				result[1][r][a] = test.avgDistance();
				test.calculation(r/100.0,a/100.0,false,true);
				result[2][r][a] = test.avgDistance();
				test.calculation(r/100.0,a/100.0,true,true);
				result[3][r][a] = test.avgDistance();
				}
				System.out.println(r+" "+a);
			}
		}
		for (int i=0; i<4; ++i) {
			print("test"+i+".csv",result[i]);
		}
	}
	
	private static void print(String filename, double[][] result) {
		try (FileWriter writer = new FileWriter(filename)) {
			for (int i=0; i<result.length; ++i) {
				for (int j=0; j<result[i].length; ++j) {
					writer.write(result[i][j]+",");
				}
				writer.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
