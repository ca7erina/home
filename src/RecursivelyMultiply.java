import java.math.BigInteger;

/**
 * Recursive use
 */
public class RecursivelyMultiply {

    public static void main(String args[]) {
        int n = 22;
        System.out.println(n + "!");
        System.out.println(multiply(n));
        System.out.println(multiplyrecursively(n));
        System.out.println(multiplyBig(n));
    }


    public static long multiply(int n) {
        if(n == 0) {
            return 0;
        } else {
            long result = 1;
            for(int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        }

    }

    public static long multiplyrecursively(int n) {
        if(n == 0) {
            return 0;
        } else if(n == 1) {
            return 1;
        } else {
            return n * multiplyrecursively(n - 1);
        }


    }

    public static BigInteger multiplyBig(int n) {
        if(n == 0) {
            return new BigInteger("0");
        } else {
            BigInteger result = new BigInteger("1");
            for(int i = 1; i <= n; i++) {
                result = result.multiply(new BigInteger(String.valueOf(i)));
            }
            return result;
        }
    }


}
