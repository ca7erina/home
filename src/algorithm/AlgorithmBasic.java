package algorithm;

import java.util.Arrays;

/**
 * Created by chenxiaoxue on 10/19/15.
 */
public class AlgorithmBasic {

    public static void main(String[] args) {

        randomSwap();
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
