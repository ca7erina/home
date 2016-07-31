package designPattern.iterator;


import java.util.ArrayList;
import java.util.Iterator;

public class DinnerMenu implements Menu{
    static final int MAX_ITEMS =6;
    int numberOfItems =0;
    MenuItem[] menuItems;

    public DinnerMenu(){
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("Vegetarian BLT","bacon with lettuce and tomato on whole wheat",true,2.99);
        addItem("BLT","Bacon with Lettuce and tomato on while wheat",false,2.99);
        addItem("Hotdog","hot dog whith surkaut, relish, onions and cheese",false,3.05);

    }


    public void addItem(String name, String description, boolean vegetarian, double price){
        MenuItem menuItem = new MenuItem(name, description,vegetarian,price);

        if(numberOfItems>=MAX_ITEMS){
            System.out.println("Sorry, menu is full! Can't add item to menu");

        }else{
            menuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }

    }

    public Iterator createIterator(){
        return new DinnerMenuIterator(menuItems);
    }

    public MenuItem[] getMenuItems(){
        return menuItems;

    }

}
