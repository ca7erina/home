//import java.io.*;
//import java.util.*;
//public class Solution {
//
//
//
//    public static void main(String args[] ){
//        Scanner myScanner = new Scanner(System.in);
//        int length = Integer.parseInt(myScanner.nextLine().trim());//1-25
//        String words[] = new String[length];
//        for(int i=0;i<length;i++){
//            words[i]=myScanner.nextLine().trim();
//        }
//        //sort words[]
//        mergeSort(words,0,words.length-1);
//        //print words[]
//        for(int i=0;i<length;i++){
//            System.out.println(words[i]);
//        }
//    }
//
//    public static void mergeSort(String words[], int left, int right){
//        if (left < right){
//            int mid = left+(right-left)/2; //Same as (l+r)/2, but avoids overflow for large l and h
//            mergeSort(words, left, mid);
//            mergeSort(words, mid + 1, right);
//            merge(words, left, mid, right);
//        }
//    }
//
//    public static void merge(String words[], int left, int mid, int right){
//        int leftSize = mid - left + 1;
//        int rightSize =  right - mid;
//
//        String L[]= new String[leftSize];
//        String R[]= new String[rightSize];
//        for(int i = 0; i < leftSize; i++)
//            L[i] = words[left + i];
//        for(int j = 0; j < rightSize; j++)
//            R[j] = words[mid + 1+ j];
//
//
//        int i = 0;
//        int j = 0;
//        int k = left;
//        while (i < leftSize && j < rightSize) {
//            if (compare(L[i],R[j])) {
//                words[k] = L[i];
//                i++;
//            } else {
//                words[k] = R[j];
//                j++;
//            }
//            k++;
//        }
//        while (i < leftSize) {
//            words[curr] = L[i];
//            i++;
//            k++;
//        }
//        while (j < rightSize) {
//            words[curr] = R[j];
//            j++;
//            k++;
//        }
//    }
////
//    public static boolean compare(String a,String b){//"nine > seven " "two"<"nine" return true
//        int len1 = a.length();
//        int len2 = b.length();
//        if(len1<len2){
//            int k = 0;
//            while (k < len1) {
//                char c1 = a.charAt(len1-1-k);
//                char c2 = b.charAt(len2-1-k);
//                if (c1 > c2) {
//                    return false;
//                }
//                if (c1 < c2) {
//                    return true;
//                }
//                k++;
//            }
//            return true;
//        }else{
//            int k = 0;
//            while (k < len2) {
//                char c1 = a.charAt(len1-1-k);
//                char c2 = b.charAt(len2-1-k);
//                if (c1 > c2) {
//                    return false;
//                }
//                if (c1 < c2) {
//                    return true;
//                }
//                k++;
//            }
//            return false;
//        }
//    }



//}