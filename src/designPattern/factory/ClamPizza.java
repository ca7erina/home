package designPattern.factory;

/**
 * Created by chenxiaoxue on 7/12/16.
 */
public class ClamPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("prepare clam");
    }

    @Override
    public void bake() {
        System.out.println("bake clam");
    }

    @Override
    public void cut() {
        System.out.println("cut clam");
    }

    @Override
    public void box() {
        System.out.println("package clam");
    }
}
