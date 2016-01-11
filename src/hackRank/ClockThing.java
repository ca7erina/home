package hackRank;

import java.util.Arrays;

/**
 * Created by chenxiaoxue on 1/10/16.
 *
 * Write a Java method that takes in an int and calculates the
 percentage of the day that the digits on a digital clock exceed
 that int value when added together (e.g. three minutes to six is
 displayed as 5:57, which sums to 17).
 *
 *
 */
public class ClockThing {


    public static void main(String args[]){


    System.out.println(percentage(4));

    }

    public static double percentage(int num){
        int sum = 0;
        int exceed=0;
        for(int i=0;i<24;i++){
            for(int j =0;j<60;j++){
                int num1 = i/10;
                int num2 = i%10;
                int num3 = j/10;
                int num4 = j%10;
                if((num1+num2+num3+num4)>num){
                    exceed++;
                }
                sum++;
            }
        }
        return (double)exceed/sum;

    }

}
