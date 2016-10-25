package designPattern.proxy;

public class GumballMonitor {
    GumballMachineRemote machine;
    public GumballMonitor(GumballMachineRemote machine){
        this.machine = machine;
    }

    public void report(){
        try {
            System.out.println("Gumball Machine:" + machine.getLocation());
            System.out.println("Current iventory: " + machine.getCount() + " gumballs");
            System.out.println("Current state:" + machine.getState());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
