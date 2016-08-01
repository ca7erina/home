package designPattern.state;

/**
 * Created by chenxiaoxue on 8/1/16.
 */
public class NoQuarterState implements State {
    GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You inserted a quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuater() {
        System.out.println("You haven't inserted a quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned, but there is not quarter");
    }

    @Override
    public void dispense() {
        System.out.println("You need to pay first");
    }
}
