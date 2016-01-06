package hackRank;

import java.util.Scanner;

/**
 *
 * after the number sorted find the middle one;
 * or the avg of the middle two;
 *
 * input:
 * 7
 * 15 18 3 2 -5 6 2
 *
 * output
 * 3.0
 */
public class FindMedian {

    public static void main(String args[] ) throws Exception {
        Scanner myscanner = new Scanner(System.in);
        int length =myscanner.nextInt();
        int numbers[] = new int [length];
        for(int i=0;i<length;i++){
            numbers[i]=myscanner.nextInt();
        }

        for(int i = length - 1; i > 0; i--){
            for(int j=0;j<i;j++){
                if(numbers[j]>numbers[j+1]){
                    int temp =0;
                    temp =numbers[j+1];
                    numbers[j+1]=numbers[j];
                    numbers[j]=temp;
                }
            }
        }

        if(length%2==0){
            System.out.println((double)(numbers[(length/2)-1]+numbers[length/2])/2.0);
        }else{
            System.out.println((double)numbers[length/2]);
        }

    }
}
