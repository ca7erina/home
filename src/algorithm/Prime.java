package algorithm;

import java.util.Scanner;

/**
 * the goal is to check if a number is prime or not, if it is a prime number, output TRUE, otherwise FALSE
 * Input Format: integer N
 * Output Format: TRUE or FALSE
 * 2<=N<=10000
 */
public class Prime {

        public static void main(String args[] ) throws Exception {
            Scanner myScanner = new Scanner(System.in);
            int number =  myScanner.nextInt();
            if(number<=1){
                System.out.println("FALSE");
                return;
            }

            for(int i=number-1;i>=1;i--){
                if((number%i==0)&&(i!=1)){
                    System.out.println("FALSE");
                    return;
                }
            }

            System.out.println("TRUE");
        }

}
