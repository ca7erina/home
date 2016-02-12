package cs211.lab1;


public class PosibilityOfTriangle2 {

	public static void main(String[] args) {

		double triangle = 0;
		int baseAmount = 1000000;
		int n = 3;
		double initialStickLength = 0;
		while (initialStickLength == 0) {
			initialStickLength = Math.random()*100;//(0+100)
		}
		

		// calculate possibility 
		for (int i = 0; i < baseAmount; i++) {
			double allStickPicese[] = new double[n + 1];
			allStickPicese =getEdges(allStickPicese, n, initialStickLength);
			double edges[] = get3Edges(allStickPicese);
			if (isTriangle(edges[0], edges[1], edges[2])) {
				triangle++;
			}

		}
		System.out.println("probability:" + triangle / baseAmount);

	}
	
	/**
	 * Keep cutting the stick n times and get all pieces.
	 * @param result
	 * @param n
	 * @param sticklength
	 * @return result double[] 
	 */
	public static double[] getEdges(double result[], int n, double sticklength) {

		double cutPoint = Math.random() * sticklength;// [0,sticklength]
		double piece1 = cutPoint;
		double piece2 = sticklength - cutPoint;
		double longerPieceLength;
		if (n <= 1) {
			result[0] = piece1;
			result[1] = piece2;
			return result;
		} else {

			if (piece1 > piece2) {
				longerPieceLength = piece1;
				result[n] = piece2;

			} else {
				longerPieceLength = piece2;
				result[n] = piece1;
			}
		}
		return getEdges(result, n - 1, longerPieceLength);
	}

	
	/**
	 * Calculate if the 3 pieces can form a triangle.
	 * @param a :double
	 * @param b :double
	 * @param c :double
	 * @return isTriangle :boolean
	 */
	public static boolean isTriangle(double a, double b, double c) {
		boolean isTriangle = false;
		if (a != 0 && b != 0 && c != 0 && a + b > c && a + c > b && b + c > a) {
			isTriangle = true;
		}
		return isTriangle;
	}
	
	/**
	 * Pick 3 pieces from all pieces randomly
	 * @param allEdges :double[]
	 * @return 3pieces :double[]
	 */
	public static double[] get3Edges(double allEdges[]) {

		double result[] = new double[3];
		int a = (int) (Math.random() * allEdges.length);// [0,maxIndex]
		int b;
		do {
			b = (int) (Math.random() * allEdges.length);// [0,maxIndex] but not a
		} while (a == b);

		int c;
		do {
			c = (int) (Math.random() * allEdges.length);// [0,maxIndex] but not a or b
		} while (a == c | c == b);
		result[0] = allEdges[a];
		result[1] = allEdges[b];
		result[2] = allEdges[c];
		return result;

	}

}
