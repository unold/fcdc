package point.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ModelImageGenerator {
	
	static int size = 1000;
	static String filename = "idCont.png";

	public static void main(String[] args) {
		BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
	    for(int y = 0; y<size; y++){
	        for(int x = 0; x<size; x++){
	        	int z = (int)((1-getIntensity(x*1.0/size,y*1.0/size))*255);
	        	int value = z << 16 | z << 8 | z;
	        	image.setRGB(x, y, value);
	        }
	    }
	    File outputfile = new File(filename);
	    try {
			ImageIO.write(image, "png", outputfile);
			System.out.println("File "+filename+" created.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static double getIntensity(double x, double y) {
		x = 2*(x-0.5);
		y = 2*(y-0.5);
		
		// crisp - cardinal direction E
		/*
		double angle = Math.atan2(y,x);
		return Math.abs(angle) < Math.PI/2 ? 1 : 0;
		*/
		
		// fuzzy - cardinal direction E - cone method
		/*
		double angle = Math.atan2(y,x);
		angle = Math.PI/2 - Math.abs(angle);
		return angle > 0 ? 2*angle/Math.PI : 0;
		*/
		
		// fuzzy - cardinal direction E - distance method
		/*
		return x > 0 ? x : 0;
		*/
		
		// crisp - closeness
		/*
		return (x == 0 && y == 0) ? 1 : 0;
		*/
		
		// fuzzy - closeness - linear method
		/*
		double alpha = 0.2;
		double beta = 0.5;
		double d = Math.sqrt(x*x+y*y);
		if (d < alpha)
			return 1;
		if (d > alpha+beta)
			return 0;
		return 1-(d-alpha)/beta;
		*/
		
		// fuzzy - closeness - continuous method
		/*
		double d = Math.sqrt(x*x+y*y);
		return Math.exp(-d);
		*/
		return 0;
		
	}

}
