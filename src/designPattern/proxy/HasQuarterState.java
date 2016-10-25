package designPattern.proxy;


/**
 * Created by chenxiaoxue on 8/1/16.
 */
public class HasQuarterState implements State {
    transient GumballMachine gumballMachine;
    public HasQuarterState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }
    @Override
    public void insertQuarter() {
        System.out.println("you cannot insert another quarter");
    }

    @Override
    public void ejectQuater() {

        System.out.println("Quarter returned");
        gumballMachine.setState(gumballMachine.getState());
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned...");
        gumballMachine.setState(gumballMachine.getState());
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed");
    }
    public String toString(){
        return "HasQuarterState";
    }
}
