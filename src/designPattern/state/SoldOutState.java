package designPattern.state;

/**
 * Created by chenxiaoxue on 8/1/16.
 */
public class SoldOutState implements State {
    GumballMachine gumballMachine;
    public SoldOutState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }
    @Override
    public void insertQuarter() {
        System.out.println("You cannot insert a quarter, the machine is sold out");
    }

    @Override
    public void ejectQuater() {
        System.out.println("You cannot eject, you have't inserted a quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned, but there is no gumballs");
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed");
    }
}
