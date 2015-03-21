package main.java;

/**
 * Select Sort
 */
public class SelectSort {
    public static void main(String args[]) {
        int a[] = {12, 3, 8, 4, 5, 6};
        selectsort(a);
        for (int i:a){
            System.out.println(i);
        }
    }

    public static void selectsort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i+1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp;
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}
