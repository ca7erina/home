package hackRank;

import java.util.Scanner;

/**
 * transfer from (43)base5 to (?)base6
 *
 *
 *
 * input
 * 5
 * 6
 * 43
 *
 * ouput:
 * 35
 */
public class TransferFromDifferentBase {

    public static void main(String args[] ) throws Exception {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner myScanner = new Scanner(System.in);
        int baseA = myScanner.nextInt(); //2<=baseA<=10
        int baseB = myScanner.nextInt();//2<=baseA<=10
        myScanner.nextLine();
        String num_str = myScanner.nextLine();//a<=N<=1000000000

        // transfer from baseA to decimal;
        long dec = 0;
        int length = num_str.length();
        int j=0;
        for(int i=length-1;i>=0;i--){
            dec += Math.pow(baseA,i)*(Integer.parseInt(num_str.substring(j,j+1)));
            j++;
        }


        //transfer decimal to baseB;
        String result = "";
        while(true){
            if(dec<baseB){
                result=dec+result;
                break;
            }
            result=dec%baseB+result;
            dec = dec/baseB;
        }

        System.out.println(result);


    }
}
