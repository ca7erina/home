package algorithm.recursion;

/**
*
*             n:	0	1	2	3	4	5	6	7	8	9	10	11	…
* nth Fibonacci:	0	1	1	2	3	5	8	13	21	34	55	89	…
*  f(0)=0;
* f(1)=1;
* f(2)=1;
* f(3)=2;
* f(7)=13
 */
public class FibonacciNumbers {

    public static void main(String[] args) {
        System.out.println(f(7));
    }

    public static int  f(int n){
        if (n ==0){
            return 0;
        }
        if(n==1){
            return 1;
        }

        return f(n-1)+f(n-2);
    }

}
