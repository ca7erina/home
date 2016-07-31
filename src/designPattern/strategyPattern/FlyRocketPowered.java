package designPattern.strategyPattern;

/**
 * Created by chenxiaoxue on 7/9/16.
 */
public class FlyRocketPowered implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket!");
    }
}
