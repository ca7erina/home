package designPattern.strategyPattern;

/**
 * Created by chenxiaoxue on 7/9/16.
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println(" quack quack!");
    }
}
