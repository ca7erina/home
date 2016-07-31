package designPattern.strategyPattern;

/**
 * Created by chenxiaoxue on 7/9/16.
 */
public class ModelDuck extends Duck{

    public ModelDuck(){
        flyBehavior = new FlyNoWay();
        quackBehvior = new Quack();

    }
    public void display(){
        System.out.println("I am a model duck");
    }

}
