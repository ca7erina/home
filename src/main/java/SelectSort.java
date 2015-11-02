package main.java;

import java.util.Arrays;

/**
 * Select Sort: select the smallest on in the right and put it in the left.
 */
public class SelectSort {
    public static void main(String args[]) {
        int a[] = {12, 3, 8, 4, 5, 6, 10, 1};
        selectsort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void selectsort(int[] a) {
        for(int i = 0; i < a.length - 1; i++) {
            // int smallest=a[i];
            for(int j = i + 1; j < a.length; j++) {
                if(a[j] < a[i]) {
                    int temp;
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}
