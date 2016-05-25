package test;

/**
 * Created by chenxiaoxue on 5/19/16.
 */
public class A {
    public void call(){
        System.out.print("A");
    }
    public void ownB(){
        System.out.print("A's");
    }
}

 class B extends A{
    public void call() {
        System.out.print("B");
    }
     public void ownB(){
         System.out.print("B's");
     }
}

class C extends A{
    public void call() {
        System.out.print("C");
    }
}