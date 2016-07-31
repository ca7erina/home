package designPattern.factory;

/**
 * Created by chenxiaoxue on 7/12/16.
 */
public abstract class PizzaStore {
    SimplePizzaFactory factory;

//    public PizzaStore(SimplePizzaFactory factory){
//        this.factory = factory;
//    }
//
//    public PizzaStore(){
//
//    }


    public Pizza orderPizza( String type){
        Pizza pizza;
        pizza = this.createPizza(type); //factory.createPizza
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;

    }

    abstract Pizza createPizza(String type);

}
