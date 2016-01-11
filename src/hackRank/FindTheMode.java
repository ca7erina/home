package hackRank;

import java.util.Scanner;

/**
 * Design an algorithm that takes in an array of ints as a
 parameter and returns the mode of that array. The mode is the
 value that appears most often in a set of data. If there is more
 than one mode then it should return the lowest.
 */
public class FindTheMode {

    public static void main(String args[] ) throws Exception {
        Scanner myScanner = new Scanner(System.in);
        int size = myScanner.nextInt();
        int array[] = new int[size];

        int maxIndex=-1;
        int maxCount =0;
        for(int i=0;i<size;i++){
            array[i]=myScanner.nextInt();
        }

        for(int i=0;i<size;i++){
            int num=array[i];
            int times=1;
            for(int j=i+1;j<size;j++){
                if(num==array[j]){
                    times++;
                }
            }
            if(times>maxCount){
                maxCount=times;
                maxIndex=i;
            }
        }

        System.out.println(array[maxIndex]);

    }
}
