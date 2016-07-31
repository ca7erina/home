package designPattern.strategyPattern;

/**
 * Created by chenxiaoxue on 7/9/16.
 */
public class MallardDuck extends Duck {

    public MallardDuck(){
        quackBehvior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    public void display(){
        System.out.println(" I am a real Mallard duck");
    }

}
