package designPattern.decorator;

/**
 * Created by chenxiaoxue on 7/11/16.
 */
public class Espresso extends Beverage{

    public Espresso(){
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
