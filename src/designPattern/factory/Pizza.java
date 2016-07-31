package designPattern.factory;

import java.util.ArrayList;

/**
 * Created by chenxiaoxue on 7/12/16.
 */
public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    ArrayList toppins = new ArrayList();


    void prepare(){

        System.out.println("Preparing "+name);
        for(int i=0;i<toppins.size();i++){
            System.out.println("  "+ toppins.get(i));
        }


    }
    public  void bake(){
        System.out.println("Bake for 25mins at 350 Fan oven ");
    }
    public  void cut(){
        System.out.println("Cutting the pizza into slices ");
    }
    public  void box(){
        System.out.println("Place pizza in official PizzaStore box ");
    }

    public String getName(){
        return name;
    }



}
