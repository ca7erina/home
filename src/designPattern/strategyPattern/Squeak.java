package designPattern.strategyPattern;

/**
 * Created by chenxiaoxue on 7/9/16.
 */
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("squeak squeak!!");
    }
}
