package designPattern.decorator;

/**
 * Created by chenxiaoxue on 7/11/16.
 */
public abstract class Beverage {

    String description = "Unknown Drink";

    public String getDescription(){
        return description;
    }

    public abstract double cost();

}

