package hackRank;

import java.io.*;
import java.util.*;

/**
 * sort words inverted
 * input:
 * 5
 * sdwe
 * fwefed
 * sdfewfed
 * aaa
 * a
 *
 * output:
 * a
 * aaa
 * fwefed
 * sdfewfed
 * sdwe
 *
 */
public class SortWordsBackWards {


    public static void main(String args[]) {
        Scanner myScanner = new Scanner(System.in);
        int length = Integer.parseInt(myScanner.nextLine().trim());//1-25
        String words[] = new String[length];
        for(int i = 0; i < length; i++) {
            words[i] = myScanner.nextLine().trim();
        }
        //sort words[]
        recursiveSort(words, 0, words.length - 1);//too slow
        //print words[]
        for(int i = 0; i < length; i++) {
            System.out.println(words[i]);
        }

    }

    public static void recursiveSort(String words[], int left, int right) {
        int mid = (left + right) / 2;
        if(right ==left) {
            return;
        }
        recursiveSort(words, left, mid);
        recursiveSort(words, mid + 1, right);
        merge(words, left,mid,right);
    }

    public static void merge(String words[], int left,int mid, int right) {
        int leftsize = mid-left+1;
        int rightsize = right-mid;

        String L[] = new String[leftsize];
        String R[] = new String[rightsize];
        for(int i=0;i<leftsize;i++){
            L[i]=words[left+i];
        }
        for(int i =0;i<rightsize;i++){
            R[i] = words[mid+1+i];
        }

        int i=0;
        int j=0;
        int curr=left;
        while(i<leftsize&&j<rightsize){
            if(compare(L[i],R[j])){
                words[curr] = L[i++];
            }else{
                words[curr] = R[j++];
            }
            curr++;
        }

        while(i<leftsize){
            words[curr]=L[i];
            i++;
            curr++;
        }

        while(j<rightsize){
            words[curr]=R[j];
            j++;
            curr++;
        }

    }




    public static boolean compare(String a, String b) {//"nine > seven " "two"<"nine" return true
        int len1 = a.length();
        int len2 = b.length();
        if(len1 < len2) {
            int k = 0;
            while(k < len1) {
                char c1 = a.charAt(len1 - 1 - k);
                char c2 = b.charAt(len2 - 1 - k);
                if(c1 > c2) {
                    return false;
                }
                if(c1 < c2) {
                    return true;
                }
                k++;
            }
            return true;
        } else {
            int k = 0;
            while(k < len2) {
                char c1 = a.charAt(len1 - 1 - k);
                char c2 = b.charAt(len2 - 1 - k);
                if(c1 > c2) {
                    return false;
                }
                if(c1 < c2) {
                    return true;
                }
                k++;
            }
            return false;
        }
    }




}
