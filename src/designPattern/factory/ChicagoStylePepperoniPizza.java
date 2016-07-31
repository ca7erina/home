package designPattern.factory;

/**
 * Created by chenxiaoxue on 7/12/16.
 */
public class ChicagoStylePepperoniPizza extends Pizza {
    public ChicagoStylePepperoniPizza(){
        name = "Chicago Style Pepperoni Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";

        toppins.add("Shredded Mozzarella Cheese");

    }

    public void cut(){
        System.out.println("Cutting the pizza into Square slices");
    }
}
