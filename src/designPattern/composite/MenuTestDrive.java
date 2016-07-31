package designPattern.composite;


import java.util.ArrayList;

/**
 * Created by chenxiaoxue on 7/31/16.
 */
public class MenuTestDrive {


    public static void main(String args[]){
        MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU","Breakfast");
        MenuComponent dinnerMenu = new Menu("DINNER MENU","Dinner");

        MenuComponent allMenus = new Menu("ALL MENUS"," all menus combined");
        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinnerMenu);

       dinnerMenu.add(new MenuItem("Pasta","Spaghetti with Marinara Sauce",true,1.59));

        Waitress waitress = new Waitress(allMenus);
        waitress.printMenu();


    }
}
