package hackRank;

import java.util.Scanner;

/**
 * n=1 365/365
 * n=2 365/365 * 364/365
 * n=3 365/365 * 364/365 * 363/365
 */
public class birthdayProblem {
    public static void main(String args[] ){
        Scanner myscanner = new Scanner(System.in);
        int n = myscanner.nextInt();
        System.out.println(String.format( "%.3f", (1-birthday(n))));
    }

    //fill in the recursive birthday method
    public static double birthday(int n){
        if (n<=1){
            return 1;
        }
        int   x = n-1;
        double value =(double)(365-(n-1))/365.00; // calculate the rate of different birthday if only 2 people the rate is 364/365, 3 people is 364/365*363/365
        return (value*birthday(x));
    }


}
