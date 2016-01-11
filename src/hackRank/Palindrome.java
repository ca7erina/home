package hackRank;

import algorithm.Stack;

import java.util.Scanner;

/**
 * Created by chenxiaoxue on 11/9/15.
 */
public class Palindrome {
    public static void main(String args[] )  {
        Scanner myScanner = new Scanner(System.in);
        char words[] = myScanner.nextLine().replaceAll("\\s+","").toCharArray();
        Stack p = new Stack(words.length) ;
        for(int i=0;i<words.length;i++){
            p.push(words[i]);
        }
        int i=0;
        while(!p.isEmpty()){
            if( p.pop()!=words[i]){
                System.out.println("FALSE");
                return;
            }
            i++;
        }
        System.out.println("TRUE");
    }



//    public static void main(String[] args) {
//        Scanner myScanner = new Scanner(System.in);
//        char words[] = myScanner.nextLine().replaceAll("\\s+","").toCharArray();
//        Stack<Character> p = new Stack<Character>() ;
//
//        int counter =0;
//        int wordsLength = words.length;
//        int half = wordsLength/2-1;
//
//        for(int i =0;i<=half;i++){
//            p.push(words[i]);
//        }
//
//        if(wordsLength%2==1){
//            counter = half+2;
//        }else{
//            counter = half+1;
//        }
//
//        while(counter<words.length){
//            if(p.pop()!=words[counter]){
//                System.out.println("FALSE");
//                return;
//            }
//
//            counter++;
//        }
//        System.out.println("TRUE");
//    }

}
