package main.java;

import java.util.Arrays;

/**
 * BubbleSort O(n2)
 */
public class BubbleSort {

    public static void main(String args[]) {
        int[] a = {3, 2, 1, 7, 4, 10, 5, 8};
        sort(a);
        System.out.println(Arrays.toString(a));


    }

    public static void sort(int[] numbers) {

        for(int i = numbers.length-1; i >0; i--) {
            for(int j = 0; j < i; j++) {
                if(numbers[j] > numbers[j + 1]) {
                    int temp;
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }
}
