package main.java;

import java.util.Arrays;

/**
 * how to sort string in java
 */
public class StringArraySort {
    public static void main(String args[]){
        String str="19827364517";
        System.out.println(str);
        System.out.println(arrayJavaSort(str));
//        Collections.sort();
    }

    public static String arrayJavaSort(String str){
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        str = new String(charArray);
        return str;
    }

}
