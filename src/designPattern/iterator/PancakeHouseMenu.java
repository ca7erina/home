package designPattern.iterator;


import java.util.ArrayList;
import java.util.Iterator;

public class PancakeHouseMenu implements Menu{
    ArrayList menuItems;

    public PancakeHouseMenu(){
        menuItems = new ArrayList();
        addItem("Monster Pancake Breakfast","eggs and toast",true,2.99);
        addItem("Regular Pancake Breakfast","eggs and sausage",false,2.99);
        addItem("Blueberry Pancake Breakfast","blueberry",true,3.49);

    }


    public void addItem(String name, String description, boolean vegetarian, double price){
        MenuItem menuItem = new MenuItem(name, description,vegetarian,price);
        menuItems.add(menuItem);
    }

    public ArrayList getMenuItems(){
        return menuItems;
    }


    @Override
    public Iterator createIterator() {
        return menuItems.iterator();
    }
}
