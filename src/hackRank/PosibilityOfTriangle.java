package cs211.lab1;


public class PosibilityOfTriangle {

	public static void main(String[] args) {

		double triangle = 0;
		int baseAmount = 1000000;
		
		//initial a stick
		double wholeStickLength = 0;
		while (wholeStickLength == 0) {
			wholeStickLength = Math.random()*100; //(0+100)
		}

		// calculate possibility
		for (int i = 0; i < baseAmount; i++) {
			double pieces[] = get3pieces(wholeStickLength);
			if (isTriangle(pieces[0], pieces[1], pieces[2])) {
				triangle++;
			}

		}
		System.out.println("probability:" + triangle / baseAmount);

	}
	
	/**
	 * Cut the stick and get 3 pieces
	 * @param wholeStickLength double
	 * @return 3pieces double[]
	 */
	public static double[] get3pieces(double wholeStickLength) {
		double result[] = new double[3];
		
		// pick a point
		double cutPoint1 = Math.random() * (wholeStickLength+1);// [0,wholeStickLength]
		double piece1 = cutPoint1;
		double piece2 = wholeStickLength - cutPoint1;

		// get longger stick
		double longerStickLength;
		if (piece1 > piece2) {
			longerStickLength = piece1;
			result[0] = piece2;
		} else {
			longerStickLength = piece2;
			result[0] = piece1;
		}

		// pick a point at longer stick

		double cutPoint2 = Math.random() * (longerStickLength+1);// [0,longerStickLength]

		result[1] = cutPoint2;
		result[2] = longerStickLength - cutPoint2;

		return result;
	}
	
	/**
	 * Calculate if the 3 pieces can form a triangle.
	 * @param a double
	 * @param b double
	 * @param c double
	 * @return isTriangle boolean
	 */
	public static boolean isTriangle(double a, double b, double c) {
		boolean isTriangle = false;
		if (a != 0 && b != 0 && c != 0 && a + b > c && a + c > b && b + c > a) {
			isTriangle = true;
		}
		return isTriangle;
	}

}
