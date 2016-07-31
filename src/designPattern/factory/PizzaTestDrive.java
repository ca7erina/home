package designPattern.factory;

/**
 * Created by chenxiaoxue on 7/12/16.
 */
public class PizzaTestDrive {
    public static void main(String args[]){
        PizzaStore nyStore = new NYStylePizzaStore();
        PizzaStore chicagoStore = new ChicagoStylePizzaStore();

        Pizza p1 = nyStore.orderPizza("cheese");
        System.out.println("-- Ethan ordered a "+ p1.getName());

        Pizza p2 = chicagoStore.orderPizza("cheese");
        System.out.println("-- Joel ordered a "+ p2.getName());

    }




}
