package algorithm;

import java.util.Arrays;

/**
 * Created by chenxiaoxue on 10/19/15.
 */
public class binarySearch {

    public static void main(String[] args){
        int length = (int)(Math.random()*100);
        int a[] = new int[length];
        for(int i=0;i<length;i++){
            a[i] = (int)(Math.random()*100);
        }


        Arrays.sort(a);

        System.out.println(Arrays.toString(a));
        System.out.println( "index:"+find(a,28));
    }


    public static int find(int[] myArray, int searchKey){
        int lowerBound = 0;
        int upperBound = myArray.length-1;
        int check;
        while(true){
            check = (lowerBound + upperBound ) / 2;
            if(myArray[check]==searchKey){
                return check; // found it
            }else if(lowerBound > upperBound){
                return -1; // can't find it
            }else{ // divide range
                if(myArray[check] < searchKey){
                    lowerBound = check + 1; // it's in upper half
                }else{
                    upperBound = check - 1; // it's in lower half
                }
            }
        }
    }




}
