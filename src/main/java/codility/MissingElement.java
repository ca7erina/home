package main.java.codility;


/**
 * A zero-indexed array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
 * <p/>
 * Your goal is to find that missing element.
 * <p/>
 * Write a function:
 * <p/>
 * int solution(int A[], int N);
 * <p/>
 * that, given a zero-indexed array A, returns the value of the missing element.
 * <p/>
 * For example, given array A such that:
 * <p/>
 * A[0] = 2
 * A[1] = 3
 * A[2] = 1
 * A[3] = 5
 * the function should return 4, as it is the missing element.
 * <p/>
 * Assume that:
 * <p/>
 * N is an integer within the range [0..100,000];
 * the elements of A are all distinct;
 * each element of array A is an integer within the range [1..(N + 1)].
 * Complexity:
 * <p/>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class MissingElement {

    public static void main(String[] args) {
        int[] A = {2, 3, 1, 5};
        int[] B = {};
        int[] C = {1};
        int[] D = {2, 3, 4, 5, 6, 1};
        System.out.println(solution(A));
        System.out.println(solution(B));
        System.out.println(solution(C));
        System.out.println(solution(D));
    }

    public static int solution(int[] A) { //100
        long sumAll = 0l;
        for(int i = 1; i < A.length + 2; i++) {
            sumAll += i;
        }
        long sum = 0l;
        for(int a : A) {
            sum += a;
        }
        return (int) (sumAll - sum);
    }
}
