package designPattern.strategyPattern;

/**
 * Created by chenxiaoxue on 7/9/16.
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println(" I cannot fly");
    }
}
