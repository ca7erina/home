package test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by chenxiaoxue on 5/10/15.
 */
public class Temp {

//    public static void main(String args[] ) throws Exception {
//        Scanner scan = new Scanner(System.in);
//        String s=scan.nextLine();
//        s =s.replaceAll("[!,?.\\_'@]+", " ");
//        s =s.replaceAll("[ ]+", " ");
//        s= s.trim();
//        if(s==null||s.equals("")||s.isEmpty()){
//            System.out.println(0);
//            return;
//        }
//        String words[] = s.split(" ");
//        System.out.println(words.length);
//        for(String w:words){
//            System.out.println(w.trim());
//        }
//
//    }

    public static void main(String args[] ) throws Exception {
        Scanner myScanner = new Scanner(System.in);
        int length = myScanner.nextInt();
        String words[] = new String[length];
        myScanner.nextLine();
        for(int i=0; i<words.length;i++){
            words[i] = myScanner.nextLine().trim();
        }

        //sort
        String smallest = "";
        for(int i =0; i<words.length;i++){

            for(int j=i+1;j<words.length;j++){

                if(compare(words[j],words[i])==-1){
                    swap(words,j,i);
                    System.out.println(i+" "+j+" "+Arrays.toString(words));
                }
            }

        }


        for(int i =0; i<words.length;i++){
            System.out.print(words[i]+" ");
        }

    }
    public static int compare(String a, String b){
        System.out.println("a :"+a);
        System.out.println("b :"+b);
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
