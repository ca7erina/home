package main.java.codility.c_prefix_sums;

/**
 * Write a function:
 * <p/>
 * int solution(int A, int B, int K);
 * <p/>
 * that, given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K, i.e.:
 * <p/>
 * { i : A ≤ i ≤ B, i mod K = 0 }
 * <p/>
 * For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.
 * <p/>
 * Assume that:
 * <p/>
 * A and B are integers within the range [0..2,000,000,000];
 * K is an integer within the range [1..2,000,000,000];
 * A ≤ B.
 * Complexity:
 * <p/>
 * expected worst-case time complexity is O(1);
 * expected worst-case space complexity is O(1).
 */
public class CountDiv {
    public static void main(String args[]) {
        solution(6, 11, 2);//3
    }

    // O(1)
    public static int solution(int A, int B, int K) {
        int result = 0;
        if(A % K == 0) {
            result = ((B - A) / K) + 1;
        } else {
            result = (B - (A - A % K)) / K;
        }
        return result;
    }
}
