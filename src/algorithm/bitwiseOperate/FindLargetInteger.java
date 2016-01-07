package algorithm.bitwiseOperate;

import java.util.Scanner;

/**
 * Created by chenxiaoxue on 1/7/16.
 * <p/>
 * Find the largest integer that can be created from a pair of integers and the following bit manipulation operators:
 * <<,>>,| . Once the | is applied the two inputs are consumed and cannot be regered to again. so u can shift the two original integers as much as you wang,
 * then apply the | operator to yield a result.
 * <p/>
 * input:
 * 5
 * 6
 * <p/>
 * output:
 * 1946157056
 * <p/>
 * -2147483647<=input<=2147483647
 */
public class FindLargetInteger {


    public static void main(String args[]) throws Exception {
        Scanner myScanner = new Scanner(System.in);
        int num1 = myScanner.nextInt();
        int num2 = myScanner.nextInt();
        int max = Integer.MIN_VALUE;

        for(int i = 0; i <= 32; i++) {
            for(int j = 0; j <= 32; j++) {
                if(((num1 << i) | (num2 << j)) > max) {
                    max = (num1 << i) | (num2 << j);
                }
                if(((num1 >> i) | (num2 << j)) > max) {
                    max = ((num1 >> i) | (num2 << j));
                }
                if(((num1 << i) | (num2 >> j)) > max) {
                    max = ((num1 << i) | (num2 >> j));
                }
                if(((num1 >> i) | (num2 >> j)) > max) {
                    max = ((num1 >> i) | (num2 >> j));
                }
            }

        }

        System.out.println(max);
    }


}