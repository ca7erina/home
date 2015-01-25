/**
 * BubbleSort O(n2)
 */
public class BubblSort {

    public static void main(String args[]) {
        int[] a = {3, 2, 1, 7, 4, 9, 5, 10};
        sort(a);
        for(int i : a) {
            System.out.println(i);
        }


    }

    public static void sort(int[] numbers) {
        int temp;
        for(int i = 0; i < numbers.length; i++) {
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
