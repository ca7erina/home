package main.java;

import java.util.Arrays;

/**
 * InsertionSort: compare i with the elements before it .
 *
 */
public class InsertionSort {
    public static void main(String args[]) {
        int a[] = {12, 3, 8, 4, 5, 6,15,1,10,2};
        insertionsort(a);
        System.out.println(Arrays.toString(a));

    }

    public static void insertionsort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int j;
            for (j = i; j>0; j--) {
                if (a[j] < a[j-1]) {
                    int temp;
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }

            }

        }

    }

}
