package main.java.codility.counting_elements;

import java.util.Arrays;

/**
 * A non-empty zero-indexed array A consisting of N integers is given.
 *
 * A permutation is a sequence containing each element from 1 to N once, and only once.
 *
 * For example, array A such that:
 *
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * A[3] = 2
 * is a permutation, but array A such that:
 *
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * is not a permutation, because value 2 is missing.
 *
 * The goal is to check whether array A is a permutation.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given a zero-indexed array A, returns 1 if array A is a permutation and 0 if it is not.
 *
 * For example, given array A such that:
 *
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * A[3] = 2
 * the function should return 1.
 *
 * Given array A such that:
 *
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * the function should return 0.
 *
 * Assume that:
 *
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [1..1,000,000,000].
 * Complexity:
 *
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class PermCheck {
    /*
     * Detected time complexity:
     * O(N * log(N)) or O(N)
     *
     * required:
     * O(N)
     */

    public static void main(String args[]) {
        solution(new int[]{4,1,3,2});//1
    }

    /*
     * anti sum test:
     * sum could be the right number but also make sure there is no same number in the array,
     * after sort, number should be increasing by 1 in array.
     */
    public static int solution(int[] A) {
        int[] B;
        B = A;
        Arrays.sort(B);
        if(B.length > 0) {
            long sumActural = 0l;
            long sumExpected = 0l;
            for(int i = 0; i < B.length; i++) {
                sumActural += B[i];
                sumExpected += (i + 1);
                if(i > 0 && B[i] - B[i - 1] != 1) {
                    return 0;
                }
            }
            if(sumActural == sumExpected) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

}
