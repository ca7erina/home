package algorithm.recursion;

/**
 * Created by chenxiaoxue on 12/2/15.
 */
public class Tutorial {

    public static void main(){

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


}
