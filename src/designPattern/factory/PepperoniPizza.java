package designPattern.factory;

/**
 * Created by chenxiaoxue on 7/12/16.
 */
public class PepperoniPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("prepare pepperoni");
    }

    @Override
    public void bake() {
        System.out.println("bake pepperoni");
    }

    @Override
    public void cut() {
        System.out.println("cut pepperoni");
    }

    @Override
    public void box() {
        System.out.println("package pepperoni");
    }
}
