package algorithm.recursion;

import java.util.Arrays;

/**
 *
 * from harcker rank given code same as ppt;
 * Created by chenxiaoxue on 12/2/15.
 */
public class MergeSort_Quick {
    static int array[]={2,5,2,6,3,8,9,3};
    static int workSpace[];

    public static void main(String[] args) {
        workSpace = new int[array.length];
        mergeSort(0,array.length-1);
        System.out.println(Arrays.toString(array));
    }


    public static void mergeSort(int left, int right){
        int mid = (left + right) /2; // computes midpoint
        if(left==right) //base case
            return;
        mergeSort(left, mid);
        mergeSort(mid+1, right);
        for(int i=left; i<=right; i++)
            workSpace[i]=array[i]; //copies entire array into workspace
        int i1=left;
        int i2=mid+1;
        for(int curr=left; curr<=right; curr++){ //merge workspace
            if(i1>mid){ //copies all remnants in
                array[curr]=workSpace[i2++];
            }else if(i2>right){ //copies all remnants in
                array[curr] = workSpace[i1++];
            }else if(workSpace[i1]>workSpace[i2]){
                array[curr]=workSpace[i1++]; //merge
            }else{
                array[curr]=workSpace[i2++]; //merge
            }
        }
    }








}
