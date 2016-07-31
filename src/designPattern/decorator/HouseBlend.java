package designPattern.decorator;

/**
 * Created by chenxiaoxue on 7/11/16.
 */
public class HouseBlend extends Beverage{

    public HouseBlend(){
        description = "House Blend";
    }

    @Override
    public double cost() {
        return 0.80;
    }
}
