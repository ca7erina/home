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

    public static void insertionsort(int[] array) {
        for (int outer = 1; outer < array.length; outer++) {
            int temp=array[outer];
            int inner = outer;
            while(inner>0&& array[inner -1]>=temp){
                array[inner]=array[inner-1];
                inner--;
            }
            array[inner]=temp;
        }
    }

}
