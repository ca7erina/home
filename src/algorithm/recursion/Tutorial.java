package algorithm.recursion;

/**
 * Created by chenxiaoxue on 12/2/15.
 */
public class Tutorial {

    public static void main(String args[]){
       System.out.println(modulus(5, 3));//5%3
    }

    /**
     * trianglear numbers
     * . 1
     *
     * .
     * ..  3
     *
     * .
     * ..
     * ... 6
     */
    public static long triangularNumbers(int n){
        if (n==1){
            return 1;
        }

        return n+triangularNumbers(n-1);

    }

    public static int modulus(int val,int d){
        if(val<d){
            return val;
        }else{
            return modulus(val-d,d);
        }


    }

    public static double factorial(int n) {
        if(n<=1){
            return 1.0;
        }
        int x=n-1;
        double value =1.0/n;// double yunsuan get double result. (1/n) doesn't work, result is 0
        return (factorial(x)/value);
    }

}
