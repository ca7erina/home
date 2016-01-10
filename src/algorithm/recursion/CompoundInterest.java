package algorithm.recursion;

import java.util.Scanner;

/**
 * 7 years
 * interest 2%
 * $100
 *
 * --> get $109.46
 *
 * input :
 * 7
 * 1.3
 * 100
 *
 * output
 * 109.46
 *
 */
public class CompoundInterest {
    public static void main(String args[] ){
        Scanner myscanner = new Scanner(System.in);
        int y = myscanner.nextInt();
        double i = myscanner.nextDouble();
        double x = myscanner.nextDouble();

        System.out.println(String.format( "%.2f", compound(y,i,x)));
    }

    public static double compound(int y, double i, double x){
        if(y ==0){
            return x;
        }
        double factor = (double)i/100.00+1;
        int one = y-1;
        double two = i;
        double three = x;

        return (factor*compound(one,two,three));
    }


}
