package hackRank;

import java.util.Arrays;

/**
 * Write a Java method that takes in an array of ints and returns
 the array in the same order with all duplicates removed.
 */
public class RemoveDuplicateFromArray {

    public static void main(String args[]){
        int[] array1 = new int[]{7, 9, 10, 7, 6, 1, 1, 3, 8, 9};
        int[] array = new int[]{7, 1,7,9};
        array = removeDuplicate(array);
        System.out.println(Arrays.toString(array));
    }

    public static int[] removeDuplicate(int a[]){

       int rear = a.length-1;
        for(int i =0;i<=rear;i++){
            int num =a[i];
            for(int j=i+1;j<=rear;j++){
                if(num==a[j]){
                    int k=j;
                    while(k+1<a.length){
                        a[k]=a[k+1];
                        k++;
                    }
                    rear--;
                }
            }
        }

        int newA[] = new int[rear+1];
        for(int i=0;i<=rear;i++){
            newA[i] = a[i];
        }
        return newA;
    }


}
