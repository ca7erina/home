package main.java;

/**
 * BubbleSort O(n2)
 */
public class BubbleSort {

    public static void main(String args[]) {
        int[] a = {3, 2, 1, 7, 4, 10, 5, 8};
        sort(a);
        for(int i : a) {
            System.out.println(i);
        }


    }

    public static void sort(int[] numbers) {
        int temp;
        for(int i = 0; i < numbers.length-1; i++) {
            for(int j = 0; j < numbers.length - i - 1; j++) {
                if(numbers[j] > numbers[j + 1]) {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }
}
