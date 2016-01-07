package algorithm;

import java.util.Arrays;

/**
 * from ppt
 * put work space as the member variable is quicker;
 * Created by chenxiaoxue on 12/7/15.
 */
public class MergeSort_standard {

    public static void main(String[] args) {
    int a[] = {1,10,9,2,3,88,34,44,66,5};
        recursiveMerge(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }


    public static void recursiveMerge(int[] array, int left, int right) {
        int mid = (left + right) / 2;

        if(left == right) {
            return;
        }
        recursiveMerge(array, left, mid);
        recursiveMerge(array, mid + 1, right);
        merge(array, left, right);

        return;

    }

    public static void merge(int[] array, int left, int right) {
        int workSpace[] = new int[array.length];
        int mid = (left + right) / 2; // computes midpoint
        for(int i = left; i <= right; i++) {
            workSpace[i] = array[i]; //copies entire array into workspace
        }
        int i1 = left;
        int i2 = mid + 1;
        for(int curr = left; curr <= right; curr++) { //merge workspace
            if(i1 > mid) { //copies all remnants in
                array[curr] = workSpace[i2++];
                continue;
            }

            if(i2 > right) { //copies all remnants in
                array[curr] = workSpace[i1++];
                continue;
            }

            if(workSpace[i1] > workSpace[i2]) {
                array[curr] = workSpace[i1++]; //merge
            } else {
                array[curr] = workSpace[i2++]; //merge
            }
        }

    }

}
