package hackRank;

import java.util.Scanner;

/**
 * Created by chenxiaoxue on 12/1/15.
 */
public class birthdayProblem {
    public static void main(String args[] ){
        Scanner myscanner = new Scanner(System.in);
        int n = myscanner.nextInt();
        System.out.println(String.format( "%.3f", (1-birthday(n))));
    }

    //fill in the recursive birthday method
    public static double birthday(int n){
        int   x = n-1;
        double value =(double)(365-(n-1))/365.00;
        if (n<=1){
            return 1;
        }




        return (value*birthday(x));
    }


}
