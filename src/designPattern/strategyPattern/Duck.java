package designPattern.strategyPattern;

/**
 * Created by chenxiaoxue on 7/9/16.
 */
public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehvior;

    public Duck(){

    }

    public abstract void display();

    public void performQuack(){
        quackBehvior.quack();
    }
    public void setQuackBehvior(QuackBehavior qb){
        quackBehvior = qb;
    }


    public void performFly(){
        flyBehavior.fly();
    }

    public void setFlyBehavior(FlyBehavior fb){
        flyBehavior = fb;
    }

    public void swim(){
        System.out.println(" all ducks float, even decoys");

    }


}
