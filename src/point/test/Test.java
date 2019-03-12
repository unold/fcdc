package point.test;

import java.io.FileWriter;
import java.io.IOException;

import point.fCDC.Estimation;
import point.fCDC.KnowledgeBase;
import point.model.*;

public class Test {

	public static void main(String[] args) {
		
		//printSVG("test.svg",KnowledgeBase.test());
		
		/*
		KnowledgeBase kb = new KnowledgeBase(5,new DistanceModel());
		printSVG("testA1.svg",kb.toSVG(500,0));
		kb.reduce(0.6,0.6,0.2);
		printSVG("testB1.svg",kb.toSVG(500,0.4));
		Estimation[] estimation = kb.estimate(0.4);
		printSVG("testC1.svg",estimation[0].toSVG());
		kb.reasoning();
		estimation = kb.estimate(0.4);
		printSVG("testD1.svg",estimation[0].toSVG());
		
		kb.restore(new ConeModel());
		printSVG("testA2.svg",kb.toSVG(500,0));
		kb.reduce(0.6,0.6,0.2);
		printSVG("testB2.svg",kb.toSVG(500,0.4));
		estimation = kb.estimate(0.4);
		printSVG("testC2.svg",estimation[0].toSVG());
		kb.reasoning();
		estimation = kb.estimate(0.4);
		printSVG("testD2.svg",estimation[0].toSVG());
		*/
		
		double tau = 0.95;
		int N = 500;
		
		/*
		KnowledgeBase kb = new KnowledgeBase(N,new LogModel());
		kb.reduce(0,1,0);
		double max = average(kb.estimate(tau));
		for (double rho = 0; rho <= 1; rho += 0.1) {
			System.out.print(rho);
			
			kb.restore(new LogModel());
			kb.reduce(0,rho,0.2);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore();
			kb.reduce(0,rho,0.5);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore();
			kb.reduce(0,rho,0.8);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore(new LinearModel(1));
			kb.reduce(0,rho,0.2);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore();
			kb.reduce(0,rho,0.5);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore();
			kb.reduce(0,rho,0.8);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore(new LinearModel(0.2));
			kb.reduce(0,rho,0.2);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore();
			kb.reduce(0,rho,0.5);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore();
			kb.reduce(0,rho,0.8);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			System.out.println();
		}
		*/
		
		/*
		KnowledgeBase kb = new KnowledgeBase(N,new CrispModel());
		kb.reduce(1,0,0);
		double max = average(kb.estimate(tau));
		for (double rho = 0.9; rho <= 1; rho += 0.01) {
			System.out.print(rho);
			
			kb.restore(new CrispModel());
			kb.reduce(rho,0,0);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore(new DistanceModel());
			kb.reduce(rho,0,0.2);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore();
			kb.reduce(rho,0,0.5);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore();
			kb.reduce(rho,0,0.8);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore(new ConeModel());
			kb.reduce(rho,0,0.2);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore();
			kb.reduce(rho,0,0.5);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore();
			kb.reduce(rho,0,0.8);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			System.out.println();
		}
		*/
		
		KnowledgeBase kb = new KnowledgeBase(N,new CrispModel());
		kb.reduce(0,0,0);
		double max = average(kb.estimate(tau));
		for (double rho = 0.9; rho <= 1; rho += 0.01) {
			System.out.print(rho);
			
			kb.restore(new LinConeModel(1));
			kb.reduce(rho,0,0.1);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			kb.restore(new LogConeModel());
			kb.reduce(rho,0,0.1);
			kb.reasoning();
			System.out.print("," + (average(kb.estimate(tau))/max));
			
			System.out.println();
		}
		
		
		
	}
	
	static double average(Estimation[] estimation) {
		double a = 0;
		for (int i=0; i<estimation.length; ++i) {
			a += estimation[i].distance();
		}
		return a/estimation.length;
	}
	
	static void printSVG(String filename, String content) {
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			writer.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">");
			writer.write(content);
			writer.write("</svg>");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
