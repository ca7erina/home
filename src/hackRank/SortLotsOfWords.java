package hackRank;

import java.util.Scanner;

/**
 * using different comparison method to sort.
 *
 * Created by chenxiaoxue on 1/6/16.
 */
public class SortLotsOfWords {

    public static String[] myarray;
    public static String[] workSpace;

    public static void main(String[] args){

        Scanner myscanner = new Scanner(System.in);
        int num = Integer.parseInt(myscanner.nextLine());
        String[] array = new String[num];
        myarray = new String[num];
        workSpace = new String[num];
        for(int i=0;i<num;i++){
            myarray[i]=myscanner.nextLine();
        }

        mergeSort(0,num-1);

        for(int i=0;i<num;i++){
            System.out.println(myarray[i]);
        }
    }

    public static void mergeSort(int left, int right){
        int mid = (left + right) /2;  // computes midpoint
        if(left==right)		//base case
            return;
        mergeSort(left, mid);
        mergeSort(mid+1, right);
        for(int i=left; i<=right; i++){
            workSpace[i]=myarray[i]; //copies entire array into workspace
        }
        int i1=left;
        int i2=mid+1;
        for(int curr=left; curr<=right; curr++){ //merge workspace
            if(i1>mid){			      //copies all remnants in
                myarray[curr]=workSpace[i2++];
            }else if(i2>right){		      //copies all remnants in
                myarray[curr] = workSpace[i1++];
            }else if(compare(workSpace[i2],workSpace[i1])>0){
                myarray[curr]=workSpace[i1++];	//merge
            }else{
                myarray[curr]=workSpace[i2++];	//merge
            }
        }
    }

    //words has short length has higher priority
    public static int compare(String s1, String s2){
        if(s1.length()>s2.length()){
            return 1;
        }else if(s1.length()<s2.length()){
            return -1;
        }else{
            return s1.compareTo(s2);
        }

    }

    //words contain biggest character has higher priority
    //if the biggest character in both words are the same then compare alphabetically
//    public static int compare(String s1, String s2){
//        int big1=0;
//        int big2=0;
//        for(int i=0;i<s1.length();i++){
//            int num =(int)s1.charAt(i);
//            if(num>big1){
//                big1=num;
//            }
//        }
//
//        for(int i=0;i<s2.length();i++){
//            int num =(int)s2.charAt(i);
//            if(num>big2){
//                big2=num;
//            }
//        }
//
//        if(big1>big2){
//            return 1;
//        }else if(big1<big2){
//            return -1;
//        }else{
//            return s1.compareTo(s2);
//        }
//    }


    public static void bubblesort(){
        for (int c = 0; c < myarray.length-1; c++) {
            for (int d = 0; d < myarray.length-c-1; d++) {
                if (myarray[d].compareTo(myarray[d+1])<0){
                    String swap = myarray[d];
                    myarray[d]   = myarray[d+1];
                    myarray[d+1] = swap;
                }
            }
        }
    }
}
