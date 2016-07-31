package designPattern.factory;

/**
 * Created by chenxiaoxue on 7/12/16.
 */
public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("prepare cheese");
    }

    @Override
    public void bake() {
        System.out.println("bake cheese");
    }

    @Override
    public void cut() {
        System.out.println("cut cheese");
    }

    @Override
    public void box() {
        System.out.println("package cheese");
    }
}
