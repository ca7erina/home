package hash;

import java.util.ArrayList;

/**
 * Created by chenxiaoxue on 12/8/15.
 */
public class Temp5 {
    static ArrayList<Integer> list = new ArrayList<Integer>();

    public static void main(String args[]){
        for(int i = 400000;i>300000;i--){
            if(isPrime(i)){
                list.add(i);
            }
        }

        for(int i=0;i<list.size();i++){
           System.out.println(list.get(i));
        }
        System.out.println(list.size());
    }
    public static boolean isPrime(int num){
        boolean isPrime = true;
        for(int i=2;i<num;i++){
            if(num%i==0){
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

}
