package bits;

public class BitwiseOperator {

	public static void main(String[] args) {
		/**
		 * AND 
		 * 
		 *   0111
		 * & 0011
		 * --------
		 *   0011
		 */
		int result =7&3;
		System.out.println("0111&0011 :"+result);
		
		
		/**
		 * 
		 * OR
		 * 
		 *   1011
		 * | 0011
		 * -------
		 *   1011
		 * 
		 */
		result = 11|8;
		System.out.println("0111 | 0011 :"+result);
		
		/**
		 * XOR
		 * 
		 *  1111
		 * ^0110
		 * -----
		 *  1001
		 */
		result = 15^6;
		System.out.println("1111 ^ 0110 :"+result);
		
		
		/**
		 * Bitwise left shift (<<)
		 * 0000 1111
		 * 1111 1000
		 * 
		 * 15*2pow3
		 */
		result = 15<<3;
		System.out.println("15<<3 :"+result);
		
		
		
		/**
		 * Bitwise signed right shift (>>)
		 */
		/**
		 * 
		 * 0000 0111 0100
		 * 0000 0000 1110 
		 * 
		 * around 116 / 2pow3
		 */
		result = 116>>3;
		System.out.println("116>>3 :"+result);

		/**
		 * negative number add 1 at front
		 *
		 * 1111 1000 1100
		 * 1111 1111 0001 
		 * 
		 * around -116 / 2pow3
		 */
		result = -116>>3;
		System.out.println("-116>>3 :"+result);
		
		
		
		
		/**
		 * Bitwise unsigned right shift (>>>)
		 */
		/**
		 * 
		 * 0000 0111 0100
		 * 0000 0000 1110 
		 * 
		 * around 116 / 2pow3
		 */
		result = 116>>>3;
		System.out.println("116>>>3 :"+result);
	
		/**
		 * negative number add 0 at front (unsigned)
		 *
		 *11111111 111111111 11111111 10001100
		 *00011111 111111111 11110001 11110001 
		 * 
		 * around -116 / 2pow3
		 */
		result = -116>>>3;
		System.out.println("-116>>>3 :"+result);
		
	}

}
