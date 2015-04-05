package main.java.codility.counting_elements;

import java.util.*;

/**
 * Write a function:
 *
 * int solution(int A[], int N);
 *
 * that, given a non-empty zero-indexed array A of N integers, returns the minimal positive integer that does not occur in A.
 *
 * For example, given:
 *
 * A[0] = 1
 * A[1] = 3
 * A[2] = 6
 * A[3] = 4
 * A[4] = 1
 * A[5] = 2
 * the function should return 5.
 *
 * Assume that:
 *
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 * Complexity:
 *
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class MissingInteger {
    public static void main(String args[]) {
            solution(new int[]{1,3,6,4,1,2});//5
    }

    public static int solution(int[] A) {  //O(N)
        int[] B;
        B = A;
        Arrays.sort(B);
        if(B[0] > 1) { //all positive number and all of them > 1
            return 1;
        } else if(B[B.length - 1] <= 0) { //all negative numbers
            return 1;
        }
        for(int i = 1; i < B.length; i++) { //have positive and negtive
            if(B[i] <= 0) { // only care the one start >0;
                continue;
            }
            //noinspection StatementWithEmptyBody
            if(B[i] == B[i - 1] || B[i] - B[i - 1] == 1) {

            } else {
                if(B[i] > 0 && B[i] != 1 && B[i - 1] <= 0) {
                    return 1;
                } else if(B[i] > 1 && B[i - 1] >= 0) {
                    return B[i - 1] + 1;
                }
            }
        }
        return B[B.length - 1] + 1;
    }
}
