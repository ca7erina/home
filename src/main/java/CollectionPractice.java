package main.java;

import java.util.ArrayList;
import java.util.TreeSet;


public class CollectionPractice {

    public static void main(String[] args) {
        set();

    }

    public static void set() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Baby");
        list.add("Abby");
        list.add("Candybby");
        TreeSet<String> treeSet = new TreeSet<String>();
        treeSet.addAll(list);
        for(String s : treeSet) {
            System.out.println(s);
        }
    }
}
