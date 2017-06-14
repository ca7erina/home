package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class DistinctArray {

    public static void main(String[] args) {
        int[] A = {2, 4, 2, 222, 6, 14, 44, 4, 26, 6, 4, 22, 6, 6};
        Arrays.sort(A);

        for(int i : A) {
            System.out.print(i + " ");
        }
        System.out.println("--" + A.length);

        int[] C = distinct2(A);
        for(int i : C) {
            System.out.print(i + " ");
        }
        System.out.println("--" + C.length);

        int[] D = distinct1(A);
        for(int i : D) {
            System.out.print(i + " ");
        }
        System.out.println("--" + D.length);
    }


    // turn it to treeset(sorted) to distinct the duplicates;
    public static int[] distinct1(int[] A) {
        TreeSet<Integer> tree = new TreeSet<Integer>();
        ArrayList<Integer> a = new ArrayList<Integer>();
        for(int i : A) {
            a.add(i);
        }
        tree.addAll(a);
        // treeset to array
        int[] c = new int[tree.size()];
        int i = 0;
        for(Object o : tree.toArray()) {
            c[i] = Integer.parseInt(o.toString());
            i++;
        }
        return c;
    }


    // create list to get the distinct elements;
    public static int[] distinct2(int[] A) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i : A) {
            if(!list.contains(i)) {
                list.add(i);
            }
        }
        int[] c = new int[list.size()];
        int i = 0;
        for(Object o : list) {
            c[i] = Integer.parseInt(o.toString());
            i++;
        }
        return c;
    }

}
