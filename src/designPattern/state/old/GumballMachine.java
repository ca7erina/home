package designPattern.state.old;

public class GumballMachine {
    final static int SOLD_OUT = 0;
    final static int NO_QUARTER = 1;
    final static int HAS_QUARTER = 2;
    final static int SOLD =3;

    int state = SOLD_OUT;
    int count =0;

    public GumballMachine(int count){
        this.count = count;
        if(count >0){
            state = NO_QUARTER;
        }
    }

    public void insertQuarter(){
        if(state == HAS_QUARTER){
            System.out.println("you cannot insert another quarter");
        } else if (state == NO_QUARTER){
            state = HAS_QUARTER;
            System.out.println("You inserted a quarter");
        } else if (state == SOLD_OUT){
            System.out.println("You cannot insert a quarter, the machine is sold out");
        } else if (state == SOLD){
            System.out.println("Please wait, we're already giving you a gumball");
        }
    }

    public void ejectQuarter(){
        if(state == HAS_QUARTER){
            state = NO_QUARTER;
            System.out.println("Quarter returned");
        } else if (state == NO_QUARTER){
            System.out.println("You haven't inserted a quarter");
        } else if (state == SOLD){
            System.out.println("Sorry, you already turned the crank");
        } else if (state == SOLD_OUT){
            System.out.println("You cannot eject, you have't inserted a quarter");
        }
    }

    public void turnCrank(){
        if(state == SOLD){
            System.out.println("Turning twice doesn't get you another gumball");
        } else if (state == NO_QUARTER){
            System.out.println("You turned, but there is not quarter");
        } else if (state == SOLD_OUT){
            System.out.println("You turned, but there is no gumballs");
        } else if (state == HAS_QUARTER){
            System.out.println("You turned...");
            state = SOLD;
            dispense();
        }
    }


    public void dispense(){
        if(state == SOLD){
            System.out.println("A gumball comes rolling out the slot");
            count--;
            if(count==0){
                System.out.println("opps, out of gumballs");
                state = SOLD_OUT;
            }else{
                state = NO_QUARTER;
            }
        } else if (state == NO_QUARTER){
            System.out.println("You need to pay first");
        } else if (state == SOLD_OUT){
            System.out.println("No gumball dispensed");
        } else if (state == HAS_QUARTER){
            System.out.println("No gumball dispensed");
        }
    }

    public String toString(){
        return "Machine state:" + state;
    }

}
