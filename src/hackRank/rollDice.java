package hackRank;

/**
 * how many rolls to get a six;
 *
 * before getting snapchat, you could roll many times dice, if you rolled many times, get a avg number, if you didn't get any six before get a snapechat u get 0 six;
 * and then you do this test "base" times.
 *
 *
 */
public class rollDice {
    public static void main(String args[]){
        int base =1000000;
        int sum=0;

        //test base times of rolling, get a six, and get avg of the number;
        for(int i=0;i<base;i++) {
            int got6Times = 0;
            while(true) {
                got6Times++;
                if(roll() == 6) { //got 6
                    break;
                }

            }
            sum+=got6Times; //record every time get 6 took how many roll;
        }
        System.out.println("roll dice "+base+" times, average how many rolls to get six is: " + Math.round((double)sum/base)); //avg

        //get snapchat and get 6
        base =1000;// how many times you do this test
        sum=0; // for calculate avg.
        for(int i=0;i<base;i++) {
            double test = processGetSnapChatAndRollDice();
//            System.out.println(i+" "+test);
            sum+=processGetSnapChatAndRollDice(); //record every time get 6 took how many roll;
        }
        long result = Math.round((double) sum / base);
        System.out.println("got Snapchat and got six, average how many rolls to get six: " + (result == 0 ? 0 :result)); //avg




       //get snapchat and sneeze and get 6
        base =1000; //how many times you do this test
        sum=0; // for calculate avg.
        for(int i=0;i<base;i++) {
            sum+=processGetSnapChatAndRollDiceAndSneezed();
        }
        result = Math.round((double) sum / base);
        System.out.println("sneezed and got Snapchat and got six ,calculate average how many rolls: " + (result == 0 ? 0 :result)); //avg



    }
    public static double processGetSnapChatAndRollDiceAndSneezed(){
        long start = System.currentTimeMillis();
        long getSneeze = timeGettingSneeze(); // get random sneeze time;
        long getSnapChat = timeGettingSnapchat(); //get random snapchat time;
        long wait = getSneeze>getSnapChat? getSneeze:getSnapChat ;
        long end = start + wait;
        int n=0;
        int sum=0;
        while (System.currentTimeMillis() < end) {

            int sumOfRollTImes=0;
            while(true) {
                sumOfRollTImes++;
                int get =roll();
                if( get == 6) { //got 6
                    break;
                }

            }
            sum+=sumOfRollTImes; //record every time get 6 took how many roll;
            n++;
        }

        if(n==0) return 0; //didn't get a six but  snapchat came ;
      //  System.out.println((double)sum/n );
        return Math.round((double)sum/n);
    }

    public static double processGetSnapChatAndRollDice(){
        //get snapchat and get 6
        long start_time = System.currentTimeMillis();
        long wait_time = timeGettingSnapchat();
        long end_time = start_time + wait_time;

        int sum=0; //for calculate avg
        int n=0;
        while (System.currentTimeMillis() < end_time) { // during the time before get snap chat.
            int sumOfRollTImes=0;
            while(true) {
                sumOfRollTImes++;

                int get =roll();
                if( get == 6) { //got 6

                    break;
                }
            }
            sum+=sumOfRollTImes; //record every time get 6 took how many roll;
            n++;
        }

        if(n==0) return 0; //didn't get a six got  snapchat   and sneezed;
      //  System.out.println((double)sum/n );
        return (double)sum/n;

    }

    public static int roll(){
        return (int)(Math.random()*6)+1;
    }

    public static long timeGettingSnapchat(){
        return (long)(Math.random()*(1000*0.0059)); //< half mins
    }

    public static long timeGettingSneeze(){
        return (long)(Math.random()*(1000*0.0059)); //< half mins
    }



}
