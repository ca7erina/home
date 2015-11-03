package hackRank;

import java.util.Scanner;

/**
 * input number: how many words
 *
 * input:
 * 3
 * banana
 * orange
 * apple
 *
 * output:
 * 3
 * apple banana orange
 *
 */

public class SortWords {
    public static void main(String args[] ) throws Exception {
        Scanner myScanner = new Scanner(System.in);
        int length = myScanner.nextInt();
        String words[] = new String[length];
        myScanner.nextLine();
        for(int i=0; i<words.length;i++){
            words[i] = myScanner.nextLine().trim();
        }

        //sort
        for(int i =0; i<words.length;i++){
            for(int j=i+1;j<words.length;j++){
                if(compare(words[j],words[i])==-1){
                    swap(words,j,i);
                }
            }

        }


        for(int i =0; i<words.length;i++){
            System.out.print(words[i]+" ");
        }

    }
    public static int compare(String a, String b){
        int result=0;
        a = a.toLowerCase();
        b = b.toLowerCase();
        if(a.length()<b.length()){
            for(int i =0; i<a.length();i++){
                if(a.charAt(i)==b.charAt(i)){
                    continue; //a==b
                }
                if(a.charAt(i)>b.charAt(i)){
                    return 1; //a>b
                }else{
                    return -1;//a<b
                }
            }
            return -1;//a<b
        }else if(a.length()==b.length()){
            for(int i =0; i<b.length();i++){
                if(a.charAt(i)==b.charAt(i)){
                    continue; //a==b
                }
                if(a.charAt(i)>b.charAt(i)){
                    return 1; //a>b
                }else{
                    return -1;//a<b
                }
            }
            return 0;
        }else if(a.length()>b.length()){
            for(int i =0; i<b.length();i++){
                if(a.charAt(i)==b.charAt(i)){
                    continue; //a==b
                }
                if(a.charAt(i)>b.charAt(i)){
                    return 1; //a>b
                }else{
                    return -1;//a<b
                }
            }
            return 1;//a>b;
        }

        return result;
    }

    public static void swap(String str[], int a, int b){
        String temp ="";
        temp = str[a];
        str[a]=str[b];
        str[b] = temp;
    }
}