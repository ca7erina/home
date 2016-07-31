package designPattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by chenxiaoxue on 7/31/16.
 */
public class MenuTestDrive {


    public static void main(String args[]){
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
      //  ArrayList breakfastItems = pancakeHouseMenu.getMenuItems();

        DinnerMenu dinnerMenu = new DinnerMenu();
    //    MenuItem[] dinnerItems = dinnerMenu.getMenuItems();


        // print items
//        for(int i =0; i<breakfastItems.size();i++){
//            MenuItem menuItem = (MenuItem)breakfastItems.get(i);
//            System.out.println(menuItem.getName()+" ");
//            System.out.println(menuItem.getPrice()+" ");
//            System.out.println(menuItem.getDescription()+" ");
//        }
//
//        for(int i =0; i<dinnerItems.length;i++){
//            MenuItem menuItem = dinnerItems[i];
//            System.out.println(menuItem.getName()+" ");
//            System.out.println(menuItem.getPrice()+" ");
//            System.out.println(menuItem.getDescription()+" ");
//        }

        ArrayList menus = new ArrayList();
        menus.add(pancakeHouseMenu);
        menus.add(dinnerMenu);
            //create iterator
        Waitress waitress = new Waitress(menus);
        waitress.printMenu();





    }
}
