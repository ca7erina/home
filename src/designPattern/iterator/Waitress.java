package designPattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by chenxiaoxue on 7/31/16.
 */
public class Waitress {
    ArrayList menus;

    public Waitress(ArrayList menus){
        this.menus = menus;

    }

    public void printMenu(){
        Iterator menuIterator = menus.iterator();
        while(menuIterator.hasNext()){
            Menu menu =( Menu)menuIterator.next();
            printMenu(menu.createIterator());
        }
    }

    public void printMenu(Iterator iterator){
        while(iterator.hasNext()){
            MenuItem menuItem = (MenuItem) iterator.next();
            System.out.print(menuItem.getName()+" ");
            System.out.print(menuItem.getPrice() + "\n");
            System.out.println(menuItem.getDescription() + " \n");
        }

    }
}
