package test;

import algorithm.linkedList.LinkedList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
//        System.out.println(2%3); //=0...2
//        System.out.println(1%7);//=0...1
//        System.out.println(7%1);//=7..0

     A a = new B();
       a.call();
        B b = new B();
       b.call();

    }

    public static int method(int number){
        if (number == 3){
            return 3;
        }
        return method((number % 4) + 1) + 2;
    }
}
