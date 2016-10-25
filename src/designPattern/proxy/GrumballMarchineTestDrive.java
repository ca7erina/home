package designPattern.proxy;

import java.rmi.Naming;

/**
 * Created by chenxiaoxue on 8/1/16.
 */
public class GrumballMarchineTestDrive {


    public static void main(String args[]){
        GumballMachine gumballMachine =null;


        int count ;
        if(args.length<2){
            System.out.println("Gumball Machine <name> <inventory>");
            System.exit(1);
        }
        try {
            count = Integer.parseInt(args[1]);
            gumballMachine = new GumballMachine(args[0], count);
            Naming.bind("//"+args[0]+"/gumballMachine",gumballMachine);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        GumballMonitor monitor = new GumballMonitor(gumballMachine);

        monitor.report();


    }
}
