package main.java.codility.time_complexity;

/**
 * equilibrium index
 *
 * This is a demo task. You can read about this task and its solutions in this blog post.
 *
 * A zero-indexed array A consisting of N integers is given. An equilibrium index of this array is any integer P such that 0 ≤ P < N and the sum of elements of lower indices is equal to the sum of elements of higher indices, i.e.
 * A[0] + A[1] + ... + A[P−1] = A[P+1] + ... + A[N−2] + A[N−1].
 * Sum of zero elements is assumed to be equal to 0. This can happen if P = 0 or if P = N−1.
 *
 * For example, consider the following array A consisting of N = 8 elements:
 *
 * A[0] = -1
 * A[1] =  3
 * A[2] = -4
 * A[3] =  5
 * A[4] =  1
 * A[5] = -6
 * A[6] =  2
 * A[7] =  1
 * P = 1 is an equilibrium index of this array, because:
 *
 * A[0] = −1 = A[2] + A[3] + A[4] + A[5] + A[6] + A[7]
 * P = 3 is an equilibrium index of this array, because:
 *
 * A[0] + A[1] + A[2] = −2 = A[4] + A[5] + A[6] + A[7]
 * P = 7 is also an equilibrium index, because:
 *
 * A[0] + A[1] + A[2] + A[3] + A[4] + A[5] + A[6] = 0
 * and there are no elements with indices greater than 7.
 *
 * P = 8 is not an equilibrium index, because it does not fulfill the condition 0 ≤ P < N.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given a zero-indexed array A consisting of N integers, returns any of its equilibrium indices. The function should return −1 if no equilibrium index exists.
 *
 * For example, given array A shown above, the function may return 1, 3 or 7, as explained above.
 *
 * Assume that:
 *
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 * Complexity:
 *
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class EquilibriumIndex1 {
    public static void main(String args[]) {
        int[] A = {-1, 3, -4, 5, 1, -6, 2, 1};
        System.out.println(solution(A));//1
        int[] B = {0};
        System.out.println(solution(B));//0
        int[] C = {500, 1, -2, -1, 2};
        System.out.println(solution(C));//0
        int[] D = {-1, 0};
        System.out.println(solution(D));//0
        int[] E = {-1, -1, 1};
        System.out.println(solution(E));//0

    }

    public static int solution(int[] A) {
        // write your code in Java SE 8
        int result = -1;
        for(int i = 0; i < A.length; i++) {
            if(sum1(A, i) == sum2(A, i)) {
                return i;
            }
        }

        return result;
    }

    public static long sum1(int[] A, int in) {
        long sum1 = 0l;
        for(int i = 0; i < in; i++) {
            sum1 = sum1 + A[i];
        }
        return sum1;
    }

    public static long sum2(int[] A, int in) {
        long sum2 = 0l;
        for(int i = in + 1; i < A.length; i++) {
            sum2 = sum2 + A[i];
        }
        return sum2;
    }


}
