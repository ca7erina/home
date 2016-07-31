package designPattern.factory;

/**
 * Created by chenxiaoxue on 7/12/16.
 */
public class ChicagoStylePizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;

        if(type.equals("cheese")){
            pizza = new ChicagoStyleCheesePizza();
        }else if (type.equals("pepperoni")){
            pizza = new ChicagoStylePepperoniPizza();
        }else if (type.equals("clam")) {
            pizza = new ChicagoStyleClamPizza();
        }

        return pizza;
    }
}
