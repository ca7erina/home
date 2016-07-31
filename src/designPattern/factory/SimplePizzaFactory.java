package designPattern.factory;

/**
 * Created by chenxiaoxue on 7/12/16.
 */
public class SimplePizzaFactory {

    public Pizza createPizza(String type){
        Pizza pizza = null;

        if(type.equals("cheese")){
            pizza = new CheesePizza();
        }else if (type.equals("pepperoni")){
            pizza = new PepperoniPizza();
        }else if (type.equals("clam")) {
            pizza = new ClamPizza();
        }

        return pizza;

    }

}
