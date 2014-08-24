package jmse.utils;

import java.util.Arrays;


public class Graphing {
	
	public Graphing() {
		// TODO Auto-generated constructor stub
	}
	

	//test math point formulas
	public static void main(String args[]) {
		System.out.println("testing");
		Double[] point1;
		Double[] point2;
		point1 = new Double[] {0.0, 300.0};
		point2 = new Double[] {1000.0, 1300.0};
		int divisions = 1000;
		
		Double[][] newPoints = getNextPoint(point1, point2, divisions);
		
				
		System.out.println("Points are:" + Arrays.deepToString(newPoints));
	}
	
	
	
	//Formula given two points and a count of divisions, it will return points in increments of the division [divisons][x,y]
	public static Double[][] getNextPoint(Double[] point1, Double[] point2, int divisions) {
		Double[][] nextPoints = new Double[divisions][2];
			Double slope = getSlope(point1[0],point1[1],point2[0],point2[1]);
			Double[] pointFraction = new Double[divisions];
			for(int i = 0; i < divisions; i++ ) {
				pointFraction[i] =  (((double)i + 1) / (double)divisions);
				for(int j = 0; j < 2; j++) {
					nextPoints[i][j] = ((point2[j] - point1[j]) * pointFraction[i]) + point1[j];
					
				}				
			}
		return nextPoints;
	}
	
	//return the slope given two points
	public static Double getSlope(Double x1, Double y1, Double x2, Double y2){
		Double slope = (y1 - y2) / (x1 - x2);
		return slope;
	}
	
}
