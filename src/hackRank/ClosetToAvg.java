package hackRank;

import java.util.Scanner;

/**
 * Created by chenxiaoxue on 1/6/16.
 */
public class ClosetToAvg {

    public static void main(String args[] ) throws Exception {
        Scanner myScanner = new Scanner(System.in);
        int size = myScanner.nextInt();
        int a[]=new int[size];
        int sum=0;
        for(int i =0;i<size;i++){
            a[i]=myScanner.nextInt();
            sum+=a[i];
        }
        double avg=(double)sum/size;
        // int avg = sum/size;
        double min=Math.abs(a[0]-avg);
        int index=0;
        for(int i =1;i<size;i++){
            double diff = Math.abs(a[i]-avg);
            if(diff==min){
                continue;
            }
            if(diff<min){
                min=diff;
                index=i;
            }


        }

        System.out.println(a[index]);

    }



//    public static void main(String args[] ){
//        Scanner myscanner = new Scanner(System.in);
//        int number = myscanner.nextInt();
//        int[] array = new int[number];
//        for(int i=0;i<number;i++){
//            array[i]=myscanner.nextInt();
//        }
//        //System.out.println(Arrays.toString(array));
//        int sum =0;
//        for(int i=0;i<number;i++){
//            sum+=array[i];
//        }
//        double average=(double)sum/number;
//        double closest=Math.abs(array[0]-average);
//        int slot = 0;
//        for(int i=1;i<number;i++){
//            if(Math.abs(array[i]-average)<closest){
//                closest=Math.abs(array[i]-average);
//                slot = i;
//            }
//        }
//        System.out.println(array[slot]);
//    }
}
