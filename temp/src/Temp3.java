import java.util.Scanner;

/**
 * Created by chenxiaoxue on 12/8/15.
 */
public class Temp3 {


    public static void main(String args[]){
        long dec = 0;
        dec += Math.pow(2,3)*(Integer.parseInt("234".substring(0,1)));
        System.out.println(method(17));

    }

    public static int method(int number){
        if (number == 2){
            return 7;
        }
        return method((number % 5) + 1) + 2;
    }
}
