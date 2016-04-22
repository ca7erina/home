package hackRank;
/**
 * how many rolls to get a six;
 *
 * before getting snapchat, you could roll many times dice, if you rolled many times, get a avg number, if you didn't get any six before get a snapechat u get 0 six;
 * and then you do this test "base" times.
 *
 *
 */
public class RollDice {
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
        base =1000000;// how many times you do this test
        sum=0; // for calculate avg.
        int temp = TimesOfrollAndGetSanpChat();
        for(int i=0;i<base;i++) {
            int got6Times = 0;
            while(true) {
            	got6Times++;
                int onetime = roll() ;
                if(onetime == 6) { //got 6
//                	System.out.println("getsix:"+got6Times);
//                	System.out.println("TimesOfrollAndGetSanpChat:"+temp);
                    break;
                } 
            }
            temp = TimesOfrollAndGetSanpChat();
//            System.out.println("snapchat "+temp);
//            System.out.println("got6Times "+got6Times);
            if(got6Times<temp||got6Times>12){
       		 	i--;
            	continue;
            }
            //System.out.println(got6Times);
            sum+=got6Times; //record every time get 6 took how many roll;
        }
        long result = Math.round((double) sum / base);
        System.out.println("got Snapchat and got six, average how many rolls to get six: " + (result == 0 ? 0 :result)); //avg


       //get snapchat and sneeze and get 6
        base =1000000; //how many times you do this test
        sum=0; // for calculate avg.
         temp = TimesOfrollAndGetSanpChatAndGetSneez();
         
         for(int i=0;i<base;i++) {
             int got6Times = 0;
             while(true) {
             	got6Times++;
                 int onetime = roll() ;
                 if(onetime == 6) { //got 6
                 //	System.out.println("getsix:"+got6Times);
                 //	System.out.println("TimesOfrollAndGetSanpChatAndSneeze:"+temp);
                     break;
                 } 
             }
             temp = TimesOfrollAndGetSanpChatAndGetSneez();
             if(got6Times<temp||got6Times>18){
        		 	i--;
             	continue;
             }
             sum+=got6Times; //record every time get 6 took how many roll;
         }
        result = Math.round((double) sum / base);
        System.out.println("sneezed and got Snapchat and got six ,calculate average how many rolls: " + (result == 0 ? 0 :result)); //avg
    }




    public static int roll(){
        return (int)(Math.random()*6)+1; //1-6
    }
    
    public static int TimesOfrollAndGetSanpChat(){
        return 6+ (int)(Math.random()*6)+1; //6 + random(1,6)
    }
    
    public static int TimesOfrollAndGetSanpChatAndGetSneez(){
    	  return 6+ (int)(Math.random()*6)+1+(int)(Math.random()*6)+1;//6 + random(1,6) + random(1,6)
    }

    public static long timeGettingSnapchat(){
        return (long)(Math.random()*2); //this time can roll6 times;
    }

    public static long timeGettingSneeze(){
        return (long)(Math.random()*2); //< half mins
    }


}
