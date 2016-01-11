package hackRank;

import java.util.Arrays;

/**
 * Created by chenxiaoxue on 1/10/16.
 */
public class ShuffleArray {

    public static void main(String args[]){
        int array[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        randomizeArray(array);
        System.out.println(Arrays.toString(array));
    }

    public static void randomizeArray(int a[]){
        int num = a.length;
        for(int i =0; i<a.length;i++){
           int  randomIndex  = (int)(Math.random()*num);// 0 to (num-1)
            //swap
            int temp;
            temp=a[i];
            a[i] = a[randomIndex];
            a[randomIndex] = temp;
        }

    }

    /**
     * A good way to randomize the array is actually to
     * go through each item and swap it with another
     * random item
     */
    public static void randomSwap() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        System.out.println(Arrays.toString(a));
        int length = a.length;
        for(int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * length);
            int temp = 0;
            temp = a[i];
            a[i] = a[(randomIndex)];
            a[randomIndex] = temp;
        }
        System.out.println(Arrays.toString(a));

    }
}
