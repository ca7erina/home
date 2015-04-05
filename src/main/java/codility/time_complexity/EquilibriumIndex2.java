package main.java.codility.time_complexity;

/**
 * O(n)
 */
public class EquilibriumIndex2 {
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
        long sumLeft = 0l;
        long sumAll = 0l;
        for(int a : A) {
            sumAll += a;
        }
        for(int i = 0; i < A.length; i++) {
            if(i == 0) {
                sumLeft = 0;
            } else {
                sumLeft += A[i - 1];
            }
            if(sumLeft == sumAll - sumLeft - A[i]) {
                return i;
            }
        }
        return result;
    }


}
