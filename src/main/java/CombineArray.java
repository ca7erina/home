package main.java;

import java.util.Arrays;

/**
 * Created by chenxiaoxue on 3/21/15 .
 */
public class CombineArray {
    public static void main(String[] args) {
        int[] A = {9, 39, 3, 45, 223, 23, 35, 7};
        int[] B = {2, 4, 12, 222, 6, 14, 44, 42, 26, 678, 48, 22, 60, 88};
        Arrays.sort(A);
        Arrays.sort(B);
        for(int i : A) {
            System.out.print(i + " ");
        }
        System.out.println("--" + A.length);
        for(int i : B) {
            System.out.print(i + " ");
        }
        System.out.println("--" + B.length);
        int[] C = combine(A, B);
        for(int i : C) {
            System.out.print(i + " ");
        }
        System.out.println("--" + C.length);
    }

    public static int[] combine(int[] A, int[] B) {
        int[] c = new int[A.length + B.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < A.length && j < B.length) {
            if(A[i] < B[j]) {
                c[k] = A[i];
                i++;
                k++;
            } else {
                c[k] = B[j];
                j++;
                k++;
            }
        }
        while(i < A.length) {
            c[k] = A[i];
            i++;
            k++;
        }
        while(j < B.length) {
            c[k] = B[j];
            j++;
            k++;
        }
        return c;
    }

}
