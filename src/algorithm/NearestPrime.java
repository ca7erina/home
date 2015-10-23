package algorithm;

import java.util.Scanner;

/**
 * Created by chenxiaoxue on 10/23/15.
 */
public class NearestPrime {
    public static void main(String args[] ) throws Exception {
        Scanner myScanner  = new Scanner(System.in);
        int num = myScanner.nextInt();
        int i=1;
        if(isPrime(num)){
            System.out.println(num);
            return;
        }
        while((num<=10000)&&(num>=2)){
            if(isPrime(num-i)&&(num-i)>1){
                System.out.println(num-i);
                break;
            }
            if(isPrime(num+i)){
                System.out.println(num+i);
                break;
            }
            i++;
        }

    }

    public static boolean isPrime(int num){
        if(num<2){
            return false;
        }
        for(int i=num-1;i>1;i--){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }


}
